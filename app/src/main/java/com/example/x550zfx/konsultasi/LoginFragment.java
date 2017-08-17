package com.example.x550zfx.konsultasi;

/**
 * Created by X550Z FX on 04/08/2017.
 */

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.x550zfx.konsultasi.model.Constant;
import com.example.x550zfx.konsultasi.model.RequestInterface;
import com.example.x550zfx.konsultasi.model.ServerRequest;
import com.example.x550zfx.konsultasi.model.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.makeText;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_login;
    private EditText et_email,et_password;
    private TextView tv_register;
    private ProgressBar progress;
    private SharedPreferences pref;
    RequestInterface requestInterface;
    ProgressDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        pref = getActivity().getPreferences(0);

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);
        tv_register = (TextView)view.findViewById(R.id.tv_register);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_register:
                goToRegister();
                break;

            case R.id.btn_login:
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                progress.setVisibility(View.INVISIBLE);
                if(!email.isEmpty() && !password.isEmpty()) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    RequestInterface requestInterface = retrofit.create(RequestInterface.class);

                    ServerRequest request = new ServerRequest();
                    request.setEmail(email);
                    request.setPassword(password);
                    Call<ServerResponse> response = requestInterface.loginRequest(request);
                    response.enqueue(new Callback<ServerResponse>() {
                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                            if (response.isSuccessful()){
                                    ServerResponse resp = response.body();
                                    if(resp.getStatus().equals(Constant.STATUS)){
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putBoolean(Constant.IS_LOGGED_IN,true);
                                        editor.putString(Constant.EMAIL,resp.getData().getUser().getEmail());
                                        editor.putString(Constant.NAME,resp.getData().getUser().getEmail());
                                        editor.putString(Constant.AUTH,resp.getData().getUser().getAuthenticationToken());
                                        editor.putInt(Constant.USER_TYPE,resp.getData().getUser().getUserType());
                                        editor.apply();
                                        goToProfile();
                                    }else{
                                        Snackbar.make(getView(), "Login Failed", Snackbar.LENGTH_LONG).show();
                                    }
                                Snackbar.make(getView(), "You Logged as " + resp.getData().getUser().getEmail() + " " +resp.getData().getUser().getAuthenticationToken() +" "+resp.getData().getUser().getUserType(), Snackbar.LENGTH_LONG).show();
                            }else {
                                Log.i("debug", "onResponse: Failed Login");
                                Snackbar.make(getView(), "Make sure you are registered", Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t) {
                            progress.setVisibility(View.INVISIBLE);
                            Log.d(Constant.TAG,"failed");
                            Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                        }
                    });

                    /*Call<user> postUserCall = requestInterface.postUser(email,password);
                    postUserCall.enqueue(new Callback<user() {
                        @Override
                        public void onResponse(Call<user> call, Response<user> response) {
                            int statusCode = response.code();
                            User dump = response.body();


                            //Log.d("LogActivity","onRespone :"+statusCode);
                            Snackbar.make(getView(), "You are logged as " + dump.getEmail(), Snackbar.LENGTH_LONG).show();

                          if(resp.getEmail() != null) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putBoolean("isLogged",true);
                                editor.putString(Constant.EMAIL,resp.getEmail());
                                editor.apply();
                                goToProfile();

                          }
                           progress.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            //Log.d("LogActivity","onRespone :"+ t.getMessage());
                            progress.setVisibility(View.INVISIBLE);
                            Log.d(Constant.TAG, "failed");
                            Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                        }
                    });*/
                } else {
                    Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
    private void loginProcess(String email,String password){

    }

    private void goToRegister(){

        Fragment register = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,register);
        ft.commit();
    }

    private void goToProfile(){
        Fragment profile = new ListContactFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,profile);
        ft.commit();
    }
}