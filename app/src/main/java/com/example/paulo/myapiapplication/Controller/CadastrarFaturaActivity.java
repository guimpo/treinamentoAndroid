package com.example.paulo.myapiapplication.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.paulo.myapiapplication.API.API;
import com.example.paulo.myapiapplication.Model.Fatura;
import com.example.paulo.myapiapplication.R;
import com.example.paulo.myapiapplication.Utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.HashMap;

public class CadastrarFaturaActivity extends AppCompatActivity {

    private Button mBtn;
    private EditText mCod;
    private EditText mValor;
    private EditText mDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_fatura);
        loadAttributes();
    }

    private void loadAttributes() {
        mBtn = findViewById(R.id.btn_cadastrar_fatura);
        mCod = findViewById(R.id.codigo_fatura);
        mValor = findViewById(R.id.valor_fatura);
        mDescricao = findViewById(R.id.descricao_fatura);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = ServiceGenerator.retrofit();
                API api = retrofit.create(API.class);
                Call<HashMap<String, String>> call = api.registerFatura(ServiceGenerator.STRING_TOKEN, getFatura());

                call.enqueue(new Callback<HashMap<String, String>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CadastrarFaturaActivity.this, "ok", Toast.LENGTH_SHORT).show();
                            Toast.makeText(CadastrarFaturaActivity.this, response.body().get("status"), Toast.LENGTH_SHORT).show();
                            Toast.makeText(CadastrarFaturaActivity.this, response.body().get("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                        Toast.makeText(CadastrarFaturaActivity.this, "ruim", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private Fatura getFatura() {
        Fatura f = new Fatura();
        f.setCodigo(mCod.getText().toString());
        f.setValor(Double.parseDouble(mValor.getText().toString()));
        f.setDescricao(mDescricao.getText().toString());
        return f;
    }
}
