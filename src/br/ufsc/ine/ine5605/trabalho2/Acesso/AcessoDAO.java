
package br.ufsc.ine.ine5605.trabalho2.Acesso;

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
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class AcessoDAO{
    
    public Integer proximoID = 0;
    private HashMap<Integer, Acesso> cacheAcessos = new HashMap<>();
    private final String fileName = "acesso.dat"; 
    
    public AcessoDAO(){
        this.load();
    }
    
    public void put(Acesso acesso){
        cacheAcessos.put(this.proximoID, acesso);
        this.proximoID++;
        this.persist();
    }
    
    public Acesso get(Integer id){
        return cacheAcessos.get(id);
    }
    
    public Collection<Acesso> getList(){
        return cacheAcessos.values();
    }

    public void remove(Acesso acesso) {
        this.cacheAcessos.remove(acesso);
        this.persist();
    }
    
    private void persist(){
        
        try {
            FileOutputStream fout = new FileOutputStream(this.fileName);
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            
            oo.writeObject(cacheAcessos);
            
            oo.flush();
            fout.flush();
            
            oo.close();
            fout.close();
         
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(AcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(AcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void load(){
        
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream oo = new ObjectInputStream(fis);
            
            cacheAcessos = (HashMap<Integer, Acesso>)oo.readObject();
            this.proximoID = cacheAcessos.size(); 
            
            oo.close();
            fis.close();
        } 
        //Marco, removi o log apenas para não dar erro no início do programa :D
        catch (FileNotFoundException ex) {
            System.out.println("Ainda não há acessos cadastrados.");
        } 
        catch (IOException ex) {
            Logger.getLogger(AcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(AcessoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
