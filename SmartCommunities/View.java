import java.util.Scanner;

/**
 * Write a description of class View here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class View
{
    public String menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Smart Community--------");
        System.out.println("1 - Criar Casas");
        System.out.println("2 - Criar Fornecedores de energia");
        System.out.println("3 - Modificar estado de um dispositivo");
        System.out.println("4 - Avançar o tempo");
        System.out.println("5 - Calcular o consumo");
        System.out.println("6 - Gerar fatura");
        System.out.println("7 - Ver estatisticas");
        System.out.println("8 - Carregar estado");
        System.out.println("9 - Guardar estado");
        System.out.println("0 - Terminar");
        System.out.print("Indique a opcao: ");

        String option = sc.next();

        sc.close();
        return option;
    }

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

    public String[] editHouse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------Criar Casa-------");
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
        System.out.print("Indique o dispositivo que pretende adicionar: ");
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

    public String[] deviceProps(int option) {
        String[] properties = new String[6];
        Scanner sc = new Scanner(System.in);

        System.out.print("Indique a marca do dispositivo: ");
        properties[0] = sc.nextLine();
        System.out.print("Indique o consumo diario do dispositivo: ");
        properties[1] = sc.nextLine();
        System.out.print("Indique o custo da instalaçao do dispositivo: ");
        properties[2] = sc.nextLine();
        System.out.print("Indique se o dispositivo esta ligado: /n true - ligado/n false - desligado");
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

    public String[] changeDeviceState() {
        Scanner sc = new Scanner(System.in);
        String[] ids = new String[2];
        System.out.print("Indique o id da casa do dispositivo que pertende alterar: ");
        ids[0] = sc.nextLine();
        System.out.print("Indique o id do dispositivo que pertende alterar: ");
        ids[1] = sc.nextLine();
        sc.close();
        return ids;
    }

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

    public String[] calculateConsumption(){
        Scanner sc = new Scanner(System.in);
        String [] prop = new String[1];
        System.out.print("Indique o identificador da casa da qual pretende calcular o consumo: ");
        prop[0] = sc.nextLine();
        sc.close();
        return prop;
    }

    public String fileToLoad() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Identifique o nome do ficheiro que pretende carregar: ");
        String name = sc.nextLine();
        sc.close();
        return name;
    }

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
