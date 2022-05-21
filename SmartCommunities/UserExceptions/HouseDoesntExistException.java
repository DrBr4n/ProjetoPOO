package UserExceptions;

public class HouseDoesntExistException extends Exception
{
    public HouseDoesntExistException(String message)
    {
        System.out.println("\nA casa " + message + " não existe.\n");
    }
}
