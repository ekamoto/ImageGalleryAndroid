package com.example.minhabiblioteca.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.minhabiblioteca.R;
import com.example.minhabiblioteca.model.Livro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DetalheItemActivity extends Activity {

    private ImageView imagemLivro;
    private TextView txtTitulo;
    private TextView txtAutor;
    private TextView txtResumo;
    private RatingBar avalicoesLivro;
    private Livro livro;
    private Bitmap img;

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

        img = BitmapFactory.decodeFile(livro.getPathImagem());
        imagemLivro.setImageBitmap(img);

        txtTitulo.setText(livro.getTitulo());
        txtAutor.setText(livro.getAutor());
        txtResumo.setText(livro.getResumo());
        avalicoesLivro.setRating(new Float(livro.getAvaliacao()));

        getActionBar().setDisplayHomeAsUpEnabled(true);

        imagemLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(img.getHeight(), img.getWidth());

                imagemLivro.setLayoutParams(layout);
            }
        });

    }

    private Bitmap decodeFile(File f){
        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            // The new size we want to scale to
            final int REQUIRED_SIZE=70;

            // Find the correct scale value. It should be the power of 2.
            int scale=1;
            while(o.outWidth/scale/2>=REQUIRED_SIZE && o.outHeight/scale/2>=REQUIRED_SIZE)
                scale*=2;

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.i("agenda", "botao voltar");
            finish();
        }

        // TODO Auto-generated method stub
        return super.onOptionsItemSelected(item);
    }

}
