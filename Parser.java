import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Parser {
   
   public static void main(String[] args) throws Exception{

        File inputFile = new File("smallTest.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        // This immediately gets all the requirements that have a <req> element annotation
        NodeList nList = doc.getElementsByTagName("req");
        // now that the requirements are stored in nList, all we need to do is print them out cohesively 
        System.out.println("----------------------------");
        
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
        
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("Requirement ID: " 
                    + eElement.getAttribute("id"));
                System.out.println("Requirement Description: " 
                    + eElement
                    .getElementsByTagName("text_body")
                    .item(0)
                    .getTextContent());
            }
        }

   }
}