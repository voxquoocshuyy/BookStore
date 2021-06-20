/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.tblUsers;

/**
 *
 * @author Quoc Huy
 */
public class UsersDTO {
    String userID;
    String password;
    String name;
    String status;
    String role;

    public UsersDTO() {
    }

    public UsersDTO(String userID, String password, String name, String status, String role) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.status = status;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
