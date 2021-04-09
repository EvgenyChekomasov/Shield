package org.echek;

import org.apache.fop.apps.*;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class PDFBuilder {

   OutputStream out;

    void convertToPDF(String addressFileHold) throws IOException, FOPException, TransformerException {

        // рабочий XML файл
        StreamSource xmlSource = new StreamSource("D:\\tempAttachment.xml");

        //File xsltFile = new File("C:\\Users\\Default\\AppData\\Local\\Temp", "tempXLSAttachment.xls");

        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        // настройки вывода
        out = new FileOutputStream(addressFileHold);
        out = new BufferedOutputStream(out);

        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // настройки xslt
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(this.getClass().getResourceAsStream("/AttachmentStyle.xsl")));
            //Transformer transformer = factory.newTransformer(new StreamSource(new File("src\\main\\resources")));

            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}
