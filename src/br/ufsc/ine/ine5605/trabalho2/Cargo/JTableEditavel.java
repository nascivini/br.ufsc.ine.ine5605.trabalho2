/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Cargo;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Viinicius
 */
public class JTableEditavel extends JTable {
    
    public JTableEditavel(DefaultTableModel modelo){
        super(modelo);
    }
    
    @Override
    public boolean isCellEditable(int row, int column){
        if(column == 2){
            return true;
        }
        return true;
    }
}
