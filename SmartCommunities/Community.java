import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;
/**
 * Write a description of class Communitie here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Community implements Serializable{
    
    private String name;
    private Map<String, House> houses;
    
    public Community(){
        this.name = "Aveiro";
        this.houses = new HashMap<>();
    }

    public Community(String name, Map<String, House> houses){
        this.name = name;
        this.houses = houses;
    }

    public Community(Community o){
        this.name = o.getName();
        this.houses = o.getHouses();
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
               houses == c.houses; 
    }

    public void addHouse (House house) {
        this.houses.put(house.getId(), house);
    }
}
