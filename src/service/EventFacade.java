/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Event;
import java.sql.Timestamp;
import java.util.Date;
import util.DateUtil;

/**
 *
 * @author youss
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
     * @param green
     * @param orange
     * @param red
     * @return
     */
    public void create(String name, Date myDate, int totalTickets, String green, String orange, String red) {
        ticketFacade = new TicketFacade();
        // Event creation , Attributes Setting
        Event event = new Event(name,DateUtil.convert(myDate),
                totalTickets,stringToPercent(green),
                stringToPercent(orange),stringToPercent(red));
        create(event); // Event is now in DataBase
        ticketFacade.createTicketbyEvent(event.getId()); // Tickets will be created 
    }
    
    private double stringToPercent(String myString){
        return Double.parseDouble(myString) / 100;
    }

}
