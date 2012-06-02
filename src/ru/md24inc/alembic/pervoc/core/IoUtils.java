package src.ru.md24inc.alembic.pervoc.core;
import com.sun.xml.tree.*;
import org.w3c.dom.*;
import java.io.*;
 
public class IoUtils {
 
//
// Data Members
//
    //xml file location - from user’s input
    public static String fileLocation;
 
    //TABLE META DATA
    public static final String ROOT_ELEMENT_TAG = “PERSON”;
 
//
// Methods
//
 
     /**
      Return AddressBook object
      @return    a AddressBook Object
     */
 
 
    public static AddressBook getAddressBook(String file){
 
         fileLocation = file;
         //instantiate AddressBook object
         AddressBook addressBook = new AddressBook();
         Person person;
 
         try {
            //convert comming file location into input stream
             InputStream is = new FileInputStream(file);
 
             //create xml document
             Document doc =
               XmlDocumentBuilder.createXmlDocument(is);
 
             //get the number of person
             int size = XmlUtils.getSize(doc , ROOT_ELEMENT_TAG );
 
             for ( int i = 0; i < size; i++ ) {
 
                 //instanticate a Person object
                 person = new Person();
 
                 //get information about a person 
                 //and set information
                 Element row =
                   XmlUtils.getElement(doc , ROOT_ELEMENT_TAG , i);
                 person.setLastName(
                   XmlUtils.getValue( row , “LASTNAME” ) );
                 person.setFirstName(
                   XmlUtils.getValue( row , “FIRSTNAME” ) );
                 person.setEmail(
                   XmlUtils.getValue( row , “EMAIL” ) );
                 person.setCompany(
                   XmlUtils.getValue( row , “COMPANY” ) );
 
                 //add a person to an address book
                 addressBook.add(person);
 
             }//end for
 
         }
 
         catch ( Exception e ) {
             System.out.println( e );
             return addressBook;
         }
 
         return addressBook;
 
     }//end method
 
 ….
 
}//end of IoUtils class
