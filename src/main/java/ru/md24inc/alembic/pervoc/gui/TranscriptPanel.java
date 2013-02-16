package ru.md24inc.alembic.pervoc.gui;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
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

	public TranscriptPanel() {
		initSymbols();
	}

	private void initSymbols() {
        //
		setLayout(new GridLayout(0, 10, 2, 2));
		setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
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

		List<Object> cons = mxconf.getList("Consonants.symbol");
//        for (int i=0 ; i<cons.size();i++){
//		String consAtr = mxconf.getString("Consonants.symbol(0)[@hint]","");
//        }
		List<Object> vow = mxconf.getList("Vowels.symbol");
		int totalS = cons.size()+vow.size();
		symbols = new JLabel[totalS+2];
        Border border = BorderFactory.createLineBorder(Color.lightGray, 1);

        ListIterator<Object> it = cons.listIterator();
		symbols[0] = new JLabel("Cons");
        symbols[0].setBorder(border);
        symbols[0].setToolTipText("Consonants");
//        symbols[0].setEditable(false);
        symbols[0].setHorizontalAlignment(JTextField.CENTER);
        symbols[0].setForeground(new Color(0,0,140));
		add(symbols[0]);
		int go=1;
		while (it.hasNext()){
			Object ob = it.next();
			symbols[go] = new JLabel();
			symbols[go].setText(ob.toString());
//			symbols[go].setEditable(false);
            symbols[go].setBorder(border);
			symbols[go].setHorizontalAlignment(JLabel.CENTER);
            symbols[go].setForeground(new Color(0, 0, 140));
			symbols[go].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
			add(symbols[go]);
			go++;
		}
		symbols[++go] = new JLabel("Vow");
//        symbols[go].
        symbols[go].setToolTipText("Vowels");
//        symbols[go].setEditable(false);
        symbols[go].setHorizontalAlignment(JLabel.CENTER);
        symbols[go].setBorder(border);
        symbols[go].setForeground(new Color(130, 0, 0));
		add(symbols[go]);
		it = vow.listIterator();
		while (it.hasNext()){
			Object ob = it.next();
			symbols[go] = new JLabel();
			symbols[go].setText(ob.toString());
//			symbols[go].setEditable(false);
            symbols[go].setBorder(border);
			symbols[go].setHorizontalAlignment(JLabel.CENTER);
            symbols[go].setForeground(new Color(130, 0, 0));
			symbols[go].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
			add(symbols[go]);
			go++;
		}
	}

	public void paintComponent(Graphics g) {

	}

}
