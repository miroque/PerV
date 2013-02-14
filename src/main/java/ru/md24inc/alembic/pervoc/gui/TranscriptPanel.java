package ru.md24inc.alembic.pervoc.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.*;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class TranscriptPanel extends JPanel {
	private static final long serialVersionUID = -7784099290733841692L;

	JTextField[] symbols;

	public TranscriptPanel() {
		initSymbols();
	}

	private void initSymbols() {
		XMLConfiguration mxconf = null;
		try {
			mxconf = new XMLConfiguration(
					new URL(
							"file:///C:/Users/panov/workspaces/experiz/PerV/symbols.xml"));
		} catch (ConfigurationException e) {
			System.out.println("Reading config failed due: " + e.getMessage());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		setLayout(new GridLayout(0, 10, 2, 2));
		setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		List<Object> cons = mxconf.getList("Consonants.symbol");
		List<Object> vow = mxconf.getList("Vowels.symbol");
		int totalS = cons.size()+vow.size();
		symbols = new JTextField[totalS];
		ListIterator<Object> it = cons.listIterator();
		int go=0;
		while (it.hasNext()){
			Object ob = it.next();
			symbols[go] = new JTextField();
			symbols[go].setText(ob.toString());
			symbols[go].setEditable(false);
			symbols[go].setHorizontalAlignment(JTextField.CENTER);
			symbols[go].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
			add(symbols[go]);
			go++;
		}
		it = vow.listIterator();
		while (it.hasNext()){
			Object ob = it.next();
			symbols[go] = new JTextField();
			symbols[go].setText(ob.toString());
			symbols[go].setEditable(false);
			symbols[go].setHorizontalAlignment(JTextField.CENTER);
			symbols[go].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
			add(symbols[go]);
			go++;
		}
	}

	public void paintComponent(Graphics g) {

	}

}
