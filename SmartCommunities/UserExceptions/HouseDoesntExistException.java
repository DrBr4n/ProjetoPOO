package UserExceptions;

public class HouseDoesntExistException extends Exception
{
    public HouseDoesntExistException(String message)
    {
        System.out.println("A casa " + message + " não existe.");
    }
}
