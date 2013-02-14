package ru.md24inc.alembic.pervoc.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.*;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class TranscriptPanel extends JPanel {
	private static final long serialVersionUID = -7784099290733841692L;

	JTextField[] symbols = new JTextField[51];

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
		for (int i = 0; i < 51; i++) {
			symbols[i] = new JTextField();
			symbols[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
			symbols[i].setEditable(false);
			symbols[i].setHorizontalAlignment(JTextField.CENTER);
			add(symbols[i]);
		}
		for (Object c : cons) {
			symbols[1].setText(c.toString());
		}
	}

	public void paintComponent(Graphics g) {

	}

}
