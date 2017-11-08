package com.example.alisson.agenda;

import android.widget.EditText;

import com.example.alisson.agenda.Model.Contato;

/**
 * Created by Alisson on 27/10/2017.
 */

public class FormularioHelper {
    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText email;
    private EditText site;
    private FormularioActivity activity;
    private Contato c;

    public FormularioHelper (FormularioActivity activity){
        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        email = (EditText) activity.findViewById(R.id.formulario_email);
        site = (EditText) activity.findViewById(R.id.formulario_site);
        this.activity = activity;
    }

    public Contato getContato(){
        if (c == null){ c = new Contato(); }
        c.setNome(nome.getText().toString());
        c.setEndereco(endereco.getText().toString());
        c.setTelefone(telefone.getText().toString());
        c.setEmail(email.getText().toString());
        c.setSite(site.getText().toString());

        return c;
    }

    public void setContato(Contato c){
        this.c = c;
        nome.setText(c.getNome());
        endereco.setText(c.getEndereco());
        telefone.setText(c.getTelefone());
        email.setText(c.getEmail());
        site.setText(c.getSite());
    }

    public FormularioActivity getActivity(){
        return this.activity;
    }
}
