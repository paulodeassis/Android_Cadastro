package br.com.hektor.paulo.cadastro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Paulo on 20.07.2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE="CadastroDB";
    private static final int VERSAO = 6;
    public static final String TABELA = "ALUNO";

    public DBHelper(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ TABELA + " (ID INTEGER PRIMARY KEY, NOME TEXT UNIQUE NOT NULL, NOTA REAL, FOTO TEXT, ENDERECO TEXT, SITE TEXT, TELEFONE TEXT);";
        sqLiteDatabase.execSQL(query);
        Log.i("Executei a query:",query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql="DROP TABLE IF EXISTS "+TABELA;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
