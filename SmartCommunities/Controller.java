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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

public class Controller {
    /**
     * Contrutores da classe SmartAC.
     * Variáveis de instância. 
     */

    private View view;
    private Community community; 
    private boolean run;
    private Queue<String[]> pending;

    /**
     * Função principal
     */
    public static void main(String[] args) {
        Controller controller = new Controller();

        do {
            controller.displayMenu();
            
        } while (controller.run);

    }
    /**
     * Função Controller.
     */
    public Controller() {
        this.view = new View();
        this.community = new Community();
        this.run = true;
        this.pending = new LinkedList<String[]>();
    }

    /**
     * Menu
     */
    public void displayMenu() {
        int option = Integer.parseInt(this.view.menu());
        switch (option) {
            case 0: //Terminar
                this.run = false;
                break;
            case 1: //Criar Casas
                createHouse();
                break;
            case 2: //Criar Fornecedores
                createSupplier();
                break;
            case 3: //Modificar estado de um dispositivo
                changeDeviceState();
                break;
            case 4: //Calcular o consumo
                calculateConsumption();
                break;
            case 5: //Mudar data
                changeDate();
                break;
            case 6: //Estatisticas

                break;
            case 7: //Carregar ficheiro de objetos
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
            case 8: //Guardar num ficheiro de objetos
                try {
                    saveState();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                break;    
            case 9: //Carregar ficheiro de texto
                try {
                    loadFromLog();
                } catch (FileNotFoundException fnf) {
                    fnf.printStackTrace();
                }
                //loadFromStorLog();
                break;
            case 10: //Guardar num ficheiro de texto
                try {
                    saveLog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    /**
     * Cria dispositivos e incrementa o contador de dispositivos.
     * @param deviceType dispositivo.
     * @param props propriedades necessárias para a criação do dispositivo.
     */
    public SmartDevice createDevice(int deviceType, String[] props) {
        switch (deviceType) {
            case 1:
                SmartAC ac = new SmartAC('d' + String.valueOf(this.community.getDeviceCounter()), props[0], Float.parseFloat(props[1]), Float.parseFloat(props[2]), Boolean.parseBoolean(props[3]), Integer.parseInt(props[4]), Integer.parseInt(props[5]));
                this.community.increaseDeviceCounter();
                return ac;
            case 2:
                SmartTV tv = new SmartTV('d' + String.valueOf(this.community.getDeviceCounter()), props[0], Float.parseFloat(props[1]), Float.parseFloat(props[2]), Boolean.parseBoolean(props[3]), props[4], Integer.parseInt(props[5]));
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
    
    /**
     * Altera o estado dos dispositivos existentes.
     */
    public void changeDeviceState() {
        int option = this.view.chooseIfRoomOrDevice();
        String[] cmd = new String[3];
        if (option == 1) {
            String[] ids = this.view.changeDeviceState();
            cmd[1] = ids[0];
            cmd[2] = ids[1];
            if (this.community.getHouses().get(ids[0]).getDevices().get(ids[1]).getOn() == true) {
                cmd[0] = "turnDeviceOff";
            } else {
                cmd[0] = "turnDeviceOn";
            }
        } else if (option == 2) {
            String[] ids = this.view.changeRoomState();
            cmd[1] = ids[0];
            cmd[2] = ids[1];
            if (ids[2].compareTo("0") == 0) {
                cmd[0] = "turnRoomOn";
            } else if (ids[2].compareTo("1") == 0) {
                cmd[0] = "turnRoomOff";
            }
        }
        pending.add(cmd);
    }
    
    /** 
     * Cria a Casa.
     */
    public void createHouse() { //throws supplierDoesntExistException
        String[] props = this.view.createHouse();
        this.community.increaseHouseCounter();
        House house = new House('h' + String.valueOf(this.community.getHouseCounter()), props[0], props[1], props[2], this.community.getSuppliers().get(props[3]));
        this.community.addHouse(house);
        this.editHouse(house);  
    }
    
    /**
     * Altera as propriedades da casa.
     */
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
                    String[] cmd = {"setSupplier", house.getId() ,props[1]};
                    pending.add(cmd);
                    break;
                default:
                    this.view.invalidOption();
                    break;
            }
        } while (editing);
    }

    /**
     * Cria Fornecedor e adiciona-o à comunidade 
     */
    public void createSupplier() {
        String[] props = this.view.createSupplier();
        Supplier supplier = new Supplier(props[0], Float.parseFloat(props[1]), Integer.parseInt(props[2]), Float.parseFloat(props[3]));
        this.community.addSupplier(supplier);
    }
    
    /**
     * Muda o estado de um dispositivo específico para on.
     * @param houseId.
     * @param deviceId. 
     */
    public void turnDeviceOn(String houseId, String deviceId) {
        this.community.turnDeviceOn(houseId, deviceId);
    }
    
    /**
     * Muda o estado de um dispositivo específico para off. 
     * @param houseId.
     * @param deviceId. 
     */
    public void turnDeviceOff(String houseId, String deviceId) {
        this.community.turnDeviceOff(houseId, deviceId);
    }

    /**
     * Calcula o consumo.
     */
    public void calculateConsumption(){
        String[] prop = this.view.calculateConsumption();
        if(Integer.parseInt(prop[0]) == 1){
            this.view.printConsumption(this.community.getHouses().get(prop[1]).calcConsumption());
        }
        else if(Integer.parseInt(prop[0]) == 2){
            if(LocalDate.parse(prop[3]).isAfter(LocalDate.parse(prop[2]))){
                if(LocalDate.parse(prop[2]).isAfter(this.community.getDate())){
                    long days = ChronoUnit.DAYS.between(LocalDate.parse(prop[2]), LocalDate.parse(prop[3]));
                    this.view.printConsumption(this.community.getHouses().get(prop[1]).calcConsumption() * days);
                }
            }
            
        }
    }
    
    /**
     * Altera a data que se encontra.  
     */
    public void changeDate() {
        LocalDate date = LocalDate.parse(this.view.chooseDay());
        if (date.isAfter(community.getDate())) {
            community.setLastDate(community.getDate());
            community.setDate(date);
        } else {
            //throw invalidDate?
        }
        String[] receipts = community.generateReceipts();
        view.printReceipts(receipts);
        runQueue();
    }
    
    /**
     * Executa os pedidos pendentes.
     */
    public void runQueue() {
        Iterator<String[]> iterator = pending.iterator();
        while(iterator.hasNext()){
            String[] cmd = iterator.next();

            if (cmd[0].equalsIgnoreCase("setSupplier")) {
                community.setSupplier(cmd[1], cmd[2]);
            } else if (cmd[0].equalsIgnoreCase("turnDeviceOn")) {
                community.turnDeviceOn(cmd[1], cmd[2]);
            } else if (cmd[0].equalsIgnoreCase("turnDeviceOff")) {
                community.turnDeviceOff(cmd[1], cmd[2]);
            } else if (cmd[0].equalsIgnoreCase("turnRoomOn")) {
                this.community.turnRoomOn(cmd[1], cmd[2]);
            } else if (cmd[0].equalsIgnoreCase("turnRoomOff")) {
                this.community.turnRoomOff(cmd[1], cmd[2]);
            }
        }
    }
    
    /**
     * Guarda o estado para o ficheiro de 
     */
    public void saveState() throws IOException{
        FileOutputStream fos = new FileOutputStream("state.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.community);
        oos.flush();
        oos.close();
    }
   
   /**
    * Carrega o estado a partir de um ficheiro de objetos.
    */
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
    
    /**
     * Carrega estado a partir de um ficheiro log.
     */ 
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
            
            //smartac:id,brand,dailyconsumption,instalationcost,on,mode,temperature
            Pattern smartAcP = Pattern.compile("(smartac):(d[0-9]+),([a-z0-9 ]+),(\\d+.\\d+),(\\d+.\\d+),(true|false),(\\d+),(\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher smartAcM = smartAcP.matcher(data);
            //smartbulb:id,brand,dailyconsumption,instalationcost,on,mode,size
            Pattern smartBulbP = Pattern.compile("(smartbulb):(d[0-9]+),([a-z0-9 ]+),(\\d+.\\d+),(\\d+.\\d+),(true|false),(\\d+),(\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher smartBulbM = smartBulbP.matcher(data);
            //smartcamera:id,brand,dailyconsumption,instalationcost,on,resolution,filesize
            Pattern smartCameraP = Pattern.compile("(smartcamera):(d[0-9]+),([a-z0-9 ]+),(\\d+.\\d+),(\\d+.\\d+),(true|false),(\\(\\d+x\\d+\\)),(\\d+)", Pattern.CASE_INSENSITIVE);
            Matcher smartCameraM = smartCameraP.matcher(data);
            //smartspeaker:id,brand,dailyconsumption,instalationcost,on,radio,volume
            Pattern smartSpeakerP = Pattern.compile("(smartspeaker):(d[0-9]+),([a-z0-9 ]+),(\\d+.\\d+),(\\d+.\\d+),(true|false),([a-z0-9 ]+),(\\d{1,3})", Pattern.CASE_INSENSITIVE);
            Matcher smartSpeakerM = smartSpeakerP.matcher(data);
            //smarttv:id,brand,dailyconsumption,instalationcost,on,resolution,volume
            Pattern smartTvP = Pattern.compile("(smarttv):(d[0-9]+),([a-z0-9 ]+),(\\d+.\\d+),(\\d+.\\d+),(true|false),(\\(\\d+x\\d+\\)),(\\d{1,3})", Pattern.CASE_INSENSITIVE);
            Matcher smartTvM = smartTvP.matcher(data);
        

            if (supplierM.find()) {
                Supplier supplier = new Supplier(supplierM.group(2),
                                                Float.parseFloat(supplierM.group(3)),
                                                Integer.parseInt(supplierM.group(4)),
                                                Integer.parseInt(supplierM.group(5)));
                this.community.addSupplier(supplier);
            } else if (houseM.find()) {
                currentHouse = new House(houseM.group(2),
                                        houseM.group(3),
                                        houseM.group(4),
                                        houseM.group(5),
                                        this.community.getSuppliers().get(houseM.group(6)));
                this.community.addHouse(currentHouse);
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
                                                smartTvM.group(7),
                                                Integer.parseInt(smartTvM.group(8)));
                this.community.increaseDeviceCounter();
                currentHouse.addDeviceToRoom(currentRoom.toString(), device);
            }
        }
        if (sc != null) sc.close(); 
    }
    
    /**
     * Salva o log.
     */
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
}