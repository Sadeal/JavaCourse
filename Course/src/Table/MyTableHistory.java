package Table;

import History.HBase;

import javax.swing.table.*;

public class MyTableHistory extends AbstractTableModel {

    public HBase data;
    public MyTableHistory(HBase base){
        this.data = base;
    }

    @Override
    public int getRowCount() {
        return data.getCount();
    }

    @Override
    public String getColumnName(int column){
        switch (column){
            case 0 : return "ID";
            case 1 : return "Название";
            case 2 : return "Дата изменения стоимости";
            case 3 : return "Стоимость после изменения";
            case 4 : return "Стоимость до изменения";
        }
        return "";
    }

    @Override
    public int getColumnCount(){
        return 5;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return data.getObj(rowIndex).getID();
                case 1:
                    return data.getObj(rowIndex).getName();
                case 2:
                    return data.getObj(rowIndex).getDate();
                case 3:
                    return data.getObj(rowIndex).getCurrPrice();
                case 4:
                    return data.getObj(rowIndex).getPrevPrice();
            }
        return "default";
    }

    public void add(int id,String date, String PrevPrice, String CurrPrice, String Name){
        this.data.insert(id, date, PrevPrice, CurrPrice, Name);
        fireTableDataChanged();
    }

    public void loadDefault(){
        data.loadDefault();
        fireTableDataChanged();
    }

    public void delete(int ind){
        this.data.remove(ind);
        fireTableDataChanged();
    }
}
