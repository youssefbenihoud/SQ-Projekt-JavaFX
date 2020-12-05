/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Nutzer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import service.NutzerFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author youss
 */
public class UserController implements Initializable {

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signupBtn;

    @FXML
    private Label errorLog;

    @FXML
    NutzerFacade nutzerFacade = new NutzerFacade();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void signupUser(ActionEvent event) {
        Nutzer n = getParam();
        int res = nutzerFacade.toSignUp(n.getId(), n.getPassword(), 2);
        if (res < 0) {
            errorLog.setText("Username already exists");
            labelFill(-1);
        } else {
            errorLog.setText("Success");
            labelFill(1);
        }
    }

    @FXML
    public void userLogin(ActionEvent event) throws IOException {
        Nutzer n = getParam();
        int res = nutzerFacade.toLogIn(n.getId(), n.getPassword());
        if (res == 1) {
            Session.setAttribut(nutzerFacade.find(n.getId()), "connectedUser");
            ViewLuncher.forWard(event, nutzerFacade.forwardByType(n.getId()), this.getClass());
        } else if (res < 0) {
            errorLog.setText("Username/Password is false");
            labelFill(-1);
        }
    }

    private Nutzer getParam() {
        Nutzer n = new Nutzer();
        n.setId(usernameTF.getText());
        n.setPassword(passwordPF.getText());
        n.setType(2);
        return n;
    }

    private void labelFill(int color) { // 1 Green 2 Red
        errorLog.setTextFill(color == 1 ? Color.GREEN : Color.RED);
    }

    public TextField getUsernameTF() {
        return usernameTF;
    }

    public void setUsernameTF(TextField usernameTF) {
        this.usernameTF = usernameTF;
    }

    public PasswordField getPasswordPF() {
        return passwordPF;
    }

    public void setPasswordPF(PasswordField passwordPF) {
        this.passwordPF = passwordPF;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }

    public void setLoginBtn(Button loginBtn) {
        this.loginBtn = loginBtn;
    }

    public Button getSignupBtn() {
        return signupBtn;
    }

    public void setSignupBtn(Button signupBtn) {
        this.signupBtn = signupBtn;
    }

    public Label getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(Label errorLog) {
        this.errorLog = errorLog;
    }

}
