
package il.ac.shenkar.exercises.calculator;

import java.awt.*;
import javax.swing.*;


/**
 *
 * DisplayCalculator part of the calculator- responsible of the building the ui of the calculator
 * *
 */

public class DisplayCalculator {                                                 // using the ViewCalculator in MainCalculator
    public static JTextField res = new JTextField("0");                       //needed to public and Display the calculation result text box
    public static JFrame frame = new JFrame();                                // needed to be public
    // array of the calculator with additional keys:
    public static  String[] keys = {"Back", "C", "CE", "%", "sqrt", "x²", "1⁄x", "+/-", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "×", ".", "0", "=", "/"};   //
    public static ButtonCalculator[] buttons = new ButtonCalculator[keys.length];  //Button on the calculator

    /**
     * Initialize the calculator
     */
    private void calcInit() {
        Color c1 = new Color(200, 200, 200);                          //background color
        Color c2 = new Color(140, 140, 140);                          //Equal sign exclusive color
        Color c3 = new Color(230, 230, 230);                          //Function key and operator color
        Color c4 = new Color(240, 240, 240);                          //Digital color
        // Create a drawing board and place the text box
        JPanel textPanel = new JPanel();

        textPanel.setLayout(new BorderLayout());
        textPanel.add(res);
        res.setFont(new Font("Arial", Font.BOLD, 32));           //Set the font and size of the text in the text box, bold
        res.setHorizontalAlignment(JTextField.RIGHT);                          //The content in the text box is right aligned
        res.setEditable(false);                                                //Cannot modify the result text box
        res.setBorder(null);                                                   //Delete the border of the text box
        // Set the background color of the text box
        res.setBackground(c1);
        // Initialize the button on the calculator and place the button in a drawing board
        JPanel keysPanel = new JPanel();
        // Using a grid layout, a grid of 6 rows and 4 columns, the horizontal and vertical spacing between the grids is 2 pixels
        keysPanel.setLayout(new GridLayout(6, 4, 2, 2));
        //Initialize function button
        for (int i = 0; i < 8; i++) {
            buttons[i] = new ButtonCalculator(keys[i], c3);
            keysPanel.add(buttons[i]);
            buttons[i].setBackground(c3);
            buttons[i].setForeground(Color.black);
            buttons[i].setFont(new Font(Font.SERIF, Font.PLAIN, 18));
            buttons[i].setBorderPainted(false);  //Remove the border of the button
        }
        //Initialize operator and number key button
        for (int i = 8; i < keys.length; i++) {
            if ((i+1) % 4 == 0){
                buttons[i] = new ButtonCalculator(keys[i], c3);
            }else{
                buttons[i] = new ButtonCalculator(keys[i], c4);
            }
            keysPanel.add(buttons[i]);
            //Paint in blue only the mandatory numbers that is required for the exercise: 0,1,2,3,4,5,6,7,8,9,/,x,+,-,.,=
            buttons[i].setForeground(Color.BLUE);                              // painting the mandatory part of the exercise in blue.
            buttons[i].setFont(new Font(Font.SERIF, Font.PLAIN, 18));
            buttons[i].setBorderPainted(false);                                //Remove the border of the button
        }
        buttons[23].setBackground(c2);                                          //'=' symbol key uses special color
        keysPanel.setBackground(c1);
        //Place the panel where the text box is located in the north and the keysPanel panel in the middle of the calculator
        frame.getContentPane().add("North", textPanel);
        frame.getContentPane().add("Center", keysPanel);
        //Set the borders of the two panels, try to restore the win10 calculator
        textPanel.setBorder(BorderFactory.createMatteBorder(20, 3, 1, 3, c1));
        keysPanel.setBorder(BorderFactory.createMatteBorder(5, 3, 3, 3, c1));
        ImageIcon imageIcon=new ImageIcon("Dakirby309-Windows-8-Metro-Apps-Calculator-Metro.ico");
          frame.setIconImage(imageIcon.getImage());
        frame.setSize(380, 440);
        frame.setResizable(true);                                                  // Allow to modify the size of the calculator window
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("D&H Calculator");
    }

    public DisplayCalculator() throws DivisionByZeroException {
        calcInit();                                                                   // Initialize the calculator
        ListenerManager.addEventListener();
    }


}
