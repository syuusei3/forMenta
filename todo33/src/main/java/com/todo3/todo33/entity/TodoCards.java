package com.todo3.todo33.entity;
import lombok.Data;

import java.util.List;
//import com.todo3.todo33.entity.TodoContents;

@Data
public class TodoCards{
    public long card_id;
    public String card_title; 
    public long list_id;
    public List<TodoContents> contents;

    // public static class Content{
    //     public long content_id;
    ////     public String content; 
    //     public long card_id;
    //     //public long user_id;
    //     //public String contents;
    //     public Content(long content_id, String content, long card_id) {
    //         this.content_id = content_id;
    //         this.content = content;
    //         this.card_id = card_id;
    //     }
    // }

}
