/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
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
    private Spinner total;

    @FXML
    private Button createButton;

    @FXML
    private Spinner greenSP;
    
     @FXML
    private Spinner orangeSP;

    @FXML
    private Spinner redSP;


    @FXML
    public void createEvent(ActionEvent event) { // to create an Event with CreateButton
        int res = eventFacade.create(name.getText(), Date.valueOf(datePicker.getValue()), new Integer(getValueOfSp(total)), 
                getValueOfSp(greenSP), getValueOfSp(orangeSP), getValueOfSp(redSP)); // create an Event
        if (res < 0) {
            setLabelandButton("Input Number Only", true, Color.RED);
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //createButton.setDisable(true);

        //Configure Spinner
        setSpinnerValueFactory(greenSP, 10, 30, 10, 1);
        setSpinnerValueFactory(orangeSP, 5, 10, 5, 1);
        setSpinnerValueFactory(redSP, 1, 5, 1, 1);
        setSpinnerValueFactory(total, 10, 1000, 10, 1);
        
    }
    
    private void setSpinnerValueFactory(Spinner colorSp, int min, int max, int iniVal, int incrVal){
        SpinnerValueFactory<Integer> colorSVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, iniVal,incrVal);
        colorSp.setValueFactory(colorSVF);
    }
    
    private String getValueOfSp(Spinner sp){
        return sp.getValue().toString();
    }

}
