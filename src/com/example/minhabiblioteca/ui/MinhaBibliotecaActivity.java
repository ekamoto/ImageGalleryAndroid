package com.example.minhabiblioteca.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.minhabiblioteca.R;

public class MinhaBibliotecaActivity extends Activity {

	private ListView listaOpcoesMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_minha_biblioteca);

		listaOpcoesMenu = (ListView) findViewById(R.id.lista_menu);
		String[] values = new String[] { "Minha Coleção", "Adicionar" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		listaOpcoesMenu.setAdapter(adapter);

		listaOpcoesMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				int itemPosition = position;
				
				if (itemPosition == 0) {
					Intent intent = new Intent(MinhaBibliotecaActivity.this, ColecaoActivity.class);
		            startActivity(intent);
				}

				if (itemPosition == 1) {
					Intent intent = new Intent(MinhaBibliotecaActivity.this, NovoItemActivity.class);
		            startActivity(intent);
				}
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.minha_biblioteca, menu);
		return true;
	}

}
