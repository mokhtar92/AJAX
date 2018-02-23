/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author Ahmed_Mokhtar
 */
public class Message {
    
    private int id;
    private String sender;
    private String message;
    
    
    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }
    
    public Message(int id, String sender, String message) {
        this.id = id;
        this.sender = sender;
        this.message = message;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
