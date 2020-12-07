/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author youss
 */
@Entity
public class User implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String password;
    private int type; // 1 admin 2 normal user
    @OneToMany(mappedBy = "User")
    private List<Ticket> tickets;

    public User() {
    }

    public User(String id, String password, int type) {
        this.id = id;
        this.password = password;
        this.type = type;
    }
    
    
    

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Ticket> getTickets() {
        if(tickets == null )
            tickets = new ArrayList();
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

  
   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    
    
}
