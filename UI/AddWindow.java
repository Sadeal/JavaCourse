package UI;

import Data.*;
import com.company.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddWindow extends JFrame {

    JTextField  fieldURL, fieldName;

    public AddWindow() {
        super("Добавить");

        //кнопка добавить
        JButton Add = new JButton("Добавить");
        Add.setHorizontalAlignment(JTextField.LEFT);
        Add.addActionListener(e -> {
            try {
                MainWindow.myTableModel.add(fieldName.getText(), PriceParse.Parse(fieldURL.getText()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.setVisible(false);
        });

        //поле ввода ссылки
        fieldURL = new JTextField("Введите ссылку", 25);


        //поле ввода названия
        fieldName = new JTextField("Введите название", 25);


        //добавление полей и кнопок
        JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.add(fieldURL, BorderLayout.BEFORE_FIRST_LINE);
        this.add(fieldName, BorderLayout.CENTER);
        this.add(Add, BorderLayout.SOUTH);

        this.getContentPane();
        this.setSize(800, 200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
