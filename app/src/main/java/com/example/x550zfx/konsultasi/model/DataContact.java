package com.example.x550zfx.konsultasi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by X550Z FX on 15/08/2017.
 */

public class DataContact {
    @SerializedName("users")
    @Expose
    private User[] user;

    public User[] getUser() {
        return user;
    }

    public void setUser(User[] user) {
        this.user = user;
    }
}
