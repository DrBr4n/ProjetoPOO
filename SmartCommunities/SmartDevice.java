import java.io.Serializable;

public class SmartDevice implements Serializable{
    /**
     * Contrutores da classe SmartDevice.
     * Variáveis de instância. 
     */
    private String id;
    private String brand;
    private int dailyConsumption;
    private float installationCost;
    private boolean on;
    /**
     * Construtor vazio de SmartDevice 
     */

    public SmartDevice() {
        this.id = "404";
        this.brand = "LG";
        this.dailyConsumption = 400;
        this.installationCost = 50;
        this.on = false;
    }

    /**
     * Construtor parametrizado de SmartDevice.
     * @param id identificador do smart device.
     * @param brand marca do smart devide.
     * @param dailyConsumption consumo diário do smart device. 
     * @param installationCost custo de instalação do smart device.
     * @param on estado do smart device.
     */

    public SmartDevice(String id, String brand, int dailyConsumption, float installationCost, boolean on) {
        this.id = id;
        this.brand = brand;
        this.dailyConsumption = dailyConsumption;
        this.installationCost = installationCost;
        this.on = on;
    }

    /**
     * Construtor de cópia SmartDevice.
     * Aceita como parametro um objeto Smart Device e utiliza os seus métodos
     * de acesso aos valores das variáveis de instancia.
     */
    public SmartDevice(SmartDevice o) {
        this.id = o.getId();
        this.brand = o.getBrand();
        this.dailyConsumption = o.getDailyConsumption();
        this.installationCost = o.getInstallationCost();
        this.on = o.getOn();
    }

    /**
     * Devolve o valor do identificador.
     * @return valor do id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Actualiza o valor do id.
     * @param novo identificador.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Devolve a marca.
     * @return nome da marca.
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Actualiza a marca.
     * @param nova marca.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Devolve o consumo diário.
     * @return valor do consumo diário.
     */
    public int getDailyConsumption() {
        return this.dailyConsumption;
    }

    /**
     * Actualiza o valor do consumo diário.
     * @param novo consumo diário.
     */
    public void setDailyConsumption(int dailyConsumption) {
        this.dailyConsumption = dailyConsumption;
    }

    /**
     * Devolve o valor do custo de intalação.
     * @return valor do custo de instalação.
     */
    public float getInstallationCost() {
        return this.installationCost;
    }

    /**
     * Actualiza o valor do custo de instalação.
     * @param novo custo de instalação.
     */
    public void setInstallationCost(float installationCost) {
        this.installationCost = installationCost;
    }

    /**
     * Devolve o estado do smartdevice.
     * @return estado smartdevice.
     */
    public boolean getOn() {
        return this.on;
    }

    /**
     * Actualiza o estado do smartdevice.
     * @param novo estado .
     */
    public void setOn(boolean on) {
        this.on = on;
    }

    /**
     * Atualiza o estado de on para True
     */
    public void turnOn(){
        this.on = true;
    }

    /**
     * Atualiza o estado de on para False 
     */
    public void turnOff(){
        this.on = false;
    }

    /** 
     *Testa a veracidade entre a comparação de dois objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartDevice d = (SmartDevice) o; 
        return id == d.id && 
        brand == d.brand &&
        dailyConsumption == d.dailyConsumption &&               
        installationCost == d.installationCost && 
        on == d.on; 
    }

    /**
     * Método que cria uma cópia do objecto SmartDevice.
     * @return objecto clone do SmartDevice.
     */
    @Override
    public SmartDevice clone() {
        return new SmartDevice(this);    
    }

    /**
     * Metodo que devolve a representação em Sting dos SmartDevice.
     * @return String com as variáveis de instância de SmartDevice.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSmartDeviceId: ")
        .append(this.getId())
        .append("\nBrand: ")
        .append(this.getBrand())
        .append("\nDailyConsumption: ")
        .append(this.getDailyConsumption())
        .append("\nInstallationCost: ")
        .append(this.getInstallationCost())
        .append("\nOn: ")
        .append(this.getOn());

        return sb.toString();
    }

    public SmartDevice createDevice(String [] props) {
        // {OptionCreateOrEdit, Room, DeviceType, DeviceId, Marca, ConsumoDiario, Custo, Rng1, Rng2}
        int option = Integer.parseInt(props[2]);
        switch (option) {
            case 1:
                SmartAC ac = new SmartAC('d' + props[3], props[4], Integer.parseInt(props[5]), Float.parseFloat(props[6]), false, Integer.parseInt(props[7]), Integer.parseInt(props[8]));
                return ac;
            case 2:
                SmartTV tv = new SmartTV('d' + props[3], props[4], Integer.parseInt(props[5]), Float.parseFloat(props[6]), false, Integer.parseInt(props[7]), Integer.parseInt(props[8]));
                return tv;
            case 3:
                SmartSpeaker sp = new SmartSpeaker('d' + props[3], props[4], Integer.parseInt(props[5]), Float.parseFloat(props[6]), false, props[7], Integer.parseInt(props[8]));
                return sp;
            case 4:
                SmartBulb bb = new SmartBulb('d' + props[3], props[4], Integer.parseInt(props[5]), Float.parseFloat(props[6]), false, Integer.parseInt(props[7]), Integer.parseInt(props[8]));
                return bb;
            case 5:
                SmartCamera cm = new SmartCamera('d' + props[3], props[4], Integer.parseInt(props[5]), Float.parseFloat(props[6]), false, Integer.parseInt(props[7]), Integer.parseInt(props[8]));
                return cm;
            default:
                return null;
        }
    }
}
