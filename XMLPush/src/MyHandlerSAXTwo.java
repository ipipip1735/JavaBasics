import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

/**
 * Created by Admin is trator on 2016/8/16.
 */
public class MyHandlerSAXTwo extends DefaultHandler
{

    @Override
    public void startDocument() throws SAXException {
        System.out.println("*****  startDocument  ******");
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("*****  endDocument  ******");
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("*****  startElement  ******");
        System.out.println("uri = " + uri);
        System.out.println("localName = " + localName);
        System.out.println("qName = " + qName);

        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.println(attributes.getLocalName(i));
            System.out.println(attributes.getQName(i));
        }


        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("*****  endElement  ******");
        System.out.println("uri = " + uri);
        System.out.println("localName = " + localName);
        System.out.println("qName = " + qName);
        super.endElement(uri, localName, qName);
    }

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
        System.out.println("*****  resolveEntity  ******");
        System.out.println("publicId = " + publicId);
        System.out.println("systemId = " + systemId);
        return super.resolveEntity(publicId, systemId);
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("*****  characters  ******");
        System.out.println("start = " + start);
        System.out.println("length = " + length);
        String s = new String(ch, start, length);
        System.out.println(s);
        super.characters(ch, start, length);
    }
}
