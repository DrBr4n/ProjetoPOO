import java.util.Scanner;

/**
 * Write a description of class View here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class View
{
    public int menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Smart Community--------");
        System.out.println("1 - Criar Dispositivos");
        System.out.println("2 - Criar Casas");
        System.out.println("3 - Criar Fornecedores de energia");
        System.out.println("4 - Modificar estado de um dispositivo");
        System.out.println("5 - Avançar o tempo");
        System.out.println("6 - Calcular o consumo");
        System.out.println("7 - Gerar fatura");
        System.out.println("8 - Ver estatisticas");
        System.out.println("9 - Carregar ficheiro");
        System.out.println("\n0 - Terminar");
        System.out.print("Indique a opcao: ");

        int option = sc.nextInt();
        //while (option < 1 || option > 9) {
        //    invalidOption();
        //    option = sc.nextInt();
        //}

        sc.close();
        return option;
    }

    public int choseDevice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - SmartAC");
        System.out.println("2 - SmartTV");
        System.out.println("3 - SmartSpeaker");
        System.out.println("4 - SmartBulb");
        System.out.println("5 - SmartCamera");
        System.out.print("Indique a opcao: ");
        int option = sc.nextInt();

        while (option < 1 || option > 9) {
            invalidOption();
            option = sc.nextInt();
        }

        sc.close();
        return option;
    }

    public String[] deviceProps(int option) {
        Scanner sc = new Scanner(System.in);
        String [] props = new String[5];

        System.out.println("Indique a marca do dispositivo:");
        props[0] = sc.nextLine();
        System.out.println("Indique o consumo diario do dispositivo:");
        props[1] = sc.nextLine();
        System.out.println("Indique o custo da instalaçao do dispositivo:");
        props[2] = sc.nextLine();

        switch (option) {
            case 1:
                System.out.println("Indique o modo do dispositivo:");
                props[3] = sc.nextLine();
                System.out.println("Indique a temperatura do dispositivo:");
                props[4] = sc.nextLine();
                
                break;
            case 2:
                System.out.println("Indique a resolucao do dispositivo:");
                props[3] = sc.nextLine();
                System.out.println("Indique o volume do dispositivo:");
                props[4] = sc.nextLine();
                
                break;
            case 3:
                System.out.println("Indique a radio do dispositivo:");
                props[3] = sc.nextLine();
                System.out.println("Indique o volume do dispositivo:");
                props[4] = sc.nextLine();  
                         
                break;
            case 4:
                System.out.println("Indique o modo do dispositivo:");
                props[3] = sc.nextLine();
                System.out.println("Indique o tamanho do dispositivo:");
                props[4] = sc.nextLine();
                
                break;
            case 5:
                System.out.println("Indique a resolucao do dispositivo:");
                props[3] = sc.nextLine();
                System.out.println("Indique o tamanho de ficheiros do dispositivo:");
                props[4] = sc.nextLine();
                break;
            default:
                break;  
        }
        sc.close();
        return props;
    }

    public String[] createHouse() {
        Scanner sc = new Scanner(System.in);
        String [] prop = new String[2];
        System.out.println("-------Criar Casa-------");
        System.out.println("Indique a morada da casa: ");
        prop[0] = sc.nextLine();
        System.out.println("Indique o nome do dono da casa: ");
        prop[1] = sc.nextLine();
        sc.close();
        return prop;
    }

    public String[] houseProps() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------Criar Casa-------");
        System.out.println("1 - Adicionar Divisoes");
        System.out.println("2 - Adicionar Dispositivos");
        System.out.println("\n0 - Voltar ao menu inicial");
        System.out.print("Indique a opcao: ");
        int option = sc.nextInt();
        String [] prop = new String[3];
        prop[0] = String.valueOf(option);
        if(option == 1 ){
            System.out.println("Indique a divisao que pretende adicionar:");
            prop[1] = sc.nextLine();
            prop[2] = null;
        } else if (option == 2) {
            System.out.println("Indique a divisao a que pretende adicionar o dispositivo:");
            prop[1] = sc.nextLine();
            System.out.println("Indique o id do dispositivo que pretende adicionar:");
            prop[2] = sc.nextLine();
        }
        sc.close();
        return prop;
    }

    public String[] createSupplier() {
        Scanner sc = new Scanner(System.in);
        String [] prop = new String[3];
        System.out.println("Indique o nome do fornecedor de energia: ");
        prop[0] = sc.nextLine();
        System.out.println("Indique o preço a que vende a energia: ");
        prop[1] = sc.nextLine();
        System.out.println("Indique o valor base do custo diario: ");
        prop[2] = sc.nextLine();
        sc.close();
        return prop;
    }
        
    public void invalidOption() {
        System.out.println("Opcao invalida.");
    }

    public void invalidOperation() {
        System.out.println("Operacao invalida.");
    }

}
