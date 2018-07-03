import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/16.
 */
public class MyXMLParser {

    public static void  doStart() throws XmlPullParserException, IOException {
        System.out.println("  -------- doStart --------");




        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
        System.out.println(xmlPullParser + " is the implement of the interface!");

        FileReader read = new FileReader("XMLPull/src/a.xml");
        xmlPullParser.setInput(read);



        for (int event = xmlPullParser.getEventType();event != XmlPullParser.END_DOCUMENT ;event = xmlPullParser.nextToken()) {


            System.out.println("=============");
            System.out.println(xmlPullParser.TYPES[xmlPullParser.getEventType()]);
            System.out.println(xmlPullParser.getLineNumber());
            System.out.println(xmlPullParser.getColumnNumber());
            System.out.println("--------------------------");
            System.out.println(xmlPullParser.getName());
            System.out.println(xmlPullParser.getNamespace());
            System.out.println(xmlPullParser.getPrefix());
            System.out.println(xmlPullParser.getText());
            System.out.println("=============");

        }


    }



}
