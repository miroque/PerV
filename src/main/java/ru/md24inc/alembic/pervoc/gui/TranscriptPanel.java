package ru.md24inc.alembic.pervoc.gui;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class TranscriptPanel extends JPanel implements ComponentListener {
    private static final long serialVersionUID = -7784099290733841692L;

    List<Object> consonants;
    List<Object> vowels;
    List<Object> special;
    List<Object> consonantsHints;
    List<Object> vowelsHints;
    List<Object> specialHints;
    Border border;
    JTable typeIn;

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
        AbstractConfiguration.setDefaultListDelimiter('\u0000');
        XMLConfiguration mxconf = null;
        try {
            mxconf = new XMLConfiguration(new File("src/main/resources/symbols.xml"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        // Fill up collection variables with needed symbols
        consonants = mxconf.getList("Consonants.symbol");
        vowels = mxconf.getList("Vowels.symbol");
        special = mxconf.getList("Special.symbol");
        consonantsHints = mxconf.getList("Consonants.symbol[@hint]");
        vowelsHints = mxconf.getList("Vowels.symbol[@hint]");
        specialHints = mxconf.getList("Special.symbol[@hint]");

        add(new SymbolRow("Consonants", consonants, consonantsHints, new Color(0, 0, 150)));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new SymbolRow("Vowels", vowels, vowelsHints, new Color(150, 0, 0)));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new SymbolRow("Special", special, specialHints, new Color(93, 62, 0)));
    }

    class SymbolRow extends JPanel implements MouseListener {
        Border fronties = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);

        public SymbolRow(String h, List<Object> symbols, List<Object> hints, Color color) {
            setLayout(new BorderLayout(5, 5));

            JPanel headerPanel = new JPanel();
            headerPanel.add(new JLabel(h));
            add(headerPanel, BorderLayout.WEST);

            JPanel symbolsPanel = new JPanel(new GridLayout(0, 10, 2, 2));
            int lenthOfSymbols = symbols.size();
            for (int i = 0; i < lenthOfSymbols; i++) {
                symbolsPanel.add(createSymbolLabel(symbols.get(i), hints.get(i), color));
            }
            add(symbolsPanel, BorderLayout.CENTER);
        }

        private JLabel createSymbolLabel(Object symbol, Object hint, Color color) {
            JLabel symbolsHolder = new JLabel(symbol.toString());
            symbolsHolder.setBorder(fronties);
            symbolsHolder.setForeground(color);
            symbolsHolder.setToolTipText(hint.toString());
            symbolsHolder.addMouseListener(this);
            symbolsHolder.setHorizontalAlignment(JLabel.CENTER);
            symbolsHolder.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            return symbolsHolder;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel ex = (JLabel) e.getComponent();
            typeInActiveCell(ex);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        private void typeInActiveCell(JLabel ex) {
            if (!typeIn.isEditing()) {
                return;
            }
            for (char c : ex.getText().toCharArray()) {
                typeIn.dispatchEvent(new KeyEvent(
                        typeIn.getEditorComponent(), KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0,
                        KeyEvent.VK_UNDEFINED, c));
            }
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

    public void addTypeIn(JTable typeIn) {
        this.typeIn = typeIn;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // noop
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // noop
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // noop
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // noop
    }
}
