import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
/**
 * Write a description of class Controller here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Controller {

    private View view;
    private Community community; 
    private Map<String, Supplier> suppliers;
    private Map<String, SmartDevice> smartDevices;
    private Map<String, House> houses;
    private int houseCounter;
    private int deviceCounter;
    private boolean run;

    public static void main(String[] args) {
       Controller cont = new Controller();
       Community community = new Community();
        do {
            cont.displayMenu();

        } while (cont.run);

    }

    
    public Controller() {
        this.view = new View();
        this.community = new Community();
        this.suppliers = new HashMap<>();
        this.houses = new HashMap<>();
        this.smartDevices = new HashMap<>();
        this.run = true;

    }

    public void displayMenu() {

        int option = this.view.menu();

        switch (option) {
            case 0:
                this.run = false;
                break;
            case 1:
                createDevice();
                break;
            case 2:
                createHouse();
                break;
            case 3:
                createSupplier();
                break;
            case 10:
                writeFile();
                break;
            case 99:
                viewDevices();
                break;
            case 100:
                System.out.print(this.toString());
                break;
            default:
                break;
        }
    }
    
    public void createDevice() {
        int option = this.view.choseDevice();
        String [] props = this.view.deviceProps(option);

        switch (option) {
            case 1:
                SmartAC ac = new SmartAC('d' + String.valueOf(this.deviceCounter++), props[0], Integer.parseInt(props[1]), Float.parseFloat(props[2]), false, Integer.parseInt(props[3]), Integer.parseInt(props[4]));
                smartDevices.put(ac.getId(), ac);
                break;
            case 2:
                SmartTV tv = new SmartTV('d' + String.valueOf(this.deviceCounter++), props[0], Integer.parseInt(props[1]), Float.parseFloat(props[2]), false, Integer.parseInt(props[3]), Integer.parseInt(props[4]));
                smartDevices.put(tv.getId(), tv);
                break;
            case 3:
                SmartSpeaker sp = new SmartSpeaker('d' + String.valueOf(this.deviceCounter++), props[0], Integer.parseInt(props[1]), Float.parseFloat(props[2]), false, props[3], Integer.parseInt(props[4]));
                smartDevices.put(sp.getId(), sp);
                break;
            case 4:
                SmartBulb bb = new SmartBulb('d' + String.valueOf(this.deviceCounter++), props[0], Integer.parseInt(props[1]), Float.parseFloat(props[2]), false, Integer.parseInt(props[3]), Integer.parseInt(props[4]));
                smartDevices.put(bb.getId(), bb);
                break;
            case 5:
                SmartCamera cm = new SmartCamera('d' + String.valueOf(this.deviceCounter++), props[0], Integer.parseInt(props[1]), Float.parseFloat(props[2]), false, Integer.parseInt(props[3]), Integer.parseInt(props[4]));
                smartDevices.put(cm.getId(), cm);
                break;
            default:
                this.view.invalidOption();
            break;
        }
    }

    public void createHouse() {
        String [] ids = this.view.createHouse();
        
        Map<String, SmartDevice> emptyDevicesMap = new HashMap<>();
        Map<String, Map<String, SmartDevice>> emptyRoomMap = new HashMap<>();

        House house = new House('h' + String.valueOf(this.houseCounter++), ids[0], ids[1], emptyDevicesMap, emptyRoomMap);
        
        boolean editing = true;
        
        do {
            String [] props = this.view.houseProps();
            
            switch (Integer.parseInt(props[0])) {
                case 0:
                    editing = false;
                    break;
                case 1:
                    house.addRoom(props[1]);
                    break;
                case 2:
                    if (house.existRoom(props[1]) && smartDevices.containsKey(props[2])) {
                        house.addDeviceToRoom(props[1], props[2]);
                    } else {
                        this.view.invalidOperation();
                    }
                    break;
                default:
                    this.view.invalidOption();
                    break;
            }
        } while (editing);
        
        houses.put(house.getAddress(), house);
        community.addHouse(house);
    }

    public void createSupplier() {
        String[] props = this.view.createSupplier();
        Supplier s = new Supplier(props[0], Float.parseFloat(props[1]), Integer.parseInt(props[2]));
        suppliers.put(s.getName(), s);
    }

    public void viewDevices() {
        smartDevices.values().stream().map(SmartDevice::toString).forEach(System.out::println);
    }

    public void viewHouses() {
        houses.values().stream().map(House::toString).forEach(System.out::println);
    }

    public void viewSuppliers() {
        suppliers.values().stream().map(Supplier::toString).forEach(System.out::println);
    }

    public Map<String, House> getHouses(){
        return this.houses;
    }

    public void writeFile(){
        try{
            FileOutputStream fos = new FileOutputStream("state.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(community);
            oos.close();
            fos.close();
        }catch(IOException i){
            i.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nDevices: ");
        smartDevices.values().stream().map(SmartDevice::toString).forEach(sb::append);
        sb.append("\nHouses: ");
        houses.values().stream().map(House::toString).forEach(sb::append);
        sb.append("\nSuppliers: ");
        suppliers.values().stream().map(Supplier::toString).forEach(sb::append);

        return sb.toString();
    }
}