package br.com.hektor.paulo.cadastro;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulo on 19.07.2016.
 */
public class AlunoDAO {

    private  final DBHelper helper;
    public AlunoDAO(DBHelper helper){
        this.helper=helper;
    }


    public void insert(Aluno aluno){
        helper.getWritableDatabase().insert(helper.TABELA, null, toValues(aluno));
        helper.close();
    }

    public void delete(Aluno aluno){
        String args[]={aluno.getId().toString()};
        helper.getWritableDatabase().delete(helper.TABELA,"id=?",args);
        helper.close();
    }

    private ContentValues toValues(Aluno aluno){
        ContentValues values = new ContentValues();

        values.put("nome",aluno.getNome());
        values.put("nota",aluno.getNota());
        values.put("foto",aluno.getCaminhoFoto());
        values.put("endereco",aluno.getEndereco());
        values.put("site",aluno.getSite());
        values.put("telefone",aluno.getTelefone());

        return  values;
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<Aluno>();
        String sql = "SELECT * FROM "+DBHelper.TABELA+ ";";

        Cursor curs=helper.getReadableDatabase().rawQuery(sql,null);
        if(curs!=null & curs.getCount()>0) {
            if (curs.moveToFirst()) {
                while (curs.moveToNext()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(curs.getLong(curs.getColumnIndex("ID")));
                    aluno.setNome(curs.getString(curs.getColumnIndex("NOME")));
                    aluno.setNota(curs.getDouble(curs.getColumnIndex("NOTA")));
                    aluno.setCaminhoFoto(curs.getString(curs.getColumnIndex("FOTO")));
                    aluno.setEndereco(curs.getString(curs.getColumnIndex("ENDERECO")));
                    aluno.setSite(curs.getString(curs.getColumnIndex("SITE")));
                    aluno.setTelefone(curs.getString(curs.getColumnIndex("TELEFONE")));

                    alunos.add(aluno);
                }
                helper.close();
            }
        }
        return alunos;
    }

    public void update(Aluno alunoUpdate) {
        String args[]={alunoUpdate.getId().toString()};
        helper.getWritableDatabase().update(DBHelper.TABELA,toValues(alunoUpdate),"id=?",args);
    }
}