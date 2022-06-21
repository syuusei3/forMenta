package com.todo3.todo33.entity;
import lombok.Data;

@Data
public class TodoCards{
    public long card_id;
    public String card_title; 
    public long list_id;
    // public long user_id;

    // public long getId() {
    //     return list_id;
    // }
    // public String getCardTitle() {
    //     return card_title;
    // }
}
