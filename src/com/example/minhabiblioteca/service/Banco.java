package com.example.minhabiblioteca.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper  {

	public Banco(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE livro ("
				+ "id_livro INTEGER PRIMARY KEY autoincrement,"
				+ " titulo varchar(45) NOT NULL ,"
				+ " autor varchar(45) NOT NULL,"
				+ " resumo varchar(125) NOT NULL,"
				+ " path_imagem varchar(125) NOT NULL,"
				+ " avaliacao double NOT NULL"+ ");");
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
}
