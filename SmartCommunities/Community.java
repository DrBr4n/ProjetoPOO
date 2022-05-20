import java.util.Map;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

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
    //adicionei em todo o lado
    private LocalDateTime data;
    
    /**
     * Construtor vazio de Community
     */
    public Community(){
        this.name = "Aveiro";
        this.houses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.houseCounter = 0;
        this.deviceCounter = 0;
        this.data = LocalDateTime.now();
    }
    
    /**
     * Construtor parametrizado de Community.
     * @param name identifica o nome.
     * @param houses identifica as casas.
     * @param suppliers identifica os fornecedores.
     * @param houseCounternif conta as casas existentes.
     * @param deviceCounter conta os smartdevices existentes.
     * @param data identifica a data em que se encontra.
     */
    public Community(String name){
        this.name = name;
        this.houses = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.houseCounter = 0;
        this.deviceCounter = 0;
        this.data = LocalDateTime.now();
    }
    
    /**
     * Construtor de cópia Community.
     * Aceita como parametro um objeto House e utiliza os seus métodos
     * de acesso aos valores das variáveis de instancia.
     */
    public Community(Community o){
        this.name = o.getName();
        this.houses = o.getHouses();
        this.suppliers = o.getSuppliers();
        this.houseCounter = o.getHouseCounter();
        this.deviceCounter = o.getDeviceCounter();
        this.data = o.getData();
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
     * @param novo nome.
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Devolve as casas existentes.
     * @return houses.
     */
    public Map<String,House> getHouses(){
        return this.houses;
    }
    
    /**
     * Actualiza as casas.
     * @param nova casa.
     */
    public void setHouses(Map<String,House> houses){
        this.houses = houses;
    }
    
    /**
     * Devolve o fornecedor.
     * @return suppliers.
     */
    public Map<String,Supplier> getSuppliers() {
        return this.suppliers;
    }
    
    /**
     * Actualiza o fornecedor.
     * @param novo fornecedor.
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
     * @param novo número de casas.
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
     * @param novo número de dispositivos.
     */
    public void setDeviceCounter(int deviceCounter) {
        this.deviceCounter = deviceCounter;
    }
    
    /**
     * Devolve data atual.
     * @return data.
     */
    public LocalDateTime getData(){
        return this.data;
    }
    
    /**
     * Actualiza a data.
     * @param nova data.
     */
    public void setData(LocalDateTime data){
        this.data = data;
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
        sb.append("\nData: ")
        .append(this.getData());
        return sb.toString();
    }
    
    /**
     * Método que vai ter como função passar as variáveis de instância de Community. 
     * @return String com as variáveis de instância de Community.
     */
    public String toLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("Community:").append(this.getName()).append("\n");
        sb.append("Date: ").append(this.getData()).append("\n");
        this.getSuppliers().values().stream().map(Supplier::toLog).forEach(sb::append);
        this.getHouses().values().stream().map(House::toLog).forEach(sb::append);
        return sb.toString();
    }
    
    /** 
     *Testa a veracidade entre a comparação de dois objetos.
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
    
    /**
     * Adiciona uma casa.
     * @param adiciona house.
     */
    public void addHouse(House house) {
        this.houses.put(house.getId(), house);
    }
    
    /**
     * Adiciona um fornecedor .
     * @param adiciona 1 fornecedor.
     */
    public void addSupplier(Supplier supplier) {
        this.suppliers.put(supplier.getName(), supplier);
    }
    
    /**
     * Aumenta no contador de casas o número de casas.
     * @param adiciona 1 ao contador.
     */
    public void increaseHouseCounter() {
        this.houseCounter += 1;
    }
    
    /**
     * Aumenta no contador de dispositivos o número de smartdevices.
     * @param adiciona 1 ao contador.
     */
    public void increaseDeviceCounter() {
        this.deviceCounter += 1;
    }
}
