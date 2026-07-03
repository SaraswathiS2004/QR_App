import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXml {
    public static void main(String [] args){
        try {
            File file = new File("src/main/xml/templates.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document =  builder.parse(file);
            document.getDocumentElement().normalize();

            System.out.println("Root Element: "+ document.getDocumentElement().getNodeName());
            System.out.println("Name: "+ document.getElementsByTagName("name").item(0).getTextContent());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
