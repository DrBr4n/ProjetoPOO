/**
 * Write a description of class SmartSpeaker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartSpeaker extends SmartDevice {
    /**
     * Contrutores da classe SmartSpeaker.
     * Variáveis de instância. 
     */
    public static final int MAX = 100;

    private String radio;
    private int volume;
    /**
     * Construtor vazio de SmartSpeaker.
     */
    public SmartSpeaker() {
        super();
        this.radio = "9.35";
        this.volume = 40;
    }

    /**
     * Construtor parametrizado de SmartSpeaker.
     * @param super chama o construtor super Classe.
     * @param radio estação de rádio em que se encontra o SmartSpeaker.
     * @param volume do SmartSpeaker. 
     */
    public SmartSpeaker(String id, String brand, int dailyConsumption, float installationCost, boolean on, String radio, int volume) {
        super(id, brand, dailyConsumption, installationCost, on);
        this.radio = radio;
        this.volume = volume;
    }

    /**
     * Construtor de cópia SmartSpeaker.
     * Aceita como parametro um objeto SmartSpeaker e utiliza os seus métodos
     * de acesso aos valores das variáveis de instancia.
     */
    public SmartSpeaker(SmartSpeaker o) {
        super(o);
        this.radio = o.getRadio();
        this.volume = o.getVolume();
    }

    /**
     * Devolve a estação de rádio.
     * @return a estação de rádio.
     */
    public String getRadio() {
        return this.radio;
    }

    /**
     * Actualiza a estação de rádio.
     * @param nova estação de rádio.
     */
    public void setRadio(String radio) {
        this.radio = radio;
    }

    /**
     * Devolve o volume atual do SmartSpeaker.
     * @return o volume .
     */
    public int getVolume() {
        return this.volume;
    }

    /**
     * Actualiza o valor volume.
     * @param novo valor de volume.
     */
    public void setVolume(int volume) {
        if (volume >= 0 && volume <= MAX)
            this.volume = volume;
    }

    /**
     * Aumenta o volume do SmartSpeaker sem ultrapassar o seu máximo.
     */
    public void volumeUp() {
        if (this.volume < MAX) 
            this.volume++;
    }

    /**
     * Diminui o volume do SmartSpeaker sem ultrapassar o seu mínimo.
     */
    public void volumeDown() {
        if (this.volume > 0) 
            this.volume--;
    }

    /** 
     *Testa a veracidade entre a comparação de dois objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartSpeaker d = (SmartSpeaker) o; 
        return super.equals(d) &&
        this.radio == d.radio && 
        this.volume == d.volume; 
    }

    /**
     * Método que cria uma cópia do objecto SmartTV.
     * @return objecto clone do SmartTV.
     */
    @Override
    public SmartSpeaker clone() {
        return new SmartSpeaker(this);    
    }

    /**
     * Metodo que devolve a representação em Sting dos SmartSpeaker.
     * @return String com as variáveis de instância de SmartSpeaker.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
        .append("\nRadio: ")
        .append(this.getRadio())
        .append("\nVolume: ")
        .append(this.getVolume());

        return sb.toString();
    }
}
