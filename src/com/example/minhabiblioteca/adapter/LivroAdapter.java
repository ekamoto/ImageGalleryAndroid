package com.example.minhabiblioteca.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import android.graphics.Bitmap;
import com.example.minhabiblioteca.R;
import com.example.minhabiblioteca.model.Livro;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LivroAdapter extends BaseAdapter {

	private Context context;
	private List<Livro> livros;

	public LivroAdapter(Context context, List<Livro> livros) {
		this.context = context;
		this.livros = livros;
	}

	@Override
	public int getCount() {
		return livros.size();
	}

	@Override
	public Object getItem(int position) {
		return livros.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

    private Bitmap decodeFile(File f) {
        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            // The new size we want to scale to
            final int REQUIRED_SIZE=70;

            //Find the correct scale value. It should be the power of 2.
            int scale=1;
            while(o.outWidth/scale/2>=REQUIRED_SIZE && o.outHeight/scale/2>=REQUIRED_SIZE)
                scale*=2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Livro livro = livros.get(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Não inflar toda hora
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.lista_livros, parent, false);
        }

        TextView txttitulo = (TextView) convertView.findViewById(R.id.txtTituloLivro);
        txttitulo.setText(livro.getTitulo());

        ImageView imagemLivro = (ImageView) convertView.findViewById(R.id.imagemLivro);

        // Desse jeito fica muito pesado a imagem para uma ListView
        // Bitmap bitmap = decodeFile(new File(livro.getPathImagem()));
        // imagemLivro.setImageBitmap(bitmap);

        Bitmap bitmap = decodeFile(new File(livro.getPathImagem()));
        imagemLivro.setImageBitmap(bitmap);

        return convertView;
    }

}
