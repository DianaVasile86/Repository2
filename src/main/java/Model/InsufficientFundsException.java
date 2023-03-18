package Model;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String mesaj){
        super(mesaj);
    }
}
