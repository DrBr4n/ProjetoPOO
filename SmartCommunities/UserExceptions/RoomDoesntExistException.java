package UserExceptions;

public class RoomDoesntExistException extends Exception
{
    public RoomDoesntExistException(String message)
    {
        System.out.println("\nA divisão " + message + " não existe.\n");
    }
}
