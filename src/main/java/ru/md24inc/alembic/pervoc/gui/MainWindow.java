package ru.md24inc.alembic.pervoc.gui;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import ru.md24inc.alembic.pervoc.dao.VocabularyDao;
import ru.md24inc.alembic.pervoc.domains.Card;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * @author miroque
 */
public class MainWindow extends JFrame {
    private JFileChooser fj;
    private JTable tableOfCards;
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
        JMenuBar menuBar = new JMenuBar();

        // Creating first menu named "File"
        JMenu menuFile = new JMenu("File");
        JMenuItem menuFileItemOpen = new JMenuItem("Open...");
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
                    cards = new VocabularyDao().getVocabular(file.toString());
                    TableModel model = new CardTableModel(cards);
                    tableOfCards.setModel(model);
                    System.out.println("File - " + file.toString());
                    System.out.println(cards.size());
                }
            }
        });
        menuFile.add(menuFileItemOpen);
        JMenuItem menuFileItemSave = new JMenuItem("Save...");
        menuFileItemSave.setAccelerator(KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_S,
                java.awt.event.InputEvent.CTRL_MASK));
        menuFile.add(menuFileItemSave);
        menuFile.add(new JPopupMenu.Separator());
        JMenuItem menuFileItemQuit = new JMenuItem("Quit");
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
        JMenu menuViews = new JMenu("Views");
        JMenuItem menuViewsItemTscript = new JMenuItem("Transcript");
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
        cards.add(new Card());
        TableModel model1 = new CardTableModel(cards);
        tableOfCards = new JTable();
        tableOfCards.setModel(model1);
        tableOfCards.setAutoCreateColumnsFromModel(true);
        JScrollPane scrollPaneForTableVoc = new JScrollPane(tableOfCards);
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
                    cards.add(new Card());
                    tableOfCards.repaint();
//					scrollPaneForTableVoc.repaint();
                    System.out.println(cards.size());

                }
            }
        });
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

    private static class CardTableModel implements TableModel {

        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
        private static final Map<Integer, ColumnType> index2column = ImmutableMap.<Integer, ColumnType> builder()
                .put(0, ColumnType.WORD)
                .put(1, ColumnType.TRANSCRIPT)
                .put(2, ColumnType.TRANSLATION)
                .build();
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
            return index2column.get(columnIndex).getClazz();
        }

        @Override
        public int getColumnCount() {
            return index2column.size();
        }

        @Override
        public String getColumnName(int columnIndex) {
            return index2column.get(columnIndex).getName();
        }

        @Override
        public int getRowCount() {
            return beans.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Card bean = beans.get(rowIndex);
            return index2column.get(columnIndex).getValue(bean);
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
