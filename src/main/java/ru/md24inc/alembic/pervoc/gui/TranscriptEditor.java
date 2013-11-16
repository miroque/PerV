package ru.md24inc.alembic.pervoc.gui;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import ru.md24inc.alembic.pervoc.domains.Transcript;

public class TranscriptEditor extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 8845237212414233052L;
	private Transcript transcript = new Transcript();
	private JLabel textField = new JLabel();
	private StringBuilder editedString = new StringBuilder();
	private JTable tmpTable = null;
/*
	public TranscriptEditor(JTable tableOfCards) {
		tmpTable = tableOfCards;
	}*/

	@Override
	public Object getCellEditorValue() {
//		tmpTable 
		editedString.append("-1");
		return editedString.toString();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		editedString.setLength(0);
		editedString.append(value.toString());
		textField.setText(editedString.toString());
		return textField;
	}

}
