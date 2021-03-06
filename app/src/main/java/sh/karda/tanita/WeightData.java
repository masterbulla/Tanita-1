package sh.karda.tanita;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeightData {
    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public double getBmi() {
        return Bmi;
    }

    public void setBmi(double bmi) {
        Bmi = bmi;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getWater() {
        return Water;
    }

    public void setWater(double water) {
        Water = water;
    }

    public double getMuscle() {
        return Muscle;
    }

    public void setMuscle(double muscle) {
        Muscle = muscle;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBone() {
        return bone;
    }

    public void setBone(double bone) {
        this.bone = bone;
    }

    public int getVisceral() {
        return visceral;
    }

    public void setVisceral(int visceral) {
        this.visceral = visceral;
    }

    public String getWeightString(){
        //82.4;25,4;17.2;57.5;64.9;3.4;1965;7;29;5
        String w = String.valueOf(getWeight());
        String bmi = String.valueOf(getBmi());
        String fat = String.valueOf(getFat());
        String water = String.valueOf(getWater());
        String muscle = String.valueOf(getMuscle());
        String rating = String.valueOf(getRating());
        String rest = String.valueOf(getRest());
        String age = String.valueOf(getAge());
        String bone = String.valueOf(getBone());
        String viscelar = String.valueOf(getVisceral());
        return w + ";" +bmi+ ";" +fat+ ";" +water+ ";" +muscle+ ";" +rating+ ";" +rest+ ";" +age+ ";" +bone+ ";" +viscelar;
    }

    public void setData(String csvString){
        try{
            String[] split = csvString.split(";");
            if (split.length != 10) return;
            setWeight(Double.parseDouble(split[0]));
            setBmi(Double.parseDouble(split[1].replace(",",".")));
            setFat(Double.parseDouble(split[2]));
            setWater(Double.parseDouble(split[3]));
            setMuscle(Double.parseDouble(split[4]));
            setBone(Double.parseDouble(split[5]));
            setRest(Integer.parseInt(split[6]));
            setVisceral(Integer.parseInt(split[7]));
            setAge(Integer.parseInt(split[8]));
            int r = Integer.parseInt(split[9].replace("\n",""));
            setRating(r);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private double Weight;
    private double Bmi;
    private double fat;
    private double Water;
    private double Muscle;
    private int rating;
    private int rest;
    private int age;
    private double bone;
    private int visceral;

    public String getDate() {
        Date d = new Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String s = formatter.format(d);
        if (date == null) return s;
        s = formatter.format(date);
        return s;
    }

    public String getDateString(){
        if (this.date == null) this.date = new Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return formatter.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Date date;


    public boolean isComplete() {
        Double a = getWeight();
        Double b = getBmi();
        double c = getFat();
        double d = getMuscle();
        int e = getRating();
        int f = getRest();
        int g = getAge();
        double h = getBone();
        int i = getVisceral();
        return (getWeight() > 0) && getBmi() > 0 && getFat() > 0 && getWater() > 0 && getMuscle() > 0 && getRating() > 0 && getRest() > 0 && getAge() > 0 &&
                getBone() > 0 && getVisceral() > 0;
    }

    public void setDateFromString(String dateString) {
        //2018-06-03 11:40:14
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            this.date = new Date();
        }
        this.date = date;
    }
}
