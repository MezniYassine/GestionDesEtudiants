package com.tp.gestiondesetudiants;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Student> studentList;
    private DBHelper dbHelper ;



    public StudentAdapter(Context context, List<Student> studentList,DBHelper dbHelper) {
        this.context = context;
        this.studentList = studentList;
        this.dbHelper = dbHelper ;

    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.name.setText(student.getName());
        holder.surname.setText(student.getSurname());
        holder.mark.setText(String.valueOf(student.getMark()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("student_id", student.getId());
            context.startActivity(intent);
        });
        holder.deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Would you like to delete this student ?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        dbHelper.deleteStudent(student.getId());
                        studentList.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void updateData(List<Student> newStudents) {
        studentList.clear();
        studentList.addAll(newStudents);
        notifyDataSetChanged();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView name, surname, mark;
        ImageView deleteButton;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.NameStudent);
            surname = itemView.findViewById(R.id.SurnameStudent);
            mark = itemView.findViewById(R.id.MarkStudent);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
