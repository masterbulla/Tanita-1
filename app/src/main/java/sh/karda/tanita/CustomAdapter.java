package sh.karda.tanita;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<WeightData>{
    public CustomAdapter(@NonNull Context context, ArrayList<WeightData> weightData)  {
        super(context, R.layout.list_layout, weightData);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_layout, parent, false);
        WeightData wd = getItem(position);

        CheckBox checkBox = customView.findViewById(R.id.listCheckBox);
        checkBox.setText(wd.getDateString());

        TextView weight = customView.findViewById(R.id.listWeight);
        String weightString = String.valueOf(wd.getWeight());
        weight.setText(weightString);

        TextView age = customView.findViewById(R.id.listAge);
        age.setText(String.valueOf(wd.getAge()));

        TextView fat = customView.findViewById(R.id.listFat);
        fat.setText(String.valueOf(wd.getFat()));

        TextView muscle = customView.findViewById(R.id.listMuscle);
        muscle.setText(String.valueOf(wd.getMuscle()));
        return customView;

    }
}