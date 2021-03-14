package com.example.einzelbeispiel;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public Client (String message) throws IOException {

        Socket socket = new Socket("se2-isys.aau.at", 53212);

        OutputStream outputStream = socket.getOutputStream();

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
        dataOutputStream.close();

        socket.close();
    }
}
