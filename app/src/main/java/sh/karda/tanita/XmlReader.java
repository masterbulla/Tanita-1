package sh.karda.tanita;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlReader {
    public ArrayList<WeightData> getWeightData(String file){
        try {
            InputStream is = new FileInputStream(file);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("WeightData");

            ArrayList<WeightData> weightDataArrayList = new ArrayList<>();

            for (int i=0; i<nList.getLength(); i++) {


                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    WeightData weightData = new WeightData();
                    weightData.setWeight(Double.parseDouble(getValue("Weight",element2)));
                    weightData.setBmi(Double.parseDouble(getValue("Bmi",element2)));
                    weightData.setFat(Double.parseDouble(getValue("Fat",element2)));
                    weightData.setWater(Double.parseDouble(getValue("Water",element2)));
                    weightData.setMuscle(Double.parseDouble(getValue("Muscle",element2)));
                    weightData.setBone(Double.parseDouble(getValue("Bone",element2)));
                    weightData.setAge(Integer.parseInt(getValue("Age",element2)));
                    weightData.setRating(Integer.parseInt(getValue("Rating",element2)));
                    weightData.setRest(Integer.parseInt(getValue("Rest",element2)));
                    weightData.setVisceral(Integer.parseInt(getValue("Visceral",element2)));
                    weightDataArrayList.add(weightData);
                }
            }
            return weightDataArrayList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
