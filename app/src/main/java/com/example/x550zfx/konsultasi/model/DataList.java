package com.example.x550zfx.konsultasi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by X550Z FX on 14/08/2017.
 */

public class DataList {
    @SerializedName("users")
    @Expose
    private List<User> user;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
