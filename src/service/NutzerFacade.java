/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Nutzer;

/**
 *
 * @author youss
 */
public class NutzerFacade extends AbstractFacade<Nutzer> {

    public NutzerFacade() {
        super(Nutzer.class);
    }

    
    /**
     * Check the username and password wrote by the user
     * @param login
     * @param password
     * @return 
     */
    public int toLogIn(String login, String password) {
        Nutzer nutzer = find(login);
        if (nutzer == null) {
            return -1; // No User Found
        } else {
            if (!password.equals(nutzer.getPassword())) {
                return -2; // Wrong Password
            } else {
                return 1;// Success
            }
        }
    }
    
    
    /**
     * Sign Up a new User
     * @param login
     * @param password
     * @param myType
     * @return 
     */
    public int toSignUp(String login, String password, int myType){
        Nutzer nutzer = find(login);
        //Nutzer nutzer = findByID(login);
        if(nutzer != null ){ // if the username is already in the database
            return -1; // failed
        }else{ // check if the User is already in the database
            nutzer = new Nutzer(login, password, myType);
            create(nutzer);
            return 1; // success
        }
    }

    /**
     * check the type of the user
     * forward him to the right page according 
     * to his type
     * @param login
     * @return 
     */
    public String forwardByType(String login){
        Nutzer nutzer = find(login);
        if(nutzer != null){
            if(nutzer.getType() == 1 )
                return "EventView.fxml";
            else if (nutzer.getType() == 2 )
                return "TicketKaufenView.fxml";
        }
        return "";
    }
    
    
    
}
