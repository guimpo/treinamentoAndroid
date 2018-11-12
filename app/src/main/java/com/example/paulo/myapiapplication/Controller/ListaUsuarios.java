package com.example.paulo.myapiapplication.Controller;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.paulo.myapiapplication.API.API;
import com.example.paulo.myapiapplication.Adapters.UsuarioAdapter;
import com.example.paulo.myapiapplication.Interface.AdapterPositionOnClickListener;
import com.example.paulo.myapiapplication.Model.Auth;
import com.example.paulo.myapiapplication.Model.Usuario;
import com.example.paulo.myapiapplication.R;
import com.example.paulo.myapiapplication.Utils.JsonUtils;
import com.example.paulo.myapiapplication.Utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListaUsuarios extends AppCompatActivity  implements AdapterPositionOnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_usuarios);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Retrofit getUsuarios
        getUsuarios();
    }

    private void getUsuarios() {
        Retrofit retrofit = ServiceGenerator.retrofit();

        API api = retrofit.create(API.class);

        Call<List<Usuario>> call = api.getUsers(ServiceGenerator.STRING_TOKEN);

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    // POPULAR mUsuarios
                    mAdapter = new UsuarioAdapter(ListaUsuarios.this, response.body());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(ListaUsuarios.this, "ruim", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setAdapterPositionOnClickListener(View view, int position) {

    }
}