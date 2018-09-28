package com.konto;


import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.konto.Konto.konta; //Импортируем наш масив счетов

/**
 * Created by Nikolay on 2017-11-14.
 * Это класс который отвечает непосредственно за связь программы и человека.
 * И связь всех методов воедино.
 *
 */
public class Logowanie {
    private static final int PracownikBankuID =9999 ;          //Это логин работника банка, нужен для получения доступа к аккаунту
    private static final String PracownikBankuPasword = "root"; //Это пароль работника банка, так же нужен для получения доступа

    public static Konto kontoFind(int id){   //Это метод с помощю которого мы ищем счёт по его ID
        for(Konto k : konta){    // Перебирает все счета.
            if(k.getID() == id){ //Если находит счёт с введённым ID то возвращает этот счёт.
                return k;
            }
        }
        return null; //Если не находит то возвращает пустоту.
    }


    public static void logowanie(int id, String psword, Stage primaryStage){    //Метод который запраштвает айди и пароль, и даёт доступ к акаунту

                                //Принимает информацию с клавиатуры, приводим её к переменной pasword

            Konto konto = kontoFind(id);                              //Ищем счёт по введённому ID, и присаиваем его значение переменной конто

            if (id == PracownikBankuID && psword.equals(PracownikBankuPasword)) { //Если логин и пароль соответствуют логину и паролю работника банка то запускаем интерфей работника банка.
                new GraficalUsersInterface().PracownicBankuScene(primaryStage);//Запускает окно работника
            } else if (konto != null) { //Если переменная конто не хранит пустоту значит счёт существует
                if (konto.getPasword().equals(psword)) //Мы проверяем веддённый пароль с паролем счёта, если они совпадают то получаем доступ то запускаем интерфей работника банка
                   new GraficalUsersInterface().wlascicielScene(primaryStage,konto); //Запускает окно владельца
                else {
                    GraficalUsersInterface.masage = "Nieprawidłowe hasło.";
                    System.out.println("Nieprawidłowe hasło."); //Если пароли не совпадают то выдается сообщение об этом и b - присваиваем значение правда (Означает что цикл повторится)
                    return;
                }
            } else { //Если переменная конто хранит пустоту то счёта не существует. Выводим сообщение и присваиваем значение b - правда(Цикл повторяется)
                GraficalUsersInterface.masage ="Konto nie istnieje.";
                System.out.println("Konto nie istnieje.");
                return;
            }

    }




}
