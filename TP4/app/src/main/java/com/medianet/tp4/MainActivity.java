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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.medianet.tp4.Model.StudentModel;

import java.util.ArrayList;
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

        listeEdtudients.add(new StudentModel("ali"));
        listeEdtudients.add(new StudentModel("Mahdi"));
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
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loadListNames();
            }
        });
    }

    private void loadListNames() {
        Log.e("onTextChanged -> loadListNames",editName.getText().toString());

        // i wanna set default adapter (like just a list of names); from listeEdtudients

        String query = editName.getText().toString().toLowerCase();

        ArrayList<String> filteredNames = new ArrayList<>();
        for (StudentModel student : listeEdtudients) {
            if (student.getName().toLowerCase().contains(query)) {
                filteredNames.add(student.getName());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                filteredNames
        );

        listNames.setAdapter(adapter);

        if (!filteredNames.isEmpty()) {
            listNames.setVisibility(View.VISIBLE);
            listNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    editName.setText(filteredNames.get(i));
                    listNames.setVisibility(View.GONE);
                }
            });
        }
    }

    private void setData() {

    }

}