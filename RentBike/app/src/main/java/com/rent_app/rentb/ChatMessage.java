package com.rent_app.rentb;

/**
 * Created by ccrodrig on 2/22/2016.
 */
public class ChatMessage {
    private String author;
    private String message;

    public ChatMessage(){

    }
    public ChatMessage(String author, String message){
        this.message = message;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
}
