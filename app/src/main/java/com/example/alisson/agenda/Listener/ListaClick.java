package com.example.alisson.agenda.Listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.alisson.agenda.FormularioActivity;
import com.example.alisson.agenda.ListaActivity;
import com.example.alisson.agenda.Model.Contato;

/**
 * Created by 1513 IRON on 07/11/2017.
 */


public class ListaClick implements AdapterView.OnItemClickListener {

    private ListaActivity context;
    public ListaClick(ListaActivity context){
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contato c = (Contato) parent.getItemAtPosition(position);
        Intent intent = new Intent(context, FormularioActivity.class);
        intent.putExtra("contato", c);
        context.startActivity(intent);
    }
}