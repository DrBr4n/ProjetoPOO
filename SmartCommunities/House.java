import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;

public class House implements Serializable{

    private String id;
    private String address;
    private String ownerName;
    private String nif;
    private Supplier supplier;
    private Map<String, Map<String, SmartDevice>> rooms;//= new HashMap<>();

    public House() {
        this.id = "404";
        this.address = "Rua Das Cruzetas";
        this.ownerName = "Antonio Variacoes";
        this.nif = "999999999";
        this.supplier = new Supplier();
        this.rooms = new HashMap<>();
    }

    public House(String id, String address, String ownerName, String nif, String supplier){
        this.id = id;
        this.address = address;
        this.ownerName = ownerName;
        this.nif = nif;
        this.supplier = new Supplier();
        this.rooms = new HashMap<>();
    }

    public House(String id, String nif, String address, String ownerName, Map<String, Map<String, SmartDevice>> rooms, Supplier supplier){
        this.id = id;
        this.address = address;
        this.ownerName = ownerName;
        this.supplier = supplier;
        this.rooms = rooms;
    }   

    public House(House o){
        this.id = o.getId();
        this.address = o.getAddress();
        this.ownerName = o.getOwnerName();
        this.nif = o.getNif();
        this.supplier = o.getSupplier();
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
    
    public void setSupplier(Supplier supplier) {
        this.supplier = new Supplier(supplier);
    }
     
    public Supplier getSupplier() {
        return this.supplier;
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
        nif == h.nif &&
        supplier == h.supplier &&
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
        .append("\nSupplier: ")
        .append(this.getSupplier().toString())
        .append("\nRooms: ");
        for (String room : rooms.keySet()) {
            sb.append(room);
            this.getRooms().get(room).values().stream().map(SmartDevice::toString).forEach(sb::append);
        }
        return sb.toString();
    }

    public String toLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("House:")
            .append(this.getId() + ",")
            .append(this.getAddress() + ",")
            .append(this.getOwnerName() + ",")
            .append(this.getNif() + ",")
            .append(this.getSupplier().getName())
            .append("\n");
        for (String room : this.getRooms().keySet()) {
            sb.append("Room:").append(room).append("\n");
            this.getRooms().get(room).values().stream().map(SmartDevice::toLog).forEach(sb::append);
        }
        return sb.toString();
    }
    
    public Map<String, SmartDevice> getDevices(){
        Map<String, SmartDevice> devicesMap = new HashMap<>();
        this.rooms.values().stream().forEach(s -> devicesMap.putAll(s));
        return devicesMap; 
    }

    public boolean existsDevice(String deviceId) {
        return this.rooms.values().stream().anyMatch(room -> room.keySet().contains(deviceId));
    }

    public void turnDeviceOn(String deviceId) {
        this.rooms.values().stream().forEach(room -> room.get(deviceId).turnOn());
        //this.rooms.values().forEach(room -> room.get(deviceId).turnOn());
    }

    public void turnDeviceOff(String deviceId) {
        this.rooms.values().stream().forEach(room -> room.get(deviceId).turnOff());
        //this.rooms.values().forEach(room -> room.get(deviceId).turnOff());
    }

    public void turnRoomOn(String room) {
        this.rooms.get(room).values().stream().forEach(SmartDevice::turnOn);
    }

    public void turnRoomOff(String room) {
        this.rooms.get(room).values().stream().forEach(SmartDevice::turnOff);
    }

    public void turnAllOn() {
        this.rooms.values().stream().forEach(room -> room.values().stream().forEach(SmartDevice::turnOn));
        //this.rooms.values().forEach(room -> room.values().forEach(SmartDevice::turnOn));
    }

    public void turnAllOff() {
        this.rooms.values().stream().forEach(room -> room.values().stream().forEach(SmartDevice::turnOff));
        //this.rooms.values().forEach(room -> room.values().forEach(SmartDevice::turnOff));
    }

    public boolean existRoom(String room){
        return this.rooms.containsKey(room);
    }

    public void addRoom(String room) {
        Map<String, SmartDevice> emptyMap = new HashMap<>();
        this.rooms.put(room, emptyMap);
    }

    public void addDeviceToRoom (String room, SmartDevice device) {
        this.rooms.get(room).put(device.getId(), device);
    }

    public float calcConsumption(){
        float consum = 0;
        //Map<String, SmartDevice> devices = (Map<String, SmartDevice>) this.rooms.values();
        Map<String, SmartDevice> devices = this.getDevices();
        for(SmartDevice sd: devices.values()){
            consum += (this.supplier.getPrice() * sd.getDailyConsumption() * (1+this.supplier.getTax()))*0.9;
        }
        return consum;
    }
}
