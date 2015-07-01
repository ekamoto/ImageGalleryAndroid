package com.example.minhabiblioteca.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.minhabiblioteca.R;
import com.example.minhabiblioteca.model.Livro;

public class DetalheItemActivity extends Activity{
	
	private ImageView imagemLivro;
	private TextView txtTitulo;
	private TextView txtAutor;
	private TextView txtResumo;
	private RatingBar avalicoesLivro;
	private Livro livro;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("UseValueOf")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.detalhes_item);
		livro = (Livro) getIntent().getSerializableExtra("livro");

		imagemLivro = (ImageView) findViewById(R.id.imagem_livro);
		txtTitulo = (TextView) findViewById(R.id.txtTitulo);
		txtAutor = (TextView) findViewById(R.id.txtAutor);
		txtResumo = (TextView) findViewById(R.id.txtResumo);
		avalicoesLivro = (RatingBar) findViewById(R.id.avaliacaoLivro);
		
		imagemLivro.setImageBitmap(BitmapFactory.decodeFile(livro.getPathImagem()));
		txtTitulo.setText(livro.getTitulo());
		txtAutor.setText(livro.getAutor());
		txtResumo.setText(livro.getResumo());
		avalicoesLivro.setRating(new Float(livro.getAvaliacao()));

        getActionBar().setDisplayHomeAsUpEnabled(true);

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
