package History;

import java.io.Serializable;

public abstract class HistoryObj implements Serializable {

    public HistoryObj(int id, String Date, String CurrPrice, String PrevPrice, String Name){
        this.id = id;
        this.Date = Date;
        this.CurrPrice = CurrPrice;
        this.PrevPrice = PrevPrice;
        this.Name = Name;
    }

    protected int id;
    protected String Date;
    protected String CurrPrice;
    protected String PrevPrice;
    protected String Name;

    public int getID(){ return id; }

    public void setID(int id){ this.id = id; }

    public String getCurrPrice(){
        return CurrPrice;
    }

    public void setCurrPrice(String currPrice){
        this.CurrPrice = currPrice;
    }

    public String getPrevPrice(){
        return PrevPrice;
    }

    public void setPrevPrice(String prevPrice){
        this.PrevPrice = prevPrice;
    }

    public String getDate(){
        return Date;
    }
    public void setDate(String date){
        this.Date = date;
    }

    public String getName(){
        return Name;
    }

    public void setName(String Name){
        this.Name = Name;
    }

}
