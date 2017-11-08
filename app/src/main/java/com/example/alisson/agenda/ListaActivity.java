package com.example.alisson.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alisson.agenda.DAO.ContatoDAO;
import com.example.alisson.agenda.Listener.AddListener;
import com.example.alisson.agenda.Listener.ListaClick;
import com.example.alisson.agenda.Model.Contato;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private ListView lista;
    private Button add;

    @Override
    protected void onResume() {
        super.onResume();
        atualizar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = (ListView) findViewById(R.id.lista_contatos);
        ListaClick listaClick = new ListaClick(ListaActivity.this);
        lista.setOnItemClickListener(listaClick);

        add = (Button) findViewById(R.id.lista_add);
        AddListener addListener = new AddListener(this);

        add.setOnClickListener(addListener);
        registerForContextMenu(lista);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_lista, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId() ) {
            case R.id.menu_lista_remover:
                AdapterView.AdapterContextMenuInfo menuInfo =
                        (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Contato c = (Contato) lista.getItemAtPosition(menuInfo.position);

                ContatoDAO dao = new ContatoDAO(this);
                try{
                    dao.remover(c);
                } finally {
                    dao.close();
                }
                atualizar();
                Toast.makeText(this, String.format("Contato %s Removido!", c.getNome()), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    private void onItemClick(AdapterView<?> lista, View item, int posicao, long id){
        Contato c = (Contato) lista.getItemAtPosition(posicao);

        Intent intent = new Intent(ListaActivity.this, FormularioActivity.class);
        intent.putExtra("contato", c);
        startActivity(intent);
    }

    private void atualizar(){
        List<Contato> contatos;
        ContatoDAO dao = new ContatoDAO(this);

        try {
            contatos = dao.listar();
        }finally {
            dao.close();
        }
        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1,contatos);
        lista.setAdapter(adapter);
    }
}
