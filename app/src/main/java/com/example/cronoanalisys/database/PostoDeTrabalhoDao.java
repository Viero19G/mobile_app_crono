package com.example.cronoanalisys.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.cronoanalisys.models.PostoDeTrabalho;
import java.util.List;

@Dao
public interface PostoDeTrabalhoDao {
    @Query("DELETE FROM posto_de_trabalho")
    void deleteAll();

    @Insert
    void insertAll(List<PostoDeTrabalho> postos);

    @Query("SELECT * FROM posto_de_trabalho")
    List<PostoDeTrabalho> getAll();
}
