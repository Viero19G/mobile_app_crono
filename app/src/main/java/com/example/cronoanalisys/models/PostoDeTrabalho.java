package com.example.cronoanalisys.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posto_de_trabalho")
public class PostoDeTrabalho {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String descricao;

    // Construtores, Getters e Setters
    public PostoDeTrabalho(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
