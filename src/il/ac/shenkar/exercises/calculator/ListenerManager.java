
package il.ac.shenkar.exercises.calculator;

import static il.ac.shenkar.exercises.calculator.OperationsManager.*;
import static il.ac.shenkar.exercises.calculator.OperationsManager.doubleOperator;
import static il.ac.shenkar.exercises.calculator.DisplayCalculator.*;
import static il.ac.shenkar.exercises.calculator.DisplayCalculator.keys;


/**
 *
 * ListenerManager- contain the logics of special buttons in the calculator
 * *
 */

public class ListenerManager {
    public static void addEventListener() throws DivisionByZeroException {
        // Add event listeners for each button
        for (int i = 0; i < keys.length; i++) {
            buttons[i].addActionListener(e -> {
                String command = e.getActionCommand();  // Get event source
                if (command.equals(keys[0])) {
                    // The user pressed the "Back" key
                    pressBackspace();
                } else if (command.equals(keys[1])) {
                    // The user pressed the "C" key
                    pressC();

                } else if (command.equals(keys[2])) {
                    // The user pressed the "CE" key
                    res.setText("0");
                } else if ("0123456789.".contains(command)) {
                    // The user pressed the number key or the decimal point key
                    pressNumber(command);
                } else if (command.equals(keys[3]) || command.equals(keys[4]) || command.equals(keys[5]) || command.equals(keys[6]) || command.equals(keys[20])) {
                    // if the  user presses the arithmetic key that only needs one number
                    try {
                        singleOperator(command);
                    } catch (DivisionByZeroException divisionByZeroException) {
                        divisionByZeroException.printStackTrace();
                    }
                } else {
                    try {
                        doubleOperator(command);
                    } catch (DivisionByZeroException divisionByZeroException) {
                        divisionByZeroException.printStackTrace();
                    }
                }
            });
        }
    }

}