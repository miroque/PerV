/**
 * 
 */
package gui;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.awt.Dimension;
import javax.swing.*;

/**
 * @author miroque
 * 
 */
public class VocaBull {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("VocaBull");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(200, 200);

		// Sizes
		Dimension dd = new Dimension();
		dd.width = 300;
		dd.height = 550;
		frame.setSize(dd);

		// Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("Hello World");
		frame.getContentPane().add(label);

		// Display the window.
		// frame.pack();
		frame.setVisible(true);

		JTable table1 = new JTable();
		table1 = preparyTable();
		JScrollPane scrollPane = new JScrollPane(table1);
		table1.setFillsViewportHeight(true);
		frame.getContentPane().add(scrollPane);
	}

	public static JTable preparyTable() {
		// Creating Column Names
		String[] columnNames = { "First Name", "Last Name", "Sport",
				"# of Years", "Vegetarian" };

		// Setting Datas
		Object[][] data = {
				{ "Kathy", "Smith", "Snowboarding", new Integer(5),
						new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2),
						new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20),
						new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

		// Creating Table and filling with datas
		JTable table = new JTable(data, columnNames);
		return table;
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
				// testing with XML
				String FILE_NAME = "./clay/mud.xml";
				File file = new File(FILE_NAME);
				if (!(file.exists() && file.canRead())) {
					System.err.println("Error: cannot read " + FILE_NAME
							+ ". Exiting now.");
					System.exit(1);
				}
				try {
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder builder = dbFactory.newDocumentBuilder();
					Document doc = builder.parse(file);

					Node rootNode = doc.getFirstChild();
					System.out.println("Root node name: "
							+ rootNode.getNodeName());

					NodeList nodes = rootNode.getChildNodes();
					System.out.println("There are " + nodes.getLength()
							+ " total nodes.");

					int count = 0;
					for (int i = 0; i < nodes.getLength(); i++) {
						Node node = nodes.item(i);
						if (node instanceof Element) {
							count++;
						}

						System.out.println("There are " + count
								+ " Element nodes.");

						Element child = (Element) nodes.item(1);
						NamedNodeMap nodeMap = child.getAttributes();
						for (int r = 0; r < nodeMap.getLength(); r++) {
							Node nodee = nodeMap.item(r);
							String name = nodee.getNodeName();
							String value = nodee.getNodeValue();
							System.out.println("Attribute (" + name
									+ ") has value(" + value + ")");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		});
	}
}
