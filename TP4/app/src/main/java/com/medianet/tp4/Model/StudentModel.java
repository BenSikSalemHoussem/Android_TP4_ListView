package com.medianet.tp4.Model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentModel {
    String Name;
    Map<String,Double> data;

    //String matiere1, matiere2, matiere3, matiere4, matiere5, matiere6;
    //Double note1, note2, note3, note4, note5, note6;


    public StudentModel(String name, Map<String, Double> data) {
        Name = name;
        this.data = data;
    }

    public StudentModel(String name) {
        this.Name = name;
        this.data = new LinkedHashMap<>();
        this.data.put("Big Data", 0.0);
        this.data.put("Android", 0.0);
        this.data.put("Angular", 0.0);
        this.data.put("UX", 0.0);
        this.data.put("DataBase", 0.0);
        this.data.put("C++", 0.0);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Map<String, Double> getData() {
        return data;
    }

    public void setData(Map<String, Double> data) {
        this.data = data;
    }
}
