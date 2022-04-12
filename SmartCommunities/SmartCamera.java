/**
 * Write a description of class SmartCamera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartCamera extends SmartDevice {

    private int resolution;
    private int fileSize;

    public SmartCamera() {
        super();
        this.resolution = 1440;
        this.fileSize = 35;
    }

    public SmartCamera(String id, String brand, int dailyConsumption, float installationCost, boolean on, int resolution, int fileSize) {
        super(id, brand, dailyConsumption, installationCost, on);
        this.resolution = resolution;
        this.fileSize = fileSize;
    }

    public SmartCamera(SmartCamera o) {
        super(o);
        this.resolution = o.getResolution();
        this.fileSize = o.getFileSize();
    }

    public int getResolution() {
        return this.resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartCamera d = (SmartCamera) o; 
        return super.equals(d) &&
               this.resolution == d.resolution && 
               this.fileSize == d.fileSize; 
    }

    @Override
    public SmartCamera clone() {
        return new SmartCamera(this);    
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
            .append("\nResolution: ")
            .append(this.getResolution())
            .append("\nFile Size: ")
            .append(this.getFileSize());

        return sb.toString();
    }
}