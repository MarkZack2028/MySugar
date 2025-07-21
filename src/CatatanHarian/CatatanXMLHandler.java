package CatatanHarian;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class CatatanXMLHandler {
    private static final String XML_FILE = "data_foodDiary.xml/";
    //"C:/Users/Lenovo/OneDrive/Documents/TugasAkhir/data_users.xml"

    public static void simpanKeXML(ArrayList<CatatanHarianData> dataList) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element root = doc.createElement("catatanharian");
            doc.appendChild(root);

            for (CatatanHarianData data : dataList) {
                Element catatan = doc.createElement("catatan");
                root.appendChild(catatan);

                tambahElemen(doc, catatan, "tanggal", data.getTanggal());
                tambahElemen(doc, catatan, "makanPagi", data.getMakanPagi());
                tambahElemen(doc, catatan, "makanSiang", data.getMakanSiang());
                tambahElemen(doc, catatan, "makanMalam", data.getMakanMalam());
                tambahElemen(doc, catatan, "username", data.getUsername());
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XML_FILE));
            transformer.transform(source, result);

            System.out.println("Data catatan harian berhasil disimpan.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<CatatanHarianData> bacaDariXML() {
        ArrayList<CatatanHarianData> list = new ArrayList<>();
        File file = new File(XML_FILE);
        if (!file.exists()) return list;

        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("catatan");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element e = (Element) nodeList.item(i);
                String tanggal = e.getElementsByTagName("tanggal").item(0).getTextContent();
                String pagi = e.getElementsByTagName("makanPagi").item(0).getTextContent();
                String siang = e.getElementsByTagName("makanSiang").item(0).getTextContent();
                String malam = e.getElementsByTagName("makanMalam").item(0).getTextContent();
                String username = e.getElementsByTagName("username").item(0).getTextContent();
                list.add(new CatatanHarianData(username ,pagi, siang, malam,tanggal));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private static void tambahElemen(Document doc, Element parent, String nama, String isi) {
        Element e = doc.createElement(nama);
        e.appendChild(doc.createTextNode(isi));
        parent.appendChild(e);
    }
}
