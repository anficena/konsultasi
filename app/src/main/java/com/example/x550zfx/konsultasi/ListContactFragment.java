package com.example.x550zfx.konsultasi;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.x550zfx.konsultasi.adapter.AdapterContact;
import com.example.x550zfx.konsultasi.model.Constant;
import com.example.x550zfx.konsultasi.model.RequestInterface;
import com.example.x550zfx.konsultasi.model.ServerContactResponse;
import com.example.x550zfx.konsultasi.model.ServerListResponse;
import com.example.x550zfx.konsultasi.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.x550zfx.konsultasi.R.id.recyclerView;
import static com.example.x550zfx.konsultasi.R.id.snackbar_action;


public class ListContactFragment extends Fragment implements View.OnClickListener, AdapterContact.onItemClickListener {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60; // menampilkan data sebanyak value

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(User user) {
        Toast.makeText(getActivity(),"You clicked"+user.getName(),Toast.LENGTH_SHORT).show();
    }

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView mRecyclerView;
    protected AdapterContact mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    private  SharedPreferences pref;
    private ArrayList<User> user;

    //int [] icon = {R.mipmap.user_icon, R.mipmap.user_icon,R.mipmap.user_icon,R.mipmap.user_icon,R.mipmap.user_icon,R.mipmap.user_icon,R.mipmap.user_icon,R.mipmap.user_icon};
    //String [] judul = {"Lina","Ummu","Bonita","Fajar","Rina","Siut","Tari","Hasanah"};
    //String [] deskripsi = {"Konsultasi online","Konsultasi online","Konsultasi online","Konsultasi online","Konsultasi online","Konsultasi online","Konsultasi online","Konsultasi online"};

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        loadUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_chat, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(recyclerView);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)
        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadUser(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request=retrofit.create(RequestInterface.class);
        pref = getActivity().getPreferences(0);
        String token = "Token token="+pref.getString(Constant.AUTH,"");
        int type = pref.getInt(Constant.USER_TYPE,0);
       // Toast.makeText(getContext(), token + " and " + type, Toast.LENGTH_LONG).show();
        Call<ServerContactResponse> call=request.getDataUser(token,type);
        call.enqueue(new Callback<ServerContactResponse>() {
            @Override
            public void onResponse(Call<ServerContactResponse> call, Response<ServerContactResponse> response) {
                if(response.isSuccessful()) {
                    ServerContactResponse serverContactResponse = response.body();
                    user = new ArrayList<>(Arrays.asList(serverContactResponse.getData().getUser()));
                    mAdapter = new AdapterContact(user);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(ListContactFragment.this);
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
//                    Snackbar.make(getView(), "Success", Snackbar.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(), "Gagal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ServerContactResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
}