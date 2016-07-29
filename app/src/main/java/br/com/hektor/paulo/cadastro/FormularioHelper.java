package br.com.hektor.paulo.cadastro;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by Paulo on 18.07.2016.
 */
public class FormularioHelper extends Activity {
    private  EditText campoNome;
    private EditText campoSite;
    private EditText campoTelefone;
    private EditText campoEndereco;
    private ImageView campoFoto;
    private SeekBar ratingNota;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.nome);
        campoEndereco= (EditText) activity.findViewById(R.id.endereco);
        campoSite = (EditText) activity.findViewById(R.id.site);
        campoTelefone= (EditText) activity.findViewById(R.id.telefone);
        campoFoto = (ImageView) activity.findViewById(R.id.foto);
        ratingNota = (SeekBar) activity.findViewById(R.id.nota);

        aluno = new Aluno();
    }

    public Aluno pegaAlunoDoFormulario() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setCaminhoFoto(campoFoto.getContext().getPackageResourcePath());
        aluno.setNota(ratingNota.getProgress());
        return aluno;
    }

    public void colocaAlunoNoFormulario(Aluno alunoUpdate) {
        campoNome.setText(alunoUpdate.getNome());
        campoEndereco.setText(alunoUpdate.getEndereco());
        campoSite.setText(alunoUpdate.getSite());
        campoTelefone.setText(alunoUpdate.getTelefone());
    }
}
