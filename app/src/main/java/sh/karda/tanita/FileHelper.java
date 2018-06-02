package sh.karda.tanita;

import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static android.content.Context.MODE_APPEND;

public class FileHelper {


    public void putData(String line, String name) {
        try {
            File externalFile = getFile(name);
            externalFile.getParentFile().mkdirs();

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(externalFile, false), "UTF-8"));
            writer.write(line);
            writer.close();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public String getDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

    public File getFile(String fileName){
        File state = Environment.getExternalStorageDirectory();
        return new File(state.toString() + "/Privat/Tanita/"+ fileName.replace(".txt","") + "/" + getDate() +".txt");
    }

    public WeightData getLastResults(String name) throws IOException {
        String lastFile = getLastFile(name).getAbsolutePath();
        String line = readFile(lastFile);
        WeightData weightData = new WeightData();
        weightData.setData(line);
        return  weightData;
    }

    private String readFile(String pathname) throws IOException {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int)file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }

    public File getLastFile(String name){
        File state = Environment.getExternalStorageDirectory();
        File folder = new File(state.toString() + "/Privat/Tanita/"+ name.replace(".txt","") + "/");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> list = new ArrayList<String>();


        for (File s:listOfFiles) {
            list.add(s.getName());
        }
        Collections.sort(list,String.CASE_INSENSITIVE_ORDER);
        int length = list.size();
        String last = list.get(length - 1);
        File lastFile = new File(state.toString() + "/Privat/Tanita/"+ name.replace(".txt","") + "/" + last);
        return  lastFile;
    }



}
