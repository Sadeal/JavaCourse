package History;

import db.DBWorker;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class HBase implements Serializable {
    public ArrayList<HistoryObj> Hobjects = new ArrayList<>();

    public HBase(){
    }

    public int getCount(){
        return this.Hobjects.size();
    }

    public HistoryObj getObj(int index){
        return Hobjects.get(index);
    }

    public void remove(int ind){
        this.Hobjects.remove(ind);
    }

    public void insert(int id, String Date, String CurrPrice, String PrevPrice, String Name){
        this.Hobjects.add(new ProductHistory(id, Date, CurrPrice, PrevPrice, Name));
    }

    public void loadDefault(){
        try {
            this.Hobjects = DBWorker.getHistory();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
