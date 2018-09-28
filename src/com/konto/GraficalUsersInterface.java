package com.konto;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Nikolay on 2017-11-16.
 */
public class GraficalUsersInterface extends Application { // Наследуемся от Applicatoin что бы подключить графический интерфейс
    static Stage primaryStage; // Это переменная самого главного окна. Создали что бы использовать в других класах


    public static void main(String[] args) { // Метод в которой запускается код когда ты запускаешь программу
        launch(); //Здесь мы запускаем метод start

    }

    @Override //Переопределение
    public void start(Stage primaryStage) { // Метод где запускается графический интерфейс и остальной код, он принимает главное окно.

        this.primaryStage = primaryStage; //Присваиваем сылку главного окна к переменоой которую обьявили в класе.

        PracownikBanku.createKonta(10);   //Мы создаем 10 счетов в банке, что бы потом с ними работать

        logowanieScene(primaryStage); //Запускаем метод окна входа.

    }


static public String masage = "Proszę podać ID ta pasword:";  //Сообщение окна входа. Оно статическоего что бы мы могли обращатся с любого класа

    /**
     * Дальше идут три метода наших трёх окон, в которых они создаются и запускаются.
     * Окна входа сделано без использования XML, полностю на джава.
     *
     *Окно работника и владельца счёта сделаны с помощю XML и SceneBilder, а обратобка событий(отслеживание нажатий и логика) реализована в Controller
     *
     */


    public  void logowanieScene(Stage primaryStage) { // окно входа, принимает главное окно

        GridPane paneLogo = new GridPane();// Создаётся контейнер, рспологает елементы с помощю таблицы, и координат

        paneLogo.setPadding(new Insets(20)); //Отступ между окном и контейнером
        paneLogo.setHgap(25); //Растояние между столбцами
        paneLogo.setVgap(15); //Растояние между Строками

        Label lableTitle = new Label(GraficalUsersInterface.masage); //Текст которому присваивается значение нащего сообщения.



        paneLogo.add(lableTitle,0,0,2,1); // Поставить на ячейку (0,0), диапазона столбца 2(обьеденение столбцов), 1 ряд - Текст сообщения.

        Label userName = new Label("Login"); // Текст логин
        TextField fieldUserName = new TextField(); // Поле ввода логина

        Label password = new Label("Password"); //Пароль
        PasswordField fieldPassword = new PasswordField();// Поле ввода пароля

        Button loginButton = new Button("LOGIN"); //Кнопка логин

        GridPane.setHalignment(userName, HPos.RIGHT);  //Выравнивание текста логин по правому краю
        paneLogo.add(userName, 0,1);//Добавление этого текста в контейнер(Окно)

        GridPane.setHalignment(password, HPos.RIGHT);
        paneLogo.add(password,0,2);

        GridPane.setHalignment(fieldUserName,HPos.LEFT);
        paneLogo.add(fieldUserName,1,1);

        GridPane.setHalignment(fieldPassword,HPos.LEFT);
        paneLogo.add(fieldPassword,1,2);

        GridPane.setHalignment(loginButton, HPos.RIGHT);
        paneLogo.add(loginButton,1,3);


        primaryStage.setTitle("Logowanie"); //Указываем текст в верху окна
        primaryStage.setScene(new Scene(paneLogo, 300, 200)); //Вставляем наше окно в главное окно
        primaryStage.show(); //Запускаем главное окно

        loginButton.setOnAction(new EventHandler<ActionEvent>(){ //Отслеживает нажатие на кнопку
            @Override
            public void handle(ActionEvent event) { // Срабатывает когда мы нажимаем на кнопку


                    String idS = fieldUserName.getText(); // Берём значение с поля айди
                    if(idS.equals(""))     //Если оно пустое то присваиваем ему значение -1, так как отсвутсвие значения вызывает ошибку.
                        idS = "-1";
                    int id = Integer.parseInt(idS); //Приводим строку к числу

                    String pasword = fieldPassword.getText();// Присваиваем значение с поля пароль переменной.
                    Logowanie.logowanie(id, pasword, primaryStage); //Вызываем метод входа и передаём туда значение айди, пароля, и главного окна что бы он мог вызывать другие окна.
                    lableTitle.setText(masage); //если метод заканчивает свою работу значит логин или пароль неправильный, он изменяет значение выводимого сообщение и завершается
                    fieldPassword.clear(); //Чистим поле пароль
                    fieldUserName.clear();//Чистим поле айди для повторного ввода.



            }
        });


    }
     static Konto konto;
    private Controller controller;
    public  void wlascicielScene(Stage primaryStage, Konto konto){
        try {
            Parent panel = FXMLLoader.load(Objects.requireNonNull(GraficalUsersInterface.class.getClassLoader().getResource("com/konto/wlascitelKontaWindow.fxml")));
            primaryStage.setTitle("Wlascitel Konta");
            primaryStage.setScene(new Scene(panel));
            primaryStage.show();
            this.konto = konto;


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void PracownicBankuScene(Stage primaryStage){
        try {
            Parent panel = FXMLLoader.load(Objects.requireNonNull(GraficalUsersInterface.class.getClassLoader().getResource("com/konto/pracownikWindow.fxml")));
            primaryStage.setTitle("Pracownic Banku");
            primaryStage.setScene(new Scene(panel));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

