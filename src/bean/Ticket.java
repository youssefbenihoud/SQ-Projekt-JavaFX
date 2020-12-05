/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author youss
 */
@Entity
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int gekauft; // 1 yes -1 no
    @ManyToOne
    private Event event = new Event();
    @ManyToOne
    private Nutzer nutzer;

    public Ticket() {
    }

    public Ticket(int gekauft) {
        this.gekauft = gekauft;
    }
    
    
    

    public int getGekauft() {
        return gekauft;
    }

    public void setGekauft(int gekauft) {
        this.gekauft = gekauft;
    }
    

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Nutzer getNutzer() {
        if(nutzer == null )
            nutzer = new Nutzer();
        return nutzer;
    }

    public void setNutzer(Nutzer nutzer) {
        this.nutzer = nutzer;
    }
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Ticket[ id=" + id + " ]";
    }
    
}
