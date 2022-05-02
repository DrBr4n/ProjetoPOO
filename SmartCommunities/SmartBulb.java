/**
 * Write a description of class SmartBulb here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartBulb extends SmartDevice {
    /**
     * Contrutores da classe SmarBulb.
     * Variáveis de instância.
     */ 
    private int mode;
    private int size;
    /**
     * Construtor vazio de SmartDevice 
     */
    public SmartBulb() {
        super();
        this.mode = 0;
        this.size = 8;
    }

    /**
     * Construtor parametrizado de SmartBulb.
     * @param super chama o construtor super Classe .
     * @param mode tonalidade da SmartBulb.
     * @param size tamanhos em centímetro SmartBulb. 
     * @param on estado do smart device.
     */
    public SmartBulb(String id, String brand, double dailyConsumption, float installationCost, boolean on, int mode, int size) {
        super(id, brand, dailyConsumption, installationCost, on);
        this.mode = mode;
        this.size = size;
    }

    /**
     * Construtor de cópia SmartBulb.
     * Aceita como parametro um objeto SmartBulb e utiliza os seus métodos
     * de acesso aos valores das variáveis de instancia.
     */
    public SmartBulb(SmartBulb o) {
        super(o);
        this.mode = o.getMode();
        this.size = o.getSize();
    }

    /**
     * Devolve a tonalidade.
     * @return mode(tonalidade da SmartBulb).
     */
    public int getMode() {
        return this.mode;
    }

    /**
     * Actualiza o valor do mode ou seja uma nova tonalidade.
     * @param novo mode.
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * Devolve o valor do tamanho da SmartBulb.
     * @return o valor do tamanho.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Actualiza o valor do tamanho.
     * @param novo tamanho.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /** 
     *Testa a veracidade entre a comparação de dois objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartBulb d = (SmartBulb) o; 
        return super.equals(d) &&
        this.mode == d.mode && 
        this.size == d.size; 
    }

    /**
     * Método que cria uma cópia do objecto SmartBulb.
     * @return objecto clone do SmartBulb.
     */
    @Override
    public SmartBulb clone() {
        return new SmartBulb(this);    
    }

    /**
     * Metodo que devolve a representação em Sting da SmartBulb.
     * @return String com as variáveis de instância de SmartBulb.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
        .append("\nMode: ")
        .append(this.getMode())
        .append("\nSize: ")
        .append(this.getSize());

        return sb.toString();
    }

}
