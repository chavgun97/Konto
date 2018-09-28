package com.konto;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionListener;
import java.util.IdentityHashMap;

/**
 * Created by Nikolay on 2017-11-17.
 * Это класс который подклуяается к FXML и отслеживает все события в программе, принимает все значение и содержит все методы.
 */
public class  Controller {


    @FXML
    TextField textIDFieldDell;   //Поле ввода айди
    @FXML
    Label createMasege;           //Сообщения для создалния счёта
    @FXML Label dellMasage;             //Сообщение для удаления счёта
    /**
     Дальше идут переменные хоязина счёта
     */
    @FXML Label idKonto;  //айди счёта
    @FXML Label balanse; //Баланс на счета
    @FXML TextField summa;      //Полле для ввода суммы перевода
    @FXML TextField idPrzelew;       //Поле для ввода айди счёта перевода
    @FXML
    TextArea wplaty;          //Поле для вывлда поступлений
    @FXML TextArea wyplaty;                    //Поле для ввывода расходов
    @FXML TextField newPasword;            //Поле для нового пароля
    @FXML Label msgPasword;             //Сообщение смены пароля
    @FXML Label msgPrzelew;             //Сообщение о переводе


    public void closeButton(ActionEvent actionEvent) { //Метод кнопки закрыть
        new GraficalUsersInterface().logowanieScene(GraficalUsersInterface.primaryStage); //Запускаем коно входа
    }

    public void dellButton(ActionEvent actionEvent) {   //Кнопки удалить
        String sId = textIDFieldDell.getText();
        if(sId.equals(""))
            sId = "-1";
        int id = Integer.parseInt(sId);
        dellMasage.setText(PracownikBanku.dellKonto(id)); //Вызываем метод удаления ранее написаный и передаём туда адйди. Метод возвращает сообщение, выводим его
    }

    public void addButton(ActionEvent actionEvent) {
        createMasege.setText(PracownikBanku.createKonta());
    } //Метод кнопки добавить счёт. Берёт значение с поля и отпраляет его в метод доавления счёта.


    /**
    Дальше идут методы хоязина счёта
     */

    public void buttonClose(ActionEvent actionEvent) {//Кнопка закрытия
        new GraficalUsersInterface().logowanieScene(GraficalUsersInterface.primaryStage);
    }


    public void buttonPrzelew(ActionEvent actionEvent) {//Метод перевода
        String sSumma = summa.getText(); //Берёт значение суммы
        if(sSumma.equals(""))
            sSumma = "-1";

        String sId = idPrzelew.getText(); //Берёт значение айди
        if(sId.equals(""))
            sId = "-1";

        double summa = Double.parseDouble(sSumma); //Приводит их к числу и дробному числу
        int id = Integer.parseInt(sId);
        msgPrzelew.setText(GraficalUsersInterface.konto.przelew(summa, id)); //Вызывает метод перевода а он возыращает сообщение об операции

        balanse.setText(String.valueOf(GraficalUsersInterface.konto.getMoney()));//Обновляем счёт

        wplaty.setText(GraficalUsersInterface.konto.getWplaty()); //Обновляем расходы и доходы
        wyplaty.setText(GraficalUsersInterface.konto.getWyplaty());
        wplaty.setEditable(true);//запрет редактирования
        wyplaty.setEditable(true);


    }

    public void buttonZmiane(ActionEvent actionEvent) { //Метод кнопки изменения пароля
        msgPasword.setText(GraficalUsersInterface.konto.zmienHaslo(this.newPasword.getText()));//Берёт новый пароль вызывает метод смены пароля, тот в свою очеред возвращает сообщения об операции
    }


    public void buttonUaktyalnij(MouseEvent mouseEvent) { //Метод который срабатывает при движении мышки по окну. Создан для обновления инвориации на окне, нечего лучше придумать не смог.
        balanse.setText(String.valueOf(GraficalUsersInterface.konto.getMoney()));// обновляет баланс
        idKonto.setText(String.valueOf(GraficalUsersInterface.konto.getID())); //Обновляет айди
        wplaty.setText(GraficalUsersInterface.konto.getWplaty());//Обновляет доходы и расхлды.
        wyplaty.setText(GraficalUsersInterface.konto.getWyplaty());
    }

}
