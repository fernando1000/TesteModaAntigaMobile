package com.simplestestemobile.dao;

import com.simplestestemobile.util.Query;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoSQLiteOpenHelper extends SQLiteOpenHelper {

	public static final String BANCO_NOME = "hospital";
	public static final int BANCO_VERSAO = 4;
	private Context context;
	
	public BancoSQLiteOpenHelper(Context _context) {
		super(_context, BANCO_NOME, null, BANCO_VERSAO);
		this.context = _context;
	}

	@Override
	public void onCreate(SQLiteDatabase sQLiteDatabase) {
		
		try {
			for (String tabela : ListaComTabelasModel.devolveListaComTabelasModel()) {
	
					Class<?> classe = Class.forName("com.simplestestemobile.model."+tabela);
				
					sQLiteDatabase.execSQL(Query.criaCreateTableComKeyy(classe));
			}
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion) {

		try {
			for (String tabela : ListaComTabelasModel.devolveListaComTabelasModel()) {
	
					Class<?> classe = Class.forName("com.simplestestemobile.model."+tabela);
					
					sQLiteDatabase.execSQL(Query.criaDropTable_final(classe));				
			}
		} 
		catch (ClassNotFoundException e) {	
			e.printStackTrace();
		} 
		
		onCreate(sQLiteDatabase);
	}
}