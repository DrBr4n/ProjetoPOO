import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.io.Serializable;

public class House implements Serializable{

    private String id;
    private String address;
    private String ownerName;
    private String nif;
    private int deviceCounter;
    private Map<String, SmartDevice> devices;
    private Map<String, Map<String, SmartDevice>> rooms = new HashMap<>();
    private String supplier;

    public House(){
        this.id = "404";
        this.address = "Rua Das Cruzetas";
        this.ownerName = "Antonio Variacoes";
        this.devices = new HashMap<>();
        this.rooms = new HashMap<>();
    }

    public House(String id, String address, String ownerName, String nif){
        this.id = id;
        this.address = address;
        this.ownerName = ownerName;
        this.nif = nif;
        this.devices = new HashMap<>();
        this.rooms = new HashMap<>();
    }

    public House(String id, String nif, String address, String ownerName, Map<String, SmartDevice> devices, Map<String, Map<String, SmartDevice>> rooms){
        this.id = id;
        this.address = address;
        this.ownerName = ownerName;
        this.nif = nif;
        this.devices = devices;
        this.rooms = rooms;
    }   

    public House(House o){
        this.id = o.getId();
        this.nif = o.getNif();
        this.address = o.getAddress();
        this.devices = o.getDevices();
        this.ownerName = o.getOwnerName();
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

    public String getNif(){
        return this.nif;
    }

    public void setNif(String nif){
        this.nif = nif;
    }

    public Map<String, SmartDevice> getDevices(){
        return this.devices;
    }

    public Map<String, SmartDevice> getDevicess(){
        Map<String, SmartDevice> devicesMap = new HashMap<>();
        this.rooms.values().stream().forEach(s -> devices.putAll(s));
        return devicesMap; 
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

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public String getSupplier() {
        return this.supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        House h = (House) o; 
        return id == h.id &&
        address == h.address && 
        ownerName == h.ownerName &&  
        nif == h.nif &&
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
        .append("\nNif: ")
        .append(this.getNif())
        .append("\nRooms: ");
        for (String room : rooms.keySet()) {
            sb.append(room);
            this.getRooms().get(room).values().stream().map(SmartDevice::toString).forEach(sb::append);
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

    public void addDeviceToRoom (String room, SmartDevice device) {
        this.rooms.get(room).put(device.getId(), device);
    }

    public boolean existRoom(String room){
        return this.rooms.containsKey(room);
    }

    public void createDevice(String [] props) {
        SmartDevice device = new SmartDevice();
        props[3] = String.valueOf(this.deviceCounter++);
        //this.addDevice(device.createDevice(props));
        this.addDeviceToRoom(props[1], device.createDevice(props));
    }
}
