package ru.netology.javacore;

import java.util.*;


public class Todos {
    private final List<String> todos = new ArrayList<>();

    public void addTask(String task) {
        todos.add(task);
    }

    public void removeTask(String task) {
        todos.remove(task);
    }

    public String getAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        todos.stream()
                .sorted();
        for (String todo : todos ){
            stringBuilder.append(todo).append(" ");
        }
        return stringBuilder.toString();
    }

}
