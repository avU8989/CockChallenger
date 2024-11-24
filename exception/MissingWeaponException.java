package worksheet5.exception;

public class MissingWeaponException extends RuntimeException{
    public MissingWeaponException(String message){
        super(message);
    }
}
