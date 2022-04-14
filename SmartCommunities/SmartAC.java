/**
 * Write a description of class SmartAC here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartAC extends SmartDevice {
    /**
    * Contrutores da classe SmartAC.
    * Variáveis de instância. 
    */
    private int mode;
    private int temperature;
    /**
     * Construtor vazio de SmartAC. 
     */
    public SmartAC() {
        super();
        this.mode = 0;
    }
    /**
     * Construtor parametrizado de SmartBulb.
     * @param super chama o construtor super Classe .
     * @param mode altera a temperatura para quente ou frio.
     */
    public SmartAC(String id, String brand, int dailyConsumption, float installationCost, boolean on, int mode) {
        super(id, brand, dailyConsumption, installationCost, on);
        this.mode = mode;
    }    
    /**
     * Construtor de cópia SmartAC.
     * Aceita como parametro um objeto SmartAC e utiliza os seus métodos
     * de acesso aos valores das variáveis de instancia.
     */
    public SmartAC(SmartAC o) {
        super(o);
        this.mode = o.getMode();
    }
    /**
     * Devolve mode quente ou frio .
     * @return mode.
     */
    public int getMode() {
        return this.mode;
    }
    /**
     * Actualiza o mode para quente ou frio .
     * @param novo mode.
     */
    public void setMode(int mode) {
        this.mode = mode;
    }
    /**
     * Devolve a temperatura.
     * @return temperatura.
     */
    public int getTemperature() {
        return this.temperature;
    }
    /**
     * Actualiza a temperatura do SmartAC.
     * @param nova temperatura.
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    /** 
     *Testa a veracidade entre a comparação de dois objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartAC d = (SmartAC) o; 
        return super.equals(d) &&
               this.mode == d.mode && 
               this.temperature == d.temperature; 
    }
    /**
     * Método que cria uma cópia do objecto SmartAC.
     * @return objecto clone do SmartAC.
    */
    @Override
    public SmartAC clone() {
        return new SmartAC(this);    
    }
    /**
     * Metodo que devolve a representação em Sting do Supplier.
     * @return String com as variáveis de instância de Supplier.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
            .append("\nMode: ")
            .append(this.getMode())
            .append("\nTemperature: ")
            .append(this.getTemperature());

        return sb.toString();
    }
}
