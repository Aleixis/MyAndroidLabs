package com.example.wusandroidlabs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import com.example.wusandroidlabs.ChatMessage;

import java.util.List;
@Dao
public interface ChatMessageDAO {
    @Insert
    public long insertMessage(ChatMessage m);

    @Query("Select * from ChatMessage")
    public List<ChatMessage> getAllMessages();

    @Delete
    void deleteMessage(ChatMessage m);

}
