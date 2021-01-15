package com.alle.san.restaurant.models;

public class ChatModel {
    String chatName;
    String messageTime;
    String lastMessage;
    String lastSender;
    String chatDp;

    public ChatModel() {
    }

    public ChatModel(String chatName, String messageTime, String lastMessage, String lastSender, String chatDp) {
        this.chatName = chatName;
        this.messageTime = messageTime;
        this.lastMessage = lastMessage;
        this.lastSender = lastSender;
        this.chatDp = chatDp;
    }

    public String getChatDp() {
        return chatDp;
    }

    public void setChatDp(String chatDp) {
        this.chatDp = chatDp;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastSender() {
        return lastSender;
    }

    public void setLastSender(String lastSender) {
        this.lastSender = lastSender;
    }
}
