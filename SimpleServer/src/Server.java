import com.javaserver.Phone;
import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8000)) {
            System.out.println("Server started!");

            while (true) {
                    Phone phone = new Phone(server);
                    new Thread(() -> {
                        String request = phone.readLine();
                        System.out.println("Request: " + request);
                        String response = "Temp in " + request + ": " + ((int) (Math.random() * 30 - 10));
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Response: " + response);
                        try {
                            phone.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
