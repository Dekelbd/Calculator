
package il.ac.shenkar.exercises.calculator;

/**
 *
 * Main
 * *
 */

public class MainCalculator {
    public static void main(String[] args) throws DivisionByZeroException {
        try{
            new DisplayCalculator();
        } catch (DivisionByZeroException e) {
            e.printStackTrace();
        }
    }
}
