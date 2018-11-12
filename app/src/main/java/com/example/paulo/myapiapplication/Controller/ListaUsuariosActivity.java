package com.example.paulo.myapiapplication.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.example.paulo.myapiapplication.API.API;
import com.example.paulo.myapiapplication.Adapters.UsuarioAdapter;
import com.example.paulo.myapiapplication.Interface.AdapterPositionOnClickListener;
import com.example.paulo.myapiapplication.Model.Usuario;
import com.example.paulo.myapiapplication.R;
import com.example.paulo.myapiapplication.Utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class ListaUsuariosActivity extends AppCompatActivity  implements AdapterPositionOnClickListener {

    private RecyclerView mRecyclerView;
    private UsuarioAdapter mAdapter;
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
                    mAdapter = new UsuarioAdapter(ListaUsuariosActivity.this, response.body());
                    mAdapter.setAdapterPositionOnClickListener(ListaUsuariosActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(ListaUsuariosActivity.this, "ruim", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setAdapterPositionOnClickListener(View view, final int position) {

        Usuario u = mAdapter.getUsuario(position);
        Intent i  = new Intent(ListaUsuariosActivity.this, UsuarioActivity.class);
        i.putExtra("usuario", u);
        startActivity(i);
    }
}
