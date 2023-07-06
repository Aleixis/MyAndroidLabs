package com.example.wusandroidlabs;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.wusandroidlabs.ChatMessage;
import com.example.wusandroidlabs.ChatMessageDAO;

@Database(entities = {ChatMessage.class}, version = 1)
public abstract class MessageDatabase extends RoomDatabase {

    public abstract ChatMessageDAO cmDAO();
}
