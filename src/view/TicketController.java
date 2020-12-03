/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Ticket;
import helper.TicketFxHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import service.TicketFacade;

/**
 * FXML Controller class
 *
 * @author youss
 */
public class TicketController implements Initializable {
    
    
    @FXML
    private TableView<Ticket> ticketTableView = new TableView();
    
    @FXML
    TicketFacade ticketFacade = new TicketFacade();
    
    private TicketFxHelper ticketFxHelper;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHelper();
    }

    private void initHelper(){
        ticketFxHelper = new TicketFxHelper(ticketTableView, ticketFacade.findAll());
    }

    public TableView<Ticket> getTicketTableView() {
        return ticketTableView;
    }

    public void setTicketTableView(TableView<Ticket> ticketTableView) {
        this.ticketTableView = ticketTableView;
    }
    
    
    
    
}
