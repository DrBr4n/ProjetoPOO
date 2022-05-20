import java.io.IOException;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

public class Controller {

    private View view;
    private Community community; 
    private boolean run;
    private Queue<String[]> pending = new LinkedList<String[]>();

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
            case 3:
                changeDeviceState();
                break;
            case 8:
                try {
                    this.community = loadState();
                } catch (FileNotFoundException fne) {
                    fne.printStackTrace();
                } catch (IOException io) {
                    io.printStackTrace();
                } catch (ClassNotFoundException cnf) {
                    cnf.printStackTrace();
                }
                break;
            case 9:
                try {
                    saveState();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                break;
            case 10:
                try {
                    loadFromLog();
                } catch (FileNotFoundException fnf) {
                    fnf.printStackTrace();
                }
                break;
            case 11:
                try {
                    saveLog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 99:
                loadFromStorLog();
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

    public void changeDeviceState() {
        String[] ids = this.view.changeDeviceState();
        String[] cmd = new String[3];
        cmd[1] = ids[0];
        cmd[2] = ids[1];
        if (this.community.getHouses().get(ids[0]).getDevices().get(ids[1]).getOn() == true) {
            cmd[0] = "turnDeviceOff";
        } else {
            cmd[0] = "turnDeviceOn";
        }
        pending.add(cmd);
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
                    //house.setSupplier(props[1]);
                    String[] cmd = {"setSupplier", house.getId() ,props[1]};
                    pending.add(cmd);
                    break;
                default:
                    this.view.invalidOption();
                    break;
            }
        } while (editing);
    }

    public void createSupplier() {
        String[] props = this.view.createSupplier();
        Supplier supplier = new Supplier(props[0], Float.parseFloat(props[1]), Integer.parseInt(props[2]), Integer.parseInt(props[3]));
        this.community.addSupplier(supplier);
    }

    public void turnDeviceOn(String houseId, String deviceId) {
        this.community.getHouses().get(houseId).getDevices().get(deviceId).turnOn();
    }

    public void turnDeviceOff(String houseId, String deviceId) {
        this.community.getHouses().get(houseId).getDevices().get(deviceId).turnOn();
    }

    //EU
    public void calculateConsumption(String houseId, LocalDateTime dateI, LocalDateTime dateF){
        long days = ChronoUnit.DAYS.between(dateI, dateF);
        this.community.getHouses().get(houseId).calcConsumption();
    }

    public void saveState() throws IOException{
        FileOutputStream fos = new FileOutputStream("state.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.community);
        oos.flush();
        oos.close();
    }
   
    public Community loadState() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("state.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Community c = (Community) ois.readObject();
        ois.close();
        return c;
    }

    public void loadFromStorLog() {
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
            
            Pattern smartSpeakerP = Pattern.compile("(smartspeaker):(\\d++),([a-z0-9 ]+),([a-z0-9 ]+),(\\d+.\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher smartSpeakerM = smartSpeakerP.matcher(data);
            
            Pattern smartCameraP = Pattern.compile("(smartcamera):(\\(\\d+x\\d+\\)),(\\d++),(\\d+.\\d+)", Pattern.CASE_INSENSITIVE);
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
                //house.setSupplier(houseM.group(4));
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

    public void loadFromLog() throws FileNotFoundException{
        Scanner sc = null;
        String name = this.view.fileToLoad();
        sc = new Scanner(new File(name));
        House currentHouse = new House();
        StringBuilder currentRoom = new StringBuilder();

        while (sc != null && sc.hasNextLine()) {
            String data = sc.nextLine();

            Pattern supplierP = Pattern.compile("(supplier):([a-z ]+),(\\d+.\\d+),(\\d+),(\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher supplierM = supplierP.matcher(data);

            Pattern houseP = Pattern.compile("(house):(h[0-9]+),([a-z0-9 ]+),([a-z ]+),(\\d+),([a-z ]+)", Pattern.CASE_INSENSITIVE);
            Matcher houseM = houseP.matcher(data);

            Pattern roomP = Pattern.compile("(room):([a-z0-9 ]+)", Pattern.CASE_INSENSITIVE);
            Matcher roomM = roomP.matcher(data);
            
            //smartac:brand,dailyconsumption,instalationcost,on,mode,temperature
            Pattern smartAcP = Pattern.compile("(smartac):(d[0-9]+),([a-z0-9 ]+),(\\d+.\\d+),(\\d+.\\d+),(true|false),(\\d+),(\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher smartAcM = smartAcP.matcher(data);
            //smartbulb:brand,dailyconsumption,instalationcost,on,mode,size
            Pattern smartBulbP = Pattern.compile("(smartbulb):(d[0-9]+),([a-z0-9 ]+),(\\d+.\\d+),(\\d+.\\d+),(true|false),(\\d+),(\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher smartBulbM = smartBulbP.matcher(data);
            //smartcamera:brand,dailyconsumption,instalationcost,on,resolution,filesize
            Pattern smartCameraP = Pattern.compile("(smartcamera):(d[0-9]+),([a-z0-9 ]+),(\\d+.\\d+),(\\d+.\\d+),(true|false),(\\(\\d+x\\d+\\)),(\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher smartCameraM = smartCameraP.matcher(data);
            //smartspeaker:brand,dailyconsumption,instalationcost,on,radio,volume
            Pattern smartSpeakerP = Pattern.compile("(smartspeaker):(d[0-9]+),([a-z0-9 ]+),(\\d+.\\d+),(\\d+.\\d+),(true|false),([a-z0-9 ]+),(\\d{1,3})", Pattern.CASE_INSENSITIVE);
            Matcher smartSpeakerM = smartSpeakerP.matcher(data);
            //smarttv:brand,dailyconsumption,instalationcost,on,resolution,volume
            Pattern smartTvP = Pattern.compile("(smartv):(d[0-9]+),([a-z0-9 ]+),(\\d+.\\d+),(\\d+.\\d+),(true|false),(\\(\\d+x\\d+\\)),(\\d{1,3})", Pattern.CASE_INSENSITIVE);
            Matcher smartTvM = smartTvP.matcher(data);
        

            if (supplierM.find()) {
                Supplier supplier = new Supplier(supplierM.group(2),
                                                Float.parseFloat(supplierM.group(3)),
                                                Integer.parseInt(supplierM.group(4)),
                                                Integer.parseInt(supplierM.group(5)));
                this.community.addSupplier(supplier);
            } else if (houseM.find()) {
                House house = new House(houseM.group(2),
                                        houseM.group(3),
                                        houseM.group(4),
                                        houseM.group(5),
                                        houseM.group(6));
                this.community.addHouse(house);
                currentHouse = new House(house);
            } else if (roomM.find()) {
                currentHouse.addRoom(roomM.group(2));
                currentRoom.replace(0, currentRoom.length(), roomM.group(2));
            } else if (smartAcM.find()) {
                SmartAC device = new SmartAC(smartAcM.group(2),
                                                smartAcM.group(3),
                                                Float.parseFloat(smartAcM.group(4)),
                                                Float.parseFloat(smartAcM.group(5)),
                                                Boolean.parseBoolean(smartAcM.group(6)),
                                                Integer.parseInt(smartAcM.group(7)),
                                                Integer.parseInt(smartAcM.group(8)));
                this.community.increaseDeviceCounter();
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            } else if (smartBulbM.find()) {
                SmartBulb device = new SmartBulb(smartBulbM.group(2),
                                                smartBulbM.group(3),
                                                Float.parseFloat(smartBulbM.group(4)),
                                                Float.parseFloat(smartBulbM.group(5)),
                                                Boolean.parseBoolean(smartBulbM.group(6)),
                                                Integer.parseInt(smartBulbM.group(7)),
                                                Integer.parseInt(smartBulbM.group(8)));
                this.community.increaseDeviceCounter();
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            } else if (smartCameraM.find()) {
                SmartCamera device = new SmartCamera(smartCameraM.group(2),
                                                smartCameraM.group(3),
                                                Float.parseFloat(smartCameraM.group(4)),
                                                Float.parseFloat(smartCameraM.group(5)),
                                                Boolean.parseBoolean(smartCameraM.group(6)),
                                                smartCameraM.group(7),
                                                Integer.parseInt(smartCameraM.group(8)));
                this.community.increaseDeviceCounter();
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            } else if (smartSpeakerM.find()) {
                SmartSpeaker device = new SmartSpeaker(smartSpeakerM.group(2),
                                                smartSpeakerM.group(3),
                                                Float.parseFloat(smartSpeakerM.group(4)),
                                                Float.parseFloat(smartSpeakerM.group(5)),
                                                Boolean.parseBoolean(smartSpeakerM.group(6)),
                                                smartSpeakerM.group(7),
                                                Integer.parseInt(smartSpeakerM.group(8)));
                this.community.increaseDeviceCounter();
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            } else if (smartTvM.find()) {
                SmartTV device = new SmartTV(smartTvM.group(2),
                                                smartTvM.group(3),
                                                Float.parseFloat(smartTvM.group(4)),
                                                Float.parseFloat(smartTvM.group(5)),
                                                Boolean.parseBoolean(smartTvM.group(6)),
                                                Integer.parseInt(smartTvM.group(7)),
                                                Integer.parseInt(smartTvM.group(8)));
                this.community.increaseDeviceCounter();
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            }
        }
        if (sc != null) sc.close(); 
    }
    
    public void saveLog() throws IOException  {
        String name = this.view.fileToSave();
        File file = new File(name);
        try {
            if (!file.createNewFile()) {
                //sub for file already exists exception
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        String log = this.community.toLog();
        byte[] bytes = log.getBytes();
        outputStream.write(bytes);
        outputStream.close();
    }

    public void runQueue() {
        Iterator<String[]> iterator = pending.iterator();
        while(iterator.hasNext()){
            String[] cmd = iterator.next();

            if (cmd[0].equalsIgnoreCase("setSupplier")) {
                community.getHouses().get(cmd[1]).setSupplier(community.getSuppliers().get(cmd[2]));
            } else if (cmd[0].equalsIgnoreCase("turnDeviceOn")) {
                community.getHouses().get(cmd[1]).getDevices().get(cmd[2]).turnOn();
            } else if (cmd[0].equalsIgnoreCase("turnDeviceOff")) {
                community.getHouses().get(cmd[1]).getDevices().get(cmd[2]).turnOff();
            }
        }
    }
}