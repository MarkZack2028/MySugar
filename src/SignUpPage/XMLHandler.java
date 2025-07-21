package SignUpPage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import java.io.File;

public class XMLHandler {

    public static void simpanData(User[] users, int userCount, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Users");
            doc.appendChild(rootElement);

            for (int i = 0; i < userCount; i++) {
                User user = users[i];
                Element userElement = doc.createElement("User");

                userElement.appendChild(createElement(doc, "Username", user.getUsername()));
                userElement.appendChild(createElement(doc, "Password", user.getPassword()));
                userElement.appendChild(createElement(doc, "FirstName", user.getFirstName()));
                userElement.appendChild(createElement(doc, "LastName", user.getLastName()));
                userElement.appendChild(createElement(doc, "Gender", user.getGender()));
                userElement.appendChild(createElement(doc, "PhoneNumber", user.getPhoneNumber()));

                rootElement.appendChild(userElement);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File(filePath)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User[] bacaData(String filePath, int[] jumlahUser) {
        User[] users = new User[100];
        int count = 0;

        try {
            File xmlFile = new File(filePath);
            if (!xmlFile.exists()) {
                jumlahUser[0] = 0;
                return users;
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("User");

            for (int i = 0; i < list.getLength(); i++) {
                Element e = (Element) list.item(i);

                String username = e.getElementsByTagName("Username").item(0).getTextContent();
                String password = e.getElementsByTagName("Password").item(0).getTextContent();
                String firstName = e.getElementsByTagName("FirstName").item(0).getTextContent();
                String lastName = e.getElementsByTagName("LastName").item(0).getTextContent();
                String gender = e.getElementsByTagName("Gender").item(0).getTextContent();
                String phone = e.getElementsByTagName("PhoneNumber").item(0).getTextContent();

                users[count++] = new User(username, password, firstName, lastName, gender, phone);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        jumlahUser[0] = count;
        return users;
    }

    private static Element createElement(Document doc, String tag, String value) {
        Element elem = doc.createElement(tag);
        elem.appendChild(doc.createTextNode(value));
        return elem;
    }
}
