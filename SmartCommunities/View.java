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

        int option = sc.nextInt();

        sc.close();
        return option;
    }

    public String[] createHouse() {
        Scanner sc = new Scanner(System.in);
        String [] houseInput = new String[2];
        System.out.println("-------Criar Casa-------");
        System.out.print("Indique a morada da casa: ");
        houseInput[0] = sc.nextLine();
        System.out.print("Indique o nome do dono da casa: ");
        houseInput[1] = sc.nextLine();
        sc.close();
        return houseInput;
    }

    public String[] houseProps() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------Criar Casa-------");
        System.out.println("1 - Adicionar Divisoes");
        System.out.println("2 - Adicionar Dispositivos");
        System.out.println("0 - Voltar ao menu inicial");
        System.out.print("Indique a opcao: ");
        int option = sc.nextInt();
        String [] properties = new String[9]; // {OptionCreateOrEdit, Room, DeviceType, DeviceId, Marca, ConsumoDiario, Custo, Rng1, Rng2}
        properties[0] = String.valueOf(option);
        if(option == 1 ){
            System.out.print("Indique a divisao que pretende adicionar: ");
            sc.nextLine();
            properties[1] = sc.nextLine();
        } else if (option == 2) {
            System.out.print("Indique a divisao a que pretende adicionar o dispositivo: ");
            sc.nextLine();
            properties[1] = sc.nextLine();
            System.out.print("Indique o dispositivo que pretende adicionar: ");
            this.deviceProps(this.choseDevice(), properties);
        }
        sc.close();
        return properties;
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

        while (option < 1 || option > 5) {
            invalidOption();
            option = sc.nextInt();
        }

        sc.close();
        return option;
    }

    public void deviceProps(int option, String[] properties) {
        Scanner sc = new Scanner(System.in);
        properties[2] = String.valueOf(option);
        System.out.print("Indique a marca do dispositivo: ");
        properties[4] = sc.nextLine();
        System.out.print("Indique o consumo diario do dispositivo: ");
        properties[5] = sc.nextLine();
        System.out.print("Indique o custo da instalaçao do dispositivo: ");
        properties[6] = sc.nextLine();

        switch (option) {
            case 1:
                System.out.print("Indique o modo do dispositivo: ");
                properties[7] = sc.nextLine();
                System.out.print("Indique a temperatura do dispositivo: ");
                properties[8] = sc.nextLine();

                break;
            case 2:
                System.out.print("Indique a resolucao do dispositivo: ");
                properties[7] = sc.nextLine();
                System.out.print("Indique o volume do dispositivo: ");
                properties[8] = sc.nextLine();

                break;
            case 3:
                System.out.print("Indique a radio do dispositivo: ");
                properties[7] = sc.nextLine();
                System.out.print("Indique o volume do dispositivo: ");
                properties[8] = sc.nextLine();  

                break;
            case 4:
                System.out.print("Indique o modo do dispositivo: ");
                properties[7] = sc.nextLine();
                System.out.print("Indique o tamanho do dispositivo: ");
                properties[8] = sc.nextLine();

                break;
            case 5:
                System.out.print("Indique a resolucao do dispositivo: ");
                properties[7] = sc.nextLine();
                System.out.print("Indique o tamanho de ficheiros do dispositivo: ");
                properties[8] = sc.nextLine();
                break;
            default:
                break;  
        }
        sc.close();
    }

    public String[] createSupplier() {
        Scanner sc = new Scanner(System.in);
        String [] prop = new String[3];
        System.out.print("Indique o nome do fornecedor de energia: ");
        prop[0] = sc.nextLine();
        System.out.print("Indique o preço a que vende a energia: ");
        prop[1] = sc.nextLine();
        System.out.print("Indique o valor base do custo diario: ");
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

    public void success() {
        System.out.println("Success");
    }

}
