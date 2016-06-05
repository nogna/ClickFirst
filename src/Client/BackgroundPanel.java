/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Nogna
 */
public class BackgroundPanel extends JFrame {

    private final ImageIcon BACKGROUND;
    private final JLabel label;

    public BackgroundPanel() {
        setLayout(new FlowLayout());
        BACKGROUND = new ImageIcon(getClass().getResource("Background.png"));
        
        label = new JLabel(BACKGROUND);
        add(label);
    }

    public void setUpBg(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setTitle("Click First!");
    }
    
}
