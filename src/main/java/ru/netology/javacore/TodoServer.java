package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private Todos todos;
    private int port;
    private String type;
    private String task;


    public TodoServer(int port, Todos todos) {
        this.todos = todos;
        this.port = port;
    }

    public TodoServer(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }


    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(port); // стартуем сервер
        System.out.println("Starting server at port " + port + ". Запустите Client");
        while (true) {  // в цикле принимаем подключения
            try (Socket socket = serverSocket.accept(); // ожидание подключения Client
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // прием потока данных
                 PrintWriter out = new PrintWriter(socket.getOutputStream()); // вывод в консоль (или файл)
            ) {
                String task = in.readLine();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                TodoServer todos1 = gson.fromJson(task, TodoServer.class);
                if (todos1.getType().equalsIgnoreCase("ADD")) {
                    todos.addTask(todos1.getTask());
                } else if (todos1.getType().equalsIgnoreCase("REMOVE")) {
                    todos.removeTask(todos1.getTask());
                }
                out.println(todos.getAllTasks());
            } catch (IOException e) {
                System.out.println("Не могу стартовать сервер");
                e.printStackTrace();
            }
        }
    }
}