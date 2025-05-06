package com.tp.gestiondesetudiants;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText nameInput = findViewById(R.id.addStudentName);
        EditText surnameInput = findViewById(R.id.addStudentSurname);
        EditText markInput = findViewById(R.id.addStudentMark);
        Button submitBtn = findViewById(R.id.Submitbutton);

        DBHelper dbHelper = new DBHelper(this);

        submitBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String surname = surnameInput.getText().toString();
            String markStr = markInput.getText().toString().trim();
            if (name.isEmpty() || surname.isEmpty() || markStr.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                double mark = Double.parseDouble(markStr);
                if (mark > 20 || mark < 0) {
                    Toast.makeText(this, "Value must be between 0 and 20 ", Toast.LENGTH_SHORT).show();
                    return;
                }
                Student student = new Student(name, surname, mark);
                dbHelper.addStudent(student);
                finish();
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
            }catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid mark value", Toast.LENGTH_SHORT).show();
            }
        });
    }
}