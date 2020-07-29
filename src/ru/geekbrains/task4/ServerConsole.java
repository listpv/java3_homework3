package ru.geekbrains.task4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerConsole
{
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream in;
    ObjectOutputStream oos;
    Scanner sc;
    final int PORT = 8189;

    public ServerConsole() throws ClassNotFoundException, IOException
    {
        try
        {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);
            boolean flag = false;
            Student student;
            Book book;
            String name;
            String bookName;
            int id;
            int age;
            while (true)
            {
                if(flag)
                {
                        String str = in.readUTF();
                        if (str.equalsIgnoreCase("/end"))
                        {
                            break;
                        }
                        else
                            {
                                System.out.println(str);
                            }
                }
                System.out.println("Enter name.");
                name = sc.nextLine();
                System.out.println("Enter book");
                bookName = sc.nextLine();
                System.out.println("Enter id");
                id = sc.nextInt();
                System.out.println("Enter age;");
                age = sc.nextInt();
                student = new Student(id, name, age);
                book = new Book(bookName);
                student.setBook(book);

                oos.writeObject(student);
                flag = true;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                oos.close();
                socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }


        }
    }
}
