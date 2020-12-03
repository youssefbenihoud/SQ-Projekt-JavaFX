/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.Event;
import bean.Ticket;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author youss
 */
public class TicketFxHelper extends AbstractFxHelper<Ticket>{
    
    private static AbstractFxHelperItem[] titres;
    
    static {
        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("ID", "id"),
            new AbstractFxHelperItem("Gekauft", "gekauft"),
            new AbstractFxHelperItem("Event", "event")};
    }

    public TicketFxHelper(TableView<Ticket> table, List<Ticket> list) {
        super(titres, table, list);
    }
    
    public TicketFxHelper(TableView<Ticket> table) {
        super(titres, table);
    }

    
    
    
}
