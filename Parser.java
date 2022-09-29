import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Parser {
   
   public static void main(String[] args) throws Exception{

        File inputFile = new File("test.xml");

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

                // get ID of the requirement in the document, this is an attribute of the element (<req id="1">)
                // the id isn't necessarily useful if the IDs are repeated for different sections
                System.out.println("Requirement ID: " 
                    + eElement.getAttribute("id"));
                    
                // gets the text of the requirement, we assume it's in a <text_body> element 
                // TODO(sashayeu): make sure the requirement stays on one line, currently if there are two <text_body> elements the requirement shows up in 2 lines 
                System.out.println("Requirement Description: " 
                    + eElement
                    .getElementsByTagName("text_body")
                    .item(0)
                    .getTextContent());
            }
        }

   }
}