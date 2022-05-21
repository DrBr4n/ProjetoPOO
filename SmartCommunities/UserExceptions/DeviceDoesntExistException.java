package UserExceptions;

public class DeviceDoesntExistException extends Exception
{
    /**
     * Constructor for objects of class DeviceDoesntExistException
     */
    public DeviceDoesntExistException(String message)
    {
        System.out.println("\nO dispositivo " + message + " não existe.\n");
    }
}
