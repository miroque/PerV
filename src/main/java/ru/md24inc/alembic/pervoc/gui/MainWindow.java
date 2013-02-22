package ru.md24inc.alembic.pervoc.gui;

import ru.md24inc.alembic.pervoc.core.Card;
import ru.md24inc.alembic.pervoc.dao.CardXmlDao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;

/**
 * 
 * @author miroque
 */
public class MainWindow extends JFrame {
	// Variables declaration
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuViews;
	private JMenuItem menuFileItemOpen;
	private JMenuItem menuFileItemQuit;
	private JMenuItem menuFileItemSave;
	private JMenuItem menuViewsItemTscript;
	private JTable tableVoc;
	private JScrollPane scrollPaneForTableVoc;
	private File file;
	private List<Card> cards;
	private TranscriptPanel tp;

	/**
	 * Creates new form MainWindow
	 */
	public MainWindow() {
		initComponents();
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow().setVisible(true);
			}
		});
	}

	private void initComponents() {
		// Creating main frame and giving it title
		// Personal Vocabular with def.action on exit.
		setTitle("Personal Vocabulary (version 0.10)");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Making min. and max sizes.
		Dimension minSize = new Dimension(300, 450);
		setMinimumSize(minSize);
		// max size not set

		// Creating bar for menu
		menuBar = new JMenuBar();

		// Creating first menu named "File"
		menuFile = new JMenu("File");
		menuFileItemOpen = new JMenuItem("Open...");
		menuFileItemOpen.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_O,
				java.awt.event.InputEvent.CTRL_MASK));
		menuFileItemOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Card.resetCount();
				JFileChooser fj = new JFileChooser();
				fj.addChoosableFileFilter(new FileFilter() {
					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						}
						String ext = null;
						String extension = f.getName();
						int i = extension.lastIndexOf('.');
						if (i > 0 && i < extension.length() - 1) {
							ext = extension.substring(i + 1).toLowerCase();
						}
						if ("pvoc".equals(ext))
							return true;
						return false;
					}

					@Override
					public String getDescription() {
						return "Personal Vocabular Files";
					}
				});
				fj.setAcceptAllFileFilterUsed(false);
				if (fj.showDialog(null, "Открыть файл") == JFileChooser.APPROVE_OPTION) {
					file = fj.getSelectedFile();
					cards = new CardXmlDao().openXMLFileAndGetAll(file);
					TableModel model = new MyTableModel(cards);
					tableVoc.setModel(model);
					System.out.println(Card.getCount());
				}
			}
		});
		menuFile.add(menuFileItemOpen);
		menuFileItemSave = new JMenuItem("Save...");
		menuFileItemSave.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.CTRL_MASK));
		menuFile.add(menuFileItemSave);
		menuFile.add(new JPopupMenu.Separator());
		menuFileItemQuit = new JMenuItem("Quit");
		menuFileItemQuit.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Q,
				java.awt.event.InputEvent.CTRL_MASK));
		menuFileItemQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuFile.add(menuFileItemQuit);

		// Adding menu File to Bar menu
		menuBar.add(menuFile);

		// Creating second menu named "Views"
		menuViews = new JMenu("Views");
		menuViewsItemTscript = new JMenuItem("Transcript");
		menuViewsItemTscript.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_T,
				java.awt.event.InputEvent.CTRL_MASK
						| java.awt.event.InputEvent.SHIFT_MASK));
		menuViewsItemTscript.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tp.setVisible(!tp.isVisible());
			}
		});
		menuViews.add(menuViewsItemTscript);

		// Adding menu Views to Bar menu
		menuBar.add(menuViews);

		// Adding menu bar in our main frame
		setJMenuBar(menuBar);

		// Creating and Adding Table with Vocabulary into main Frame
		String[] colNames = { "Word", "Transcript", "Translate" };
		Object[][] data = { { "", "", "" } };
		tableVoc = new JTable(data, colNames);
		scrollPaneForTableVoc = new JScrollPane(tableVoc);
		tableVoc.setFillsViewportHeight(true);
		add(BorderLayout.CENTER, scrollPaneForTableVoc);
		tp = new TranscriptPanel();
		tp.setVisible(false);
		add(BorderLayout.NORTH, tp);

		// frame.pack();
		setVisible(true);
	}

	private class MyTableModel implements TableModel {

		private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
		private List<Card> beans;

		public MyTableModel(List<Card> beans) {
			this.beans = beans;
		}

		@Override
		public void addTableModelListener(TableModelListener listener) {
			listeners.add(listener);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "Word";
			case 1:
				return "Transcript";
			case 2:
				return "Translate";
			}
			return "";
		}

		@Override
		public int getRowCount() {
			return beans.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Card bean = beans.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return bean.getWord();
			case 1:
				return bean.getTranscript();
			case 2:
				return bean.getTranslation();
			}
			return "";
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}

		@Override
		public void removeTableModelListener(TableModelListener listener) {
			listeners.remove(listener);
		}

		@Override
		public void setValueAt(Object value, int rowIndex, int columnIndex) {

		}

	}
}
