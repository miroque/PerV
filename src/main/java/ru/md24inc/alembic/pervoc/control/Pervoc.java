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
    private JMenuItem menuFileItemOpen;
    private JMenuItem menuFileItemQuit;
    private JMenuItem menuViewsItemTscript;
    private JTable tableVoc;
    private JScrollPane scrollPaneForTableVoc;
    
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
        menuFileItemOpen = new JMenuItem("Open...");
        menuFileItemOpen.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuFile.add(menuFileItemOpen);
        menuFile.add(new JPopupMenu.Separator());
        menuFileItemQuit = new JMenuItem("Quit");
        menuFileItemQuit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuFile.add(menuFileItemQuit);
        
        // Adding menu File to Bar menu
        menuBar.add(menuFile);

        //Creating second menu named "Views"
        menuViews = new JMenu("Views");
        menuViewsItemTscript = new JMenuItem("Transcript");
        menuViewsItemTscript.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuViews.add(menuViewsItemTscript);
        
        // Adding menu Views to Bar menu
        menuBar.add(menuViews);


        // Adding menu bar in our main frame
        setJMenuBar(menuBar);
        
        //Creating and Adding Table with Vocabulary into main Frame
        String[] colNames = {"Word","Transcript","Translate"};
        Object [][] data = {{"dog","-1-1","—обака"},{"table","tablo","стол"}};
        tableVoc = new JTable(data, colNames);
        scrollPaneForTableVoc = new JScrollPane(tableVoc);
        tableVoc.setFillsViewportHeight(true);
        add(scrollPaneForTableVoc);
        
        

        //frame.pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        // „естно признатьс€ это из урока, и € плохо представл€ю 
        // что это и как работает.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new Pervoc().setVisible(true);
            }
        });
    }
}
