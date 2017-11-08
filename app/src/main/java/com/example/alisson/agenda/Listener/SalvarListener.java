package com.example.alisson.agenda.Listener;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.alisson.agenda.DAO.ContatoDAO;
import com.example.alisson.agenda.FormularioActivity;
import com.example.alisson.agenda.FormularioHelper;
import com.example.alisson.agenda.ListaActivity;
import com.example.alisson.agenda.Model.Contato;

/**
 * Created by Alisson on 31/10/2017.
 */

public class SalvarListener implements View.OnClickListener{
    private FormularioHelper helper;

    public SalvarListener (FormularioHelper helper){
        this.helper = helper;
    }

    @Override
    public void onClick(View v) {
        Contato contato = helper.getContato();
        Contato c = helper.getContato();

        ContatoDAO dao = new ContatoDAO(helper.getActivity());
        try {
            if (c.getId() == null){
                dao.inserir(c);
            } else {
                dao.alterar(c);
            }
        }finally {
            dao.close();
        }

        Toast.makeText(helper.getActivity(), String.format("Contato %s Gravado!", contato.getNome()), Toast.LENGTH_SHORT).show();

        helper.getActivity().finish();
    }
}
