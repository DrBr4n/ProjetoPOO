import java.util.Map;

import UserExceptions.DeviceDoesntExistException;
import UserExceptions.HouseDoesntExistException;
import UserExceptions.RoomDoesntExistException;
import UserExceptions.SupplierDoesntExistException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Community implements Serializable{
    /**
     * Contrutores da classe SmartDevice.
     * Variáveis de instância. 
     */
    private String name;
    private int houseCounter;
    private int deviceCounter;
    private Map<String, House> houses;
    private Map<String, Supplier> suppliers;
    private LocalDate date;
    private LocalDate lastDate;
    private Map<String, List<String>> receiptsSuppliers;
    
    /**
     * Construtor vazio de Community
     */
    public Community(){
        this.name = "Aveiro";
        this.houses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.houseCounter = 0;
        this.deviceCounter = 0;
        this.date = LocalDate.now();
        this.lastDate = LocalDate.now();
        this.receiptsSuppliers = new HashMap<>();
    }
    
    /**
     * Construtor parametrizado de Community.
     * @param name identifica o nome.
     * @param houses identifica as casas.
     * @param suppliers identifica os fornecedores.
     * @param houseCounternif conta as casas existentes.
     * @param deviceCounter conta os smartdevices existentes.
     * @param date identifica a data em que se encontra.
     */
    public Community(String name, Map<String, House> houses, Map<String, Supplier> suppliers, int houseCounter, int deviceCounter, LocalDate date, LocalDate lastDate, Map<String, List<String>> receiptsSuppliers){
        this.name = name;
        this.houses = houses;
        this.suppliers = suppliers;
        this.houseCounter = houseCounter;
        this.deviceCounter = deviceCounter;
        this.date = date;
        this.lastDate = lastDate;
        this.receiptsSuppliers = receiptsSuppliers;
    }
    
    /**
     * Construtor de cópia Community.
     * Aceita como parametro um objeto House e utiliza os seus métodos
     * de acesso aos valores das variáveis de instancia.
     * @param Community o, objeto de Communidade.
     */
    public Community(Community o){
        this.name = o.getName();
        this.houses = o.getHouses();
        this.suppliers = o.getSuppliers();
        this.houseCounter = o.getHouseCounter();
        this.deviceCounter = o.getDeviceCounter();
        this.date = o.getDate();
        this.lastDate = o.getLastDate();
        this.receiptsSuppliers = o.getReceiptsSuppliers();
    }
    
    /**
     * Devolve o nome.
     * @return name.
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Actualiza o nome.
     * @param name novo nome.
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Devolve um clone das casas existentes.
     * @return houses.
     */
    public Map<String,House> getHouses(){
        Map<String, House> res = new HashMap<String, House>();
        for (House house : this.houses.values()) {
            res.put(house.getId(), house.clone());
        }     
        return res;
    }
    
    /**
     * Actualiza as casas.
     * @param houses nova casa.
     */
    public void setHouses(Map<String,House> houses){
        this.houses = houses;
    }                                        
    
    /**
     * Devolve um clone dos fornecedores existentes.
     * @return suppliers.
     */
    public Map<String, Supplier> getSuppliers() {
        Map<String, Supplier> res = new HashMap<String, Supplier>();
        for (Supplier supplier : this.suppliers.values()) {
            res.put(supplier.getName(), supplier.clone());
        }   
        return res;
    }
    
    /**
     * Actualiza o fornecedor.
     * @param suppliers novo fornecedor.
     */
    public void setSuppliers(Map<String,Supplier> suppliers) {
        this.suppliers = suppliers;
    }
    
    /**
     * Devolve o número de casas na Comunidade.
     * @return houseCounter.
     */
    public int getHouseCounter() {
        return this.houseCounter;
    }
    
    /**
     * Actualiza o número de casas.
     * @param houseCounter novo número de casas.
     */
    public void setHouseCounter(int houseCounter) {
        this.houseCounter = houseCounter;
    }
    
    /**
     * Devolve o número de dispositivos.
     * @return deviceCounter.
     */
    public int getDeviceCounter() {
        return this.deviceCounter;
    }
    
    /**
     * Actualiza o número de dispositivos.
     * @param deviceCounter novo número de dispositivos.
     */
    public void setDeviceCounter(int deviceCounter) {
        this.deviceCounter = deviceCounter;
    }
    
    /**
     * Devolve data atual.
     * @return data.
     */
    public LocalDate getDate(){
        return this.date;
    }
    
    /**
     * Actualiza a data.
     * @param date nova data.
     */
    public void setDate(LocalDate date){
        this.date = date;
    }

    /**
     * @return lastDate, devolve a ultima data.
     */
    public LocalDate getLastDate(){
        return this.lastDate;
    }
    /**
     * @param lastDate, atualiza a ultima data.
     */
    public void setLastDate(LocalDate lastDate){
        this.lastDate = lastDate;
    }
    
    public Map<String, List<String>> getReceiptsSuppliers() {
        Map<String, List<String>> res = new HashMap<String, List<String>>();
        for (List<String> listReceipts : this.receiptsSuppliers.values()) {
            String supplierLine = listReceipts.get(0).split("\n")[4];
            res.put(supplierLine.substring(10), listReceipts);
        }   
        return res;
    }

    public void setReceiptsSuppliers(Map<String, List<String>> receiptsSuppliers) {
        this.receiptsSuppliers = receiptsSuppliers;
    }

    /**
     * Método que cria uma cópia do objeto Community.
     * @return objecto clone de Community.
     */
    @Override
    public Community clone(){
        return new Community(this);
    }
    
    /**
     * Método que devolve a representação em Sting de Community.
     * @return String com as variáveis de instância de Community.
     */
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
    
    /**
     * Método que vai ter como função passar as variáveis de instância de Community. 
     * @return String com as variáveis de instância de Community.
     */
    public String toLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("Community:").append(this.getName()).append("\n");
        sb.append("Date: ").append(this.getDate()).append("\n");
        this.getSuppliers().values().stream().map(Supplier::toLog).forEach(sb::append);
        this.getHouses().values().stream().map(House::toLog).forEach(sb::append);
        return sb.toString();
    }
    
    /** 
     * Testa a veracidade entre a comparação de dois objetos.
     * @param Object o, objeto de Community.
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
        date == c.date;
    }
    
    /**
     * Adiciona uma casa.
     * @param house adiciona house.
     */
    public void addHouse(House house) {
        this.houses.put(house.getId(), house);
        this.increaseHouseCounter();
    }

    public boolean existsHouse(String houseId) {
        return this.houses.keySet().contains(houseId);
    }
    
    /**
     * Adiciona um fornecedor .
     * @param supplier fornecedor a ser adicionado.
     */
    public void addSupplier(Supplier supplier) {
        this.suppliers.put(supplier.getName(), supplier);
    }

    public boolean existsSupplier(String supplier) {
        return this.suppliers.keySet().contains(supplier);
    }

    /**
     * Aumenta no contador de casas o número de casas.
     */
    public void increaseHouseCounter() {
        this.houseCounter += 1;
    }
    
    /**
     * Aumenta no contador de dispositivos o número de smartdevices..
     */
    public void increaseDeviceCounter() {
        this.deviceCounter += 1;
    }
    /**
     * Altera o estado de um dispositivo.
     * @param houseId id da casa.
     * @param deviceId id do dispositivo. 
     */
    public void turnDeviceOn(String houseId, String deviceId) throws DeviceDoesntExistException {
        try {
            this.houses.get(houseId).turnDeviceOn(deviceId);
        } catch (DeviceDoesntExistException e) {
            throw e;
        }
    }
    
   /**
     * Altera o estado de um dispositivo.
     * @param houseId id da casa.
     * @param deviceId id do dispositivo. 
     */
    public void turnDeviceOff(String houseId, String deviceId) throws DeviceDoesntExistException {
        try {
            this.houses.get(houseId).turnDeviceOff(deviceId);
        } catch (DeviceDoesntExistException e) {
            throw e;
        }
    }

    /**
     * Define como ligado os dispositivos existentes num determinado espaço da casa.
     * @param room.
     */
    public void turnRoomOn(String houseId, String room) throws RoomDoesntExistException {
        try {
            this.houses.get(houseId).turnRoomOn(room);
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Define como desligados os dispositivos existentes num determinado espaço da casa.
     * @param room.
     */
    public void turnRoomOff(String houseId, String room) throws RoomDoesntExistException {
        try {
            this.houses.get(houseId).turnRoomOff(room);
        } catch (Exception e) {
            throw e;
        }
    }

    public void setSupplier(String houseId, String supplier) throws HouseDoesntExistException, SupplierDoesntExistException {
        if (!this.existsHouse(houseId)) {
            throw new HouseDoesntExistException(houseId);
        }
        if (!this.existsSupplier(supplier)) {
            throw new SupplierDoesntExistException(supplier);
        }
        this.houses.get(houseId).setSupplier(this.suppliers.get(supplier));
    }

    /**
     * Gera um recibo.
     * @return recibo.
     */
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

            String supplierName = house.getSupplier().getName();
            System.out.println(supplierName);
            if(!this.receiptsSuppliers.containsKey(supplierName)){
                this.receiptsSuppliers.put(supplierName, new ArrayList<>());
            }    
            this.receiptsSuppliers.get(supplierName).add(ret[index-1]);
        }
        System.out.println(this.receiptsSuppliers);
        return ret;
    }
}
