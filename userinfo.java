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
 * @author Manar
 */
@Entity
@Table(name = "userinfo")
public class userinfo implements Serializable {
    @Id
    @Column(name = "nationalId")
    private int nationalId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userPassword")
    private String userPassword;
//
//    @Column(name = "userStatus")
//    private String userStatus;

    @Column(name = "birthdate")
    private String birthdate;



    public userinfo() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
//
//    public String getUserStatus() {
//        return userStatus;
//    }
//
//    public void setUserStatus(String userStatus) {
//        this.userStatus = userStatus;
//    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}