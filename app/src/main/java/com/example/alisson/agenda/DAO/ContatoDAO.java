package com.example.alisson.agenda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.alisson.agenda.Model.Contato;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class ContatoDAO extends SQLiteOpenHelper{

    public ContatoDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    public void inserir(Contato contato){
        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("endereco", contato.getEndereco());
        values.put("telefone", contato.getTelefone());
        values.put("email", contato.getEmail());
        values.put("site", contato.getSite());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("Contato", null, values);
    }

    public void alterar(Contato contato){
        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("endereco", contato.getEndereco());
        values.put("telefone", contato.getTelefone());
        values.put("email", contato.getEmail());
        values.put("site", contato.getSite());

        SQLiteDatabase db = getWritableDatabase();
        String[] params = {contato.getId().toString()};
        db.update("Contato", values, "id = ?", params);
    }

    public void remover(Contato contato){
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {contato.getId().toString()};
        db.delete("Contato", "id=?", params);
    }

    public List<Contato> listar() {
        List<Contato> lista = new LinkedList<>();
        String sql = "SELECT * FROM Contato;";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql,null);

        Contato contato;

        while (c.moveToNext()){
            contato = new Contato();

            contato.setId(c.getLong(c.getColumnIndex("id")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setEndereco(c.getString(c.getColumnIndex("endereco")));
            contato.setTelefone(c.getString(c.getColumnIndex("telefone")));
            contato.setEmail(c.getString(c.getColumnIndex("email")));
            contato.setSite(c.getString(c.getColumnIndex("site")));

            lista.add(contato);
        }
        return lista;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE Contato (");
        sql.append("id INTEGER PRIMARY KEY NOT NULL, ");
        sql.append("nome TEXT NOT NULL, ");
        sql.append("endereco TEXT, ");
        sql.append("telefone TEXT, ");
        sql.append("email TEXT, ");
        sql.append("site TEXT ");
        sql.append(");");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
