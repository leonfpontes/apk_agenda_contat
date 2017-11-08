package com.example.alisson.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alisson.agenda.DAO.ContatoDAO;
import com.example.alisson.agenda.Listener.SalvarListener;
import com.example.alisson.agenda.Model.Contato;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Contato c = (Contato) intent.getSerializableExtra("contato");
        if (c != null) {
            helper.setContato(c);
        }

        Button salvar = (Button) findViewById(R.id.formulario_salvar);

        SalvarListener salvarListener = new SalvarListener(helper);
        salvar.setOnClickListener(salvarListener);
    }

}
