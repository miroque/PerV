package src.gui;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PerVoc {

	private JFrame mainFrame;
	private JFileChooser fileChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerVoc window = new PerVoc();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PerVoc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("PerVoc - Personal Vocabulary - ver. 0.1");
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		// Create a file chooser
		fileChooser = new JFileChooser();

		JMenuItem mntmOpenFile = new JMenuItem("Open File ...");

		// Handle open menu action.
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// fc.showOpenDialog(null);
				fileChooser.showOpenDialog(mainFrame);
				// int returnVal = fc.showOpenDialog(PerVoc.this)
			}
		});
		mnFile.add(mntmOpenFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Action Performed");
				mainFrame.dispose();
			}
		});
		mnFile.add(mntmExit);
	}

}
