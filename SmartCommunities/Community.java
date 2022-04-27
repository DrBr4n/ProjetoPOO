import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;

public class Community implements Serializable{

    private String name;
    private int houseCounter;
    private Map<String, House> houses;
    private Map<String, Supplier> suppliers;

    public Community(){
        this.name = "Aveiro";
        this.houses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.houseCounter = 0;
    }

    public Community(String name){
        this.name = name;
        this.houses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.houseCounter = 0;
    }

    public Community(Community o){
        this.name = o.getName();
        this.houses = o.getHouses();
        this.suppliers = o.getSuppliers();
        this.houseCounter = o.getHouseCounter();
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
        houseCounter == c.houseCounter;
    }

    public String createHouse(String[] ids) {
        House house = new House('h' + String.valueOf(this.houseCounter++), ids[0], ids[1]);
        this.houses.put(house.getId(), house);
        return house.getId();
    }

    public int editHouse(String houseId, String [] props) {
        House house = houses.get(houseId);
        int result = 0;
        switch (Integer.parseInt(props[0])) {
            case 0:
                result = 2;
                break;
            case 1:
                house.addRoom(props[1]);
                break;
            case 2:
                if (house.existRoom(props[1])) {
                    house.createDevice(props);
                } else {
                    result = 1;
                }
                break;
            default:
                result = 3;
                break;
        }
        return result;
    }

    public void createSupplier(String[] props) {
        Supplier s = new Supplier(props[0], Float.parseFloat(props[1]), Integer.parseInt(props[2]));
        suppliers.put(s.getName(), s);
    }

    public void viewHouses() {
        houses.values().stream().map(House::toString).forEach(System.out::println);
    }

    public void viewSuppliers() {
        suppliers.values().stream().map(Supplier::toString).forEach(System.out::println);
    }

}
