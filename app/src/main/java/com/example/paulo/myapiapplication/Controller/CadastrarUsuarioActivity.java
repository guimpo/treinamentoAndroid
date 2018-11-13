package com.example.paulo.myapiapplication.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.paulo.myapiapplication.API.API;
import com.example.paulo.myapiapplication.R;
import com.example.paulo.myapiapplication.Utils.JsonUtils;
import com.example.paulo.myapiapplication.Model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;

public class CadastroActivity extends AppCompatActivity {

    private EditText et_nome;
    private EditText et_email;
    private EditText et_senha;
    private Button btn_cadastrar;


    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        et_nome = findViewById(R.id.etName);
        et_email = findViewById(R.id.etEmail);
        et_senha = findViewById(R.id.etSenha);
        btn_cadastrar = findViewById(R.id.btCadastrar);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

    }

    private Usuario getUser() {
        Usuario user = new Usuario();
        user.name = et_nome.getText().toString();
        user.email = et_email.getText().toString();
        user.password = et_senha.getText().toString();
        return user;
    }

    private void cadastrar() {
        // Instanciar o usuaŕio
        Usuario user = getUser();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api-senai-tcs.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(JsonUtils.getGson(Usuario.class)))
                .build();

        API api = retrofit.create(API.class);
        Call<HashMap<String, String>> call = api.registerUser(user);

        call.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, response.body().get("msg"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

            }
        });
    }


}
