package com.example.paulo.myapiapplication.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.paulo.myapiapplication.API.API;
import com.example.paulo.myapiapplication.Model.Usuario;
import com.example.paulo.myapiapplication.R;
import com.example.paulo.myapiapplication.Utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.HashMap;

public class AtualizarUsuarioActivity extends AppCompatActivity {

    private EditText mNome;
    private EditText mEmail;
    private EditText mSaldo;
    private EditText mCpf;
    private EditText mTelefone;
    private EditText mDescricao;
    private Button mButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_usuario);

        mNome = findViewById(R.id.nome_usuario_a);
        mEmail = findViewById(R.id.email_usuario_a);
        mSaldo = findViewById(R.id.saldo_usuario_a);
        mCpf = findViewById(R.id.cpf_usuario_a);
        mTelefone = findViewById(R.id.telefone_usuario_a);
        mDescricao = findViewById(R.id.descricao_usuario_a);
        mButton = findViewById(R.id.btn_atualizar_usuario);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = ServiceGenerator.retrofit();
                API api = retrofit.create(API.class);

                Usuario u = ServiceGenerator.USER;

                u.name = mNome.getText().toString();
                Call<HashMap<String, String>> call = api.editar(ServiceGenerator.STRING_TOKEN, ServiceGenerator.USER);

                call.enqueue(new Callback<HashMap<String, String>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AtualizarUsuarioActivity.this, response.body().get("status"), Toast.LENGTH_SHORT).show();
                            Toast.makeText(AtualizarUsuarioActivity.this, response.body().get("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                        Toast.makeText(AtualizarUsuarioActivity.this, "ruim", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
