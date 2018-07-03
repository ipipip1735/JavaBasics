import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * Created by Administrator on 2017/5/16.
 */
public class Main {


    public static void main(String[] args) throws XmlPullParserException, IOException {
        System.out.println("  ----- Main -----");


//        MyXMLParser.doStart();
        MyXMLSerializer.doSerializer();


    }


}
