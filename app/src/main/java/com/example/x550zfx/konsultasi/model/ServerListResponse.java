package com.example.x550zfx.konsultasi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by X550Z FX on 14/08/2017.
 */

public class ServerListResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private DataList data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataList getData() {
        return data;
    }

    public void setData(DataList data) {
        this.data = data;
    }
}
