package com.medianet.tp4;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.medianet.tp4.Model.StudentModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    ListView listNames, listNotes;
    ImageButton btnAddEtudiant;
    ArrayList<StudentModel> listeEdtudients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.edit_name);
        listNames = findViewById(R.id.list_view_names);
        listNotes = findViewById(R.id.list_view_notes);
        btnAddEtudiant = findViewById(R.id.btn_plus);

        Map<String, Double> aliData = new LinkedHashMap<>();
        aliData.put("Big Data", 15.0);
        aliData.put("Android", 18.0);
        aliData.put("Angular", 12.0);
        aliData.put("UX", 14.0);
        aliData.put("DataBase", 17.0);
        aliData.put("C++", 11.0);
        Map<String, Double> MahdiData = new LinkedHashMap<>();
        MahdiData.put("Big Data", 10.0);
        MahdiData.put("Android", 8.0);
        MahdiData.put("Angular", 12.0);
        MahdiData.put("UX", 14.3);
        MahdiData.put("DataBase", 1.0);
        MahdiData.put("C++", 11.5);
        listeEdtudients.add(new StudentModel("ali", aliData));
        listeEdtudients.add(new StudentModel("Mahdi", MahdiData));
        listeEdtudients.add(new StudentModel("Mohamed Amine"));

        addButtonListner();
        addEditTextListner();
    }

    private void addButtonListner() {
        btnAddEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void addEditTextListner() {

        editName.addTextChangedListener(new TextWatcher() {
            @Override public void afterTextChanged(Editable editable) {}
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loadListNames();
            }
        });

        editName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    loadListNames();
                }
            }
        });
    }

    private void loadListNames() {

        String query = editName.getText().toString().toLowerCase();

        ArrayList<StudentModel> filteredStudents = new ArrayList<>();
        for (StudentModel student : listeEdtudients) {
            if (student.getName().toLowerCase().contains(query)) {
                filteredStudents.add(student);
            }
        }

        ArrayAdapter<StudentModel> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                filteredStudents
        );

        listNames.setAdapter(adapter);

        if (!filteredStudents.isEmpty()) {
            listNames.setVisibility(View.VISIBLE);
            listNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    editName.setText(filteredStudents.get(i).getName());
                    listNames.setVisibility(View.GONE);
                    loadListNotes(filteredStudents.get(i));
                }
            });
        }
    }

    /// Liste Notes Adapter
    private void loadListNotes(StudentModel student) {
        Log.e("loadListNotes", "loadListNotes");
        ListNotesAdapter adapter = new ListNotesAdapter(this, student);
        listNotes.setAdapter(adapter);
        listNotes.setVisibility(View.VISIBLE);

        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String matiere = new ArrayList<>(student.getData().keySet()).get(i);
                Double note = student.getNoteByMatiere(matiere);
                Toast.makeText(getApplicationContext(), matiere + ": " + (note>=10 ? "Succeed" : "Fail"), Toast.LENGTH_SHORT).show();
            }
        });
    }

}