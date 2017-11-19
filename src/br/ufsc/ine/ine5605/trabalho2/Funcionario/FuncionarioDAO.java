/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.ine.ine5605.trabalho2.Funcionario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 09108881910
 */
public class FuncionarioDAO {
    
    private HashMap<Integer, Funcionario> cacheFuncionarios = new HashMap<>();
    
    private final String fileName = "funcionario.dat";
    
    public FuncionarioDAO() {
        load();
    }
    
    public void put(Funcionario funcionario) {
        cacheFuncionarios.put(funcionario.getMatricula(), funcionario);
        persist();
    }
    
    public Funcionario get(int matricula) {
        return cacheFuncionarios.get(matricula);
    }
    
    public Collection<Funcionario> getList() {
        return cacheFuncionarios.values();
    }
    
    public void remove(Funcionario funcionario) {
        cacheFuncionarios.remove(funcionario.getMatricula());
        persist();
    }
    
    public void persist() {
        try {
            FileOutputStream fOS = new FileOutputStream(fileName);
            ObjectOutputStream oOS = new ObjectOutputStream(fOS);
            
            oOS.writeObject(cacheFuncionarios);
            
            oOS.flush();
            fOS.flush();
            
            oOS.close();
            fOS.close();
            
                    
        } catch (FileNotFoundException ex) {
            persist();
        } catch (IOException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void load() {
        try {
            FileInputStream fIS = new FileInputStream(fileName);
            ObjectInputStream oIS = new ObjectInputStream(fIS);
            
                cacheFuncionarios = (HashMap<Integer, Funcionario>) oIS.readObject();
                
                oIS.close();
                fIS.close();

            
        } catch (FileNotFoundException ex) {
            persist();
        } catch (IOException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
