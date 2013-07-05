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
    private JFileChooser fj = new JFileChooser();
    private JTable tableOfCards = new JTable();
    private File file;
    private List<Card> cards = new ArrayList<Card>();
    private TranscriptPanel transcriptPanel = new TranscriptPanel();

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
        setMinimumSize(new Dimension(300, 450));
        // max size not set

        // Creating bar for menu
        setJMenuBar(createMenuBar());

        // Creating and Adding Table with Vocabulary into main Frame
        add(BorderLayout.CENTER, createCardsScrollPane());
        add(BorderLayout.NORTH, createTranscriptPanel());

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createViewsMenu());
        return menuBar;
    }

    private JMenu createFileMenu() {
        JMenu menuFile = new JMenu("File");
        menuFile.add(createOpenMenuItem());
        menuFile.add(createSaveMenuItem());
        menuFile.add(new JPopupMenu.Separator());
        menuFile.add(createQuitMenuItem());
        return menuFile;
    }

    private JMenuItem createQuitMenuItem() {
        JMenuItem menuFileItemQuit = new JMenuItem("Quit");
        menuFileItemQuit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q,
                InputEvent.CTRL_MASK));
        menuFileItemQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return menuFileItemQuit;
    }

    private JMenuItem createSaveMenuItem() {
        JMenuItem menuFileItemSave = new JMenuItem("Save...");
        menuFileItemSave.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S,
                InputEvent.CTRL_MASK));
        return menuFileItemSave;
    }

    private JMenuItem createOpenMenuItem() {
        JMenuItem menuFileItemOpen = new JMenuItem("Open...");
        menuFileItemOpen.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O,
                InputEvent.CTRL_MASK));
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
        return menuFileItemOpen;
    }

    private JMenu createViewsMenu() {
        JMenu menuViews = new JMenu("Views");
        menuViews.add(createTranscriptMenuItem());
        return menuViews;
    }

    private JMenuItem createTranscriptMenuItem() {
        JMenuItem menuViewsItemTscript = new JMenuItem("Transcript");
        menuViewsItemTscript.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T,
                InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        menuViewsItemTscript.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transcriptPanel.setVisible(!transcriptPanel.isVisible());
            }
        });
        return menuViewsItemTscript;
    }

    private JScrollPane createCardsScrollPane() {
        cards.add(new Card());
        tableOfCards.setModel(new CardTableModel(cards));
        tableOfCards.setAutoCreateColumnsFromModel(true);
        tableOfCards.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_INSERT) {
                    cards.add(new Card());
                    tableOfCards.repaint();
//					scrollPaneForTableVoc.repaint();
                    System.out.println(cards.size());

                }
            }
        });
        tableOfCards.addComponentListener((ComponentListener) transcriptPanel);
        tableOfCards.setFillsViewportHeight(true);
        return new JScrollPane(tableOfCards);
    }

    private TranscriptPanel createTranscriptPanel() {
        transcriptPanel.setVisible(false);
        transcriptPanel.addTypeIn(tableOfCards);
        return transcriptPanel;
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
