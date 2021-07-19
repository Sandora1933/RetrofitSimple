package com.example.retrofitsimple;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebsiteAPI {
    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") int id);
}
