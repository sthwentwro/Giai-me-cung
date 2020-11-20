/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Windows
 */
public class MazeRunner {
        public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
           JFrame window = new JFrame("Giải mê cung");
           View panel = new View(850, 600);
            window.add(panel);
            window.pack();
            window.setResizable(false);
            window.setLocation(100,50);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
