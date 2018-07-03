import org.kxml2.io.KXmlSerializer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.KeyManagementException;

/**
 * Created by Administrator on 2017/5/19.
 */
public class MyXMLSerializer {

    public static void doSerializer() throws IOException {

        KXmlSerializer kXmlSerializer = new KXmlSerializer();

        Writer writer = new FileWriter("XMLPull/src/b.xml");
        kXmlSerializer.setOutput(writer);
        kXmlSerializer.startDocument("utf-8", true);


        kXmlSerializer.ignorableWhitespace(System.lineSeparator());


        String dtd = " aoo  [\n" +
                "        <!ELEMENT aoo (boo) >\n" +
                "        <!ELEMENT boo (aentity) >\n" +
                "        <!ELEMENT aentity      (#PCDATA)>\n" +
                "        <!ENTITY entity \"this is custom entity\">\n" +
                "        ]";
        kXmlSerializer.docdecl(dtd);
        kXmlSerializer.ignorableWhitespace(System.lineSeparator());
        kXmlSerializer.comment("ddd");
        kXmlSerializer.setPrefix("as", "a.s.cm");
        kXmlSerializer.startTag("a.s.cm", "aoo");
        kXmlSerializer.attribute("a.s.cm", "tt", "1");
        kXmlSerializer.endTag("a.s.cm", "aoo");


        kXmlSerializer.getName();


//        kXmlSerializer.cdsect("ccccccc");
//        kXmlSerializer.entityRef("zz");
        kXmlSerializer.text("dd<dd>dd");

        kXmlSerializer.ignorableWhitespace(System.lineSeparator());
        kXmlSerializer.endDocument();

    }
}
