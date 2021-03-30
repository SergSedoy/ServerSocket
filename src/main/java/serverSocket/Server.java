package serverSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 7789;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(SERVER_PORT);// серверсокет прослушивает порт 7789
             Socket clientSocket = server.accept()) {  // accept() будет ждать пока кто-нибудь не захочет подключиться
            System.out.println("Сервер запущен!");
            System.out.println("New connection accepted");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                final String name = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                out.write(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                out.flush(); // выталкиваем все из буфера
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            System.out.println("Сервер закрыт!");
        }
    }
}
