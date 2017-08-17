package com.example.x550zfx.konsultasi.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.x550zfx.konsultasi.R;
import com.example.x550zfx.konsultasi.model.User;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by X550Z FX on 15/08/2017.
 */

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ViewHolder> {
    private ArrayList<User> user;
    private onItemClickListener onItemClickListener;
    public AdapterContact(ArrayList<User> user) {
        this.user = user;
    }

    @Override
    public AdapterContact.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_row, viewGroup, false);
        return new ViewHolder(view);
    }
    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public void onBindViewHolder(AdapterContact.ViewHolder viewHolder, final int i) {
        viewHolder.tv_name.setText(user.get(i).getName());
        viewHolder.tv_mail.setText(user.get(i).getEmail());
        viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("test onclick");
                onItemClickListener.onItemClick(user.get(i));
            }
        });
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(user.get(i));
                }
            }
        });
    }

    public interface onItemClickListener{
        void onItemClick(User user);
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_mail;
        public RelativeLayout container;
        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView)view.findViewById(R.id.user_name);
            tv_mail = (TextView)view.findViewById(R.id.content);
            container = (RelativeLayout)view.findViewById(R.id.container);


        }
    }

}