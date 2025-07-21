package PesanHasil;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PendaftaranManager {
    private static final List<Pendaftaran> listPendaftaran = new ArrayList<>();
    private static final String FILE_PATH = "pendaftaran.xml";

    public static void tambahData(Pendaftaran data) {
        listPendaftaran.add(data); // Tambahkan data baru
        saveToXML();
    }

    public static List<Pendaftaran> getAllData() {
        return listPendaftaran;
    }

    public static void loadFromXML() {
    listPendaftaran.clear(); // Hapus data lama di memori agar tidak dobel

    try {
        File file = new File("pendaftaran.xml");
        if (!file.exists()) return; // Jika file belum ada, skip

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("pendaftaran");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String nama = elem.getElementsByTagName("namaLengkap").item(0).getTextContent();
                String ttl = elem.getElementsByTagName("ttl").item(0).getTextContent();
                String alamat = elem.getElementsByTagName("alamat").item(0).getTextContent();
                String telepon = elem.getElementsByTagName("nomorTelepon").item(0).getTextContent();
                String gender = elem.getElementsByTagName("gender").item(0).getTextContent();

                Pendaftaran p = new Pendaftaran(nama, ttl, alamat, telepon, gender);
                listPendaftaran.add(p);
            }
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getTagValue(String tag, Element element) {
        NodeList list = element.getElementsByTagName(tag);
        if (list.getLength() > 0) {
            Node node = list.item(0);
            return node.getTextContent();
        }
        return "";
    }
    public static void saveToXML() {
    try {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // Root element
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("pendaftaranList");
        doc.appendChild(rootElement);

        // Tambahkan setiap data pendaftaran
        for (Pendaftaran p : listPendaftaran) {
            Element pendaftaranEl = doc.createElement("pendaftaran");

            Element nama = doc.createElement("namaLengkap");
            nama.appendChild(doc.createTextNode(p.getNamaLengkap()));
            pendaftaranEl.appendChild(nama);

            Element ttl = doc.createElement("ttl");
            ttl.appendChild(doc.createTextNode(p.getTtl()));
            pendaftaranEl.appendChild(ttl);

            Element alamat = doc.createElement("alamat");
            alamat.appendChild(doc.createTextNode(p.getAlamat()));
            pendaftaranEl.appendChild(alamat);

            Element telepon = doc.createElement("nomorTelepon");
            telepon.appendChild(doc.createTextNode(p.getNomorTelepon()));
            pendaftaranEl.appendChild(telepon);

            Element gender = doc.createElement("gender");
            gender.appendChild(doc.createTextNode(p.getGender()));
            pendaftaranEl.appendChild(gender);

            rootElement.appendChild(pendaftaranEl);
        }

        // Tulis ke file XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("pendaftaran.xml")); // ⬅️ Disimpan di root project

        transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
