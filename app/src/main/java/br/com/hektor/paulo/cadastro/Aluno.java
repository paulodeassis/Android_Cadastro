package br.com.hektor.paulo.cadastro;


import java.io.Serializable;

public class Aluno implements Serializable{
    private long id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String nome,site,telefone,endereco,caminhoFoto;
    private Double nota;

    public  Aluno(){};
    public Aluno(String nome,String site, String telefone,String endereco,String caminhofoto,double nota){
        this.nome=nome;
        this.site=site;
        this.telefone=telefone;
        this.endereco=endereco;
        this.caminhoFoto=caminhofoto;
        this.nota=nota;
    }
    @Override
    public String toString(){
        String vazio="";
        if(nome!=null) {
            return id + " - " + nome;
        }
        return  vazio;
    }

}
