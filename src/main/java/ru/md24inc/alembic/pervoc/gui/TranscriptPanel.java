package ru.md24inc.alembic.pervoc.gui;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;

import javax.swing.*;
import javax.swing.border.Border;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class TranscriptPanel extends JPanel {
    private static final long serialVersionUID = -7784099290733841692L;

    //	JTextField[] symbols;
    JLabel[] symbols;
    List<Object> consonants;
    List<Object> vowels;
    List<Object> special;
    Border border;

    public TranscriptPanel() {
        initSymbols();
    }

    private void initSymbols() {
        //Setting up Panel properties
//        setLayout(new GridLayout(0, 10, 2, 2));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        border = BorderFactory.createLineBorder(Color.lightGray, 1);
        //Reading xml file with transcript symbols
        XMLConfiguration mxconf = null;
        try {
            mxconf = new XMLConfiguration(
                    new URL(
//							"file:///C:/Users/panov/workspaces/experiz/PerV/symbols.xml"));
                            "file:///C:\\Users\\miroque\\intelijwsp\\PerV\\symbols.xml"));
        } catch (ConfigurationException e) {
            System.out.println("Reading config failed due: " + e.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //Fill up collection variables with needed symbols
        consonants = mxconf.getList("Consonants.symbol");
        vowels = mxconf.getList("Vowels.symbol");
        special = mxconf.getList("Special.symbol");
//        for (int i=0 ; i<consonants.size();i++){
//		String consAtr = mxconf.getString("Consonants.symbol(0)[@hint]","");
//        }

        //Initiate new array of Jlabels with total symbols
        //plus 2 additional for hints words
//        int totalSymbols = consonants.size() + vowels.size() + special.size();
//        symbols = new JLabel[totalSymbols + 3];

        //Filling Consonant symbols
//        symbols[0] = new JLabel("Cons");
//        symbols[0].setBorder(border);
//        symbols[0].setToolTipText("Consonants");
//        symbols[0].setHorizontalAlignment(JTextField.CENTER);
//        symbols[0].setForeground(new Color(0, 0, 140));
//        add(symbols[0]);
//        ListIterator<Object> it = consonants.listIterator();
//        int go = 1;
//        while (it.hasNext()) {
//            Object ob = it.next();
//            symbols[go] = new JLabel();
//            symbols[go].setText(ob.toString());
//            symbols[go].setBorder(border);
//            symbols[go].setHorizontalAlignment(JLabel.CENTER);
//            symbols[go].setForeground(new Color(0, 0, 140));
//            symbols[go].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
//            add(symbols[go]);
//            go++;
//        }
        //Filling Vowels symbols
//        symbols[++go] = new JLabel("Vow");
//        symbols[go].setToolTipText("Vowels");
//        symbols[go].setHorizontalAlignment(JLabel.CENTER);
//        symbols[go].setBorder(border);
//        symbols[go].setForeground(new Color(130, 0, 0));
//        add(symbols[go]);
//        it = vowels.listIterator();
//        while (it.hasNext()) {
//            Object ob = it.next();
//            symbols[go] = new JLabel();
//            symbols[go].setText(ob.toString());
//            symbols[go].setBorder(border);
//            symbols[go].setHorizontalAlignment(JLabel.CENTER);
//            symbols[go].setForeground(new Color(130, 0, 0));
//            symbols[go].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
//            add(symbols[go]);
//            go++;
//        }
        //Filling Special symbols
//        symbols[++go] = new JLabel("Spec");
//        symbols[go].setToolTipText("Special");
//        symbols[go].setHorizontalAlignment(JLabel.CENTER);
//        symbols[go].setBorder(border);
//        symbols[go].setForeground(new Color(130, 100, 0));
//        add(symbols[go]);
//        it = special.listIterator();
//        while (it.hasNext()) {
//            Object ob = it.next();
//            symbols[go] = new JLabel();
//            symbols[go].setText(ob.toString());
//            symbols[go].setBorder(border);
//            symbols[go].setHorizontalAlignment(JLabel.CENTER);
//            symbols[go].setForeground(new Color(130, 100, 0));
//            symbols[go].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
//            add(symbols[go]);
//            go++;
//        }
        SymbolRow[] test = new SymbolRow[3];
        test[0] = new SymbolRow();
        test[0].setInfo("Consonants", consonants);
        add(test[0]);
        add(Box.createRigidArea(new Dimension(0, 10)));
        test[1] = new SymbolRow();
        test[1].setInfo("Vowels", vowels);
        add(test[1]);
        add(Box.createRigidArea(new Dimension(0, 10)));
        test[2] = new SymbolRow();
        test[2].setInfo("Special", special);
        add(test[2]);
    }

    class SymbolRow extends JPanel {
        JLabel header;
        JLabel[] symbolsHolder;
        Border fronties = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
        GridLayout forSymbols;
        BorderLayout forAll;
        JPanel hPanel;
        JPanel sPanel;

        public SymbolRow() {
            forSymbols = new GridLayout(0, 10, 2, 2);
            forAll = new BorderLayout(5, 5);
            hPanel = new JPanel();
//            hPanel.setVisible(true);
            sPanel = new JPanel(forSymbols);
//            sPanel.setVisible(true);
            header = new JLabel();
            setLayout(forAll);
            add(hPanel, BorderLayout.WEST);
            add(sPanel, BorderLayout.CENTER);
        }

        public void setInfo(String h, List<Object> in) {
            header.setText(h);
            hPanel.add(header);
            symbolsHolder = new JLabel[in.size()];
            ListIterator<Object> it = in.listIterator();
            int go = 0;
            while (it.hasNext()) {
                Object ob = it.next();
                symbolsHolder[go] = new JLabel(ob.toString());
                symbolsHolder[go].setBorder(fronties);
                symbolsHolder[go].setHorizontalAlignment(JLabel.CENTER);
                symbolsHolder[go].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
                sPanel.add(symbolsHolder[go]);
            }
        }
    }

    public void paintComponent(Graphics g) {

    }

}
