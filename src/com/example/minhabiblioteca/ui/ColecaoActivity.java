package com.example.minhabiblioteca.ui;

import java.util.List;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.minhabiblioteca.adapter.LivroAdapter;
import com.example.minhabiblioteca.model.Livro;
import com.example.minhabiblioteca.service.BancoService;

public class ColecaoActivity extends ListActivity {
	
	private List<Livro> livros;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

		BancoService bancoService = new BancoService();
		livros = bancoService.getAllLivros(getApplicationContext());
		setListAdapter(new LivroAdapter(getApplicationContext(), livros));


    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Livro livro = livros.get(position);
		
		Intent intent = new Intent(getApplicationContext(), DetalheItemActivity.class);
	    intent.putExtra("livro", livro);
	    startActivity(intent);      
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            Log.i("agenda", "botao voltar");
            finish();
        }

        // TODO Auto-generated method stub
        return super.onOptionsItemSelected(item);
    }

}
