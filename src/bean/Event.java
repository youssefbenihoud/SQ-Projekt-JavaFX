/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author youss
 */
@Entity
public class Event implements Serializable {

   

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date date;
    private boolean isPeriodical;
    private int totalTickets;
    
    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    public Event() {
    }

    public Event(Date date, int totalTickets) {
        this.date = date;
        this.totalTickets = totalTickets;
    }

    public Event(Long id,Date date, int totalTickets) {
        this.id = id;
        this.date = date;
        this.totalTickets = totalTickets;
    }

    public Event(String name, Date date, boolean isPeriodical, int totalTickets) {
        this.name = name;
        this.date = date;
        this.isPeriodical = isPeriodical;
        this.totalTickets = totalTickets;
    }
    
    
    

    public Event(String name, Date date, int totalTickets) {
        this.name = name;
        this.date = date;
        this.totalTickets = totalTickets;
    }
    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

  
    

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    
    
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        if(totalTickets < 0)
            totalTickets = Math.abs(totalTickets);
        this.totalTickets = totalTickets;
    }

    public boolean isIsPeriodical() {
        return isPeriodical;
    }

    public void setIsPeriodical(boolean isPeriodical) {
        this.isPeriodical = isPeriodical;
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Event[ id=" + id + " ]";
    }
    
}
