package com.company;

import UI.MainWindow;
import db.DBWorker;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        DBWorker.initDB();
        new MainWindow();
    }
}
