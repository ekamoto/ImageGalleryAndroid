package com.example.minhabiblioteca.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.example.minhabiblioteca.R;
import com.example.minhabiblioteca.model.Livro;
import com.example.minhabiblioteca.service.BancoService;

import java.io.File;

public class NovoItemActivity extends Activity {

	private ImageView imagemLivro;
	private Button btnAdicionar;
	private Button btnInserirImagem;
	private EditText txtTitulo;
	private EditText txtAutor;
	private EditText txtResumo;
	private RatingBar avalicoesLivro;
	private String pathImagem;
	private BancoService bancoService;
    private Button btnCapturarImagem;
    private static final int CODIGO_IMAGEM = 1020394857;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.adicionar_item);

        getActionBar().setDisplayHomeAsUpEnabled(true);

		imagemLivro = (ImageView) findViewById(R.id.imagem_livro);
		btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
		txtTitulo = (EditText) findViewById(R.id.txtTitulo);
		txtAutor = (EditText) findViewById(R.id.txtAutor);
		txtResumo = (EditText) findViewById(R.id.txtResumo);
		avalicoesLivro = (RatingBar) findViewById(R.id.avaliacaoLivro);
		btnInserirImagem = (Button) findViewById(R.id.btnInserirImagem);
        btnCapturarImagem = (Button) findViewById(R.id.btnCapturarImagem);

		bancoService = new BancoService();

		btnAdicionar.setOnClickListener(new OnClickListener() {

			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
			public void onClick(View arg0) {

				Livro livro = new Livro();
				livro.setTitulo(txtTitulo.getText().toString());
				livro.setAutor(txtAutor.getText().toString());
				livro.setResumo(txtResumo.getText().toString());
				livro.setAvaliacao(avalicoesLivro.getRating());
				livro.setPathImagem(pathImagem);

				bancoService.salvarLivro(livro, getApplicationContext());

/*				try {

					Banco banco = new Banco(getApplicationContext(),
							"banco", null, 1);

					ContentValues contentValues = new ContentValues();
					contentValues.put("titulo", livro.getTitulo());
					contentValues.put("autor", livro.getAutor());
					contentValues.put("resumo", livro.getResumo());
					contentValues.put("avaliacao", livro.getAvaliacao());
					contentValues.put("path_imagem", livro.getPathImagem());
					banco.getWritableDatabase().insert("livro", null,
							contentValues);

					Toast toast = Toast.makeText(getApplicationContext(),
							"Livro cadastrado com sucesso!!", 5);
					toast.show();

				} catch (Exception e) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"Erro ao salvar informa��es no banco!", 5);
					toast.show();
				}*/

				txtTitulo.setText("");
				txtAutor.setText("");
				txtResumo.setText("");
				avalicoesLivro.setRating(1);
				pathImagem = "";
				imagemLivro.setBackgroundResource(R.drawable.ic_launcher);
				imagemLivro.setImageBitmap(null);

			}
		});

		btnInserirImagem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(
						Intent.createChooser(intent, "Selecione uma imagem"), 1);
			}

		});

        btnCapturarImagem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CODIGO_IMAGEM);

                File criaDir = new File("imagemGaleriaAndroid");
                boolean b = criaDir.mkdir();
                if(b || criaDir.exists()){
                    Toast.makeText(getApplicationContext(), "Ok Criou ou já Existe Diretório", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Não Criou Diretório", Toast.LENGTH_LONG).show();
                }
            }
        });

	}

    // Retorno de startActivityForResult
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if ((requestCode == 1 || requestCode == CODIGO_IMAGEM) && resultCode == RESULT_OK && null != data) {

			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			imagemLivro.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			pathImagem = picturePath;
		} else if(requestCode == CODIGO_IMAGEM) {

            // É o mesmo comportamento do if acima
        }
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {

            Log.i("agenda", "botao voltar");
            finish();
        }

        // TODO Auto-generated method stub
        return super.onOptionsItemSelected(item);
    }
}