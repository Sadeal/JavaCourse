package Data;

public abstract class Obj {

    public Obj(String Name, String Price){
        this.Name = Name;
        this.Price = Price;
    }

    protected String Name;
    protected String Price;

    public String getName(){
        return Name;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public String getPrice(){
        return Price;
    }

    public void setPrice(String Price){
        this.Price = Price;
    }

}
