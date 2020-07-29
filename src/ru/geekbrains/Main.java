package ru.geekbrains;

//        1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
//        2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
//        Может пригодиться следующая конструкция: ArrayList<InputStream> al = new ArrayList<>(); ...
//        Enumeration<InputStream> e = Collections.enumeration(al);
//        3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
//        Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
//        Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
//        4. Сделать клиен-серверное приложение. Передать по сети сеарилизованный объект.

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class Main
{

    public static void main(String[] args) throws IOException
    {
	// write your code here

        task1();        // Задание 1.
        task2Var1();    // Задание 2, 1-й вариант.
        task2Var2();    // Задание 2, 2-й вариант.
        task3();        // Задание 3.

    }

    public static void task1()
    {

        fullfillFile("Lesson3/Task1.txt", '*', 50, false );
        byte[] buf = new byte[50];
        try(FileInputStream in = new FileInputStream("Lesson3/Task1.txt"))
        {
            int count;
            while ((count = in.read(buf)) != -1)
            {
                for (int i = 0; i < count; i++)
                {
                    System.out.print((char) buf[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void task2Var1() throws IOException {
        ArrayList<InputStream> ali = new ArrayList<>();
        for(int i = 1; i <= 5; i++)
        {
            String str = "Lesson3/Task2_" + i + ".txt";
            fullfillFile(str,(char) (i + 48), 100, false);
            ali.add(new FileInputStream(str));
        }
        long t = System.currentTimeMillis();
        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));
        byte[] mass = new byte[500];
        //  пытался записать одномоментно в mass, типа in.read(mass), не получается, записывается только первая часть
        // из 100 элементов. Поэтому и второй способ. Думал по времени выиграть, но, видимо очень иало операций
        // с малым количеством. Время одинаковое.
        int x;
        int j = 0;
        while ((x = in.read()) != -1)
        {
            mass[j] = (byte) x;
            j++;
        }
        System.out.println("Время " + (System.currentTimeMillis() - t));
        FileOutputStream out = new FileOutputStream("Lesson3/Task2_total.txt");
        out.write(mass);
        in.close();
        out.close();
    }

    public static void task2Var2() throws IOException {
        ArrayList<InputStream> ali = new ArrayList<>();
        for(int i = 1; i <= 5; i++)
        {
            String str = "Lesson3/Task2_" + i + ".txt";
            fullfillFile(str, (char) (i + 48), 100, false);
            ali.add(new FileInputStream(str));
        }
        long t = System.currentTimeMillis();
        byte[] mass = new byte[500];
        // Второй способ, дуиал выиграть по времени, но в этом случае нет.
        int i = 0;
        for (InputStream o: ali)
        {
            o.read(mass, i, 100);
            i = i + 100;
            o.close();
        }
        System.out.println("Время " + (System.currentTimeMillis() - t));
        FileOutputStream out = new FileOutputStream("Lesson3/Task2_total.txt");
        out.write(mass);
        out.close();
    }

    public static void task3()
    {
        // Заполнение файла страницами по 1000 символов.
        long t = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++)
        {
            fullfillFile("Lesson3/Task3.txt",(char) (i + 34), 1000, true);
        }
        System.out.println("Время загрузки " + (System.currentTimeMillis() - t));
        ////////////////////////////////////
        while (true)
        {
            System.out.println("Введите номер страницы от 1 до 27000. Если неинтересно, введите -1");
            Scanner scanner = new Scanner(System.in);
            int str = scanner.nextInt();
            if(str == -1)
            {
                break;
            }
            long t1 = System.currentTimeMillis();
            byte[] mass = new byte[1000];
            try (RandomAccessFile raf = new RandomAccessFile("Lesson3/Task3.txt", "r")) {
                raf.seek(((str - 1) * 1000));
                raf.read(mass, 0, 1000);
                System.out.println("Время чтения из файла " + (System.currentTimeMillis() - t1));
                for (int i = 0; i < mass.length; i++) {
                    System.out.print((char) mass[i]);
                }
                System.out.println();
                System.out.println("Время чтения из файла и вывода на экран " + (System.currentTimeMillis() - t1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // Заполнение массива.
    public static byte @NotNull [] fullfillArrayWithChar(char chr, int quant)
    {
        char[] chars = new char[quant];
        for (int i = 0; i < chars.length; i++)
        {
            if (i == 99)
            {
                chars[i] = '\n';
            }
            else
                {
                    chars[i] = chr;
                }
        }
//        chars[chars.length - 1] = '\n';
       return (new String(chars, 0, chars.length).getBytes());
    }

    // Заполнение файла.
    public static void fullfillFile(String name, char chr, int quant, boolean tOf)
    {
        try(FileOutputStream out = new FileOutputStream(name, tOf))
        {
            out.write(fullfillArrayWithChar(chr, quant));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
