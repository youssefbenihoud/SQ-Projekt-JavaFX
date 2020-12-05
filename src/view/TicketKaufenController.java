/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Event;
import bean.Nutzer;
import helper.EventFxHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import service.EventFacade;
import service.TicketFacade;
import sun.util.logging.resources.logging;
import util.Session;

/**
 * FXML Controller class
 *
 * @author youss
 */
public class TicketKaufenController implements Initializable {

    @FXML
    private TableView<Event> eventTableView = new TableView();

    @FXML
    EventFacade eventFacade = new EventFacade(); // Calling the Service, where the methods called

    @FXML
    TicketFacade ticketFacade = new TicketFacade();

    @FXML
    private TextField ticketNumberTextField;

    @FXML
    private Button buyTicketBtn;

    @FXML
    private PieChart pieChart = new PieChart();

    private EventFxHelper eventFxHelper = new EventFxHelper(eventTableView, eventFacade.findAll());

    @FXML
    private Label avTicketLabel; // The Label that shows the Available Tickets ( Sorry for naming it errorLog )

    @FXML
    public void selectEvent(MouseEvent event) { // Selecting an Event
        Event e = eventFxHelper.getSelected();
        fillTextLabel();
        if (e != null) {
            ticketNumberTextField.setEditable(true);
        }
        editLabelByBoughtTickets(e.getId());
        showChart(e.getId());
    }

    @FXML
    void buyTicket(ActionEvent event) { // When Clicking the Buy Button
        // verify how many tickets if greater than the available tickets
        // verify if the typed number is less than 0
        Event e = eventFxHelper.getSelected();
        Nutzer nutzer = (Nutzer) Session.getAttribut("connectedUser");
        ticketFacade.buyTicket(e.getId(),nutzer.getId(), new Integer(ticketNumberTextField.getText()));
        fillTextLabel();
        editLabelByBoughtTickets(e.getId());
        showChart(e.getId());
        //eventFxHelper.setList(eventFacade.findAll());

    }

    @FXML
    void verifyNumber(KeyEvent event) { // Verifying the wrong Typed Numbers of wished Tickets
        String inputText = event.getCharacter();
        if (eventFacade.verifyNumberInput(inputText) != 1 || eventFacade.verifyNumberInput(ticketNumberTextField.getText()) != 1) {
            ticketNumberTextField.setText("");
            buyTicketBtn.setDisable(true);
        } else {
            buyTicketBtn.setDisable(false);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHelper();
        buyTicketBtn.setDisable(true);
    }

    /**
     * Set the TableView of our Events
     */
    private void initHelper() {
        eventFxHelper = new EventFxHelper(eventTableView, eventFacade.findAll());
        showChart(0L);
    }

    /**
     * HighLight the Label with the correspondent Color
     */
    private void fillTextLabel() {
        Event e = eventFxHelper.getSelected();
        int chosenColor = ticketFacade.colorOfAvailability(e.getId());
        switch (chosenColor) {
            case 1:
                avTicketLabel.setTextFill(Color.GREEN);
                break;
            case 2:
                avTicketLabel.setTextFill(Color.ORANGE);
                break;
            case 3:
                avTicketLabel.setTextFill(Color.RED);
                break;
            case 4:
                avTicketLabel.setTextFill(Color.BLACK);
                break;
            default:
                break;
        }
    }

    /**
     * Edit the Label, where the available Tickets are shown
     *
     * @param eventID
     */
    private void editLabelByBoughtTickets(Long eventID) {
        Event e = eventFacade.find(eventID);
        int ticketRest = (ticketFacade.findByGekauft(e.getId(), -1)).size();
        avTicketLabel.setText(ticketRest + "");
    }

    public void showChart(Long eventID) {
        Event e = eventFacade.find(eventID);
        pieChart.getData().clear();
        if (e != null) {

            double gekaufteTickets = (double) (ticketFacade.findByGekauft(e.getId(), 1)).size() / (double) e.getTotalTickets();
            PieChart.Data sliceG = new PieChart.Data("Gekauft", gekaufteTickets);
            PieChart.Data sliceNG = new PieChart.Data("Nicht Gekauft", 1 - gekaufteTickets);

            pieChart.getData().add(sliceG);
            pieChart.getData().add(sliceNG);
        } else {
            PieChart.Data slice = new PieChart.Data("Keine Infos", 1);
            pieChart.getData().add(slice);
        }
    }

    public TableView<Event> getEventTableView() {
        return eventTableView;
    }

    public void setEventTableView(TableView<Event> eventTableView) {
        this.eventTableView = eventTableView;
    }

    public EventFxHelper getEventFxHelper() {
        return eventFxHelper;
    }

    public void setEventFxHelper(EventFxHelper eventFxHelper) {
        this.eventFxHelper = eventFxHelper;
    }

    public Label getAvTicketLabel() {
        return avTicketLabel;
    }

    public void setAvTicketLabel(Label avTicketLabel) {
        this.avTicketLabel = avTicketLabel;
    }

    public TextField getTicketNumberTextField() {
        return ticketNumberTextField;
    }

    public void setTicketNumberTextField(TextField ticketNumberTextField) {
        this.ticketNumberTextField = ticketNumberTextField;
    }

    public Button getBuyTicketBtn() {
        return buyTicketBtn;
    }

    public void setBuyTicketBtn(Button buyTicketBtn) {
        this.buyTicketBtn = buyTicketBtn;
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    public void setPieChart(PieChart pieChart) {
        this.pieChart = pieChart;
    }

}
