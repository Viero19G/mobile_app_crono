package com.example.cronoanalisys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cronoanalisys.api.PostoDeTrabalhoAPI;
import com.example.cronoanalisys.api.RetrofitClient;
import com.example.cronoanalisys.database.AppDatabase;
import com.example.cronoanalisys.models.PostoDeTrabalho;
import com.example.cronoanalisys.models.PostoDeTrabalhoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Button btnSyncPostos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSyncPostos = findViewById(R.id.btnSyncPostos);

        btnSyncPostos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncPostos();
            }
        });
    }

    private void syncPostos() {
        // Usando RetrofitClient para obter a instância do Retrofit
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        PostoDeTrabalhoAPI postoDeTrabalhoApi = retrofit.create(PostoDeTrabalhoAPI.class);

        // Fazendo a requisição GET
        Call<List<PostoDeTrabalho>> call = postoDeTrabalhoApi.getPostosDeTrabalho();
        call.enqueue(new Callback<PostoDeTrabalhoResponse>() {
            @Override
            public void onResponse(Call<PostoDeTrabalhoResponse> call, Response<PostoDeTrabalhoResponse> response) {
                if (response.isSuccessful()) {
                    PostoDeTrabalhoResponse postoDeTrabalhoResponse = response.body();
                    if (postoDeTrabalhoResponse != null && postoDeTrabalhoResponse.getData() != null) {
                        List<PostoDeTrabalho> postos = postoDeTrabalhoResponse.getData();

                        // Agora vamos atualizar o banco de dados local
                        AppDatabase db = AppDatabase.getDatabase(MainActivity.this);
                        db.postoDeTrabalhoDao().deleteAll(); // Deleta todos os postos antes de adicionar os novos
                        db.postoDeTrabalhoDao().insertAll(postos); // Insere os novos postos no banco local

                        Toast.makeText(MainActivity.this, "Postos sincronizados!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Erro na sincronização", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostoDeTrabalhoResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
