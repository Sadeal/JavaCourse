package Data;

public class Graphic extends Obj {
    private String MaxPrice;
    private String MinPrice;

    public Graphic(String Name, String Price){
        super(Name, Price);
    }

    public String getMaxPrice() {
        return MaxPrice;
    }

    public void setMaxPrice(String MaxPrice) {
        this.MaxPrice = MaxPrice;
    }

    public String getMinPrice() {
        return MinPrice;
    }

    public void setMinPrice(String MinPrice) {
        this.MinPrice = MinPrice;
    }
}
