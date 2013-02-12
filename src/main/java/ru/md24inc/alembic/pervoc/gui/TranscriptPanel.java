package ru.md24inc.alembic.pervoc.gui;

import java.awt.Graphics;

import javax.swing.*;


public class TranscriptPanel extends JPanel {
	JTextField [] symbols = new JTextField[10];
	
	public TranscriptPanel (){
		for (int i=0; i<10; i++){
			symbols[i]=new JTextField("i"+i);
			add(symbols[i]);
		}
		
	}
	
	
	public void paintComponent(Graphics g){
		
	}
	
}
