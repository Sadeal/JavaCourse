package UI;

import Data.*;
import com.company.MyTableModel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JTable jTable;
    public static MyTableModel myTableModel;

    public MainWindow(){
        super("Market Parser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        myTableModel = new MyTableModel(new Base());

        jTable = new JTable();
        jTable.setModel(myTableModel);

        JScrollPane jScrollPane = new JScrollPane(jTable);
        //кнопка удаления
        JButton buttonDelete = new JButton("Удалить");
        buttonDelete.setHorizontalAlignment(JTextField.LEFT);
        buttonDelete.addActionListener(e -> {
            try {
                myTableModel.delete(jTable.getSelectedRow());
            }
            catch(IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Выберите строку");
            }
        });

        //кнопка добавления
        JButton buttonAdd = new JButton("Добавить");
        buttonAdd.setHorizontalAlignment(JTextField.LEFT);
        buttonAdd.addActionListener(e -> {
            new AddWindow();
        });

        //добавление кнопок
        this.add(buttonAdd, BorderLayout.NORTH);
        this.add(buttonDelete, BorderLayout.SOUTH);

        this.getContentPane();
        this.add(jScrollPane);
        this.pack();
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
