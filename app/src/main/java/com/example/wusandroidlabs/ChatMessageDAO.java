package com.example.wusandroidlabs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wusandroidlabs.ChatMessage;

import java.util.List;
@Dao
public interface ChatMessageDAO {
    @Insert
    public long insertMessage(ChatMessage insert);

    @Query("Select * from ChatMessage")
    public List<ChatMessage> getAllMessages();


    @Update
    public int UpdateMessage (ChatMessage updatedMessage);

    @Delete
    void deleteMessage(ChatMessage m);

}
