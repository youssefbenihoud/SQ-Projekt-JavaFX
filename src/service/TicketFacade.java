/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Event;
import bean.Ticket;
import java.util.List;

/**
 *
 * @author youss
 */
public class TicketFacade extends AbstractFacade<Ticket>{
    
    public TicketFacade() {
        super(Ticket.class);
    }
    
    EventFacade eventFacade;
    
    
    
    /**
     * Tickets to create, the Amount of tickets depends on Event 
     * and How Many Tickets to create for this Event
     * 
     * @param eventID
     * @return 
     */
    public int createTicketbyEvent(Long eventID){
        eventFacade = new EventFacade();
        Event event = eventFacade.find(eventID);
        if(event == null ){
            return -1; // No Event
        }else{
            for (int i = 0; i < event.getTotalTickets() ; i++) { // How Many Ticket to create, depending on how many totalTickets for the Event
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setGekauft(-1); // default : nicht gekauft 
            create(ticket);
            }
          return 1; // All Tickets are added with the event
        }
    }
    
    
    
    /**
     * Color to change depending on how many tickets are Available
     * @param eventID
     * @return 1 for green, 2 for orange, 3 for red
     */
    public int colorOfAvailability(Long eventID){
        eventFacade = new EventFacade();
        Event ev = eventFacade.find(eventID);
        
        int availableTickets = findByGekauft(eventID, -1).size();
        Double percentAvailable = (double) availableTickets / (double) ev.getTotalTickets();
        if(percentAvailable >= 0.1 ){
            return 1; // green
        }else if ( percentAvailable >= 0.05){
            return 2; // orange
        }else if (percentAvailable > 0.0 ){
            return 3; // red
        }else{
            return 4;
        }
    }
    
    
    /**
     * a Method to buy a Ticket, we change the status of ticket if bought
     * @param eventID
     * @param numberOfTickets
     * @return -1 if Event is Null, -2 if wrong type, -3 if the Amount of wished Tickets are greater the the Available Tickets 
     * 
     */
    public int buyTicket(Long eventID, int numberOfTickets){
        eventFacade = new EventFacade();
        Event ev = eventFacade.find(eventID);
        int ticketsCount = findByGekauft(eventID, -1).size();
        if(ev == null){
            return -1; // event is null
        }else { // if the found Event is not Null
            if(numberOfTickets < 0 || verifyNumberInput(String.valueOf(numberOfTickets)) < 0 ){
                return -2; // numberOfTickets less than 0 or the Available or is not Digital
            }else if (numberOfTickets > ticketsCount ){
                return -3; // numberOfTickets are greater than the Available Tickets
            }else {
                List<Ticket> availableTickets = findByGekauft(eventID, -1);
                for(int i = 0 ; i < numberOfTickets ; i++){
                    // TODO: methode to connect the sold tickets to the user
                    Ticket t = availableTickets.get(i); // we get the Ticket
                    t.setGekauft(1); // Change the Status
                    edit(t); // Edit in the Database
                }
                
                //ticketsCount = findByGekauft(eventID, -1).size();
                //ev.setTotalTickets(ticketsCount);
                eventFacade.edit(ev); // Edit Event on the Database ( because it's linked with the changed Tickets )
            }
        }
        return 1; // Success
    }
    
    
    
    
    /**
     * 
     * @param eventID
     * @param gekauft
     * @return List of Tickets by Event and the Status of Ticket if bought or not
     */
    public List<Ticket> findByGekauft(Long eventID, int gekauft){
        return getEntityManager().createQuery("SELECT t FROM Ticket t WHERE t.event.id = "+eventID+" AND t.gekauft = "+ gekauft + "").getResultList();
    }
}
