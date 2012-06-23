package src.ru.md24inc.alembic.pervoc.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import src.ru.md24inc.alembic.pervoc.core.*;

public class ConsoleXML {
	public final static String file = "D:\\workspace\\PerV\\Xclay\\mud.xml";

	public static void main(String args[]) {
		// Open XML File
		openXMLFile(file);
		// Parcing XML Tree to Java Objects
		// Printing to System.out with tub
	}

	public static void openXMLFile(String fileName) {
		BufferedReader buff = null;

		try {
			FileReader file = new FileReader(promtToFile());
			buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else
					// Printing file to console
					System.out.println(line);
			}
		} catch (IOException e) {
			i = 3;
			System.out.println("Error -- " + e.getMessage());
		} finally {
			buff.close();
		}

	}
}
