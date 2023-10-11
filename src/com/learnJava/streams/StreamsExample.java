package com.learnJava.streams;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsExample {

    public static void main(String[] args) {

        Map<String, List<String>> studentMap = StudentDataBase.getAllStudents().stream()
                .collect(Collectors.toMap(Student::getName, Student::getActivities));



    }
}
