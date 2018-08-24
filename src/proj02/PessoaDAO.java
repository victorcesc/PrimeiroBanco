/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO == data acess object
 * @author aluno
 */
public class PessoaDAO {
    
 
       public boolean inserir(Pessoa p) {
        try (Connection conexao = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Pessoa (nome, telefone, peso, altura, email) VALUES "
                    + "(?,?,?,?,?)";
           
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, p.getNome());
                stmt.setString(2, p.getTelefone());
                stmt.setDouble(3, p.getPeso());
                stmt.setInt(4, p.getAltura());
                stmt.setString(5, p.getEmail());
               
                stmt.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
 
       
       public List<Pessoa> listarTodas(){
           List<Pessoa> pessoas = new ArrayList<>();
           
           try(Connection conexao = ConnectionFactory.getConnection()){
              
               //comando do banco de dados para selecionar algo ou alguem na table
               String sql = "SELECT * from Pessoa";
               
               try(PreparedStatement stmt = conexao.prepareStatement(sql);
                       ResultSet rs  = stmt.executeQuery()){
                       while(rs.next()){
                       Pessoa c = new Pessoa(
                                rs.getString("nome"),
                                rs.getString("telefone"),
                                rs.getString("email"),
                                rs.getDouble("peso"),
                                rs.getInt("altura"));
                       
                                           
                       c.setIdPessoa(rs.getInt("idPessoa"));
                       pessoas.add(c);
                                      
                       }                 
                       
                       }
               }catch (SQLException ex){
                   System.out.println(ex.toString());
               }
               return pessoas;
           }
           
           
           
       
       
       
       }
       
       

    

