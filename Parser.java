import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Parser {
   
   public static void main(String[] args) throws Exception{

        for (int i = 1; i<19;i++){
            NodeList nList = extractRequirements(String(i)+".xml");
            // now that the requirements are stored in nList, all we need to do is print them out cohesively 
            createCSV(nList,outputName);
        }
        
   }

   public static NodeList extractRequirements(String filename) throws Exception{

    File inputFile = new File(filename);

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

    Document doc = dBuilder.parse(inputFile);
    doc.getDocumentElement().normalize();

    // This immediately gets all the requirements that have a <req> element annotation
    NodeList nList = doc.getElementsByTagName("req");

    return nList;
   }

   public static void createCSV(NodeList nList, String outputName) throws Exception{

    File outputCSV = new File(outputName);
    PrintWriter pw = new PrintWriter(new FileWriter(outputCSV));

    for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);
    
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;

            String id = eElement.getAttribute("id").toString();
            String requirement = eElement.getElementsByTagName("text_body").item(0).getTextContent().toString();
            requirement = requirement.replaceAll("\n", "");

            pw.print(String.format("%s, %s%n", id, requirement));
        }
    }

    pw.close();
   }
}