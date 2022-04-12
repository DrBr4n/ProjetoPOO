import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class House here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class House {
    private String address;
    private String ownerName;
    private Map<String, SmartDevice> devices;
    private Map<String, List<String>> rooms;
    
    public House(){
        this.address = "";
        this.ownerName = "";
        this.devices = new HashMap<>();
        this.rooms = new HashMap<>();
    }
    
    public House(String address, String ownerName, Map<String, SmartDevice> devices, Map<String, List<String>> rooms){
        this.address = address;
        this.ownerName = ownerName;
        this.devices = devices;
        this.rooms = rooms;
    } 
    
    public House(House h){
        this.address = h.getAddress();
        this.ownerName = h.getOwnerName();
        this.devices = h.getDevices();
        this.rooms = h.getRooms();
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getOwnerName(){
        return this.address;
    }
    
    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }
    
    public Map<String, SmartDevice> getDevices(){
       return this.devices;
       //return this.devices.values().stream().collect(Collectors.toMap(id.SmartDevice :: getId, id.SmartDevice::clone));
    }
    
    public void setDevices(Map<String, SmartDevice> devices){
        this.devices = devices;
    }
    
    public Map<String, List<String>> getRooms(){
       return this.rooms;
    }
    
    public void setRooms(Map<String, List<String>> rooms){
        this.rooms = rooms;
    }
    
    /*
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        House h = (House) o; 
        return price == s.price && 
               dailyCost == s.dailyCost; 
    }
    */
   
    public House clone() {
        return new House(this);    
    }
    
    /*
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nHouse:")
            .append("\nAddress: ")
            .append(this.getAddress())
            .append("\nOwner's Name: ")
            .append(this.getOwnerName());
        for(SmartDevice sd : devices){
            
        }
            
        return sb.toString();
    }
    */
}
