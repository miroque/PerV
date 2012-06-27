package ru.md24inc.alembic.pervoc.control;

import ru.md24inc.alembic.pervoc.core.*;

public class ConsoleXMLObj {
	public final static String srcOfFile = "D:\\workspace\\PerV\\Xclay\\mud.xml";

	public static void main(String args[]){
		Vocabulary vocaBul = new Vocabulary();
		vocaBul.openXMLFile(srcOfFile);
		vocaBul.printVocabular();
	}
}