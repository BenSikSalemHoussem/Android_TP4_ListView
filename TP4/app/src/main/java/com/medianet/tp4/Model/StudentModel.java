package com.medianet.tp4.Model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentModel {
    private static int counter = 0;
    int id;
    String Name;
    Map<String,Double> data;

    public StudentModel(String name, Map<String, Double> data) {
        this.id = ++counter;
        Name = name;
        this.data = data;
    }

    public StudentModel(String name) {
        this.id = ++counter;
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

    public Double getNoteByMatiere(String matiere) {
        return data.get(matiere);
    }


    @Override
    public String toString() {
        return Name;
    }
}
