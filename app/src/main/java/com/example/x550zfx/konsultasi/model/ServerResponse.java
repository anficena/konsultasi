package com.example.x550zfx.konsultasi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by X550Z FX on 09/08/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse  {
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("data")
        @Expose
        private Data data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }