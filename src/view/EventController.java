/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.EventFacade;

/**
 * FXML Controller class
 *
 * @author Gruppe 3
 */
public class EventController implements Initializable {

    @FXML
    EventFacade eventFacade = new EventFacade();

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField name;

    @FXML
    private Label errorLog;

    @FXML
    private Spinner total;

    @FXML
    private CheckBox isPeriodical;

    @FXML
    public void addEvent(ActionEvent event) { // to create an Event with CreateButton
        eventFacade.addEvent(name.getText(), Date.valueOf(datePicker.getValue()), new Integer(getValueOfSp(total)), isPeriodical.isSelected()); // create an Event
        showDialog("You have created "+name.getText()+" with "+getValueOfSp(total)+" Tickets, it will find place on "
                + datePicker.getValue());
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        ViewLuncher.forWard(event, "UserView.fxml", EventController.class);
    }

    /**
     * Set the given Spinner with the given Parameters
     *
     * @param colorSp
     * @param min
     * @param max
     * @param iniVal
     * @param incrVal
     */
    private void setSpinnerValueFactory(Spinner colorSp, int min, int max, int iniVal, int incrVal) {
        SpinnerValueFactory<Integer> colorSVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, iniVal, incrVal);
        colorSp.setValueFactory(colorSVF);
    }

    private String getValueOfSp(Spinner sp) { // Parse the Value of Spinner to String
        return sp.getValue().toString();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //createButton.setDisable(true);

        //Configure Spinner
        setSpinnerValueFactory(total, 1, 1000, 75, 1);

    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public Spinner getTotal() {
        return total;
    }

    public void setTotal(Spinner total) {
        this.total = total;
    }

    public Label getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(Label errorLog) {
        this.errorLog = errorLog;
    }

    public CheckBox getIsPeriodical() {
        return isPeriodical;
    }

    public void setIsPeriodical(CheckBox isPeriodical) {
        this.isPeriodical = isPeriodical;
    }

    private void showDialog(String msg) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button okBtn = new Button("OK");

        VBox vbox = new VBox(new Text(msg), okBtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        
        okBtn.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        dialogStage.hide();
      }
    });

        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
        
       
    }

}
