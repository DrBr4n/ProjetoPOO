/**
 * Write a description of class SmartAC here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartAC extends SmartDevice {

    private int mode;
    private int temperature;

    public SmartAC() {
        super();
        this.mode = 0;
    }

    public SmartAC(String id, String brand, int dailyConsumption, float installationCost, boolean on, int mode) {
        super(id, brand, dailyConsumption, installationCost, on);
        this.mode = mode;
    }    

    public SmartAC(SmartAC o) {
        super(o);
        this.mode = o.getMode();
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartAC d = (SmartAC) o; 
        return super.equals(d) &&
               this.mode == d.mode && 
               this.temperature == d.temperature; 
    }

    @Override
    public SmartAC clone() {
        return new SmartAC(this);    
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
            .append("\nMode: ")
            .append(this.getMode())
            .append("\nTemperature: ")
            .append(this.getTemperature());

        return sb.toString();
    }
}
