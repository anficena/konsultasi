package com.example.x550zfx.konsultasi;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.x550zfx.konsultasi.model.Constant;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getPreferences(0);
        initFragment();
    }

    private void initFragment(){
        Fragment fragment;
        if(pref.getBoolean(Constant.TAG,false)){
            fragment = new ListFragment();
        }else {
            fragment = new LoginFragment();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fragment);
        ft.commit();
    }

}