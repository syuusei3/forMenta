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
        public List<Content> contents;
        //public long user_id;
        //public String contents;

        public static class Content {
            public long content_id;
            public String content; 
            public long card_id;

            public Content(long content_id, String content, long card_id) {
                this.content_id = content_id;
                this.content = content;
                this.card_id = card_id;
            }
        }

        public Card(long card_id, String card_title, long list_id) {
            this.card_id = card_id;
            this.card_title = card_title;
            this.list_id = list_id;
            this.contents = new ArrayList<Content>();
        }
    }

    public TodoModel(long list_id, String list_title){
        this.list_id = list_id;
        this.list_title = list_title;
        this.cards = new ArrayList<Card>();
    }
}
