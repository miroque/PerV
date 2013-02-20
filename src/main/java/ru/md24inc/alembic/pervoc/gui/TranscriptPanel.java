package ru.md24inc.alembic.pervoc.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;
import java.util.ListIterator;

import javax.swing.*;
import javax.swing.border.Border;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class TranscriptPanel extends JPanel {
    private static final long serialVersionUID = -7784099290733841692L;

    // JTextField[] symbols;
    JLabel[] symbols;
    List<Object> consonants;
    List<Object> vowels;
    List<Object> special;
    Border border;

    public TranscriptPanel() {
        initSymbols();
    }

    private void initSymbols() {
        // Setting up Panel properties
        // setLayout(new GridLayout(0, 10, 2, 2));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        border = BorderFactory.createLineBorder(Color.lightGray, 1);
        // Reading xml file with phonetic symbols
        XMLConfiguration mxconf = null;
        try {
            mxconf = new XMLConfiguration(new File("src/main/resources/symbols.xml"));
        } catch (ConfigurationException e) {
            System.out.println("Reading config failed due: " + e.getMessage());
        }
        // Fill up collection variables with needed symbols
        consonants = mxconf.getList("Consonants.symbol");
        vowels = mxconf.getList("Vowels.symbol");
        special = mxconf.getList("Special.symbol");


        add(new SymbolRow("Consonants", consonants, new Color(0, 0, 150)));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new SymbolRow("Vowels", vowels, new Color(150, 0, 0)));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new SymbolRow("Special", special, new Color(150, 150, 0)));
    }

    class SymbolRow extends JPanel implements MouseListener {
        JLabel header;
        JLabel[] symbolsHolder;
        Border fronties = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
        GridLayout layoutForSymbols;
        BorderLayout layoutForAll;
        JPanel headerPanel;
        JPanel symbolsPanel;

        public SymbolRow(String h, List<Object> in, Color color) {
            layoutForSymbols = new GridLayout(0, 10, 2, 2);
            layoutForAll = new BorderLayout(5, 5);
            headerPanel = new JPanel();
            symbolsPanel = new JPanel(layoutForSymbols);
            header = new JLabel();
            setLayout(layoutForAll);
            add(headerPanel, BorderLayout.WEST);
            add(symbolsPanel, BorderLayout.CENTER);
            header.setText(h);
            headerPanel.add(header);
            symbolsHolder = new JLabel[in.size()];
            int go = 0;
            for (Object ob : in) {
                symbolsHolder[go] = new JLabel(ob.toString());
                symbolsHolder[go].setBorder(fronties);
                symbolsHolder[go].setForeground(color);
                symbolsHolder[go].addMouseListener(this);
                symbolsHolder[go].setHorizontalAlignment(JLabel.CENTER);
                symbolsHolder[go].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
                symbolsPanel.add(symbolsHolder[go]);
                go++;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel ex = (JLabel) e.getComponent();
            System.out.print(ex.getText() + " ");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }


    }

    public void paintComponent(Graphics g) {

    }

}
