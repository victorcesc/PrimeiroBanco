/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj02;

/**
 *
 * @author aluno
 */
public class Pessoa {
    
    private int idPessoa;
    private String nome;
    private String telefone;
    private String email;
    private double peso;
    private int altura;
    
    
    public Pessoa(){
    }

    public Pessoa(String nome, String telefone, String email, double peso, int altura) {
        //this.idPessoa = idPessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.peso = peso;
        this.altura = altura;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    
    
    
    
    
}
