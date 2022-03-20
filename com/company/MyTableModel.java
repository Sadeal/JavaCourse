package com.company;

import Data.*;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

    private Base data;
    public MyTableModel(Base base){
        this.data = base;
    }

    @Override
    public int getRowCount() {
        return data.getCount();
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0 : return "Название";
            case 1 : return "Цена";
        }
        return "";
    }

    @Override
    public int getColumnCount(){
        return 2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        if(columnIndex == 0) {
            data.getObj(rowIndex).setName((String)aValue);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 0) {
            return true;
        }
        else
            return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return data.getObj(rowIndex).getName();
            case 1: return data.getObj(rowIndex).getPrice();
        }
        return "default";
    }

    public void delete(int ind){
        this.data.remove(ind);
        fireTableDataChanged();
    }

    public void add(String Name, String Price){
        this.data.insert(Name, Price);
        fireTableDataChanged();
    }
}
