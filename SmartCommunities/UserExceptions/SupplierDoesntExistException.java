package UserExceptions;

public class SupplierDoesntExistException extends Exception
{
    public SupplierDoesntExistException(String message)
    {
        System.out.println("\nO fornecedor não existe " + message + " não existe.\n");
    }
}
