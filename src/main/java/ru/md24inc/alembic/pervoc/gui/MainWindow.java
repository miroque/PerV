package ru.md24inc.alembic.pervoc.gui;

import ru.md24inc.alembic.pervoc.dao.VocabularyDao;
import ru.md24inc.alembic.pervoc.domains.Card;
import ru.md24inc.alembic.pervoc.domains.Transcript;
import ru.md24inc.alembic.pervoc.domains.Translation;
import ru.md24inc.alembic.pervoc.domains.Word;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.ArrayList;
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
	private JFileChooser fj;
	private JTable tableOfCards;
	private JScrollPane scrollPaneForTableVoc;
	private File file;
	private List<Card> cards;
	private TranscriptPanel transcriptPanel;

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
		fj = new JFileChooser();
		menuFileItemOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
						fj.setCurrentDirectory(f);
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
					cards = new VocabularyDao().getVocabular(file.toString());
					TableModel model = new CardTableModel(cards);
					tableOfCards.setModel(model);
					System.out.println("File - "+file.toString());
					System.out.println(cards.size());
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
				transcriptPanel.setVisible(!transcriptPanel.isVisible());
			}
		});
		menuViews.add(menuViewsItemTscript);

		// Adding menu Views to Bar menu
		menuBar.add(menuViews);

		// Adding menu bar in our main frame
		setJMenuBar(menuBar);

		// Creating and Adding Table with Vocabulary into main Frame
        cards = new ArrayList<Card>();
//        cards.add(new Card("","",""));
        cards.add(new Card());
//        cards.add(new Card());
        TableModel model1 = new CardTableModel(cards);
		tableOfCards = new JTable();
        tableOfCards.setModel(model1);
		scrollPaneForTableVoc = new JScrollPane(tableOfCards);
        tableOfCards.addComponentListener((ComponentListener) transcriptPanel);
		tableOfCards.setFillsViewportHeight(true);
		add(BorderLayout.CENTER, scrollPaneForTableVoc);
		transcriptPanel = new TranscriptPanel();
		transcriptPanel.setVisible(false);
        transcriptPanel.addTypeIn(tableOfCards);
		add(BorderLayout.NORTH, transcriptPanel);

		// frame.pack();
		setVisible(true);
	}

	private class CardTableModel implements TableModel {

		private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
		private String[] colNames = { "Word", "Transcript", "Translation" };
		private List<Card> beans;

		public CardTableModel(List<Card> beans) {
			this.beans = beans;
		}

		@Override
		public void addTableModelListener(TableModelListener listener) {
			listeners.add(listener);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (colNames[columnIndex].equalsIgnoreCase("Word"))
				return Word.class;
			else if (colNames[columnIndex].equalsIgnoreCase("Transcript"))
				return Transcript.class;
			else if (colNames[columnIndex].equalsIgnoreCase("Translation"))
				return Translation.class;
			else
				return null;
		}

		@Override
		public int getColumnCount() {
			return colNames.length;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return colNames[columnIndex];
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
				return bean.getWord().getValue();
			case 1:
				return bean.getTranscript().getValue();
			case 2:
				return bean.getTranslation().getValue();
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
			 beans.set(rowIndex, (Card) value);
//			 fireTableCellUpdated(rowIndex, columnIndex);
		}

	}
}
