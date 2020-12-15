/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Event;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import util.DateUtil;

/**
 *
 * @author Gruppe 3
 */
public class EventFacade extends AbstractFacade<Event> {

    public EventFacade() {
        super(Event.class);
    }

    TicketFacade ticketFacade;

    /**
     * to Create an Event
     *
     * @param name
     * @param myDate
     * @param totalTickets
     * @param isPeriodical
     */
    public void addEvent(String name, Date myDate, int totalTickets, boolean isPeriodical) {
        ticketFacade = new TicketFacade();
        // Event creation , Attributes Setting
        Event event = new Event(name,DateUtil.convert(myDate), isPeriodical, totalTickets);
        create(event); // Event is now in DataBase
        ticketFacade.createTicketbyEvent(event.getId()); // Tickets will be created 
    }
    
    
    public void updateEvent(Date myDate){
       //TODO to Add Same Event for the Next 5 years
    }
    
    private double stringToPercent(String myString){
        return Double.parseDouble(myString) / 100;
    }
    
    public List<Event> findByDate(Date myDate){
        return getEntityManager().createQuery("SELECT e FROM Event e WHERE e.date = '"+DateUtil.convert(myDate)+"'").getResultList();
    }
    
    public List<Event> findByDateAndPeriodical(Date myDate, boolean isPeriodical){
        return getEntityManager().createQuery("SELECT e FROM Event e WHERE e.date = '"+DateUtil.convert(myDate)+"' AND e.isPeriodical ='"+isPeriodical+"'").getResultList();
    }

}
