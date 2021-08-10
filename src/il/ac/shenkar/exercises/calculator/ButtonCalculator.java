
package il.ac.shenkar.exercises.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * ButtonCalculator- for design the button in the calculator
 * *
 */

public class ButtonCalculator extends JButton {
    private int mouse = 1;                            // Whether the mouse enters the button
    Color color0;
    Color color1 = new Color(200, 200, 200);

    public ButtonCalculator(String buttonText, Color color) {
        super(buttonText);
        color0 = color;
        //Add mouse monitor
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //When the mouse enters, the mouse enter state is changed to TRUE, and the button is redrawn
                mouse = 0;
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouse = 1;

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                mouse =-1;

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouse = 0;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw gradient background color
        switch (mouse) {
            case 0:
                this.setBackground(color1);
                break;
            case 1:
                this.setBackground(color0);
                break;
            case -1:
            default:
                break;
        }
        super.paintComponent(g);

    }

}