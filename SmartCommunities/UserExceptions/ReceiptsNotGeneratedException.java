package UserExceptions;

public class ReceiptsNotGeneratedException extends Exception
{
    public ReceiptsNotGeneratedException()
    {
        System.out.println("Ainda não foi gerada nenhuma fatura.");
    }
}
