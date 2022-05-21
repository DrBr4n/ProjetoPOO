import java.util.Scanner;

/**
 * Write a description of class View here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class View
{
    /**
     * Menu Principal.
     * @return opção escolhida.
     */
    public String menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Smart Community--------");
        System.out.println("1 - Criar Casas");
        System.out.println("2 - Criar Fornecedores de energia");
        System.out.println("3 - Modificar estado de um dispositivo");
        System.out.println("4 - Calcular o consumo");
        System.out.println("5 - Mudar data");
        System.out.println("6 - Estatisticas");
        System.out.println("7 - Carregar ficheiro de objetos");
        System.out.println("8 - Guardar num ficheiro de objetos");
        System.out.println("9 - Carregar ficheiro de texto");
        System.out.println("10 - Guardar num ficheiro de texto");
        System.out.println("0 - Terminar");
        System.out.println("Indique a opcao: ");

        String option = sc.next();

        sc.close();
        return option;
    }

    /**
     * Menu criação de casa.
     * @return array de Strings com dados de identificação da casa.
     */
    public String[] createHouse() {
        Scanner sc = new Scanner(System.in);
        String [] houseInput = new String[4];
        System.out.println("-------Criar Casa-------");
        System.out.print("Indique a morada da casa: ");
        houseInput[0] = sc.nextLine();
        System.out.print("Indique o nome do dono da casa: ");
        houseInput[1] = sc.nextLine();
        System.out.print("Indique o NIF do dono da casa: ");
        houseInput[2] = sc.nextLine();
        System.out.print("Indique o fornecedor da casa: ");
        houseInput[3] = sc.nextLine();
        sc.close();
        return houseInput;
    }

    /**
     * Menu criação de casa.
     * @return array de Strings com propriedades da casa.
     */
    public String[] editHouse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------Alterar Casa-------");
        System.out.println("1 - Adicionar Divisoes");
        System.out.println("2 - Adicionar Dispositivos");
        System.out.println("3 - Alterar Fornecedor");
        System.out.println("0 - Voltar ao menu inicial");
        System.out.print("Indique a opcao: ");
        int option = sc.nextInt();
        String [] properties = new String[2]; 
        properties[0] = String.valueOf(option);
        if(option == 1 ){
            System.out.print("Indique a divisao que pretende adicionar: ");
            sc.nextLine();
            properties[1] = sc.nextLine();
        } else if (option == 2) {
            System.out.print("Indique a divisao a que pretende adicionar o dispositivo: ");
            sc.nextLine();
            properties[1] = sc.nextLine();
        } else if (option == 3) {
            System.out.println("Indique o nome do novo fornecedor:");
            sc.nextLine();
            properties[1] = sc.nextLine();
        }
        sc.close();
        return properties;
    }
    
    /**
     * Escolhe dispositivo.
     * @return dispositivo escolhido.
     */
    public int choseDevice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Indique o dispositivo que pretende adicionar: \n");
        System.out.println("1 - SmartAC");
        System.out.println("2 - SmartTV");
        System.out.println("3 - SmartSpeaker");
        System.out.println("4 - SmartBulb");
        System.out.println("5 - SmartCamera");
        System.out.print("Indique a opcao: ");
        int option = sc.nextInt();

        while (option < 1 || option > 5) {
            invalidOption();
            option = sc.nextInt();
        }

        sc.close();
        return option;
    }

    /**
     * Identifica o dispositivo escolhido.
     * @param option opção.
     * @return propriedades do dispositivo.
     */
    public String[] deviceProps(int option) {
        String[] properties = new String[6];
        Scanner sc = new Scanner(System.in);

        System.out.print("Indique a marca do dispositivo: ");
        properties[0] = sc.nextLine();
        System.out.print("Indique o consumo diario do dispositivo: ");
        properties[1] = sc.nextLine();
        System.out.print("Indique o custo da instalaçao do dispositivo: ");
        properties[2] = sc.nextLine();
        System.out.print("Indique se o dispositivo esta ligado (true - ligado, false - desligado): ");
        properties[3] = sc.nextLine();

        switch (option) {
            case 1:
                System.out.print("Indique o modo do dispositivo: ");
                properties[4] = sc.nextLine();
                System.out.print("Indique a temperatura do dispositivo: ");
                properties[5] = sc.nextLine();
                break;
            case 2:
                System.out.print("Indique a resolucao do dispositivo: ");
                properties[4] = sc.nextLine();
                System.out.print("Indique o volume do dispositivo: ");
                properties[5] = sc.nextLine();
                break;
            case 3:
                System.out.print("Indique a radio do dispositivo: ");
                properties[4] = sc.nextLine();
                System.out.print("Indique o volume do dispositivo: ");
                properties[5] = sc.nextLine();  
                break;
            case 4:
                System.out.print("Indique o modo do dispositivo: ");
                properties[4] = sc.nextLine();
                System.out.print("Indique o tamanho do dispositivo: ");
                properties[5] = sc.nextLine();
                break;
            case 5:
                System.out.print("Indique a resolucao do dispositivo: ");
                properties[4] = sc.nextLine();
                System.out.print("Indique o tamanho de ficheiros do dispositivo: ");
                properties[5] = sc.nextLine();
                break;
            default:
                break;  
        }
        sc.close();
        return properties;
    }
    /**
     * Escolhe se pertende alterar uma divisão ou um dispositivo.
     * @return a uma opção.
     */
    public int chooseIfRoomOrDevice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Indique o que pretende alterar: \n");
        System.out.println("1 - Dispositivo especifico");
        System.out.println("2 - Divisão de uma casa");
        int option = sc.nextInt();
        sc.close();
        return option;
    }
    
    /**
     * Muda o estado dos dispositivos.
     * @return propriedades do dispositivo.
     */ 
    public String[] changeDeviceState() {
        Scanner sc = new Scanner(System.in);
        String[] ids = new String[3];
        System.out.print("Indique o identificador da casa do dispositivo que pertende alterar: ");
        ids[0] = sc.nextLine();
        System.out.print("Indique o identificador do dispositivo que pertende alterar: ");
        ids[1] = sc.nextLine();
        System.out.print("Pretende ligar(0) ou desligar(1)?: ");
        ids[2] = sc.nextLine();
        sc.close();
        return ids;
    }
    /**
     * Escolhe qual a divisão para ligar ou desligar.
     * @return a opção escolhida.
     */
    public String[] changeRoomState() {
        Scanner sc = new Scanner(System.in);
        String[] ids = new String[3];
        System.out.print("Indique o identificador da casa do dispositivo que pertende alterar: ");
        ids[0] = sc.nextLine();
        System.out.print("Indique o nome da divisao que pertende alterar: ");
        ids[1] = sc.nextLine();
        System.out.print("Pretende ligar(0) todos os dispositivos da divisao ou desligar(1)?: ");
        ids[2] = sc.nextLine();
        sc.close();
        return ids;
    }
    
    /**
     * Cria um fornecedor para a casa.
     * @return propriedades do fornecedor.
     */
    public String[] createSupplier() {
        Scanner sc = new Scanner(System.in);
        String [] prop = new String[4];
        System.out.print("Indique o nome do fornecedor de energia: ");
        prop[0] = sc.nextLine();
        System.out.print("Indique o preço a que vende a energia: ");
        prop[1] = sc.nextLine();
        System.out.print("Indique o valor base do custo diario: ");
        prop[2] = sc.nextLine();
        System.out.print("Indique o valor da taxa de imposto(0% a 100%): ");
        prop[3] = sc.nextLine();
        sc.close();
        return prop; 
    }
    
    /**
     * Calcula o consumo.
     * @return propriedades de consumo.
     */
    public String[] calculateConsumption(){
        Scanner sc = new Scanner(System.in);
        String [] prop = new String[4];
        System.out.print("Indique o identificador da casa da qual pretende calcular o consumo: ");
        prop[1] = sc.nextLine();
        System.out.println("1 - Calcular o consumo diario");
        System.out.println("2 - Calcular o consumo entre dois dias");
        System.out.println("Indique a opcao: ");
        int option = sc.nextInt();
        prop[0] = String.valueOf(option);
        if (option == 2) {
            System.out.println("Indique o dia inicial da contagem: ");
            sc.nextLine();
            prop[2] = sc.nextLine();
            System.out.println("Indique o dia final da contagem: ");
            sc.nextLine();
            prop[3] = sc.nextLine();
        }
        sc.close();
        return prop;
    }

    
    /**
     * Print do Consumo.
     * @param consumption consumo.
     */
    public void printConsumption(float consumption){
        System.out.println(String.valueOf(consumption));
    }

    /**
     * Print do recebibo.
     * @param receipts recibo.  
     */
    public void printReceipts(String[] receipts){
        for (String receipt : receipts) {
            System.out.println("Fatura: ");
            System.out.println(receipt);
        }
    }

    /**
     * Muda o dia em que se encontra.
     * @return o dia.
     */
    public String chooseDay() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique o dia para o qual quer mudar: ");
        String day = sc.nextLine();
        sc.close();
        return day;
    }
    
    /**
     * Escolhe as estatísticas.
     * @return estatísticas escolhida.
     */
    public int choseStatistics() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique o que pretende calcular: ");
        System.out.println("1 - Casa que gastou mais num período");
        System.out.println("2 - Comercializador com maior volume de fatorização");
        System.out.println("3 - Listar as faturas de um comercializador");
        System.out.println("4 - Ordenar de forma decrescente os consumidores de energia num período");
        System.out.println("Indique a opcao: ");
        int option = sc.nextInt();

        while (option < 1 || option > 4) {
            invalidOption();
            option = sc.nextInt();
        }

        sc.close();
        return option;
    }

    public String[] chooseDatesToHouseConsumedMore(){
        Scanner sc = new Scanner(System.in);
        String [] prop = new String[2];
        System.out.println("Indique o periodo sobre o qual pretende saber a casa com o maior consumo: ");
        
        System.out.println("Indique o dia inicial do periodo: ");
        sc.nextLine();
        prop[0] = sc.nextLine();
        System.out.println("Indique o dia final do periodo: ");
        sc.nextLine();
        prop[1] = sc.nextLine();
        
        sc.close();
        return prop;
    }

    public void printHouseConsumedMore(String house){
        System.out.println("Casa que consumiu mais: ");
        System.out.println(house);        
    }

    public void printSupplierMoreReceipts(String supplier){
        System.out.println("Comercializador que ganhou mais: ");
        System.out.println(supplier);        
    }

    public String allReceiptsOfSupplier(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Indique o comercializador do qual quer ver as faturas: ");
        sc.nextLine();
        String prop = sc.nextLine();
        
        sc.close();
        return prop;
    }

    public void printAllReceiptsOfSupplier(String[] receipts){
        for (String receipt : receipts) {
            System.out.println("Fatura: ");
            System.out.println(receipt);
        }       
    }

    public String[] descOrderOfHouseConsumption(){
        Scanner sc = new Scanner(System.in);
        String [] prop = new String[2];
        System.out.println("Indique o periodo sobre o qual pretende a ordenação das casas com maior consumo: ");
        
        System.out.println("Indique o dia inicial do periodo: ");
        sc.nextLine();
        prop[0] = sc.nextLine();
        System.out.println("Indique o dia final do periodo: ");
        sc.nextLine();
        prop[1] = sc.nextLine();
        
        sc.close();
        return prop;
    }
    /**
     * Print das Casas
     */
    public void printDescOrderHouses(Object[] houses){
        for (Object house : houses) {
            System.out.println("Casa: ");
            System.out.println(house);
        }       
    }

    /**
     * Nome do ficheiro para carregar.
     * @return nome do ficheiro.
     */
    public String fileToLoad() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Identifique o nome do ficheiro que pretende carregar: ");
        String name = sc.nextLine();
        sc.close();
        return name;
    }

    /**
     * Nome do ficheiro para carregar.
     * @return nome do ficheiro.
     */
    public String fileToSave() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Identifique o nome do ficheiro em que pretende guardar o log: ");
        String name = sc.nextLine();
        sc.close();
        return name;
    }
    
    /**
     * Print caso haja uma opção inválida.
     */
    public void invalidOption() {
        System.out.println("Opcao invalida.");
    }
    
    /**
     * Print caso haja uma operação inválida.
     */
    public void invalidOperation() {
        System.out.println("Operacao invalida.");
    }
    
    /**
     * Print caso realize tudo com sucesso.
     */
    public void success() {
        System.out.println("Success");
    }
}