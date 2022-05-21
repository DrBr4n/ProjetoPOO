package UserExceptions;

public class ReceiptsNotGeneratedException extends Exception
{
    public ReceiptsNotGeneratedException()
    {
        System.out.println("\nAinda não foi gerada nenhuma fatura.\n");
    }
}
