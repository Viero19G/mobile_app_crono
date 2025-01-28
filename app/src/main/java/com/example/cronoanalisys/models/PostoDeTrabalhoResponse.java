package com.example.cronoanalisys.models;

import java.util.List;

public class PostoDeTrabalhoResponse {
    private String message;
    private List<PostoDeTrabalho> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PostoDeTrabalho> getData() {
        return data;
    }

    public void setData(List<PostoDeTrabalho> data) {
        this.data = data;
    }
}
