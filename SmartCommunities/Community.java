import java.util.Map;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;

public class Community implements Serializable{

    private String name;
    private int houseCounter;
    private int deviceCounter;
    private Map<String, House> houses;
    private Map<String, Supplier> suppliers;
    private LocalDate date;
    private LocalDate lastDate;

    public Community(){
        this.name = "Aveiro";
        this.houses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.houseCounter = 0;
        this.deviceCounter = 0;
        this.date = LocalDate.now();
    }

    public Community(String name){
        this.name = name;
        this.houses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.houseCounter = 0;
        this.deviceCounter = 0;
        this.date = LocalDate.now();
    }

    public Community(Community o){
        this.name = o.getName();
        this.houses = o.getHouses();
        this.suppliers = o.getSuppliers();
        this.houseCounter = o.getHouseCounter();
        this.deviceCounter = o.getDeviceCounter();
        this.date = o.getDate();
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Map<String,House> getHouses(){
        return this.houses;
    }

    public void setHouses(Map<String,House> houses){
        this.houses = houses;
    }

    public Map<String,Supplier> getSuppliers() {
        return this.suppliers;
    }

    public void setSuppliers(Map<String,Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public int getHouseCounter() {
        return this.houseCounter;
    }

    public void setHouseCounter(int houseCounter) {
        this.houseCounter = houseCounter;
    }

    public int getDeviceCounter() {
        return this.deviceCounter;
    }

    public void setDeviceCounter(int deviceCounter) {
        this.deviceCounter = deviceCounter;
    }
    
    public LocalDate getDate(){
        return this.date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalDate getLastDate(){
        return this.lastDate;
    }

    public void setLastDate(LocalDate lastDate){
        this.lastDate = lastDate;
    }
    
    @Override
    public Community clone(){
        return new Community(this);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nName: ")
        .append(this.getName())
        .append("\nHouses: ");
        this.getHouses().values().stream().map(House::toString).forEach(sb::append);
        sb.append("\nDate: ")
        .append(this.getDate());
        return sb.toString();
    }
    
    public String toLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("Community:").append(this.getName()).append("\n");
        sb.append("Date: ").append(this.getDate()).append("\n");
        this.getSuppliers().values().stream().map(Supplier::toLog).forEach(sb::append);
        this.getHouses().values().stream().map(House::toLog).forEach(sb::append);
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        Community c = (Community) o; 
        return name == c.name &&
        houses == c.houses && 
        suppliers == c.suppliers &&
        houseCounter == c.houseCounter &&
        deviceCounter == c.deviceCounter &&
        date == c.date;
    }

    public void addHouse(House house) {
        this.houses.put(house.getId(), house);
    }

    public void addSupplier(Supplier supplier) {
        this.suppliers.put(supplier.getName(), supplier);
    }

    public void increaseHouseCounter() {
        this.houseCounter += 1;
    }

    public void increaseDeviceCounter() {
        this.deviceCounter += 1;
    }

    public String[] generateReceipts() {
        String[] ret = new String[getHouseCounter()];
        int index = 0;

        Iterator<House> iterator = getHouses().values().iterator();
        while (iterator.hasNext()) {
            House house = iterator.next();
            StringBuilder sb = new StringBuilder();
            sb.append("HouseId: " + house.getId() + "\n")
            .append("Consumption: " + String.valueOf(house.calcConsumption()) + "\n")
            .append("Cost: " + String.valueOf(house.calcConsumption() * house.getSupplier().getPrice()) + "\n")
            .append("Period: " + String.valueOf(lastDate) + " to " + String.valueOf(date) + "\n")
            .append("Supplier: " + house.getSupplier().getName() + "\n");
            ret[index] = sb.toString();
            index++; 
        }
        return ret;
    }
}
