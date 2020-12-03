/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import service.EventFacade;

/**
 * FXML Controller class
 *
 * @author youss
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
    private TextField total;

    @FXML
    private Button createButton;

    @FXML
    public void createEvent(ActionEvent event) { // to create an Event with CreateButton
        int res = eventFacade.create(name.getText(), Date.valueOf(datePicker.getValue()), new Integer(total.getText())); // create an Event
        if (res < 0) {
            errorLog.setText("Input Number Only");
            errorLog.setTextFill(Color.RED);
            createButton.setDisable(true);
        }
    }

    @FXML
    public void verifyNumber(KeyEvent event) { // to verify the typed Number in TextField

        String inputText = event.getCharacter();
        if (eventFacade.verifyNumberInput(inputText) == 1 || eventFacade.verifyNumberInput(total.getText()) == 1) {
            setLabelandButton("Good", false, Color.GREEN);
        } else {
            setLabelandButton("Input Digits Only!", true, Color.RED);
        }
    }

    private void setLabelandButton(String logError, Boolean btnDisable, Color myColor) {
        errorLog.setText(logError);
        errorLog.setTextFill(myColor);
        createButton.setDisable(btnDisable);
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

    public TextField getTotal() {
        return total;
    }

    public void setTotal(TextField total) {
        this.total = total;
    }

    public Label getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(Label errorLog) {
        this.errorLog = errorLog;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        createButton.setDisable(true);

    }

}
