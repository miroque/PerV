//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.01 at 04:40:25 PM GMT+08:00 
//


package ru.md24inc.alembic.pervoc.domains;

import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cardType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cardType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="word" type="{}wordType"/>
 *         &lt;element name="transcript" type="{}transcriptType"/>
 *         &lt;element name="translation" type="{}translationType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="date" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cardType", propOrder = {
    "word",
    "transcript",
    "translation"
})
public class Card {

    @XmlElement(required = true)
    protected Word word = new Word();
    @XmlElement(required = true)
    protected Transcript transcript = new Transcript();
    @XmlElement(required = true)
    protected Translation translation = new Translation();
    @XmlAttribute(required = true)
    protected String date;

    public Card(String string, String string2, String string3) {
    	word.setValue(string);
    	transcript.setValue(string2);
    	translation.setValue(string3);
    	date = new GregorianCalendar().getTime().toString();
	}
    public Card() {
    	word.setValue("");
    	transcript.setValue("");
    	translation.setValue("");
    	date = new GregorianCalendar().getTime().toString();
    }

	/**
     * Gets the value of the word property.
     * 
     * @return
     *     possible object is
     *     {@link Word }
     *     
     */
    public Word getWord() {
        return word;
    }

    /**
     * Sets the value of the word property.
     * 
     * @param value
     *     allowed object is
     *     {@link Word }
     *     
     */
    public void setWord(Word value) {
        this.word = value;
    }

    /**
     * Gets the value of the transcript property.
     * 
     * @return
     *     possible object is
     *     {@link Transcript }
     *     
     */
    public Transcript getTranscript() {
        return transcript;
    }

    /**
     * Sets the value of the transcript property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transcript }
     *     
     */
    public void setTranscript(Transcript value) {
        this.transcript = value;
    }

    /**
     * Gets the value of the translation property.
     * 
     * @return
     *     possible object is
     *     {@link Translation }
     *     
     */
    public Translation getTranslation() {
        return translation;
    }

    /**
     * Sets the value of the translation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Translation }
     *     
     */
    public void setTranslation(Translation value) {
        this.translation = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }
    
    public String toString(){
    	return "Card ("+date+")["+word.getValue()+"|"+ transcript.getValue() +"|"+ translation.getValue() +" ]";
    }

}
