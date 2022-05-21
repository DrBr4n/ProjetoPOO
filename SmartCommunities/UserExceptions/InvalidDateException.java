package UserExceptions;

public class InvalidDateException extends Exception
{
    public InvalidDateException()
    {
        System.out.println("A data inserida não é válida.");
    }
}
