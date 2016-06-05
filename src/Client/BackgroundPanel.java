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


    public BackgroundPanel() {
        setLayout(new FlowLayout());
        
        add(label);
    }

    public void setUpBg(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setTitle("Click First!");
    }
    
}
