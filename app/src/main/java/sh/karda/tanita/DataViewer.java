package sh.karda.tanita;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DataViewer extends AppCompatActivity {
    private ArrayList<WeightData> weightDataArrayList;
    FileHelper fileHelper;
    XmlReader xmlReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_viewer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //weightDataArrayList = (ArrayList<WeightData>)getIntent().getExtras().getSerializable("WeightData");
        fileHelper = new FileHelper();
        weightDataArrayList = new ArrayList<>();
        xmlReader = new XmlReader();
        String xmlFileToRead = fileHelper.getFile("Debug").toString().replace(".txt",".xml");
        weightDataArrayList = xmlReader.getWeightData(xmlFileToRead);

        ListAdapter listAdapter = new CustomAdapter(this,weightDataArrayList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(listAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
