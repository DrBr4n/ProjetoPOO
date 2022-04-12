/**
 * Write a description of class SmartSpeaker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartSpeaker extends SmartDevice {

    public static final int MAX = 100;

    private String radio;
    private int volume;

    public SmartSpeaker() {
        super();
        this.radio = "9.35";
        this.volume = 40;
    }

    public SmartSpeaker(String id, String brand, int dailyConsumption, float installationCost, boolean on, int volume, String radio) {
        super(id, brand, dailyConsumption, installationCost, on);
        this.radio = radio;
        this.volume = volume;
    }

    public SmartSpeaker(SmartSpeaker o) {
        super(o);
        this.radio = o.getRadio();
        this.volume = o.getVolume();
    }

    public String getRadio() {
        return this.radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        if (volume >= 0 && volume <= MAX)
            this.volume = volume;
    }

    public void volumeUp() {
        if (this.volume < MAX) 
            this.volume++;
    }

    public void volumeDown() {
        if (this.volume > 0) 
            this.volume--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartSpeaker d = (SmartSpeaker) o; 
        return super.equals(d) &&
               this.radio == d.radio && 
               this.volume == d.volume; 
    }

    @Override
    public SmartSpeaker clone() {
        return new SmartSpeaker(this);    
    }
    
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
