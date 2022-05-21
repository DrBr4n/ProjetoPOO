import UserExceptions.MaxVolumeException;
import UserExceptions.MinVolumeException;

/**
 * Write a description of class SmartTV here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartTV extends SmartDevice {
    /**
     * Contrutores da classe SmartTV.
     * Variáveis de instância. 
     */
    public static final int MAX = 100;

    private String resolution;
    private int volume;
    /**
     * Construtor vazio de SmartTV. 
     */
    public SmartTV() {
        super();
        this.volume = 40;
        this.resolution = "2160x1080";
    }

    /**
     * Construtor parametrizado de SmarTV.
     * @param super chama o construtor super Classe .
     * @param volume volume da SmartTv.
     * @param resolution resolução da SmartTV. 
     */
    public SmartTV(String id, String brand, float dailyConsumption, float installationCost, boolean on, String resolution, int volume) {
        super(id, brand, dailyConsumption, installationCost, on);
        this.volume = volume;
        this.resolution = resolution;
    }

    /**
     * Construtor de cópia SmartTV.
     * Aceita como parametro um objeto SmartTV e utiliza os seus métodos
     * de acesso aos valores das variáveis de instancia.
     * @param SmartTV o objeto.
     */
    public SmartTV(SmartTV o) {
        super(o);
        this.volume = o.getVolume();
        this.resolution = o.getResolution();
    }

    /**
     * Devolve o volume da SmartTV.
     * @return volume da SmartTV.
     */
    public int getVolume() {
        return this.volume;
    }

    /**
     * Actualiza o volume da SmartTV.
     * @param volume valor do volume.
     */
    public void setVolume(int volume) throws MaxVolumeException, MinVolumeException{
        if (volume > MAX) throw new MaxVolumeException();
        else if (volume < 0) throw new MinVolumeException();
        this.volume = volume;
    }

    /**
     * Devolve a resolução da SmartTV.
     * @return resolução.
     */
    public String getResolution() {
        return this.resolution;
    }

    /**
     * Actualiza a resolução da SmartTV.
     * @param resolution nova resolução.
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /** 
     * Testa a veracidade entre a comparação de dois objetos.
     * @param Object o objeto SmartTV.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartTV d = (SmartTV) o; 
        return super.equals(d) &&
        this.resolution == d.resolution && 
        this.volume == d.volume; 
    }

    /**
     * Método que cria uma cópia do objecto SmartTV.
     * @return objecto clone do SmartTV.
     */
    @Override
    public SmartTV clone() {
        return new SmartTV(this);    
    }

    /**
     * Metodo que devolve a representação em Sting dos SmartTV.
     * @return String com as variáveis de instância de SmartTV.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
        .append("\nResolution: ")
        .append(this.getResolution())
        .append("\nVolume: ")
        .append(this.getVolume());

        return sb.toString();
    }
    
    /**
     * Método que vai ter como função passar as variáveis de instância de SmartTV. 
     * @return String com as variáveis de instância de SmartTV.
     */
    @Override
    public String toLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmartBulb:")
            .append(super.toLog() + ",")
            .append(this.getResolution() + ",")
            .append(this.getVolume())
            .append("\n");
        return sb.toString();
    }
}
