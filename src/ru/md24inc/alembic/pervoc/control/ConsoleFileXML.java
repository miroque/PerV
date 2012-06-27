package ru.md24inc.alembic.pervoc.control;

import ru.md24inc.alembic.pervoc.core.*;
import java.io.*;

public class ConsoleFileXML {
	public static String promtToFile() {
		// Creating Console promt for filename
		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		String fileName = c.readLine("Enter your fileName: ");
		return fileName;
	}

	public static void main(String args[]) throws IOException {
		// Open file
		BufferedReader buff = null;
		for (int i = 0; i < 3; i++) {
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
				i=3;
				System.out.println("Error -- " + e.getMessage());
			} finally {
				buff.close();
			}
		}
		// Printing file to console

	}
}
