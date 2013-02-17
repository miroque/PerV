package ru.md24inc.alembic.pervoc.core;

import java.util.Calendar;
import java.util.Date;

public class Card {
    private static int count;
    private Word word;
    private Transcript transcript;
    private Translation translation;
    private Date dated;

    public static int getCount() {
        return count;
    }
    public static void resetCount(){
        count=0;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }


    public Date getDated() {
        return dated;
    }


    public Card() {
        count++;
        word = new Word();
        transcript = new Transcript();
        translation = new Translation();
    }

    public Card(Date date, String wrd, String trc, String trs) {
        count++;
        dated = date;
        word = new Word(wrd);
        transcript = new Transcript(trc);
        translation = new Translation(trs);
    }

    public Card(String wrd, String trc, String trs) {
        count++;
        dated = Calendar.getInstance().getTime();
        word = new Word(wrd);
        transcript = new Transcript(trc);
        translation = new Translation(trs);
    }

    public String getWord() {
        return word.getWord();
    }

    public void setWord(String word) {
        this.word.setWord(word);
    }

    public String getTranscript() {
        return transcript.getTranscript();
    }

    public void setTranscript(String transcript) {
        this.transcript.setTranscript(transcript);
    }

    public String getTranslation() {
        return translation.getTranslation();
    }

    public void setTranslation(String translation) {
        this.translation.setTranslation(translation);
    }
}
