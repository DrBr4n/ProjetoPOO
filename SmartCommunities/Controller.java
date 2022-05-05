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
import java.lang.Boolean;


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
        int option = Integer.parseInt(this.view.menu());
        switch (option) {
            case 0:
                this.run = false;
                break;
            case 1:
                createHouse();
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
            case 99:
                loadFromLog();
                break;
            case 100:
                System.out.println(this.community.toLog());
                break;
            case 101:
                System.out.println(this.community.toString());
                break;
            default:
                break;
        }
    }

    public SmartDevice createDevice(int deviceType, String[] props) {
        switch (deviceType) {
            case 1:
                SmartAC ac = new SmartAC('d' + String.valueOf(this.community.getDeviceCounter()), props[0], Float.parseFloat(props[1]), Float.parseFloat(props[2]), Boolean.parseBoolean(props[3]), Integer.parseInt(props[4]), Integer.parseInt(props[5]));
                this.community.increaseDeviceCounter();
                return ac;
            case 2:
                SmartTV tv = new SmartTV('d' + String.valueOf(this.community.getDeviceCounter()), props[0], Float.parseFloat(props[1]), Float.parseFloat(props[2]), Boolean.parseBoolean(props[3]), Integer.parseInt(props[4]), Integer.parseInt(props[5]));
                this.community.increaseDeviceCounter();
                return tv;
            case 3:
                SmartSpeaker sp = new SmartSpeaker('d' + String.valueOf(this.community.getDeviceCounter()), props[0], Float.parseFloat(props[1]), Float.parseFloat(props[2]), Boolean.parseBoolean(props[3]), props[4], Integer.parseInt(props[5]));
                this.community.increaseDeviceCounter();
                return sp;
            case 4:
                SmartBulb bb = new SmartBulb('d' + String.valueOf(this.community.getDeviceCounter()), props[0], Float.parseFloat(props[1]), Float.parseFloat(props[2]), Boolean.parseBoolean(props[3]), Integer.parseInt(props[4]), Integer.parseInt(props[5]));
                this.community.increaseDeviceCounter();
                return bb;
            case 5:
                SmartCamera cm = new SmartCamera('d' + String.valueOf(this.community.getDeviceCounter()), props[0], Float.parseFloat(props[1]), Float.parseFloat(props[2]), Boolean.parseBoolean(props[3]), props[4], Integer.parseInt(props[5]));
                this.community.increaseDeviceCounter();
                return cm;
            default:
                return null;
        }
    }

    public void createHouse() {
        String[] props = this.view.createHouse();
        House house = new House('h' + String.valueOf(this.community.getHouseCounter()), props[0], props[1], props[2], props[3]);
        this.community.increaseHouseCounter();
        this.community.addHouse(house);
        this.editHouse(house);
    }

    public void editHouse(House house) {
        boolean editing = true;
        do {
            String [] props = this.view.editHouse();
            switch (Integer.parseInt(props[0])) {
                case 0:
                    editing = false;
                    break;
                case 1:
                    house.addRoom(props[1]);
                    break;
                case 2:
                    int deviceType = this.view.choseDevice();
                    SmartDevice sd = this.createDevice(deviceType, this.view.deviceProps(deviceType));
                    house.addDeviceToRoom(props[1], sd);
                    break;
                case 3:
                    house.setSupplier(props[1]);
                    break;
                default:
                    this.view.invalidOption();
                    break;
            }
        } while (editing);
    }

    public void createSupplier() {
        String[] props = this.view.createSupplier();
        Supplier supplier = new Supplier(props[0], Float.parseFloat(props[1]), Integer.parseInt(props[2]));
        this.community.addSupplier(supplier);
    }

    public void turnHouseOn(String houseId) {
        this.community.getHouses().get(houseId).turnAllOn();
    }

    public void turnHouseOff(String houseId) {
        this.community.getHouses().get(houseId).turnAllOff();
    }

    public void turnDeviceOn(String houseId, String deviceId) {
        this.community.getHouses().get(houseId).getDevices().get(deviceId).turnOn();
    }

    public void turnDeviceOff(String houseId, String deviceId) {
        this.community.getHouses().get(houseId).getDevices().get(deviceId).turnOn();
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
            sc = new Scanner(new File("sLog.txt"));
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
                this.community.increaseHouseCounter();
                house.setOwnerName(houseM.group(2));
                house.setNif(houseM.group(3));
                house.setSupplier(houseM.group(4));
                this.community.addHouse(house);
                currentHouse = new House(house);
            } else if (roomM.find()) {
                currentHouse.addRoom(roomM.group(2));
                currentRoom.replace(0, currentRoom.length(), roomM.group(2));
            } else if (smartBulbM.find()) {
                SmartBulb device = new SmartBulb();
                device.setId('d' + String.valueOf(this.community.getDeviceCounter()));
                this.community.increaseDeviceCounter();
                if (smartBulbM.group(2).equalsIgnoreCase("cold")) device.setMode(0);
                else if (smartBulbM.group(2).equalsIgnoreCase("neutral")) device.setMode(1);
                else if (smartBulbM.group(2).equalsIgnoreCase("warm")) device.setMode(2);
                device.setSize(Integer.parseInt(smartBulbM.group(3)));
                device.setDailyConsumption(Float.parseFloat(smartBulbM.group(4)));
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            } else if (smartSpeakerM.find()) {
                SmartSpeaker device = new SmartSpeaker();
                device.setId('d' + String.valueOf(this.community.getDeviceCounter()));
                this.community.increaseDeviceCounter();
                device.setVolume(Integer.parseInt(smartSpeakerM.group(2)));
                device.setRadio(smartSpeakerM.group(3));
                device.setBrand(smartSpeakerM.group(4));
                device.setDailyConsumption(Float.parseFloat(smartSpeakerM.group(5)));
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            } else if (smartCameraM.find()) {
                SmartCamera device = new SmartCamera();
                device.setId('d' + String.valueOf(this.community.getDeviceCounter()));
                this.community.increaseDeviceCounter();
                device.setResolution(smartCameraM.group(2));
                device.setFileSize(Integer.parseInt(smartCameraM.group(3)));
                device.setDailyConsumption(Float.parseFloat(smartCameraM.group(4)));
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            }
        }
        if (sc != null) sc.close(); 
    }
}