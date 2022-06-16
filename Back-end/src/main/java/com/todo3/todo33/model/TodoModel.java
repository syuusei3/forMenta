package com.todo3.todo33.model;

import java.util.List;
import java.util.ArrayList;

public class TodoModel {
    public long list_id;
    public String list_title;
    public List<Card> cards;
    
    public static class Card {
        public long card_id;
        public String card_title; 
        public long list_id;
        //public long user_id;
        //public String contents;
        public Card(String card_title) {
            this.card_title = card_title;
        }
        // public Card(String card_title, long list_id) {
        //     this.card_title = card_title;
        //     this.list_id = list_id;
        // }
    }
    public TodoModel(long list_id, String list_title){
        this.list_id = list_id;
        this.list_title = list_title;
        this.cards = new ArrayList<Card>();
    }
}
