/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.AvailableTicket;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import service.EventFacade;
import service.TicketFacade;
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
    private Spinner ticketNumberTextField;

    @FXML
    private Button buyTicketBtn;

    @FXML
    private PieChart pieChart = new PieChart();

    private EventFxHelper eventFxHelper = new EventFxHelper(eventTableView, eventFacade.findAll()); // Helper that helps us fill the TableView with the wished List<Event>

    @FXML
    private Label avTicketLabel; // The Label that shows the Available Tickets ( Sorry for naming it errorLog )

    @FXML
    public void selectEvent(MouseEvent event) { // Selecting an Event
        Event e = eventFxHelper.getSelected();
        fillTextLabel(); // Change the Color of the Available Tickets Number
        if (e != null) { // Testing if selected Event is not Null
            setSpinnerValueFactory(ticketNumberTextField, 1,
                    ticketFacade.findByGekauft(e.getId(), -1).size(), 0, 1); // Setting the Maximal Numbers of Available Tickets, User can order.
            editLabelByBoughtTickets(e.getId()); // Update the Label of Available Tickets after every Selection
            showChart(e.getId()); // Update the Chart after Every Bought
        }

    }

    @FXML
    void buyTicket(ActionEvent event) { // When Clicking the Buy Button
        Event e = eventFxHelper.getSelected(); // Get the Selected Event chosen from the Table by the User
        Nutzer nutzer = (Nutzer) Session.getAttribut("connectedUser"); // Get the Connected User after Connection
        ticketFacade.buyTicket(e.getId(), nutzer.getId(), getValueOfSp(ticketNumberTextField)); // Call of the Buy Methode from TicketService

        fillTextLabel(); // Change the Color of the Available Tickets Number ( Whether Green, Orange or Red )
        editLabelByBoughtTickets(e.getId()); // Update the Label of Available Tickets after every Bought
        showChart(e.getId()); // Update the Chart of Available Tickets after every Bought

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHelper();
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

    /**
     * Update Chart after every Event Selection
     *
     * @param eventID
     */
    public void showChart(Long eventID) {
        Event e = eventFacade.find(eventID);
        pieChart.getData().clear();
        if (e != null) {

            double gekaufteTickets = (double) (ticketFacade.findByGekauft(e.getId(), 1)).size() / (double) e.getTotalTickets();
            PieChart.Data sliceG = new PieChart.Data("Bought", gekaufteTickets);
            PieChart.Data sliceNG = new PieChart.Data("Available", 1 - gekaufteTickets);

            pieChart.getData().add(sliceG);
            pieChart.getData().add(sliceNG);
        } else {
            PieChart.Data slice = new PieChart.Data("No Infos", 1);
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

    public Spinner getTicketNumberTextField() {
        return ticketNumberTextField;
    }

    public void setTicketNumberTextField(Spinner ticketNumberTextField) {
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

    private String getValueOfSp(Spinner sp) {
        return sp.getValue().toString();
    }

    private void setSpinnerValueFactory(Spinner colorSp, int min, int max, int iniVal, int incrVal) {
        SpinnerValueFactory<Integer> colorSVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, iniVal, incrVal);
        colorSp.setValueFactory(colorSVF);
    }

}
