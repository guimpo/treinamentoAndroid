package com.example.paulo.myapiapplication.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.paulo.myapiapplication.API.API;
import com.example.paulo.myapiapplication.R;
import com.example.paulo.myapiapplication.Utils.JsonUtils;
import com.example.paulo.myapiapplication.Utils.ServiceGenerator;
import com.example.paulo.myapiapplication.Model.Auth;
import com.example.paulo.myapiapplication.Model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPass;
    private Button btAcessar;

    public String token;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etSenha);
        btAcessar = findViewById(R.id.btAcessar);

        btAcessar.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             acessar();
                                         }
                                     }
        );
    }

    private void acessar() {

        Auth user = new Auth();
        user.email = etEmail.getText().toString();
        user.password = etPass.getText().toString();

        Retrofit retrofit = ServiceGenerator.retrofit(Auth.class);

        API api = retrofit.create(API.class);

        Call<HashMap<String, String>> call = api.authUser(user);

        call.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                if (response.isSuccessful()) {
                    ServiceGenerator.STRING_TOKEN = response.body().get("jwt");
                    irParaHome();
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "ruim", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void irParaHome() {

        Retrofit retrofit = ServiceGenerator.retrofit();

        API api = retrofit.create(API.class);

        Call<Usuario> call = api.getUser(ServiceGenerator.STRING_TOKEN);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "irParaHome", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, response.body().email, Toast.LENGTH_SHORT).show();
                    ServiceGenerator.USER = response.body();

                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "ruim", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
