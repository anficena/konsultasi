package com.example.x550zfx.konsultasi;

/**
 * Created by X550Z FX on 04/08/2017.
 */

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.x550zfx.konsultasi.model.Constant;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView tv_name,tv_email,tv_message;
    private SharedPreferences pref;
    private AppCompatButton btn_change_password,btn_logout;
    private EditText et_old_password,et_new_password;
    private AlertDialog dialog;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pref = getActivity().getPreferences(0);
        tv_name.setText("Welcome : "+pref.getString(Constant.EMAIL,""));
        tv_email.setText(pref.getString(Constant.EMAIL,""));
    }

    private void initViews(View view){

        tv_name = (TextView)view.findViewById(R.id.tv_name);
        tv_email = (TextView)view.findViewById(R.id.tv_email);
        btn_change_password = (AppCompatButton)view.findViewById(R.id.btn_chg_password);
        btn_logout = (AppCompatButton)view.findViewById(R.id.btn_logout);
        btn_change_password.setOnClickListener(this);
        btn_logout.setOnClickListener(this);

    }

    private void showDialog(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_chg_password:
                showDialog();
                break;
            case R.id.btn_logout:
                logout();
                break;
        }
    }

    private void logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isLogged",false);
        editor.putString(Constant.EMAIL,"");
        editor.putString(Constant.NAME,"");
        editor.apply();
        goToLogin();
    }

    private void goToLogin(){
        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }

    private void changePasswordProcess(String email,String old_password,String new_password){


    }
}