package com.example.paulo.myapiapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.paulo.myapiapplication.Model.Usuario;
import com.example.paulo.myapiapplication.R;
import com.squareup.picasso.Picasso;

public class UsuarioActivity extends AppCompatActivity {

    private ImageView mFoto;
    private Usuario user;
    private TextView mNome;
    private TextView mEmail;
    private TextView mSaldo;
    private TextView mCpf;
    private TextView mTelefone;
    private TextView mDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Intent i = getIntent();
        user = (Usuario) i.getSerializableExtra("usuario");
        setup();

    }

    private void setup() {
        mFoto = findViewById(R.id.foto_usuario);
        String imageUri = user.picture;
        Picasso.get().load(imageUri).into(mFoto);
        mNome = findViewById(R.id.nome_usuario);
        mNome.setText(user.name);
        mEmail = findViewById(R.id.email_usuario);
        mEmail.setText(user.email);
        mSaldo = findViewById(R.id.saldo_usuario);
        mSaldo.setText(String.valueOf(user.saldo));
        mCpf = findViewById(R.id.cpf_usuario);
        mCpf.setText(user.cpf);
        mTelefone = findViewById(R.id.telefone_usuario);
        mTelefone.setText(user.telefone);
        mDescricao = findViewById(R.id.descricao_usuario);
        mDescricao.setText(user.descricao);
    }
}
