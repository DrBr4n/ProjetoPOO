import java.util.Map;
import java.util.HashMap;

/**
 * Write a description of class House here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class House {

    private String id;
    private String address;
    private String ownerName;
    private Map<String, SmartDevice> devices;
    private Map<String, Map<String, SmartDevice>> rooms = new HashMap<>();
    
    public House(){
        this.id = "h0";
        this.address = "Rua Das Cruzetas";
        this.ownerName = "Antonio Variacoes";
        this.devices = new HashMap<>();
        this.rooms = new HashMap<>();
    }
    
    public House(String id, String address, String ownerName){
        this.id = id;
        this.address = address;
        this.ownerName = ownerName;
        this.devices = new HashMap<>();
        this.rooms = new HashMap<>();
    }
    
    public House(String id, String address, String ownerName, Map<String, SmartDevice> devices, Map<String, Map<String, SmartDevice>> rooms){
        this.id = id;
        this.address = address;
        this.ownerName = ownerName;
        this.devices = devices;
        this.rooms = rooms;
    }   
    
    public House(House o){
        this.id = o.getId();
        this.address = o.getAddress();
        this.ownerName = o.getOwnerName();
        this.devices = o.getDevices();
        this.rooms = o.getRooms();
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }

    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getOwnerName(){
        return this.ownerName;
    }
    
    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }
    
    public Map<String, SmartDevice> getDevices(){
       return this.devices;
    }
    
    public void setDevices(Map<String, SmartDevice> devices){
        this.devices = devices;
    }
    
    public Map<String, Map<String, SmartDevice>> getRooms(){
       return this.rooms;
    }
    
    public void setRooms(Map<String, Map<String, SmartDevice>> rooms){
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        House h = (House) o; 
        return id == h.id &&
               address == h.address && 
               ownerName == h.ownerName &&
               devices == h.devices &&
               rooms == h.rooms; 
    }
   
    @Override
    public House clone() {
        return new House(this);    
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nIdentifier: ")
            .append(this.getId())
            .append("\nAddress: ")
            .append(this.getAddress())
            .append("\nOwner's Name: ")
            .append(this.getOwnerName())
            .append("\nDevices: ");
            for (SmartDevice o : devices.values()) {
                 sb.append(o.toString());
            }
            sb.append("\nRooms: ");
            for (String room : rooms.keySet()) {
                sb.append(room);
           }
        return sb.toString();
    }

    public boolean existsDevice(String deviceId) {
        return this.devices.keySet().contains(deviceId);
    }
    
    public void addDevice(SmartDevice o) {
        this.devices.put(o.getId(), o);
    }

    public void setDeviceOn(String deviceId) {
        this.devices.get(deviceId).turnOn();
    }

    public void setDeviceOff(String deviceId) {
        this.devices.get(deviceId).turnOn();
    }

    public void setAllOn() {
        this.devices.values().forEach(SmartDevice::turnOn);
    }

    public void setAllOff() {
        this.devices.values().forEach(SmartDevice::turnOff);
    }

    public void addRoom(String room) {
        Map<String, SmartDevice> emptyMap = new HashMap<>();
        this.rooms.put(room, emptyMap);
    }

    public void addDeviceToRoom (String room, String deviceId) {
        this.rooms.get(room).put(deviceId, devices.get(deviceId));
    }
    
    public boolean existRoom(String room){
        return this.rooms.containsKey(room);
    }



}
