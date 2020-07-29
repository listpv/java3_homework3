package ru.geekbrains.task4;

import java.io.Serializable;

public class Student implements Serializable
{
    int id;
    String name;
    Book book;
    int age;

    public Student(int id, String name, int age)
    {
        this.id = id;
        this.name = name;
        this.age  = age;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void info()
    {
//        System.out.println("id = " + id + ", name is " + name + ", book is " + book.getName());
        System.out.println("id = " + id + ", name is " + name + ", age is " + age + ", book is " + book.getName());
    }
}
