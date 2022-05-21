

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert.*;

/**
 * The test class ControllerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ControllerTest
{
    /**
     * Default constructor for test class ControllerTest
     */
    public ControllerTest()
    {
    }
    
    @Test
    public void createSupplier() {
        Supplier s1 = new Supplier("Iberdrola",5.0f,50,30.0f);
    }
    
    @Test
    public void createHouse() {
        HashMap<String, House> emptyHouses = new HashMap<>();
        HashMap<String, Supplier> emptySuppliers = new HashMap<>();
        LocalDate now = LocalDate.now();
        Community c1 = new Community("Aveiro", emptyHouses, emptySuppliers, 0, 0, now, now);

        Supplier s1 = new Supplier("Iberdrola",5.0f,50,30.0f);
        c1.addSupplier(s1);

        House h1 = new House("h1","Rua Das Cruzetas","Vicente de Carvalho Castro","365597405",s1);
        c1.addHouse(h1);

        assertEquals(1, c1.getHouseCounter(), "Numero de casas incorreto");
        assertEquals(1, c1.getHouses().size(), "Numero de casas incorreto");
    }

    @Test
    public void createDevice() {
        HashMap<String, House> emptyHouses = new HashMap<>();
        HashMap<String, Supplier> emptySuppliers = new HashMap<>();
        LocalDate now = LocalDate.now();
        Community c1 = new Community("Aveiro", emptyHouses, emptySuppliers, 0, 0, now, now);
        Supplier s1 = new Supplier("Iberdrola",5.0f,50,30.0f);
        c1.addSupplier(s1);
        House h1 = new House("h1","Rua Das Cruzetas","Vicente de Carvalho Castro","365597405",s1);
        c1.addHouse(h1);
        h1.addRoom("quarto");
        h1.addRoom("cozinha");
        
        SmartSpeaker sd1 = new SmartSpeaker("d1","Marshall",5.21f,50.0f,false,"M80 Radio",65);
        SmartBulb sd2 = new SmartBulb("d2","LG",3.78f,50.0f,true,1,12);
        SmartCamera sd3 = new SmartCamera("d3","LG",3.97f,50.0f,false,"(2160x1440)",71);
        SmartTV sd4 = new SmartTV("d4","LG",4.5f,40.0f,false,"(1377x788)",50);
        SmartAC sd5 = new SmartAC("d5","Sony",5.67f,50.0f,false,0,30);
        
        h1.addDeviceToRoom("quarto", sd1);
        h1.addDeviceToRoom("quarto", sd2);
        h1.addDeviceToRoom("cozinha", sd3);
        h1.addDeviceToRoom("cozinha", sd4);
        h1.addDeviceToRoom("quarto", sd5);


        assertEquals(true, h1.existsDevice("d1"), "sd1 nao existe");
        assertEquals(true, h1.existsDevice("d2"), "sd2 nao existe");
        assertEquals(true, h1.existsDevice("d3"), "sd3 nao existe");
        assertEquals(true, h1.existsDevice("d4"), "sd4 nao existe");
        assertEquals(true, h1.existsDevice("d5"), "sd5 nao existe");

        assertEquals(5, h1.getDevices().keySet().size(), "Numero de dispositivos incorreto");

    }

    @Test
    public void turnDeviceAndRoomOn() {
        HashMap<String, House> emptyHouses = new HashMap<>();
        HashMap<String, Supplier> emptySuppliers = new HashMap<>();
        LocalDate now = LocalDate.now();
        Community c1 = new Community("Aveiro", emptyHouses, emptySuppliers, 0, 0, now, now);
        Supplier s1 = new Supplier("Iberdrola",5.0f,50,30.0f);
        c1.addSupplier(s1);
        House h1 = new House("h1","Rua Das Cruzetas","Vicente de Carvalho Castro","365597405",s1);
        c1.addHouse(h1);
        h1.addRoom("quarto");
        h1.addRoom("cozinha");
        
        SmartSpeaker sd1 = new SmartSpeaker("d1","Marshall",5.21f,50.0f,false,"M80 Radio",65);
        SmartBulb sd2 = new SmartBulb("d2","LG",3.78f,50.0f,true,1,12);
        SmartCamera sd3 = new SmartCamera("d3","LG",3.97f,50.0f,false,"(2160x1440)",71);
        SmartTV sd4 = new SmartTV("d4","LG",4.5f,40.0f,false,"(1377x788)",50);
        SmartAC sd5 = new SmartAC("d5","Sony",5.67f,50.0f,false,0,30);
        
        h1.addDeviceToRoom("quarto", sd1);
        h1.addDeviceToRoom("quarto", sd2);
        h1.addDeviceToRoom("cozinha", sd3);
        h1.addDeviceToRoom("cozinha", sd4);
        h1.addDeviceToRoom("quarto", sd5);

        c1.turnDeviceOn("h1", "d1");
        c1.turnDeviceOn("h1", "d2");
        c1.turnDeviceOn("h1", "d5");
        c1.turnRoomOn("h1", "cozinha");


        assertEquals(true, h1.getDevices().get("d1").getOn(), "sd1 nao ligado");
        assertEquals(true, h1.getDevices().get("d2").getOn(), "sd2 nao ligado");
        assertEquals(true, h1.getDevices().get("d3").getOn(), "sd3 nao ligado");
        assertEquals(true, h1.getDevices().get("d4").getOn(), "sd4 nao ligado");
        assertEquals(true, h1.getDevices().get("d5").getOn(), "sd5 nao ligado");
    }
}
