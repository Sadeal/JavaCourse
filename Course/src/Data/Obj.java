package Data;

import java.io.Serializable;
import java.util.Date;

public abstract class Obj implements Serializable {

    public Obj(int id, String Name, String Price, String URL, int Market){
        this.id = id;
        this.Name = Name;
        this.Price = Price;
        this.URL = URL;
        this.Market = Market;
    }

    protected int id;
    protected String Name;
    protected String Price;
    protected String URL;
    protected int Market;

    public int getID(){ return id; }

    public void setID(int id){ this.id = id;}

    public String getName(){ return Name; }

    public void setName(String Name){
        this.Name = Name;
    }

    public String getPrice(){
        return Price;
    }

    public void setPrice(String Price){
        this.Price = Price;
    }

    public String getURL() { return URL; }

    public void setURL(String URL) { this.URL = URL; }

    public int getMarket() { return Market; }

    public void setMarket(int Market) { this.Market = Market; }

    public Date getDate(){
        java.util.Date date = new java.util.Date();
        return date;
    }

}
