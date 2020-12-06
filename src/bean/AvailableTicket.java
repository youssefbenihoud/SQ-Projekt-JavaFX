/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author youss
 */
public enum AvailableTicket {
    Green(0.10), Orange(0.05), Red(0.0);
   
    
    private double vl;
    
    
    public double getVl(){
        return vl;
    }
    
     AvailableTicket(double vl){
        this.vl = vl;
    }
}
