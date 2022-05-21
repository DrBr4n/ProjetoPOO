package UserExceptions;

public class RoomDoesntExistException extends Exception
{
    public RoomDoesntExistException(String message)
    {
        System.out.println("A divisão " + message + " não existe");
    }
}
