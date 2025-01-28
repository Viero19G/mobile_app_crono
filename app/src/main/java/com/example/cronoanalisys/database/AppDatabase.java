package com.example.cronoanalisys.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.cronoanalisys.models.PostoDeTrabalho;

@Database(entities = {PostoDeTrabalho.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Aqui você cria o método para acessar o DAO
    public abstract PostoDeTrabalhoDao postoDeTrabalhoDao();

    // Instância única do banco de dados
    private static AppDatabase INSTANCE;

    // Método para obter a instância do banco de dados
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "cronoanalisys_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
