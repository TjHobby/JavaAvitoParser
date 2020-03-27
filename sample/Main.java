package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Timer;

public class Main extends Application {
    Settings settings;
    Controller controller;
    Timer timer;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Avito Parser");
        FlowPane topPane = new FlowPane(10,10);
        topPane.setAlignment(Pos.CENTER);
        Text db = new Text("Настройки базы данных");
        TextField keyWords = new TextField("Ключевые слова");
        TextField dbIP = new TextField("IP базы данных");
        TextField dbName = new TextField("Имя базы данных");
        TextField dbUser = new TextField("Имя пользователя базы данных");
        PasswordField dbPass = new PasswordField();
        dbPass.appendText("Пароль для БД");
        TextField tgToken = new TextField("Телеграм токен");
        CheckBox kurskOnly = new CheckBox("Искать только в Курске");
        TextField coolDown = new TextField("Cooldown в секундах");
        Button runButton = new Button("RUN");
        runButton.addEventHandler(ActionEvent.ACTION, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                controller = new Controller(settings);
                timer = new Timer();
                timer.schedule(controller,settings.getCooldown()*1000,settings.getCooldown()*1000);
            }
        });
        Button stopButton = new Button("STOP");
        stopButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                timer.cancel();
            }
        });
        Button applyButton = new Button("Apply");
        applyButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                settings = null;
                settings = new Settings(keyWords.getText(),Integer.parseInt(coolDown.getText()),kurskOnly.isSelected(),dbIP.getText(),dbUser.getText(),dbPass.getText(),dbName.getText(),tgToken.getText());
            }
        });
        topPane.getChildren().addAll(runButton,stopButton, applyButton);
        BorderPane rootNode = new BorderPane();
        rootNode.setMinSize(300,345);
        rootNode.setTop(topPane);
        FlowPane centerPane = new FlowPane(Orientation.VERTICAL,11,11);
        centerPane.setAlignment(Pos.CENTER);
        centerPane.getChildren().addAll(keyWords,coolDown,kurskOnly,db,dbIP,dbName,dbUser,dbPass,tgToken);
        rootNode.setCenter(centerPane);
        FlowPane bottomPane = new FlowPane(10,10);
        bottomPane.setAlignment(Pos.TOP_CENTER);
        rootNode.setBottom(bottomPane);
        primaryStage.setScene(new Scene(rootNode,300, 380));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
