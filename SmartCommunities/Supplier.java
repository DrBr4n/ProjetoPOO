
/**
 * Write a description of class Supplier here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Supplier {

    /**
    * Contrutores da classe Supplier.
    * Variáveis de instância. 
    */

    private float price;
    private int dailyCost;
    /**
     * Construtor vazio do Supplier. 
     */  
    public Supplier() {
        this.price = 5;
        this.dailyCost = 50;
    }
    /**
     * Construtor parametrizado de Supplier.
     * @param price preço do fornecedor.
     * @param dailyCost custo diário.
     */
    public Supplier(float price, int dailyCost){
        this.price = price;
        this.dailyCost = dailyCost;
    }

    /**
     * Construtor de cópia Supplier.
     * Aceita como parametro um objeto Supplier e utiliza os seus métodos
     * de acesso aos valores das variáveis de instancia.
     */

    public Supplier(Supplier o){
        this.price = o.getPrice();
        this.dailyCost = o.getDailyCost();

    }
    /**
     * Devolve o valor do preço.
     * @return o valor do preço.
     */
    public float getPrice() {
        return this.price;
    }
    /**
     * Actualiza o valor do preço.
     * @param novo preço.
     */
    public void setPrice(float price) {
        this.price = price;
    }
    /**
     * Devolve o valor do custo diário.
     * @return o valor do custo diário.
     */
    public int getDailyCost() {
        return this.dailyCost;
    }
    /**
     * Actualiza o valor do custo diário.
     * @param novo custo diário.
     */
    public void setDailyCost(int dailyCost) {
        this.dailyCost = dailyCost;
    }
    /** 
     *Testa a veracidade entre a comparação de dois objetos.
     */
    public boolean equals(Object o) {
        if (this == o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        Supplier s = (Supplier) o; 
        return price == s.price && 
               dailyCost == s.dailyCost; 
    }
    /**
     * Método que cria uma cópia do objecto Supplier.
     * @return objecto clone do Supplier.
    */
    public Supplier clone() {
        return new Supplier(this);    
    }
    /**
     * Metodo que devolve a representação em Sting do Supplier.
     * @return String com as variáveis de instância de Supplier.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSupplier:")
            .append("\nPrice: ")
            .append(this.getPrice())
            .append("\nDaily Cost: ")
            .append(this.getDailyCost());
            
        return sb.toString();
    }
}

