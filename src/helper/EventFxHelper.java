/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.Event;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author youss
 */
public class EventFxHelper extends AbstractFxHelper<Event>{
    
    private static AbstractFxHelperItem[] titres;
    
    static {
        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Event Name", "name"),
            new AbstractFxHelperItem("Date", "date"),
            new AbstractFxHelperItem("Total Tickets", "totalTickets")};
    }

    public EventFxHelper(TableView<Event> table, List<Event> list) {
        super(titres, table, list);
    }
    
    public EventFxHelper(TableView<Event> table) {
        super(titres, table);
    }

    
    
    
}
