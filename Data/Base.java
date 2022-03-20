package Data;

import java.util.ArrayList;

public class Base {
    private ArrayList<Obj> objects = new ArrayList<>();

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

    public void insert(String Name, String Price){
        this.objects.add(new Product(Name, Price));
    }
}
