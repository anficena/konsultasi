package com.example.x550zfx.konsultasi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by X550Z FX on 15/08/2017.
 */

public class ServerContactResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private DataContact data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataContact getData() {
        return data;
    }

    public void setData(DataContact data) {
        this.data = data;
    }
}
