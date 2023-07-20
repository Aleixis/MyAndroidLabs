package com.example.wusandroidlabs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
    public class ChatMessage
    {
        @ColumnInfo(name="message")
        public  String message;


        @ColumnInfo(name="timeSent")
        public String timeSent;

        @ColumnInfo(name="isSend")
        boolean isSend;

        @PrimaryKey(autoGenerate=true)
        @ColumnInfo(name="id")
        public long id;

        public ChatMessage(String s,
                           String time,
                           boolean type ){
            message = s;
            timeSent = time;
            isSend = type;



        }

        public ChatMessage()//for database queries
        {}
    }
