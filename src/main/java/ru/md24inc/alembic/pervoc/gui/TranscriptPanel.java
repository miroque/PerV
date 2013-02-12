package ru.md24inc.alembic.pervoc.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.*;


public class TranscriptPanel extends JPanel {
	JTextField [] symbols = new JTextField[10];
	
	public TranscriptPanel (){
		for (int i=0; i<10; i++){
			symbols[i]=new JTextField();
			symbols[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
			add(symbols[i]);
		}
		symbols[0].setText("\u0243");
		symbols[1].setText("\u0246");
		symbols[2].setText("\u02A4");
		symbols[3].setText("\u02A7");
		
	}
	
	
	public void paintComponent(Graphics g){
		
	}
	
}
