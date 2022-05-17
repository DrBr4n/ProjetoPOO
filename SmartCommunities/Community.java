import java.util.Map;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Community implements Serializable{

    private String name;
    private int houseCounter;
    private int deviceCounter;
    private Map<String, House> houses;
    private Map<String, Supplier> suppliers;
    //adicionei em todo o lado
    private LocalDateTime data;

    public Community(){
        this.name = "Aveiro";
        this.houses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.houseCounter = 0;
        this.deviceCounter = 0;
        this.data = LocalDateTime.now();
    }

    public Community(String name){
        this.name = name;
        this.houses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.houseCounter = 0;
        this.deviceCounter = 0;
        this.data = LocalDateTime.now();
    }

    public Community(Community o){
        this.name = o.getName();
        this.houses = o.getHouses();
        this.suppliers = o.getSuppliers();
        this.houseCounter = o.getHouseCounter();
        this.deviceCounter = o.getDeviceCounter();
        this.data = o.getData();
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

    public LocalDateTime getData(){
        return this.data;
    }

    public void setData(LocalDateTime data){
        this.data = data;
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
        sb.append("\nData: ")
        .append(this.getData());
        return sb.toString();
    }
    /*
    //tens de mudar isto, porque o supplier ja nao e string
    public String toLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("Community:").append(this.getName()).append("\n");
        
        //this.getSuppliers().values().stream().map(Supplier::toLog).forEach(sb::append);
        this.getHouses().values().stream().map(House::toLog).forEach(sb::append);
        //Nao sei se afeta os logs:
        //sb.append("\nData: ").append(this.getData());
        return sb.toString();
    }
    */

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
        data == c.data;
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


}
