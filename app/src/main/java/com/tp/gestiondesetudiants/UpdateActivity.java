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

public class UpdateActivity extends AppCompatActivity {
    private EditText editName, editSurname, editMark;
    private int studentId;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DBHelper(this);
        studentId = getIntent().getIntExtra("student_id", -1);

        editName = findViewById(R.id.editStudentName);
        editSurname = findViewById(R.id.editStudentSurname);
        editMark = findViewById(R.id.editStudentMark);
        Button updateButton = findViewById(R.id.updatebutton);

        if (studentId == -1) {
            Toast.makeText(this, "Error: Invalid Student ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Student student = dbHelper.getStudentById(studentId);
        if (student != null) {
            editName.setText(student.getName());
            editSurname.setText(student.getSurname());
            editMark.setText(String.valueOf(student.getMark()));
        }

        updateButton.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String surname = editSurname.getText().toString().trim();
            String markStr = editMark.getText().toString().trim();

            if (name.isEmpty() || surname.isEmpty() || markStr.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double mark = Double.parseDouble(markStr);
                dbHelper.updateStudent(studentId, name, surname, mark);
                Toast.makeText(this, "Student Updated", Toast.LENGTH_SHORT).show();
                finish(); // Go back to MainActivity
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid mark value", Toast.LENGTH_SHORT).show();
            }
        });
    }
}