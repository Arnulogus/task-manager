package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import controller.imanager.TaskManager;
import controller.utility.Managers;
import handler.HttpTaskHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpTaskServer {
    private static final int PORT = 8080;
    private final TaskManager manager;
    private final HttpServer server;

    public HttpTaskServer() throws IOException, InterruptedException {
        this(Managers.getDefault());
    }

    public HttpTaskServer(TaskManager manager) throws IOException {
        this.manager = manager;
        server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        server.createContext("/tasks", new HttpTaskHandler(manager));
    }

    public void start() {
        server.start();
        System.out.println("\nHTTP-сервер запущен на " + PORT + " порту!");
    }

    public void stop() {
        server.stop(1);
        System.out.println("\nHTTP-сервер приостановлен");
    }
}

