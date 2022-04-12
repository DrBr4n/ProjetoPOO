/**
 * Write a description of class SmartTV here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartTV extends SmartDevice {
    
    public static final int MAX = 100;

    private int resolution;
    private int volume;

    public SmartTV() {
        super();
        this.volume = 40;
        this.resolution = 2160;
    }

    public SmartTV(String id, String brand, int dailyConsumption, float installationCost, boolean on,  int volume, int resolution) {
        super(id, brand, dailyConsumption, installationCost, on);
        this.volume = volume;
        this.resolution = resolution;
    }

    public SmartTV(SmartTV o) {
        super(o);
        this.volume = o.getVolume();
        this.resolution = o.getResolution();
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

    public int getResolution() {
        return this.resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartTV d = (SmartTV) o; 
        return super.equals(d) &&
               this.resolution == d.resolution && 
               this.volume == d.volume; 
    }

    @Override
    public SmartTV clone() {
        return new SmartTV(this);    
    }
    
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
}
