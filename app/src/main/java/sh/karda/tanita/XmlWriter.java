package sh.karda.tanita;

import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class XmlWriter {

    private String topTag = "TanitaData";
    private String weightDataTag = "WeightData";
    private String weightTag = "Weight";
    private String bmiTag = "Bmi";
    private String fatTag = "Fat";
    private String waterTag = "Water";
    private String muscleTag = "Muscle";
    private String ratingTag = "Rating";
    private String restTag = "Rest";
    private String ageTag = "Age";
    private String boneTag = "Bone";
    private String visceralTag = "Visceral";

    public void writeXml(ArrayList<WeightData> weightDatas, String xmlFile) throws IOException {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer2 = new StringWriter();
        serializer.setOutput(writer2);
        serializer.startDocument("UTF-8", true);
        serializer.startTag("", topTag);
        for (WeightData wd:weightDatas){
            serializer.startTag("", weightDataTag);
            serializer.attribute(weightDataTag, "Date", wd.getDate());

            serializer.startTag("", weightTag);
            serializer.text(String.valueOf(wd.getWeight()));
            serializer.endTag("", weightTag);

            serializer.startTag("", bmiTag);
            serializer.text(String.valueOf(wd.getBmi()));
            serializer.endTag("", bmiTag);

            serializer.startTag("", fatTag);
            serializer.text(String.valueOf(wd.getFat()));
            serializer.endTag("", fatTag);

            serializer.startTag("", waterTag);
            serializer.text(String.valueOf(wd.getWater()));
            serializer.endTag("", waterTag);

            serializer.startTag("", muscleTag);
            serializer.text(String.valueOf(wd.getMuscle()));
            serializer.endTag("", muscleTag);

            serializer.startTag("", ratingTag);
            serializer.text(String.valueOf(wd.getRating()));
            serializer.endTag("", ratingTag);

            serializer.startTag("", restTag);
            serializer.text(String.valueOf(wd.getRest()));
            serializer.endTag("", restTag);

            serializer.startTag("", ageTag);
            serializer.text(String.valueOf(wd.getAge()));
            serializer.endTag("", ageTag);

            serializer.startTag("", boneTag);
            serializer.text(String.valueOf(wd.getBone()));
            serializer.endTag("", boneTag);

            serializer.startTag("", visceralTag);
            serializer.text(String.valueOf(wd.getVisceral()));
            serializer.endTag("", visceralTag);

            serializer.endTag("",weightDataTag);
        }
        serializer.endTag("", topTag);
        serializer.endDocument();
        String test = writer2.toString();
        writeToFile(xmlFile, test);
    }

    private void writeToFile(String fileName,String str) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName, false);
        fos.write(str.getBytes(),0,str.length());
        fos.close();
    }
}
