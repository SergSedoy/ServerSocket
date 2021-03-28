package serverSocket;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            try (Socket clientSocket = new Socket("localhost", Server.SERVER_PORT); // этой строкой мы запрашиваем у сервера доступ на соединение
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                String name = reader.readLine(); // ждём пока клиент что-нибудь не напишет в консоль
                out.write(name + "\n"); // отправляем сообщение на сервер
                out.flush();
                String serverWord = in.readLine(); // ждём, что скажет сервер
                System.out.println(serverWord);
            } finally {
                System.out.println("Клиент был закрыт...");
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}