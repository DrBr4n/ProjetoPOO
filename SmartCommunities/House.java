import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class House implements Serializable{
    /**
     * Contrutores da classe SmartDevice.
     * Variáveis de instância. 
     */
    private String id;
    private String address;
    private String ownerName;
    private String nif;
    private Supplier supplier;
    private Map<String, Map<String, SmartDevice>> rooms;
    
    /**
     * Construtor vazio de House
     */
    public House() {
        this.id = "404";
        this.address = "Rua Das Cruzetas";
        this.ownerName = "Antonio Variacoes";
        this.nif = "999999999";
        this.supplier = new Supplier();
        this.rooms = new HashMap<>();
    }
    
    /**
     * Construtor parametrizado de House.
     * @param id identifica a Casa .
     * @param address morada da casa.
     * @param ownerName nome do dono.
     * @param nif nif do dono.
     * @param supplier fornecedor da casa.
     * @param rooms partes da casa existentes na casa.
     */
    public House(String id, String address, String ownerName, String nif, Supplier supplier){
        this.id = id;
        this.address = address;
        this.ownerName = ownerName;
        this.nif = nif;
        this.supplier = supplier;
        this.rooms = new HashMap<>();
    }
    
    /**
     * Construtor parametrizado de House.
     * @param id identifica a Casa .
     * @param address morada da casa.
     * @param ownerName nome do dono.
     * @param nif nif do dono.
     * @param supplier fornecedor da casa.
     * @param rooms partes da casa existentes na casa.
     */
    public House(String id, String nif, String address, String ownerName, Map<String, Map<String, SmartDevice>> rooms, Supplier supplier){
        this.id = id;
        this.address = address;
        this.ownerName = ownerName;
        this.nif = nif;
        this.supplier = supplier;
        this.rooms = rooms;
    }   
    
    /**
     * Construtor de cópia House.
     * Aceita como parametro um objeto House e utiliza os seus métodos
     * de acesso aos valores das variáveis de instancia.
     * @param House o, objeto de House.
     */
    public House(House o){
        this.id = o.getId();
        this.address = o.getAddress();
        this.ownerName = o.getOwnerName();
        this.nif = o.getNif();
        this.supplier = o.getSupplier();
        this.rooms = o.getRooms();
    }
    
    /**
     * Devolve o Id atual da casa.
     * @return o id .
     */
    public String getId(){
        return this.id;
    }
    
    /**
     * Actualiza o id.
     * @param id novo id.
     */
    public void setId(String id){
        this.id = id;
    }
    
    /**
     * Devolve a morada da casa.
     * @return address.
     */
    public String getAddress(){
        return this.address;
    }
    
    /**
     * Actualiza a morada.
     * @param address nova morada.
     */
    public void setAddress(String address){
        this.address = address;
    }
    
    /**
     * Devolve o nome do dono da casa.
     * @return ownerName.
     */
    public String getOwnerName(){
        return this.ownerName;
    }
    
    /**
     * Actualiza o nome do dono da casa.
     * @param ownerName novo nome do dono da casa.
     */
    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }
    
    /**
     * Devolve o nif do dono da casa.
     * @return nif.
     */
    public String getNif(){
        return this.nif;
    }
    
    /**
     * Actualiza o nif.
     * @param nif novo nif.
     */
    public void setNif(String nif){
        this.nif = nif;
    }

    /**
     * Devolve o fornecedor da casa.
     * @return supplier.
     */
    public Supplier getSupplier() {
        return this.supplier;
    }

    /**
     * Actualiza o fornecedor.
     * @param supplier novo fornecedor.
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = new Supplier(supplier);
    }
    
    
    /**
     * Devolve um clone das partes da casa com um clone de todos os seus dispositivos associados.
     * @return rooms.
     */
    public Map<String, Map<String, SmartDevice>> getRooms(){
        Map<String, Map<String, SmartDevice>> res = new HashMap<>();
        for (String room : this.rooms.keySet()) {
            res.put(room, new HashMap<>());
            for (String device : this.rooms.get(room).keySet()) {
                res.get(room).put(device, this.rooms.get(room).get(device).clone());
            }    
        }
        return res;
    }
    
    /**
     * Actualiza as partes da casa existentes.
     * @param rooms novas partes da casa.
     */
    public void setRooms(Map<String, Map<String, SmartDevice>> rooms){
        this.rooms = rooms;
    }
    
    /** 
     * Testa a veracidade entre a comparação de dois objetos.
    * @param Object o, objeto de House.
     */
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
    
    /**
     * Método que cria uma cópia do objeto House.
     * @return objecto clone de House.
     */
    @Override
    public House clone() {
        return new House(this);    
    }
    
    /**
     * Método que devolve a representação em Sting de House.
     * @return String com as variáveis de instância de House.
     */
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
        for (String room : this.getRooms().keySet()) {
            sb.append("Room:").append(room).append("\n");
            this.getRooms().get(room).values().stream().map(SmartDevice::toLog).forEach(sb::append);
        }
        return sb.toString();
    }
    
    /**
     * Método que vai ter como função passar as variáveis de instância de House. 
     * @return String com as variáveis de instância de House.
     */
    public String toLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("House:")
            .append(this.getId() + ",")
            .append(this.getAddress() + ",")
            .append(this.getOwnerName() + ",")
            .append(this.getNif() + ",")
            .append(this.supplier.getName())
            .append("\n");
        for (String room : this.getRooms().keySet()) {
            sb.append("Room:").append(room).append("\n");
            this.getRooms().get(room).values().stream().map(SmartDevice::toLog).forEach(sb::append);
        }
        return sb.toString();
    }
    /**
     * Devolve um clone de todos os dispositivos.
     * @return mapa de dispositivos.
     */
    public Map<String, SmartDevice> getDevices(){
        Map<String, SmartDevice> res = new HashMap<>();
        for (Map<String, SmartDevice> room : this.rooms.values()) {
            for (SmartDevice device : room.values()) {
                res.put(device.getId(), device.clone());
            }
        }
        return res; 
    }
    
    /**
     * Testa se existem dispositivos.
     * @return verifica se um dispositivo existe.
     */
    public boolean existsDevice(String deviceId) {
        return this.rooms.values().stream().anyMatch(room -> room.keySet().contains(deviceId));
    }
    
    /**
     * Define os dispositivos como ligados.
     * @param deviceId
     */
    public void turnDeviceOn(String deviceId) {
        if (!existsDevice(deviceId)) {
            //throw deviceDoesntExist
            System.out.println("deviceDoesntExist");
        }
        for (String room : this.rooms.keySet()) {
            if (this.rooms.get(room).containsKey(deviceId)) {
                this.rooms.get(room).get(deviceId).turnOn();
            }
        }
    }
    
    /**
     * Define os dispositivos como desligados.
     * @param deviceId
     */
    public void turnDeviceOff(String deviceId) {
        if (!existsDevice(deviceId)) {
            //throw deviceDoesntExist
            System.out.println("deviceDoesntExist");
        } 
        for (String room : this.rooms.keySet()) {
            if (this.rooms.get(room).containsKey(deviceId)) {
                this.rooms.get(room).get(deviceId).turnOff();
            }
        }
    }
    
    
    /**
     * Define como ligado os dispositivos existentes num determinado espaço da casa.
     * @param room.
     */
    public void turnRoomOn(String room) {
        if (!existRoom(room)) {
            //throw roomDoesNotExist
            System.out.println("roomDoesntExist");
        }
        this.rooms.get(room).values().stream().forEach(SmartDevice::turnOn);
    }
    
    /**
     * Define como desligados os dispositivos existentes num determinado espaço da casa.
     * @param room.
     */
    public void turnRoomOff(String room) {
        if (!existRoom(room)) {
            //throw roomDoesNotExist
            System.out.println("roomDoesntExist");
        } 
        this.rooms.get(room).values().stream().forEach(SmartDevice::turnOff);
    }
     
    /** 
     * Testa se existe um espaço da casa específico.
     * @return se existe um espaço da casa.
     */
    public boolean existRoom(String room){
        return this.rooms.containsKey(room);
    }
    
    /**
     * Adiciona um novo espaço à casa.
     * @param room.
     */
    public void addRoom(String room) {
        Map<String, SmartDevice> emptyMap = new HashMap<>();
        this.rooms.put(room, emptyMap);
    }
    
    /**
     * Adiciona um novo dispositivo.
     * @param room.
     * @param device.
     */
    public void addDeviceToRoom (String room, SmartDevice device) {
        this.rooms.get(room).put(device.getId(), device);
    }

    /**
     * Calcula o consumo dos dispositivos.
     * @return consumo.
     */
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
