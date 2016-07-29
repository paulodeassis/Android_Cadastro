package br.com.hektor.paulo.cadastro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Paulo on 13.07.2016.
 */
public class FormularioActivity extends Activity {
    private FormularioHelper helper;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);
        final Button botao = (Button)findViewById(R.id.btnsalvar);
        helper = new FormularioHelper(this);
        dbHelper = new DBHelper(FormularioActivity.this);

        final Aluno alunoUpdate=(Aluno)getIntent().getSerializableExtra(Extra.ALUNO_SELECIONADO);

        if(alunoUpdate!=null){
            helper.colocaAlunoNoFormulario(alunoUpdate);
            botao.setText("Alterar");
        }

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = helper.pegaAlunoDoFormulario();
                final AlunoDAO dao = new AlunoDAO(dbHelper);

                if(alunoUpdate!=null) {
                    aluno.setId(alunoUpdate.getId());
                    dao.update(aluno);
                }
                else {
                    dao.insert(aluno);
                    Toast.makeText(FormularioActivity.this, "Aluno: " + aluno.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}
