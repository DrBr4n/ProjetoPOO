/**
 * Write a description of class SmartDevice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartDevice {

    private String id;
    private String brand;
    private int dailyConsumption;
    private float installationCost;
    private boolean on;

    public SmartDevice() {
        this.id = "404";
        this.brand = "LG";
        this.dailyConsumption = 400;
        this.installationCost = 50;
        this.on = false;
    }

    public SmartDevice(String id, String brand) {
        this.id = id;
        this.brand = brand;
        this.dailyConsumption = 0;
        this.installationCost = 0.0f;
        this.on = false;
    }
    
    public SmartDevice(String id, String brand, int dailyConsumption, float installationCost, boolean on) {
        this.id = id;
        this.brand = brand;
        this.dailyConsumption = dailyConsumption;
        this.installationCost = installationCost;
        this.on = on;
    }

    public SmartDevice(SmartDevice o) {
        this.id = o.getId();
        this.brand = o.getBrand();
        this.dailyConsumption = o.getDailyConsumption();
        this.installationCost = o.getInstallationCost();
        this.on = o.getOn();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDailyConsumption() {
        return this.dailyConsumption;
    }

    public void setDailyConsumption(int dailyConsumption) {
        this.dailyConsumption = dailyConsumption;
    }

    public float getInstallationCost() {
        return this.installationCost;
    }

    public void setInstallationCost(float installationCost) {
        this.installationCost = installationCost;
    }

    public boolean getOn() {
        return this.on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public void turnOn(){
        this.on = true;
    }

    public void turnOff(){
        this.on = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartDevice d = (SmartDevice) o; 
        return id == d.id && 
               brand == d.brand &&
               dailyConsumption == d.dailyConsumption &&               
               installationCost == d.installationCost && 
               on == d.on; 
    }

    @Override
    public SmartDevice clone() {
        return new SmartDevice(this);    
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSmartDeviceId: ")
            .append(this.getId())
            .append("\nBrand: ")
            .append(this.getBrand())
            .append("\nDailyConsumption: ")
            .append(this.getDailyConsumption())
            .append("\nInstallationCost: ")
            .append(this.getInstallationCost())
            .append("\nOn: ")
            .append(this.getOn());

        return sb.toString();
    }
}
