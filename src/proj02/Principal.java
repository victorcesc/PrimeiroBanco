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
public class Principal {
    
    public static void main(String args[]){
        
        PessoaDAO pDao = new PessoaDAO();
        
        Pessoa p = new Pessoa("Juca","1234","juca@email",80,180);
        
        pDao.inserir(p);
        
        
    
    }
    
    
    
    
}
