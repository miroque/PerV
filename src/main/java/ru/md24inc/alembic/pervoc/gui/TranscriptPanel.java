package ru.md24inc.alembic.pervoc.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class TranscriptPanel extends JPanel {
	private static final long serialVersionUID = -7784099290733841692L;

	List<Object> consonants;
	List<Object> vowels;
	List<Object> special;
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
		add(new SymbolRow("Special", special, new Color(93, 62, 0)));
	}

	class SymbolRow extends JPanel implements MouseListener {
		JLabel header;
		JLabel symbolsHolder;
		Border fronties = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
		GridLayout layoutForSymbols;
		BorderLayout layoutForAll;
		JPanel headerPanel;
		JPanel symbolsPanel;

		public  SymbolRow(String h, List<Object> in, Color color) {
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
			for (Object ob:in){
				symbolsHolder = new JLabel(ob.toString());
				symbolsHolder.setBorder(fronties);
				symbolsHolder.setForeground(color);
				symbolsHolder.setToolTipText("Here will bee hint");
				symbolsHolder.addMouseListener(this);
				symbolsHolder.setHorizontalAlignment(JLabel.CENTER);
				symbolsHolder.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,16));
				symbolsPanel.add(symbolsHolder);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel ex = (JLabel) e.getComponent();
			System.out.print(ex.getText() + " ");
            typeInActiveCell(ex);
		}

        @Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

        private void typeInActiveCell(JLabel ex) {
        	System.out.println("Class of column: "+typeIn.getColumnClass(typeIn.getSelectedColumn()));
        	Class nc = typeIn.getColumnClass(typeIn.getSelectedColumn());
            /*if (typeIn.isCellSelected(typeIn.getSelectedRow(), typeIn.getSelectedColumn())) {
                System.out.println("Value at selected cell: " + typeIn.getModel().getValueAt(typeIn.getSelectedRow(), typeIn.getSelectedColumn()).toString());
                typeIn.setValueAt(typeIn.getValueAt(typeIn.getSelectedRow(), typeIn.getSelectedColumn()).toString() + ex.getText(), typeIn.getSelectedRow(), typeIn.getSelectedColumn());
            } else {
                System.out.println("Sorry cell not selected");
            }
            typeIn.repaint();*/
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

    public void addTypeIn(JTable typeIn){
        this.typeIn = typeIn;
    }
}
