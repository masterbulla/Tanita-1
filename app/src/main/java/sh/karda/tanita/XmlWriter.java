package sh.karda.tanita;

import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class XmlWriter {

    public void writeXml(ArrayList<WeightData> weightDatas, String xmlFile) throws IOException {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer2 = new StringWriter();
        serializer.setOutput(writer2);
        serializer.startDocument("UTF-8", true);
        String topTag = "TanitaData";
        serializer.startTag("", topTag);
        for (WeightData wd:weightDatas){
            String weightDataTag = "WeightData";
            serializer.startTag("", weightDataTag);

            String dateTag = "Date";
            serializer.startTag("", dateTag);
            serializer.text(String.valueOf(wd.getDate()));
            serializer.endTag("", dateTag);

            String weightTag = "Weight";
            serializer.startTag("", weightTag);
            serializer.text(String.valueOf(wd.getWeight()));
            serializer.endTag("", weightTag);

            String bmiTag = "Bmi";
            serializer.startTag("", bmiTag);
            serializer.text(String.valueOf(wd.getBmi()));
            serializer.endTag("", bmiTag);

            String fatTag = "Fat";
            serializer.startTag("", fatTag);
            serializer.text(String.valueOf(wd.getFat()));
            serializer.endTag("", fatTag);

            String waterTag = "Water";
            serializer.startTag("", waterTag);
            serializer.text(String.valueOf(wd.getWater()));
            serializer.endTag("", waterTag);

            String muscleTag = "Muscle";
            serializer.startTag("", muscleTag);
            serializer.text(String.valueOf(wd.getMuscle()));
            serializer.endTag("", muscleTag);

            String ratingTag = "Rating";
            serializer.startTag("", ratingTag);
            serializer.text(String.valueOf(wd.getRating()));
            serializer.endTag("", ratingTag);

            String restTag = "Rest";
            serializer.startTag("", restTag);
            serializer.text(String.valueOf(wd.getRest()));
            serializer.endTag("", restTag);

            String ageTag = "Age";
            serializer.startTag("", ageTag);
            serializer.text(String.valueOf(wd.getAge()));
            serializer.endTag("", ageTag);

            String boneTag = "Bone";
            serializer.startTag("", boneTag);
            serializer.text(String.valueOf(wd.getBone()));
            serializer.endTag("", boneTag);

            String visceralTag = "Visceral";
            serializer.startTag("", visceralTag);
            serializer.text(String.valueOf(wd.getVisceral()));
            serializer.endTag("", visceralTag);

            serializer.endTag("", weightDataTag);
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
