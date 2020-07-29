package ru.geekbrains.task4;

import java.io.IOException;

public class SerializeMain
{
    public static void main(String[] args) throws IOException
    {
//////    для окончания набора в консоли */* , для завершения работы /end /
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new ServerConsole();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new ClientConsole();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
