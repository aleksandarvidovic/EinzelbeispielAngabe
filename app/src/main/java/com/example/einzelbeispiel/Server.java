package com.example.einzelbeispiel;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private String message;

    public Server() throws IOException {

        ServerSocket serverSocket = new ServerSocket(53212);

        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();

        DataInputStream dataInputStream = new DataInputStream(inputStream);

        message = dataInputStream.readUTF();

        serverSocket.close();
        socket.close();
    }

    public String getMessage(){

        return message;
    }
}
