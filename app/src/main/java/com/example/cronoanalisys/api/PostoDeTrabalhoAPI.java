package com.example.cronoanalisys.api;

import com.example.cronoanalisys.models.PostoDeTrabalho;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostoDeTrabalhoAPI {
    @GET("postos/postolist/")
    Call<List<PostoDeTrabalho>> getPostosDeTrabalho();
}
