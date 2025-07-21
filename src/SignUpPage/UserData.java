package SignUpPage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class UserData {
    private static final String XML_FILE_PATH = "data_users.xml"; // Simpan di root folder proyek

    // Simpan data ke XML
    public static void saveUsersToXML(ArrayList<User> users) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("users");
            doc.appendChild(rootElement);

            for (User user : users) {
                Element userElement = doc.createElement("user");

                addElement(doc, userElement, "username", user.getUsername());
                addElement(doc, userElement, "password", user.getPassword());
                addElement(doc, userElement, "firstName", user.getFirstName());
                addElement(doc, userElement, "lastName", user.getLastName());
                addElement(doc, userElement, "gender", user.getGender());
                addElement(doc, userElement, "phoneNumber", user.getPhoneNumber());

                rootElement.appendChild(userElement);
            }

            // Tulis ke file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(XML_FILE_PATH));
            transformer.transform(source, result);
            //debugging
            System.out.println("MENYIMPAN DATA KE XML");

            System.out.println("Data users berhasil disimpan ke XML.");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }

    public static User validateLogin(String username, String password) {
        ArrayList<User> users = loadUsersFromXML();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // login berhasil
            }
        }
        return null; // login gagal
    }

    // Baca data dari XML
    public static ArrayList<User> loadUsersFromXML() {
        ArrayList<User> users = new ArrayList<>();

        File xmlFile = new File(XML_FILE_PATH);
        if (!xmlFile.exists()) {
            System.out.println("File XML belum ada. Mengembalikan list kosong.");
            return users;
        }

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList userList = doc.getElementsByTagName("user");

            for (int i = 0; i < userList.getLength(); i++) {
                Node node = userList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;

                    String username = e.getElementsByTagName("username").item(0).getTextContent();
                    String password = e.getElementsByTagName("password").item(0).getTextContent();
                    String firstName = e.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = e.getElementsByTagName("lastName").item(0).getTextContent();
                    String gender = e.getElementsByTagName("gender").item(0).getTextContent();
                    String phoneNumber = e.getElementsByTagName("phoneNumber").item(0).getTextContent();

                    User user = new User(username, password, firstName, lastName, gender, phoneNumber);
                    users.add(user);
                }
            }

            System.out.println("Berhasil load data dari XML. Jumlah user: " + users.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
    public static ArrayList<User> getAllUsers() {
        return loadUsersFromXML(); // method ini sudah ada dan mengembalikan semua data user dari XML
    }

    // Helper method untuk membuat elemen
    private static void addElement(Document doc, Element parent, String tagName, String text) {
        Element elem = doc.createElement(tagName);
        elem.appendChild(doc.createTextNode(text));
        parent.appendChild(elem);
    }
}
