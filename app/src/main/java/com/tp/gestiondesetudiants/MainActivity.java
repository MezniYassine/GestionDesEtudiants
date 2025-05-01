package com.tp.gestiondesetudiants;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialisation de la base de données
        dbHelper = new DBHelper(this);

        // Bouton d'ajout
        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivity(intent);
        });

        // Initialisation du RecyclerView
        recyclerView = findViewById(R.id.studentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Charger les données
        List<Student> students = dbHelper.getAllStudents();
        adapter = new StudentAdapter(this, students);
        recyclerView.setAdapter(adapter);
    }

    // Optionnel : Rafraîchir la liste après ajout
    @Override
    protected void onResume() {
        super.onResume();
        List<Student> students = dbHelper.getAllStudents();
        adapter.updateData(students);
    }
}