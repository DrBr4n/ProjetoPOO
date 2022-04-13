
/**
 * Write a description of class Supplier here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Supplier {
    
    private float price;
    private int dailyCost;
    
    public Supplier() {
        this.price = 5;
        this.dailyCost = 50;
    }
    
    public Supplier(float price, int dailyCost){
        this.price = price;
        this.dailyCost = dailyCost;
    }
    
    public Supplier(Supplier o){
        this.price = o.getPrice();
        this.dailyCost = o.getDailyCost();
    }
    
    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public int getDailyCost() {
        return this.dailyCost;
    }

    public void setDailyCost(int dailyCost) {
        this.dailyCost = dailyCost;
    }
    
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        Supplier s = (Supplier) o; 
        return price == s.price && 
               dailyCost == s.dailyCost; 
    }

    public Supplier clone() {
        return new Supplier(this);    
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSupplier:")
            .append("\nPrice: ")
            .append(this.getPrice())
            .append("\nDaily Cost: ")
            .append(this.getDailyCost());
            
        return sb.toString();
    }
}

