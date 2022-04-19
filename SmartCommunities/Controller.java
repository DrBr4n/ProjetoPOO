import java.io.IOException;
import java.io.FilterInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Controller {

    private View view;
    private Community community; 
    private boolean run;

    public static void main(String[] args) {
        Controller cont = new Controller();

        do {
            cont.displayMenu();

        } while (cont.run);

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
                createDevice();
                break;
            case 2:
                createHouse();
                break;
            case 3:
                createSupplier();
                break;
            case 9:
                loadState();
                break;
            case 10:
                saveState();
                break;
            case 99:
                this.community.viewDevices();
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
    
        if (option < 1 || option > 5) {
            this.view.invalidOption();   
        } else {
            community.createDevice(option, props);
        }
    }

    public void createHouse() {
        String [] ids = this.view.createHouse();
        String houseId = community.createHouse(ids);
        boolean editing = true;
        do {
            String [] props = this.view.houseProps();
            switch (community.editHouse(houseId, props)) {
                case 0:
                    this.view.success();
                    break;
                case 2:
                    this.view.invalidOperation();
                    break;
                case 3:
                    editing = false;
                    break;
                default:
                    this.view.invalidOption();
                    break;
            }
        } while (editing);
    }

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
}
