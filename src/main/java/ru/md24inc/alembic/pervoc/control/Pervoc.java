/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.md24inc.alembic.pervoc.control;

import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author miroque
 */
public class Pervoc extends JFrame {
    // Variables declaration
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuViews;
    
    /**
     * Creates new form Pervoc
     */
    public Pervoc() {
        initComponents();
    }

    private void initComponents() {
        // Creating main frame and giving it title
        // Personal Vocabular with def.action on exit.
        setTitle("Personal Vocabulary (version 0.1)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Making min. and max sizes.
        Dimension minSize = new Dimension(300, 450);
        setMinimumSize(minSize);
        //max size not set

        //Creating bar for menu
        menuBar = new JMenuBar();

        //Creating first menu named "File"
        menuFile = new JMenu("File");

        menuBar.add(menuFile);

        //Creating second menu named "Views"
        menuViews = new JMenu("Views");

        menuBar.add(menuViews);


        // Adding menu bar in our main frame
        setJMenuBar(menuBar);

        //frame.pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        // Честно признаться это из урока, и я плохо представляю 
        // что это и как работает.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new Pervoc().setVisible(true);
            }
        });
    }
}
