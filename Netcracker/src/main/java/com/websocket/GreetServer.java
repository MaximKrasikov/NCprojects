package com.websocket;

import com.forms.ChatMessage;

import javax.websocket.DecodeException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Admin on 17.03.2019.
 */
public class GreetServer {

    //private static final Logger LOG = (Logger) LoggerFactory.getLogger(GreetServer.class);
    private ChatMessage message;
    private ServerSocket serverSocket;
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                new EchoClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
                MessageDecoder messageDecoder= new MessageDecoder();
                String inputLine;
                try {
                    while ((inputLine= in.readLine())!= null) {
                        ChatMessage message=messageDecoder.decode(inputLine);
                        if (".".equals(message.getContent())) {
                            out.println("bye");
                            break;
                        }
                        out.println(message.getContent());
                        System.out.println(message.getContent());
                    }
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DecodeException e) {
                    e.printStackTrace();
                }
        }
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
