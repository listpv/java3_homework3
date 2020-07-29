package ru.geekbrains.task4;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientConsole
{
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
//    ObjectOutputStream oos;
    ObjectInputStream ois;
    final String IP_ADDRESS = "localhost";
    final int PORT = 8189;
    Scanner sc;

    public ClientConsole() throws ClassNotFoundException {
        try
        {
            socket = new Socket(IP_ADDRESS, PORT);
            ois = new ObjectInputStream(socket.getInputStream());
//            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
//            oos = new ObjectOutputStream(socket.getOutputStream());

            sc = new Scanner(System.in);
            Student student;
            while (true)
            {

                student = (Student)ois.readObject();
                student.info();
                System.out.println("Y N");
                String var = sc.nextLine();
                    if(var.equalsIgnoreCase("N"))
                    {
                        out.writeUTF("/end");
                        break;
                    }
                    if(var.equalsIgnoreCase("Y"))
                    {
                        out.writeUTF("Продолжаем.");
                    }

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                ois.close();
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
/*        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    while (true)
                    {
//                        String scannerStr = sc.nextLine();
//                        out.writeUTF("это клиент " + scannerStr);
                        String str = in.readUTF();
                        if (str.equals("это сервер /end"))
                        {
                            break;
                        }
                        System.out.println( str);
                        String scannerStr = sc.nextLine();
                        out.writeUTF("это клиент " + scannerStr);

                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();*/

    }
}
