package src.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.ScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JTable;

public class App {

	private JFrame frmMdincTestWindows;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmMdincTestWindows.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMdincTestWindows = new JFrame();
		frmMdincTestWindows.setTitle("MD24inc. Test windows system.");
		frmMdincTestWindows.setBounds(100, 100, 609, 499);
		frmMdincTestWindows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMdincTestWindows.getContentPane().setLayout(
				new GridLayout(0, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmMdincTestWindows.getContentPane().add(tabbedPane);

		JPanel pStart = new JPanel();
		tabbedPane.addTab("Start Project", null, pStart, null);
		pStart.setLayout(new BorderLayout(0, 0));

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBackground(new Color(224, 255, 255));
		pStart.add(scrollPane, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("Make Work");
		pStart.add(btnNewButton, BorderLayout.SOUTH);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		pStart.add(table, BorderLayout.NORTH);

		JPanel pControl = new JPanel();
		tabbedPane.addTab("Control Project", null, pControl, null);
		pControl.setLayout(new BorderLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		frmMdincTestWindows.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("New menu");
		mnNewMenu.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("new menu open");
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		mnNewMenu.add(mntmNewMenuItem);
	}

}
