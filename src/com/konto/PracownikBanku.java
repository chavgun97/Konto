package com.konto;

import static com.konto.Konto.konta;

/**
 * Created by Nikolay on 2017-11-14.
 * Класс работника банка. Он может создавать и удалять банковские счета.
 */
public class PracownikBanku {
    private static int uniqueID = 0;        //Это переменная уникального ID счёта

    public static String dellKonto(int id){   // Метод который принимает значение ID и удаляет этот счёт
        Konto dellKonto = Logowanie.kontoFind(id); //Находит счёт
        if(dellKonto == null) {  //Если такого счёта не существует выдаёт об этом сообщение и закрывает метод
            String s = "Konto nie istnieje";
            System.out.println(s);

            return s;
        }
        String s = "Konto jest usunięto ID:  " + dellKonto.getID();
        konta.remove(dellKonto); //Удаляет счёт из нашего масива счетов
        System.out.println(s); // Выводит об этом сообщение.
        return s;
    }

    public static void createKonta(int ilosc){   //Метод который создаёт несколько счетов. Принимает значение количества этих счетов
        for(int i = 0; i<ilosc; i++){
            konta.add(new Konto(uniqueID)); //Добавляет новый счёт с уникальный айди
            uniqueID++; //ПРибавляет к переменной уникального айди 1, что бы она не повторялась.
        }
    }

    public static String createKonta(){ //То же самое но создаёт только один счёт
        Konto newKonto = new Konto(uniqueID);
            konta.add(newKonto);
            uniqueID++;
            return "Счёт создан, айди: " + newKonto.getID();
    }
    public static void createKonta(String pasword, int many){ //Создаёт только один счёт но ещё примимает значение пароля и денег. В программе не используется. Создал для возможного расширения возможностей программы.
        konta.add(new Konto(uniqueID, pasword, many ));
        uniqueID++;
    }


}
