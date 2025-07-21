package PesanHasil;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PendaftaranXMLHandler {

    private static final String FILE_PATH = "pendaftaran.xml";

    public static void simpanKeXML(List<Pendaftaran> dataList) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("pendaftaranList");
            doc.appendChild(rootElement);

            for (Pendaftaran data : dataList) {
                Element dataElement = doc.createElement("pendaftaran");

                Element nama = doc.createElement("namaLengkap");
                nama.appendChild(doc.createTextNode(data.getNamaLengkap()));
                dataElement.appendChild(nama);

                Element ttl = doc.createElement("ttl");
                ttl.appendChild(doc.createTextNode(data.getTtl()));
                dataElement.appendChild(ttl);

                Element alamat = doc.createElement("alamat");
                alamat.appendChild(doc.createTextNode(data.getAlamat()));
                dataElement.appendChild(alamat);

                Element telepon = doc.createElement("nomorTelepon");
                telepon.appendChild(doc.createTextNode(data.getNomorTelepon()));
                dataElement.appendChild(telepon);

                Element gender = doc.createElement("gender");
                gender.appendChild(doc.createTextNode(data.getGender()));
                dataElement.appendChild(gender);

                rootElement.appendChild(dataElement);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            File file = new File(FILE_PATH);
            //file.getParentFile().mkdirs();
            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Pendaftaran> bacaDariXML() {
        List<Pendaftaran> dataList = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return dataList;

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("pendaftaran");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;

                    String nama = e.getElementsByTagName("namaLengkap").item(0).getTextContent();
                    String ttl = e.getElementsByTagName("ttl").item(0).getTextContent();
                    String alamat = e.getElementsByTagName("alamat").item(0).getTextContent();
                    String telepon = e.getElementsByTagName("nomorTelepon").item(0).getTextContent();
                    String gender = e.getElementsByTagName("gender").item(0).getTextContent();

                    dataList.add(new Pendaftaran(nama, ttl, alamat, telepon, gender));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
