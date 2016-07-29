package br.com.hektor.paulo.cadastro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Paulo on 11.07.2016.
 */
public class ListaAlunosActivity extends AppCompatActivity {
    private ListView lst;
    private DBHelper dbHelper=new DBHelper(ListaAlunosActivity.this);
    private Aluno aluno;
    AlunoDAO dao = new AlunoDAO(dbHelper);

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);

        List<Aluno>alunos=dao.listarAlunos();

        lst = (ListView) findViewById(R.id.lstView);
        final ArrayAdapter<Aluno>adapter=new ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1,alunos);

            lst.setAdapter(adapter);
        registerForContextMenu(lst);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aluno alunoUpdate=(Aluno)adapterView.getItemAtPosition(i);

                Intent irParaOFormulario=new Intent(ListaAlunosActivity.this,FormularioActivity.class);
                irParaOFormulario.putExtra(Extras.ALUNO_SELECIONADO,alunoUpdate);
                startActivity(irParaOFormulario);
                //Toast.makeText(ListaAlunosActivity.this,"Aluno "+adapterView.getItemAtPosition(i),Toast.LENGTH_LONG).show();
            }
        });

        //Click longo no ítem.
        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                 aluno = (Aluno)adapterView.getItemAtPosition(i);
                //Toast.makeText(ListaAlunosActivity.this,"Aluno "+adapterView.getItemAtPosition(i), Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }
    @Override
    public void onResume(){
        super.onResume();
        this.carregarLista();
    }
    public void carregarLista(){
        AlunoDAO dao = new AlunoDAO(dbHelper);
        List<Aluno> alunos = dao.listarAlunos();

        lst = (ListView) findViewById(R.id.lstView);
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);

        lst.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_alunos,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.novo:
                Intent irParaFormulario = new Intent(this,FormularioActivity.class);
                startActivity(irParaFormulario);
                break;
            case R.id.mpMapa:
                Toast.makeText(ListaAlunosActivity.this,"Mapa", Toast.LENGTH_LONG);
                break;
            case R.id.env_alunos:
                Toast.makeText(ListaAlunosActivity.this,"Enviar Alunos",Toast.LENGTH_LONG);
                break;
            case R.id.rec_alunos:
                Toast.makeText(ListaAlunosActivity.this,"Receber Alunos", Toast.LENGTH_LONG);
                break;
            case R.id.pfPreferencias:
                Toast.makeText(ListaAlunosActivity.this,"Preferencias",Toast.LENGTH_LONG);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        MenuItem ligar = menu.add("Ligar");
        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Uri telefoneAluno=Uri.parse("tel:"+aluno.getTelefone());
                Intent intencaoLigar = new Intent(Intent.ACTION_CALL);
                startActivity(intencaoLigar.setData(telefoneAluno));
                return false;
            }
        });
        menu.add("Enviar SMS");
        menu.add("Achar no Mapa");
        MenuItem navegarNoSite = menu.add("Navegar no site");
        navegarNoSite.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                return false;
            }
        });
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                dao.delete(aluno);
                carregarLista();
                return false;
            }
        });
        menu.add("Enviar E-mail");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.deletar:

                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
  /*  public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        menu.add("Ligar");
        menu.add("Enviar SMS");
        menu.add("Achar no Mapa");
        menu.add("Navegar no site");
        //menu.add("Deletar");
        menu.add("Enviar E-mail");

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                dao.delete();
                return  false;
            }
        });
    }*/
}
