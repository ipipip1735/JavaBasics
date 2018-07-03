import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/16.
 */
public class SaxXML {

    public static void main(String[] args) {


        SaxXML saxXML = new SaxXML();

//        saxXML.parseForSAX();
        saxXML.parseForSAX2();


    }


    private void parseForSAX() {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        File xml = new File("XMLPush/src/config.xml");
        MyHandler myHandler = new MyHandler();

        try {
            parser.parse(xml, myHandler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void parseForSAX2() {


        MyHandler myHandler = new MyHandler();
        XMLReader xmlReader = null;
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        xmlReader.setContentHandler(myHandler);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("XMLPush/src/config.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            xmlReader.parse(new InputSource(fileReader));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }


}
