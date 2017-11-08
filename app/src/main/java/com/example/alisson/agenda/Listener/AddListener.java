package com.example.alisson.agenda.Listener;

import android.content.Intent;
import android.view.View;

import com.example.alisson.agenda.FormularioActivity;
import com.example.alisson.agenda.ListaActivity;

/**
 * Created by Alisson on 31/10/2017.
 */

public class AddListener implements View.OnClickListener {
    private ListaActivity context;
    public AddListener (ListaActivity activity){
        this.context = activity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, FormularioActivity.class);
        context.startActivity(intent);
    }
}
