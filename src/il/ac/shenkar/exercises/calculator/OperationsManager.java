
package il.ac.shenkar.exercises.calculator;

import java.text.DecimalFormat;


/**
 *
 * OperationsManager part of the calculator- calculation of every sign and exception handler
 * *
 */

public class OperationsManager {

    private static boolean firstDigit = true;          // Mark whether the user pressed the first number of the entire expression, or the first number after the operator
    private static double resultNum = 0.0000;          // intermediate result of calculation
    private static String equOperator = "=";           // The operator of the current operation (you need to restore it to "=" when you press "C")
    private static boolean isValidOp = true;    // Determine whether the operation is legal

    public static void pressBackspace() {
        String text = DisplayCalculator.res.getText();
        int i = text.length();
        if (i > 0) {
            text = text.substring(0, i - 1);  // Backspace, remove the last character of the text
            if (text.length() == 0) {
                // If the text has no content, initialize the various values f the calculator
                DisplayCalculator.res.setText("0");
                firstDigit = true;
                equOperator = "=";
            } else {
                // display new text from the view
                DisplayCalculator.res.setText(text);
            }
        }
    }

    /**
     * Handle the event that the C key is pressed
     */
    public static void pressC() {
        // Initialize various values the calculator
        DisplayCalculator.res.setText("0");
        firstDigit = true;
        equOperator = "=";
    }

    /**
     * Handle the event that the number key is pressed
     */
    public static void pressNumber(String key) {
        if (firstDigit) {
            // The input is the first number
            DisplayCalculator.res.setText(key);
        } else if ((key.equals(".")) && (!DisplayCalculator.res.getText().contains("."))) {
            // The input is a decimal point, and there is no decimal point before, the decimal point will be attached to the back of the result text box
            DisplayCalculator.res.setText(DisplayCalculator.res.getText() + ".");
        } else if (!key.equals(".")) {
            // If the input is not a decimal point, append the number to the back of the result text box
            DisplayCalculator.res.setText(DisplayCalculator.res.getText() + key);
        }
        firstDigit = false;
    }

    /**
     * Handle the event that the operator key is pressed
     */
    //Only a number of operations
    public static void singleOperator(String key) throws DivisionByZeroException {
        equOperator = key;  // The operator is the button pressed by the user
        switch (equOperator) {
            case "1⁄x":
                // reciprocal operation
                if (resultNum == 0) {
                    isValidOp = false;  //The operation is illegal
                    DisplayCalculator.res.setText("Zero has no countdown");
                    throw new DivisionByZeroException("Zero has no countdown");
                } else {
                    resultNum = 1 / getNumberFromText();
                    dropDecimal(resultNum);
                }
                break;
            case "sqrt":
                // Square root operation
                if (resultNum < 0) {
                    isValidOp = false;  //The operation is illegal
                    DisplayCalculator.res.setText("The root sign cannot be negative");
                    throw new DivisionByZeroException("The root sign cannot be negative");
                } else {
                    resultNum = Math.sqrt(getNumberFromText());
                    dropDecimal(resultNum);
                }
                break;
            case "x²":
                // Square operation
                resultNum = getNumberFromText() * getNumberFromText();
                dropDecimal(resultNum);
                break;
            case "%":
                // Percent sign operation, divide by 100
                resultNum = getNumberFromText() / 100;
                DisplayCalculator.res.setText(String.valueOf(resultNum));
                break;
            case "+/-":
                // Positive and negative operations
                resultNum = getNumberFromText() * (-1);
                if (isValidOp) {
                    // When the operation is legal, the result is a decimal with 4 digits after the decimal point, and the integer is output normally
                    dropDecimal(resultNum);
                }
                firstDigit = true;
                isValidOp = true;
                break;
        }
    }

    //Need two operations
    public static void doubleOperator(String key)  throws DivisionByZeroException  {
        switch (equOperator) {
            case "/":
                // Division operation
                // If the value in the current result text box is equal to 0
                if (getNumberFromText() == 0.0) {
                    isValidOp = false;  //The operation is illegal
                    DisplayCalculator.res.setText("Divisor cannot be zero");
                    throw new DivisionByZeroException("Divisor cannot be zero");
                } else {
                    resultNum /= getNumberFromText();
                }

                break;


            case "+":
                // addition operation
                resultNum += getNumberFromText();
                break;
            case "-":
                // subtraction operation
                resultNum -= getNumberFromText();
                break;
            case "×":
                // multiplication
                resultNum *= getNumberFromText();
                break;
            case "=":
                // Assignment operation
                resultNum = getNumberFromText();
                break;
        }
        dropDecimal(resultNum);
        equOperator = key;  // The operator is the button pressed by the user
        firstDigit = true;
        isValidOp = true;
    }

    public static void dropDecimal(double resultNum) {
        long t1;
        double t2;
        t1 = (long) resultNum;
        t2 = resultNum - t1;
        if (t2 == 0) {
            DisplayCalculator.res.setText(String.valueOf(t1));
        } else {
            DisplayCalculator.res.setText(String.valueOf(new DecimalFormat("0.00000000").format(resultNum)));
        }
    }

    /**
     * Get the number from the result text box
     */
    public static double getNumberFromText() {
        double result = 0;
            result = Double.parseDouble(DisplayCalculator.res.getText());
        return result;
    }
}