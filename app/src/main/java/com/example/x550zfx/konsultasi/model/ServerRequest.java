package com.example.x550zfx.konsultasi.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

/**
 * Created by X550Z FX on 08/08/2017.
 */
public class ServerRequest {
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) { this.email = email;}

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }