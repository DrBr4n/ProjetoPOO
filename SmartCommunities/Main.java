import java.util.*;
/**
 * Escreva a descrição da classe Main aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Main
{
    public static void main(String[] args) throws Exception {

        Manage m = new Manage();
        /*
        do {
            int choice = Menu();
            switch (choice) {
                case 1:
                    m.addSmartDevice(createSmartDevice());                    
                    break;
                case 2:
                    m.addHouse(createHouse());
                    break;
                case 3:
                    m.addSupplier(createSupplier());
                    break;
                /*
                case 4:
                
                    break;
                case 5:
                   
                    break;
                case 6:
                   
                    break;
                case 7:
                   
                    break;
                case 8:
                   
                    break;
                case 9:
                  
                    break;
               
                case 0:
                    System.out.println("Terminado");
                    return;
            }
        }while(true);
        */
    }
   
    
    public static int Menu(){
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
        return sc.nextInt();
    }
    
    public static SmartDevice createSmartDevice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique o identificador do dispositivo:");
        String id = sc.nextLine();
        System.out.println("Indique a marca do dispositivo:");
        String brand = sc.nextLine();
        System.out.println("Indique o consumo diario do dispositivo:");
        int dailyConsumption = sc.nextInt();
        System.out.println("Indique o custo da instalaçao do dispositivo:");
        float installationCost = sc.nextFloat();
        boolean on = false;
        
        //SmartDevice sd = new SmartDevice(id, brand, dailyConsumption, installationCost, on);
        //SmartDevice sd = new SmartDevice();
        return null;
    }
    
    public static House createHouse(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique a morada da casa: ");
        String address = sc.nextLine();
        System.out.println("Indique o nome do dono da casa: ");
        String ownerName = sc.nextLine();
        
        House house = new House(address, ownerName);
        
        do{
            System.out.println("-------Criar Casa-------");
            System.out.println("1 - Adicionar Divisoes");
            System.out.println("2 - Adicionar Dispositivos");
            System.out.println("\n0 - Voltar ao menu inicial");
            
            int choice = sc.nextInt();
            if(choice == 0) return house;
            
            switch(choice){
                case 1:
                    System.out.println("Indique a divisao que pretende adicionar:");
                    sc.nextLine();
                    String room = sc.nextLine();
                    house.addRoom(room);
                    break;
                case 2:
                    System.out.println("Indique a divisao a que pretende adicionar o dispositivo:");
                    sc.nextLine();
                    String roomD = sc.nextLine();
                    if(house.existRoom(roomD)){
                        System.out.println("Indique o dispositivo que pretende adicionar:");
                        String device = sc.nextLine();
                        house.addDeviceToRoom(roomD, device);
                    }else{
                        System.out.println("A divisao " + roomD + " nao existe nesta casa.");
                    }
                    break;
            }
        }while(true);
    }
    
    public static Supplier createSupplier(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique o nome do fornecedor de energia: ");
        String name = sc.nextLine();
        System.out.println("Indique o preço a que vende a energia: ");
        float price = sc.nextFloat();
        System.out.println("Indique o valor base do custo diario: ");
        int dailyCost = sc.nextInt();
        
        Supplier s = new Supplier(name, price, dailyCost);
        return s;
    }
 
}
