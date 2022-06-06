package Table;

import Data.*;
import UI.HistoryWindow;
import db.DBWorker;

import javax.swing.table.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class MyTableModel extends AbstractTableModel {

    public Base data;
    public MyTableModel(Base base){ this.data = base; }

    @Override
    public int getRowCount() {
        return data.getCount();
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0 : return "№";
            case 1 : return "Название";
            case 2 : return "Стоимость";
            case 3 : return "Магазин";
        }
        return "";
    }

    @Override
    public int getColumnCount(){
        return 4;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        }
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
        if(columnIndex == 1) {
            return true;
        }
        else
            return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return rowIndex+1;//data.getObj(rowIndex).getID();
            case 1: return data.getObj(rowIndex).getName();
            case 2: return data.getObj(rowIndex).getPrice();
            case 3: {
                if(data.getObj(rowIndex).getMarket()==0) return "Wildberries";
                if(data.getObj(rowIndex).getMarket()==1) return "Ozon";
                if(data.getObj(rowIndex).getMarket()==2) return "Яндекс.Маркет";
                if(data.getObj(rowIndex).getMarket()==3) return "citilink";
            }
        }
        return "default";
    }

    public void refreshPrice(){
        try {
            for (int i = 0; i < this.getRowCount(); i++) {
                if (!PriceParse.Parse(data.getObj(i).getURL(), data.getObj(i).getMarket()).equals(data.getObj(i).getPrice())) { // если новая цена с сайта, не равна той, что уже есть
                    HistoryWindow.add(data.getObj(i).getID(), LocalDate.now().toString(), PriceParse.Parse(data.getObj(i).getURL(), data.getObj(i).getMarket()), data.getObj(i).getPrice(), data.getObj(i).getName()); // добавляем его в историю
                    DBWorker.addHistory(data.getObj(i).getID(), LocalDate.now().toString(), PriceParse.Parse(data.getObj(i).getURL(), data.getObj(i).getMarket()), data.getObj(i).getPrice(), data.getObj(i).getName(), data.getObj(i).getID());
                    data.getObj(i).setPrice(PriceParse.Parse(data.getObj(i).getURL(), data.getObj(i).getMarket()));      // и изменяем цену в главной таблице
                    DBWorker.setPrice(i,PriceParse.Parse(data.getObj(i).getURL(), data.getObj(i).getMarket()));
                }
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int ind){
        this.data.remove(ind);
        fireTableDataChanged();
    }

    public void add(int id, String Name, String Price, String URL, int Market){
        this.data.insert(id, Name, Price, URL, Market);
        try {
            DBWorker.addObj(id, Name, Price, URL, Market);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fireTableDataChanged();
    }

    public void save(File path){
        data.save(path);
    }

    public void saveDefault(){
        //data.saveDefault();
        try {
            for(int i = 0; i<data.getCount(); i++)
                DBWorker.addObj(data.getObj(i).getID(), data.getObj(i).getName(), data.getObj(i).getPrice(), data.getObj(i).getURL(), data.getObj(i).getMarket());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void load(File path){
        data.load(path);
        fireTableDataChanged();
    }

    public void loadDefault(){
        data.loadDefault();
        fireTableDataChanged();
    }

    public void refresh(){
        fireTableDataChanged();
    }
}
