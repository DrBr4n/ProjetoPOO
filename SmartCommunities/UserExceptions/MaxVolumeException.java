package UserExceptions;

public class MaxVolumeException extends Exception
{
    public MaxVolumeException()  
    {
        System.out.println("\nVolume máximo excedido.\n");
    }
}
