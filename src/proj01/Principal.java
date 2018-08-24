/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj01;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class Principal {

    public static void criarTabela(Connection conexao) throws SQLException {
        // Pessoa: nome, telefone, peso, altura, email
        String sql = "CREATE TABLE Pessoa("
                + "idPessoa INTEGER PRIMARY KEY NOT NULL,"
                + "nome VARCHAR(30),telefone VARCHAR(30),"
                + "peso REAL, altura INTEGER, email VARCHAR(50))";
        Statement stmt = conexao.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    public static void inserirPessoa(Connection conexao) throws SQLException {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Entre com o nome: ");
        String nome = teclado.nextLine();
        System.out.println("Entre com o telefone: ");
        String telefone = teclado.nextLine();
        System.out.println("Entre com o email: ");
        String email = teclado.nextLine();
        System.out.println("Entre com o peso: ");
        double peso = teclado.nextDouble();
        System.out.println("Entre com o altura: ");
        int altura = teclado.nextInt();

        String sql = "INSERT INTO Pessoa("
                + "nome, telefone, peso, altura, email)"
                + "VALUES('" + nome + "',"
                + "'" + telefone + "',"
                + "'" + peso + "',"
                + "'" + altura + "',"
                + "'" + email + "'"
                + ")";

        Statement stmt = conexao.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    public static void listarTodos(Connection conexao) throws SQLException {
        String sql = "SELECT * FROM Pessoa";
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println(String.format("|%-5s|%-25s|%-25s|%-10s|%-10s|%-2s|","ID","Nome","Telefone","Peso","Altura","Email"));
        
        
        while (rs.next()) {
            System.out.println(String.format("|%-5d|%-25s|%-25s|%-10f|%-10s|%-2s|",
                rs.getInt("idPessoa"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getDouble("peso"),
                rs.getInt("altura"),
                rs.getString("email")));            
        }
        rs.close();
        stmt.close();

    }

    public static int listarPessoa(Connection conexao, String email) throws SQLException {        
        int idPessoa = 0;
        String sql = "SELECT * FROM Pessoa WHERE email='" + email + "'";

        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(String.format("|%-5d|%-25s|%-25s|%-10f|%-10s|%-2s|",
                rs.getInt("idPessoa"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getDouble("peso"),
                rs.getInt("altura"),
                rs.getString("email")));
                idPessoa = rs.getInt("idPessoa");
        }
        rs.close();
        stmt.close();
        return idPessoa;
    }
    public static void updatePessoa(Connection conexao, int idPessoa, String nome, float peso, int altura, String email, String telefone)throws SQLException{
        
        String sql = "UPDATE Pessoa SET nome = '" + nome + "', email = '" + email + "', peso = " + peso + ", altura = "+ altura +", telefone = '"+ telefone +"' WHERE idPessoa = " + idPessoa;
        
        Statement stmt = conexao.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        
    }
    public static void main(String[] args) {
        //Scanner teclado = new Scanner(System.in);
        try {
            Connection conexao = DriverManager.getConnection("jdbc:sqlite:meubanco.db");
            //criarTabela(conexao);
            Principal.listarTodos(conexao);
            
            System.out.println("Digite o email: ");
            Scanner teclado = new Scanner(System.in);
            String email = teclado.nextLine();            
            int idPessoa = Principal.listarPessoa(conexao, email);
            if(idPessoa != 0){
                System.out.println("Digite o novo nome da pessoa:");
                String novoNome = teclado.nextLine();
                
                System.out.println("Digite o novo telefone da pessoa:");
                String novoTelefone = teclado.nextLine();
                
                System.out.println("Digite o novo e-mail da pessoa:");
                String novoEmail = teclado.nextLine();

                System.out.println("Digite o novo peso da pessoa:");
                float novoPeso = teclado.nextFloat();

                System.out.println("Digite a nova altura da pessoa:");
                int novaAltura = teclado.nextInt();
                
                Principal.updatePessoa(conexao, idPessoa, novoNome, novoPeso, novaAltura, novoEmail, novoTelefone);
            }else{
                System.out.println("Pessoa não cadastrada.");
            }
            
            
            
            
            Principal.listarTodos(conexao);
            
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
