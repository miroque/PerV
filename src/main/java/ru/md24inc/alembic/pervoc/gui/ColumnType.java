package ru.md24inc.alembic.pervoc.gui;

import ru.md24inc.alembic.pervoc.domains.Card;
import ru.md24inc.alembic.pervoc.domains.Transcript;
import ru.md24inc.alembic.pervoc.domains.Translation;
import ru.md24inc.alembic.pervoc.domains.Word;

public enum ColumnType {
    WORD(Word.class) {
        @Override
        public String getValue(Card card) {
            return card.getWord().getValue();
        }
    },
    TRANSCRIPT(Transcript.class) {
        @Override
        public String getValue(Card card) {
            return card.getTranscript().getValue();
        }
    },
    TRANSLATION(Translation.class) {
        @Override
        public String getValue(Card card) {
            return card.getTranslation().getValue();
        }
    };

    private final Class<?> clazz;

    private ColumnType(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getName() {
        return getClazz().getSimpleName();
    }

    public abstract String getValue(Card card);
}
