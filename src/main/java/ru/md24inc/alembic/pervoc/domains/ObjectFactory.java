//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.01 at 04:40:25 PM GMT+08:00 
//


package ru.md24inc.alembic.pervoc.domains;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Vocabulary_QNAME = new QName("", "vocabulary");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Translation }
     * 
     */
    public Translation createTranslationType() {
        return new Translation();
    }

    /**
     * Create an instance of {@link Card }
     * 
     */
    public Card createCardType() {
        return new Card();
    }

    /**
     * Create an instance of {@link Word }
     * 
     */
    public Word createWordType() {
        return new Word();
    }

    /**
     * Create an instance of {@link Transcript }
     * 
     */
    public Transcript createTranscriptType() {
        return new Transcript();
    }

    /**
     * Create an instance of {@link Vocabulary }
     * 
     */
    public Vocabulary createVocabularyType() {
        return new Vocabulary();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vocabulary }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "vocabulary")
    public JAXBElement<Vocabulary> createVocabulary(Vocabulary value) {
        return new JAXBElement<Vocabulary>(_Vocabulary_QNAME, Vocabulary.class, null, value);
    }

}
