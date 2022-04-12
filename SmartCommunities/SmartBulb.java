/**
 * Write a description of class SmartBulb here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartBulb extends SmartDevice {

    private int mode;
    private int size;

    public SmartBulb() {
        super();
        this.mode = 0;
        this.size = 8;
    }

    public SmartBulb(String id, String brand, int dailyConsumption, float installationCost, boolean on, int mode, int size) {
        super(id, brand, dailyConsumption, installationCost, on);
        this.mode = mode;
        this.size = size;
    }

    public SmartBulb(SmartBulb o) {
        super(o);
        this.mode = o.getMode();
        this.size = o.getSize();
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartBulb d = (SmartBulb) o; 
        return super.equals(d) &&
               this.mode == d.mode && 
               this.size == d.size; 
    }

    @Override
    public SmartBulb clone() {
        return new SmartBulb(this);    
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
            .append("\nMode: ")
            .append(this.getMode())
            .append("\nSize: ")
            .append(this.getSize());

        return sb.toString();
    }


}
