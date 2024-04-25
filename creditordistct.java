/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insafproject;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author USER
 */
@Entity
@Table(name = "creditor_distct")
public class creditordistct implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "Creditorname")
    private String Creditorname;

    @Column(name = "Distctname")
    private String Distctname;

    public creditordistct() {
    }

    public String getCreditorname() {
        return Creditorname;
    }

    public void setCreditorname(String Creditorname) {
        this.Creditorname = Creditorname;
    }

    public String getDistctname() {
        return Distctname;
    }

    public void setDistctname(String Distctname) {
        this.Distctname = Distctname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
}