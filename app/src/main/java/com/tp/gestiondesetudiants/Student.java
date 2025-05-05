package com.tp.gestiondesetudiants;

public class Student {
    private String name;
    private String surname;
    private double mark;
    private int id;

    public Student(String name, String surname, double mark) {
        this.name = name;
        this.surname = surname;
        this.mark = mark;
        this.id = -1;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public double getMark() { return mark; }
    public void setMark(double mark) { this.mark = mark; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}