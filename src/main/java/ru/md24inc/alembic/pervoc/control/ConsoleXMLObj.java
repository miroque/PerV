package ru.md24inc.alembic.pervoc.control;

import ru.md24inc.alembic.pervoc.core.*;

public class ConsoleXMLObj {
    //public final static String srcOfFile = "D:\\workspace\\PerV\\Xclay\\mud.xml";

    public final static String srcOfFile = "c:\\Users\\miroque\\Documents\\NetBeansProjects\\PerV\\Xclay\\mud.xml";

    public static void main(String args[]) {
        Vocabulary vocaBul = new Vocabulary();
        vocaBul.openXMLFile(srcOfFile);
        vocaBul.printVocabular();
        vocaBul.saveXMLFile("c:\\Users\\miroque\\Documents\\NetBeansProjects\\PerV\\Xclay\\mudSaved.xml");
    }
}
