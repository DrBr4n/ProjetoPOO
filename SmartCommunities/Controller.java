import java.io.IOException;
import java.io.FilterInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller {

    private View view;
    private Community community; 
    private boolean run;
    

    public static void main(String[] args) {
        Controller controller = new Controller();

        do {
            controller.displayMenu();
            
        } while (controller.run);

    }

    public Controller() {
        this.view = new View();
        this.community = new Community();
        this.run = true;
    }

    public void displayMenu() {
        int option = this.view.menu();
        switch (option) {
            case 0:
                this.run = false;
                break;
            case 1:
                //createHouse();
                break;
            case 2:
                createSupplier();
                break;
            case 8:
                loadState();
                break;
            case 9:
                saveState();
                break;
            case 100:
                System.out.println(this.community.toString());
                break;
            case 99:
                loadFromLog();
                break;
            default:
                break;
        }
    }
    /**
     public void createHouse() {
         String houseId = community.createHouse(this.view.createHouse());
         boolean editing = true;
         do {
             String [] props = this.view.houseProps();
             switch (community.editHouse(houseId, props)) {
                 case 0:
                     this.view.success();
                     break;
                 case 1:
                     this.view.invalidOperation();
                     break;
                 case 2:
                     editing = false;
                     break;
                 default:
                     this.view.invalidOption();
                     break;
             }
         } while (editing);
     }
     */

    public void createSupplier() {
        String[] props = this.view.createSupplier();
        community.createSupplier(props);
    }

    public void saveState(){
        try{
            FileOutputStream fos = new FileOutputStream("state");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(community);
            oos.close();
            fos.close();
        }catch(IOException i){
            i.printStackTrace();
        }
    }

    public void loadState(){
        try{
            FileInputStream fis = new FileInputStream("state");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.community = (Community) ois.readObject();
            fis.close();
            ois.close();
        }catch(IOException i){
            i.printStackTrace();
        }catch(ClassNotFoundException c){
            c.printStackTrace();
        }
    }

    public void loadFromLog() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("log.txt"));
        }
        catch (FileNotFoundException e) {
                e.printStackTrace();
        }

        House currentHouse = new House();
        StringBuilder currentRoom = new StringBuilder();

        while (sc != null && sc.hasNextLine()) {
            String data = sc.nextLine();

            Pattern supplierP = Pattern.compile("(fornecedor):([a-z ]+)", Pattern.CASE_INSENSITIVE);
            Matcher supplierM = supplierP.matcher(data);

            Pattern houseP = Pattern.compile("(casa):([a-z ]+),(\\d+),([a-z ]+)", Pattern.CASE_INSENSITIVE);
            Matcher houseM = houseP.matcher(data);

            Pattern roomP = Pattern.compile("(divisao):([a-z0-9 ]+)", Pattern.CASE_INSENSITIVE);
            Matcher roomM = roomP.matcher(data);
            
            Pattern smartBulbP = Pattern.compile("(smartbulb):([a-z0-9 ]+),(\\d++),(\\d+.\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher smartBulbM = smartBulbP.matcher(data);
            
            Pattern smartSpeakerP = Pattern.compile("(smartspeaker):([a-z0-9 ]+),(\\d++),(\\d+.\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher smartSpeakerM = smartSpeakerP.matcher(data);
            
            Pattern smartCameraP = Pattern.compile("(smartcamera):([a-z0-9 ]+),(\\d++),(\\d+.\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher smartCameraM = smartCameraP.matcher(data);

            if (supplierM.find()) {
                Supplier supplier = new Supplier();
                supplier.setName(supplierM.group(2));
                this.community.addSupplier(supplier);
            } else if (houseM.find()) {
                House house = new House();
                house.setId('h' + String.valueOf(this.community.getHouseCounter()));
                this.community.setHouseCounter(this.community.getHouseCounter() + 1);
                house.setOwnerName(houseM.group(2));
                house.setNif(houseM.group(3));
                house.setSupplier(houseM.group(4));
                this.community.addHouse(house);
                currentHouse = new House(house); //use clone or not?
            } else if (roomM.find()) {
                currentHouse.addRoom(roomM.group(2));
                currentRoom.replace(0, currentRoom.length(), roomM.group(2));
            } else if (smartBulbM.find()) {
                SmartBulb device = new SmartBulb();
                device.setId('d' + String.valueOf(this.community.getDeviceCounter()));
                this.community.setDeviceCounter(this.community.getDeviceCounter() + 1);
                if (smartBulbM.group(2).equalsIgnoreCase("cold")) device.setMode(0);
                else if (smartBulbM.group(2).equalsIgnoreCase("neutral")) device.setMode(1);
                else if (smartBulbM.group(2).equalsIgnoreCase("warm")) device.setMode(2);
                device.setSize(Integer.parseInt(smartBulbM.group(3)));
                device.setDailyConsumption(Float.parseFloat(smartBulbM.group(4)));
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            } else if (smartSpeakerM.find()) {
                SmartSpeaker device = new SmartSpeaker();
                device.setId('d' + String.valueOf(this.community.getDeviceCounter()));
                this.community.setDeviceCounter(this.community.getDeviceCounter() + 1);
                device.setVolume(Integer.parseInt(smartSpeakerM.group(2)));
                device.setRadio(smartSpeakerM.group(3));
                device.setBrand(smartSpeakerM.group(4));
                device.setDailyConsumption(Float.parseFloat(smartSpeakerM.group(5)));
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            } else if (smartCameraM.find()) {
                SmartCamera device = new SmartCamera();
                device.setId('d' + String.valueOf(this.community.getDeviceCounter()));
                this.community.setDeviceCounter(this.community.getDeviceCounter() + 1);
                device.setResolution(smartCameraM.group(2));
                device.setFileSize(Integer.parseInt(smartCameraM.group(3)));
                device.setDailyConsumption(Float.parseFloat(smartCameraM.group(4)));
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            }
        }
        if (sc != null) sc.close(); 

    }
}