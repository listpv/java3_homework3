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
    DataOutput out;
    ObjectOutputStream oos;
//    ObjectInputStream ois;
    Scanner sc;
    final int PORT = 8189;

    public ServerConsole() throws ClassNotFoundException {
        try
        {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
//            out = new DataOutputStream(socket.getOutputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
//            ois = new ObjectInputStream(socket.getInputStream());
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
//                if(flag)
//                {
////                    String str = (String)ois.readObject();
//                        String str = in.readUTF();
//                        if (str.equalsIgnoreCase("/end"))
//                        {
//                            break;
//                        }
////                    ois.close();
//                }
////                    String var;
                System.out.println("Enter name.");
                name = sc.nextLine();
                System.out.println("Enter id");
                id = sc.nextInt();
                System.out.println("Enter age;");
                age = sc.nextInt();
                System.out.println("Enter book");
                bookName = sc.nextLine();
//                System.out.println("Enter id");
//                id = sc.nextInt();
//                        if(var.equalsIgnoreCase("/end"))
//                        {
//                            stp = true;
//                            break;
//                        }
                student = new Student(id, name, age);
                book = new Book(bookName);
                student.setBook(book);
//                        ObjectOutputStream oos = new ObjectOutputStream((OutputStream) out);
                oos.writeObject(student);
//                        flag = true;
//                        break;
//                        if(var.equalsIgnoreCase("*/*"))
//                        {
//                            break;
//                        }
//                        arString.add(var);
 //                   }
//                    if(stp)
//                    {
//                        out.writeUTF("это сервер /end");
//                    }
//                    else
//                    {
//                        out.writeUTF("Сервер написал " + arString.toString());
//                    }
//                    flag = false;
//                }
//                String str = in.readUTF();
//                if (str.equals("это клиент /end"))
//                {
//                    out.writeUTF("это сервер /end");
//                    break;
//                }
//                System.out.println(str);
//                String scannerStr = sc.nextLine();
//                String var;
//                boolean stp = false;
//                ArrayList<String> arString = new ArrayList<>();
//                while (true)
//                {
//                    var = sc.nextLine();
//                    if(var.equalsIgnoreCase("/end"))
//                    {
//                        stp = true;
//                        break;
//                    }
//                    if(var.equalsIgnoreCase("*/*"))
//                    {
//                        break;
//                    }
//                    arString.add(var);
//                }
//                if(stp)
//                {
//                    out.writeUTF("это сервер /end");
//                }
//                else {
//                    out.writeUTF("Сервер написал " + arString);
//                }
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
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                serverSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
