package com.example.paulo.myapiapplication.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class PagarFaturaActivity extends AppCompatActivity {


    private EditText mCod;
    private Button mBtn;
    private TextView mSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar_fatura);

        mCod = findViewById(R.id.codigo_pagar_fatura);
        mBtn = findViewById(R.id.btn_pagar_fatura);
        mSaldo = findViewById(R.id.saldo_pagar_fatura);

        mSaldo.setText(String.valueOf(ServiceGenerator.USER.saldo));

        Retrofit retrofit = ServiceGenerator.retrofit();

        API api = retrofit.create(API.class);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Call<HashMap<String,String>> call = api.pagarFatura(ServiceGenerator.STRING_TOKEN, mCod.getText().toString());

                call.enqueue(new Callback<HashMap<String,String>>() {
                    @Override
                    public void onResponse(Call<HashMap<String,String>> call, Response<HashMap<String,String>> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(PagarFaturaActivity.this, response.body().get("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HashMap<String,String>> call, Throwable t) {
                        Toast.makeText(PagarFaturaActivity.this, "ruim", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Call<Usuario> call = api.getUser(ServiceGenerator.STRING_TOKEN);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(PagarFaturaActivity.this, response.body().email, Toast.LENGTH_SHORT).show();
                    ServiceGenerator.USER = response.body();
                    mSaldo.setText(String.valueOf(ServiceGenerator.USER.saldo));
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(PagarFaturaActivity.this, "ruim", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
