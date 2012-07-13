/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.md24inc.alembic.pervoc.control;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.*;


/**
 *
 * @author miroque
 */
public class Pervoc {

    private static void initFrame() {
        // Creating main frame and giving it title
        // Personal Vocabular with def.action on exit.
        JFrame frame = new JFrame("Personal Vocabulary (version 0.1)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Making min. and max sizes.
        Dimension minSize = new Dimension(300, 450);
        frame.setMinimumSize(minSize);
        //max size not set
        
        //Creating bar for menu
        JMenuBar menuBar = new JMenuBar();
        
        //Creating first menu named "File"
        JMenu menu = new JMenu("File");
        
        menuBar.add(menu);
        
        //Creating second menu named "Views"
        menu = new JMenu("Views");
        
        menuBar.add(menu);
        
        
        // Adding menu bar in our main frame
        frame.setJMenuBar(menuBar);

        //frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        // Честно признаться это из урока, и я плохо представляю 
        // что это и как работает.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                initFrame();
            }
        });
    }
}
