package il.ac.shenkar.exercises.calculator;
import java.lang.Exception;


/**
 *
 * DivisionByZeroException- making exception if the user decide to divide by zero.
 * *
 */

public class DivisionByZeroException extends  Exception{
    public DivisionByZeroException() {
        super();
    }
    public DivisionByZeroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public DivisionByZeroException(String message, Throwable cause) {
        super(message, cause);
    }


    public DivisionByZeroException(String message) {
        super(message);
    }

    public DivisionByZeroException(Throwable cause) {
        super(cause);
    }
}
