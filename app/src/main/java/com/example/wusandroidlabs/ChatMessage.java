package com.example.wusandroidlabs;
    public class ChatMessage
    {
        public String message;
        boolean isSend;
        public String timeSent;
        public ChatMessage(String s,
                           String time,
                           boolean type){
            message = s;
            timeSent = time;
            isSend = type;
        }


    }
