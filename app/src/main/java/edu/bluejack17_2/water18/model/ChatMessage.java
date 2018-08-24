package edu.bluejack17_2.water18.model;

import java.util.Date;

public class ChatMessage {

    private String messageText;
    private String sender;
    private String receiver;
    private long messageTime;

    public ChatMessage(String messageText, String sender, String receiver){
        this.messageText = messageText;
        this.sender = sender;
        this.receiver = receiver;

        messageTime = new Date().getTime();
    }

    public String getMessageText(){
        return messageText;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
