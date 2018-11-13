package com.example.paulo.myapiapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.paulo.myapiapplication.Model.Usuario;
import com.example.paulo.myapiapplication.R;
import com.example.paulo.myapiapplication.Utils.ServiceGenerator;


public class HomeActivity extends AppCompatActivity {

    private TextView mLblNome;
    private LinearLayout mPessoas;
    private ImageView mGerarFatura;
    private ImageView mPagarFatura;
    private ImageView mPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadVariables();
        setVariables();
    }

    private void loadVariables() {
        mLblNome = (TextView) findViewById(R.id.lbNome);
        mPessoas = (LinearLayout) findViewById(R.id.pessoas);
    }

    private void setVariables() {
        String greetings = getResources().getString(R.string.ola_usuario);
        greetings += " ";
        greetings += ServiceGenerator.USER.name;
        greetings += ",";
        mLblNome.setText(greetings);

        mPessoas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ListaUsuariosActivity.class);
                startActivity(i);
            }
        });

        mGerarFatura = findViewById(R.id.gerar_fatura_home);
        mGerarFatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CadastrarFaturaActivity.class);
                startActivity(i);
            }
        });

        mPagarFatura = findViewById(R.id.pagar_fatura_home);
        mPagarFatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, PagarFaturaActivity.class);
                startActivity(i);
            }
        });

        mPerfil = findViewById(R.id.perfil_home);
        mPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, AtualizarUsuarioActivity.class);
                startActivity(i);
            }
        });

    }
}
