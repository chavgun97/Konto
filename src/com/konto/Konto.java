package com.konto;                                //Это пакет (Адрес папки в которой находиться код)

import java.io.BufferedReader;                    //Это библиотеки который мы используем в коде
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by  on 2017-11-14.
 *Короче это класс нашего счёта. Здесь описываются все возможные операции с счётом.
 */
public class Konto {
    public static ArrayList<Konto> konta = new ArrayList<Konto>();          //Это масив куда мы записываем созданные счета. Масив статический по этому некого отношения к класу он не имеет.
    private final  int ID;                                              // Тут мы создаём переменную которая будет хранить уникальный ID счёта.final - означает что после обьявление мы не можем изменять её значение  ./private - означает что к ней можно обращатся только с этого класа.

    public String getPasword() {        // Это геттер Пароля, фенкция которая возвращает значение пароля. Для того что бы мы могли обращатся к переменной с другого класса
        return pasword;
    }

    private String pasword = "0000";     //Пароль по умолчанию 0000
    private double money = 10;           //Баланс по умолчанию 10.0 . Double означает что могут хранится дробные числа.
    private String wplata = "";                // куда будут записыватся все поступления.
    private String wyplata = "";                // куда будут записыватся все расходы.


    public double getMoney() {
        return money;
    } // Геттер который возвращает значение баланса на счету

    public Konto(int ID, String pasword, int money) {   // Это конструктор, он вызывается когда мы создаём обьект этого класса, принимает значение и присваивает их внутренним переменным
        this.ID = ID;
        this.pasword = pasword;
        this.money = money;
    }
    public Konto(int ID) {
        this.ID =ID;
    } //Второй конструктор который, котрый принимает только одно значение.



    public String getWyplaty() { // Геттер который возвращает значение всех расходов

        return wyplata;
    }

    public String getWplaty() { //Геттер который возвращает значение всех доходов

        return wplata;
    }

    public int getID() {
        return ID;
    } //Геттера который врзвращает значение ID



    public void OdbieraniePieniedzy(double money, int id){  //Метод который принимает поступления на счет. Он принимает два парамет параметра: Сумма, ID отправляющего:
        this.money+=money;                                   //Сумма баланса = сумма баланса + поступившая сумма
        wplata += "Summa: " + money + " <----ID Konta: " + id + "\n"; // Тут создается строка которая будет записана в масив поступлений

    }



    public String przelew(double money, int id) {       //Метод который переводит деньги на другой счёт. throws Exception - означает что он может выдавать ошибку.
        Konto odbiorca;
        String s = "";

        if (money <= this.money) { //Если введённоая сумма меньше и ровняется текущему балансу то выполняем следующий код
            //код
            odbiorca = Logowanie.kontoFind(id); //Вызывем метод который ищет счёт по ID и присваивает значение этого счёта odbiorca
            if (odbiorca == null) { //Если значение  odbiorca пустое, значит его не существует
                s = "Takie konto nie istnieje.";
                System.out.println(s);
                return s; // Код закрыает метод.
            }
            this.money -= money;                                            //Отнимаем сумму от баланса
             wyplata += "Summa: " + money + " --->ID Konta: " + id +"\n";    //Записываем транзакцию в расходы.

            odbiorca.OdbieraniePieniedzy(money, this.ID); // Вызвыем метод у принимающего счёта, и передаём туда значение суммы перевода и ID нашего счёта
        } else {
            s = "Not enough money.";
            System.out.println(s); //А если сумма перевода больше имеющегося баланса выводиться сообщение.
        }
        return s;
    }
    public String zmienHaslo(String newHaslo){ //Метод который принимает значение нового пароля и меняет старый пароль на новый.
        this.pasword = newHaslo;
        System.out.println("newHaslo  - " + newHaslo);
        return "Haslo jest zmienione na " + newHaslo;
    }

}
