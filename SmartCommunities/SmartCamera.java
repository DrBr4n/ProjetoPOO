/**
 * Write a description of class SmartCamera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartCamera extends SmartDevice {
    /**
     * Contrutores da classe SmartCamera.
     * Variáveis de instância. 
     */
    private String resolution;
    private int fileSize;
    /**
     * Construtor vazio de SmartCamera.
     */
    public SmartCamera() {
        super();
        this.resolution = "(1280x720)";
        this.fileSize = 35;
    }

    /**
     * Construtor parametrizado de SmarCamera.
     * @param super chama o construtor super Classe .
     * @param resolution resolução da SmartCamera.
     * @param fileSize tamanho dos ficheiros. 
     */
    public SmartCamera(String id, String brand, float dailyConsumption, float installationCost, boolean on, String resolution, int fileSize) {
        super(id, brand, dailyConsumption, installationCost, on);
        this.resolution = resolution;
        this.fileSize = fileSize;
    }

    /**
     * Construtor de cópia SmartCamera.
     * Aceita como parametro um objeto SmartCamera e utiliza os seus métodos
     * de acesso aos valores das variáveis de instancia.
     */
    public SmartCamera(SmartCamera o) {
        super(o);
        this.resolution = o.getResolution();
        this.fileSize = o.getFileSize();
    }

    /**
     * Devolve a resolução da SmartCamera.
     * @return a resolução.
     */
    public String getResolution() {
        return this.resolution;
    }

    /**
     * Actualiza a resolução da SmartCamera.
     * @param novo valor de resolução.
     */

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * Devolve o tamanho do ficheiro.
     * @return o tamanho do ficheiro.
     */
    public int getFileSize() {
        return this.fileSize;
    }

    /**
     * Actualiza o tamanho do ficheiro.
     * @param novo tamanho de ficheiro.
     */

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    /** 
     *Testa a veracidade entre a comparação de dois objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        SmartCamera d = (SmartCamera) o; 
        return super.equals(d) &&
        this.resolution == d.resolution && 
        this.fileSize == d.fileSize; 
    }

    /**
     * Método que cria uma cópia do objecto SmartCamera.
     * @return objecto clone do SmartCamera.
     */
    @Override
    public SmartCamera clone() {
        return new SmartCamera(this);    
    }

    /**
     * Metodo que devolve a representação em Sting dos SmartCamera.
     * @return String com as variáveis de instância de SmartCamera.
     */
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