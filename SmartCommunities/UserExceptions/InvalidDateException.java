package UserExceptions;

public class InvalidDateException extends Exception
{
    public InvalidDateException()
    {
        System.out.println("\nA data inserida não é válida.\n");
    }
}
