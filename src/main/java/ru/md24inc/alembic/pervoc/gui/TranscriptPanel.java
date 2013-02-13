package ru.md24inc.alembic.pervoc.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.*;


public class TranscriptPanel extends JPanel {
	private static final long serialVersionUID = -7784099290733841692L;

	JTextField [] symbols = new JTextField[51];
	
	public TranscriptPanel (){
		setLayout(new GridLayout(0, 10, 2, 2));
		setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		
		for (int i=0; i<51; i++){
			symbols[i]=new JTextField();
			symbols[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
			symbols[i].setEditable(false);
			symbols[i].setHorizontalAlignment(JTextField.CENTER);
			add(symbols[i]);
		}
		symbols[0].setText("\u00E6");
		symbols[1].setText("\u0244");
		symbols[2].setText("\u0245");
		symbols[3].setText("\u0246");
		symbols[4].setText("\u02A4");
		symbols[5].setText("\u02A7");
		symbols[6].setText("\u0261");
	}
	
	
	public void paintComponent(Graphics g){
		
	}
	
}
