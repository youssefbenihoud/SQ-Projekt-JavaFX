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
public class EventFacade extends AbstractFacade<Event>  {
    
    public EventFacade() {
        super(Event.class);
    }
    
   TicketFacade ticketFacade;
    
    
   /**
    * to Create an Event
    * @param name
    * @param myDate
    * @param totalTickets
    * @return 
    */
    public int create(String name,Date myDate, int totalTickets){
        // Event creation , Attributes Setting
        Event event = new Event();
        event.setName(name);
        event.setDate(DateUtil.convert(myDate));
        
        
        if(verifyNumberInput(String.valueOf(totalTickets)) == 1){
            ticketFacade = new TicketFacade();
            event.setTotalTickets(totalTickets);
            create(event);
            ticketFacade.createTicketbyEvent(event.getId());
            return 1; // the typed totalTickets is Digital
        }else{
            return -1; // the typed totalTickets is not Digital
        }
        
    }
    
    
    
    
}
