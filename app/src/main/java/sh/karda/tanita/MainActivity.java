package sh.karda.tanita;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText bmi;
    WeightData weightData;
    FileHelper fileHelper;
    ArrayList<WeightData> weightDataArrayList;
    Calendar myCalendar;
    EditText dateText;
    Date defaultDate;
    DatePickerDialog.OnDateSetListener date;
    XmlReader xmlReader;


    private String getBmi(){
        if (bmi != null)
            if (bmi.getText() != null)
                return bmi.getText().toString();
        return "";
    }
    EditText weight;
    private String getWeight(){
        String r = "";
        if (weight != null)
            if (weight.getText() != null) {
                r = weight.getText().toString();
            }
        return r;
    }
    EditText fat;
    private String getFat(){
        if (fat != null)
            if (fat.getText() != null)
                return fat.getText().toString();
        return "";
    }
    EditText water;
    private String getWater(){
        if (water != null)
            if (water.getText() != null)
                return water.getText().toString();
        return "";
    }
    EditText muscle;
    private String getMuscle(){
        if (muscle != null)
            if (muscle.getText() != null)
                return muscle.getText().toString();
        return "";
    }
    EditText bone;
    private String getBone(){
        if (bone != null)
            if (bone.getText() != null)
                return bone.getText().toString();
        return "";
    }
    EditText rest;
    private String getRest(){
        if (rest != null)
            if (rest.getText() != null)
                return rest.getText().toString();
        return "";
    }
    EditText visceral;
    private String getVisceral(){
        if (visceral != null)
            if (visceral.getText() != null)
                return visceral.getText().toString();
        return "";
    }
    EditText age;
    private String getAge(){
        if (age != null)
            if (age.getText() != null)
                return age.getText().toString();
        return "";
    }
    EditText rating;
    private String getRating(){
        if (rating != null)
            if (rating.getText() != null)
                return rating.getText().toString();
        return "";
    }
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        fileHelper = new FileHelper();

        weightData = new WeightData();
        weightData.setDate(new Date());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String xmlFileToRead = fileHelper.getFile("Debug").toString().replace(".txt",".xml");

        xmlReader = new XmlReader();
        weightDataArrayList = xmlReader.getWeightData(xmlFileToRead);

        fab = findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weightData == null || !weightData.isComplete()) return;
                if (BuildConfig.DEBUG){
                    fileHelper.putData(weightData.getWeightString(), "Debug.txt");
                    weightDataArrayList.add(weightData);
                    XmlWriter xmlWriter = new XmlWriter();
                    String xmlFile = fileHelper.getFile("Debug").toString().replace(".txt",".xml");
                    try {
                        xmlWriter.writeXml(weightDataArrayList, xmlFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    fileHelper.putData(weightData.getWeightString(), "Anders.txt");
                }
                Toast.makeText(MainActivity.this, "Saved to Phone", Toast.LENGTH_SHORT).show();
                fab.setVisibility(View.GONE);
            }
        });

        //Date
        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        dateText = findViewById(R.id.editDate);
        defaultDate = new Date();
        Format formatter = new SimpleDateFormat("MM-dd", Locale.getDefault());
        String s = formatter.format(defaultDate);
        dateText.setText(s);
        weightData.setDate(defaultDate);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, MainActivity.this, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        // End Date


        bmi = findViewById(R.id.editBmi);
        bmi.setFocusable(false);
        bmi.setClickable(false);

        weight = findViewById(R.id.editWeight);
        weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if (Double.parseDouble(getWeight()) > 70d && Double.parseDouble(getWeight()) < 90d ){
                        double bmiValue = Double.parseDouble(getWeight())/Math.pow(1.8,2);
                        String result = String.format("%.1f", bmiValue).replace(",",".");
                        bmi.setText(result);
                        weightData.setBmi(Double.parseDouble(result));
                        weightData.setWeight(Double.parseDouble(getWeight()));
                    }
                }
            }
        });

        fat = findViewById(R.id.editFat);
        fat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                weightData.setFat(Double.parseDouble(getFat()));
                checkAllFields();
            }
        });

        water = findViewById(R.id.editWater);
        water.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                weightData.setWater(Double.parseDouble(getWater()));
                checkAllFields();
            }
        });

        muscle = findViewById(R.id.muscular);
        muscle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                weightData.setMuscle(Double.parseDouble(getMuscle()));
                checkAllFields();
            }
        });

        bone = findViewById(R.id.editBone);
        bone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                weightData.setBone(Double.parseDouble(getBone()));
                checkAllFields();
            }
        });

        visceral = findViewById(R.id.editVisceral);
        visceral.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                weightData.setVisceral(Integer.parseInt(getVisceral()));
                checkAllFields();
            }
        });

        rest = findViewById(R.id.editBmr);
        rest.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                weightData.setRest(Integer.parseInt(getRest()));
                checkAllFields();
            }
        });

        age = findViewById(R.id.editAge);
        age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                weightData.setAge(Integer.parseInt(getAge()));
                checkAllFields();
            }
        });

        rating = findViewById(R.id.editRa);
        rating.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                weightData.setRating(Integer.parseInt(getRating()));
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        dateText.setText(sdf.format(myCalendar.getTime()));
    }

    private boolean checkAllFields() {
        if (weightData.isComplete()){
            fab.setVisibility(View.VISIBLE);
            return true;
        }
        else {
            fab.setVisibility(View.GONE);
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if (weightDataArrayList == null){
                String xmlFileToRead = fileHelper.getFile("Debug").toString().replace(".txt",".xml");
                weightDataArrayList = xmlReader.getWeightData(xmlFileToRead);
            }
            Intent showMenu = new Intent(MainActivity.this, DataViewer.class);
            //showMenu.putExtra("DATA", weightDataArrayList);
            startActivity(showMenu);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateString = String.format("%02d", month+1) + "-" + String.format("%02d", dayOfMonth);
        dateText.setText(dateString);
        weightData.setDate(new GregorianCalendar(year, month, dayOfMonth).getTime());
    }
}
