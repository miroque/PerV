package ru.md24inc.alembic.pervoc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.StringUtils;

import ru.md24inc.alembic.pervoc.dao.VocabularyDao;
import ru.md24inc.alembic.pervoc.domains.Card;
import ru.md24inc.alembic.pervoc.domains.Transcript;
import ru.md24inc.alembic.pervoc.domains.Translation;
import ru.md24inc.alembic.pervoc.domains.Vocabulary;
import ru.md24inc.alembic.pervoc.domains.Word;

/**
 * @author miroque
 */
public class MainWindow extends JFrame {
//<<<<<<< HEAD
    // Variables declaration
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuViews;
	private JMenuItem menuFileItemNew;
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
	private Vocabulary vocabulary;

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
        menuFileItemNew = new JMenuItem("New");
		menuFileItemNew.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_N,
				java.awt.event.InputEvent.CTRL_MASK));
		menuFileItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vocabulary = new Vocabulary();
				vocabulary.getCards().add(new Card());
				tableOfCards.setModel(new VocaTableModel(vocabulary));
				tableOfCards.repaint();
			}
		});
		menuFile.add(menuFileItemNew);
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
                        return StringUtils.equals(ext, "pvoc");
                    }

                    @Override
                    public String getDescription() {
                        return "Personal Vocabular Files";
                    }
                });
                fj.setAcceptAllFileFilterUsed(false);
                if (fj.showDialog(null, "Открыть файл") == JFileChooser.APPROVE_OPTION) {
                    file = fj.getSelectedFile();
                    tableOfCards.setModel(new VocaTableModel(new VocabularyDao().getVocabular(file.toString())));
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
        
        tableOfCards = new JTable();
        vocabulary = new Vocabulary();
        tableOfCards.setModel(new VocaTableModel(vocabulary));
        tableOfCards.setAutoCreateColumnsFromModel(true);
        scrollPaneForTableVoc = new JScrollPane(tableOfCards);
        tableOfCards.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_INSERT) {
                	vocabulary.getCards().add(new Card());
					tableOfCards.repaint();
                }
            }
        });

        tableOfCards.addComponentListener((ComponentListener) transcriptPanel);
        tableOfCards.setFillsViewportHeight(true);
        add(BorderLayout.CENTER, scrollPaneForTableVoc);
        transcriptPanel = new TranscriptPanel();
        transcriptPanel.setVisible(false);
        transcriptPanel.addTypeIn(tableOfCards);
//<<<<<<< HEAD
        add(BorderLayout.NORTH, transcriptPanel);

        // frame.pack();
        setVisible(true);
    }

    private class VocaTableModel extends AbstractTableModel {
		protected Vocabulary tmpVocabulary;
		private String[] colNames = { "Word", "Transcript", "Translation" };
		
		public VocaTableModel(Vocabulary voc){
			tmpVocabulary = voc;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex < 0 || columnIndex > colNames.length)
				return "ERROR";
			else
				return colNames[columnIndex];
		}
		
		@Override
		public int getRowCount() {
			return tmpVocabulary.getCards().size();
		}

		@Override
		public int getColumnCount() {
			return colNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0: return tmpVocabulary.getCards().get(rowIndex).getWord().getValue(); 
			case 1: return tmpVocabulary.getCards().get(rowIndex).getTranscript().getValue(); 
			case 2: return tmpVocabulary.getCards().get(rowIndex).getTranslation().getValue();
			default: return null;
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex){
			switch(columnIndex){
			case 0: return Word.class; 
			case 1: return Transcript.class; 
			case 2: return Translation.class;
			default: return null;
			}
		}
    }

}
