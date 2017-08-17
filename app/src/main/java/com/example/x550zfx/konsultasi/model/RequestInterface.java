package com.example.x550zfx.konsultasi.model;

/**
 * Created by X550Z FX on 04/08/2017.
 */

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestInterface {
    @POST("users/session")
    Call<ServerResponse> loginRequest(@Body ServerRequest request);

    @GET("users")
    Call<ServerContactResponse> getDataUser(@Header("Authorization") String authorization,
                                            @Query("user_type") int userType);
}