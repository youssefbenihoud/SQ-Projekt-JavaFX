/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.AvailableTicket;
import bean.Event;
import bean.User;
import bean.Ticket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author youss
 */
public class TicketFacade extends AbstractFacade<Ticket> {

    public TicketFacade() {
        super(Ticket.class);
    }

    EventFacade eventFacade;
    UserFacade userFacade;

    /**
     * Tickets to create, the Amount of tickets depends on Event and How Many
     * Tickets to create for this Event
     *
     * @param eventID
     * @return
     */
    public int createTicketbyEvent(Long eventID) {
        eventFacade = new EventFacade();
        Event event = eventFacade.find(eventID);
        if (event == null) {
            return -1; // No Event
        } else {
            for (int i = 0; i < event.getTotalTickets(); i++) { // How Many Ticket to create, depending on how many totalTickets for the Event
                Ticket ticket = new Ticket();
                ticket.setEvent(event);
                ticket.setBought(-1); // default : nicht gekauft 
                create(ticket);
            }
            return 1; // All Tickets are added with the event
        }
    }

    /**
     * Color to change depending on how many tickets are Available
     *
     * @param eventID
     * @return 1 for green, 2 for orange, 3 for red
     */
    public int colorOfAvailability(Long eventID) {
        eventFacade = new EventFacade();
        Event ev = eventFacade.find(eventID);
        try {
            int availableTickets = findByBought(eventID, -1).size();
            Double percentAvailable = (double) availableTickets / (double) ev.getTotalTickets();
            return whichColor(percentAvailable);
        }catch(Exception ex){
            System.out.println("Exception colorOfAvailability :"+ex);
        }

        return -1;
    }

    public int whichColor(double percentAvailable) {
        AvailableTicket at = null;

        if (percentAvailable <= 0) {
            percentAvailable = 0.0;
        }

        if (percentAvailable > at.Green.getVl()) {
            return 1; // green
        } else if (percentAvailable > at.Orange.getVl()) {
            return 2; // orange
        } else if (percentAvailable > at.Red.getVl()) {
            return 3; // red
        }
        return 4; // default Color
    }

    /**
     * a Method to buy a Ticket, we change the status of ticket if bought
     *
     * @param eventID
     * @param userID
     * @param numberOfTicket
     * @return -1 if Event is Null, -2 if wrong type, -3 if the Amount of wished
     * Tickets are greater the the Available Tickets
     *
     */
    public int buyTicket(Long eventID, String userID, String numberOfTicket) {
        eventFacade = new EventFacade();
        int numberOfTickets = Integer.parseInt(numberOfTicket);
        Event ev = eventFacade.find(eventID);
        int ticketsCount = findByBought(eventID, -1).size();
        try { // if the found Event is not Null
            if (numberOfTickets < 0) {
                return -2; // numberOfTickets less than 0 or the Available or is not Digital
            } else if (numberOfTickets > ticketsCount) {
                return -3; // numberOfTickets are greater than the Available Tickets
            } else {
                List<Ticket> availableTickets = findByBought(eventID, -1); // List of not bought Tickets
                userFacade = new UserFacade(); // Calling the Service of NuUser               
                for (int i = 0; i < numberOfTickets; i++) {
                    Ticket t = availableTickets.get(i); // we get the Ticket
                    // TODO: methode to connect the sold tickets to the user
                    User u = userFacade.find(userID); // Find the User
                    t.setUser(u); // Set the Ticket bought from the connected User to the User
                    t.setBought(1); // Change the Status
                    edit(t); // Edit in the Database
                }

                eventFacade.edit(ev); // Edit Event on the Database ( because it's linked with the changed Tickets )
                return 1; // Success
            }
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
        return -1;
    }

    /**
     *
     * @param eventID
     * @param bought
     * @return List of Tickets by Event and the Status of Ticket if bought or
     * not
     */
    public List<Ticket> findByBought(Long eventID, int bought) {
        List<Ticket> resultTicket = getEntityManager().createQuery("SELECT t FROM Ticket t WHERE t.event.id = " + eventID + " AND t.bought = " + bought + "").getResultList();
        if (resultTicket == null) {
            return new ArrayList<>();
        }

        return resultTicket;
    }
}
