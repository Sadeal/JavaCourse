package Data;

import db.DBWorker;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Base implements Serializable {
    public ArrayList<Obj> objects = new ArrayList<>();

    public Base(){
    }

    public int getCount(){
        return this.objects.size();
    }

    public Obj getObj(int index){
        return objects.get(index);
    }

    public void remove(int ind){
        this.objects.remove(ind);
    }

    public void insert(int id, String Name, String Price, String URL, int Market){
        this.objects.add(new Product(id, Name, Price, URL, Market));
    }

    public void save(File path){
        try {
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(objects);
                oos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    } // End of saveDatabase

    public void load(File path){
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.objects = (ArrayList<Obj>)ois.readObject();
            ois.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadDefault(){
        try {
            this.objects = DBWorker.getObj();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
