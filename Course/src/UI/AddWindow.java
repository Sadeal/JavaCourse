package UI;

import Data.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class AddWindow extends JFrame {

    JTextField  fieldURL, fieldName;

    public AddWindow() throws IOException {
        super("Добавить");

        JComboBox jCB = new JComboBox(new String[]{
                "Wildberies",
                "Ozon",
                "Yandex.Market",
                "citilink"
        });
        jCB.setVisible(false);

        Training trn = new Training();
        //чекбокс для множественного добавления
        JCheckBox mCheck = new JCheckBox("Несколько");

        //кнопка добавить
        JButton Add = new JButton("Добавить");
        Add.setHorizontalAlignment(JTextField.LEFT);
        Add.addActionListener(e -> {
            try {
                int id = 1;
                if(MainWindow.myTableModel.getRowCount()!=0)
                    for(int i=0; i<MainWindow.myTableModel.getRowCount(); i++)
                    {
                        if(id<=MainWindow.myTableModel.data.getObj(i).getID())
                            id=MainWindow.myTableModel.data.getObj(i).getID()+1;
                    }
                char[] s = fieldURL.getText().toCharArray();
                boolean get = true;
                for(int i = 0; i<fieldURL.getText().length(); i++) {
                    if(s[i]=='.') {
                        try {
                            if (s[i - 11] == 'w' && s[i - 10] == 'i' && s[i - 9] == 'l' && s[i - 8] == 'd' && s[i - 7] == 'b' && s[i - 6] == 'e' && s[i - 5] == 'r' && s[i - 4] == 'r' && s[i - 3] == 'i' && s[i - 2] == 'e' && s[i - 1] == 's') {
                                jCB.setSelectedIndex(0);
                                get = true;
                                break;
                            } else if (s[i - 4] == 'o' && s[i - 3] == 'z' && s[i - 2] == 'o' && s[i - 1] == 'n') {
                                jCB.setSelectedIndex(1);
                                get = true;
                                break;
                            } else if (s[i - 6] == 'y' && s[i - 5] == 'a' && s[i - 4] == 'n' && s[i - 3] == 'd' && s[i - 2] == 'e' && s[i - 1] == 'x') {
                                jCB.setSelectedIndex(2);
                                get = true;
                                break;
                            } else if (s[i - 8] == 'c' && s[i - 7] == 'i' && s[i - 6] == 't' && s[i - 5] == 'i' && s[i - 4] == 'l' && s[i - 3] == 'i' && s[i - 2] == 'n' && s[i - 1] == 'k') {
                                jCB.setSelectedIndex(3);
                                get = true;
                                break;
                            } else get = false;
                        } catch(IndexOutOfBoundsException ex) { get=false; }
                    }
                }
                if(get) {
                    if(Objects.equals(fieldName.getText(), "")){
                        fieldName.setText(PriceParse.ParseTitle(fieldURL.getText()));
                    }
                    MainWindow.myTableModel.add(id, fieldName.getText(), PriceParse.Parse(fieldURL.getText(), jCB.getSelectedIndex()), fieldURL.getText(), jCB.getSelectedIndex());
                    trn.deleting();
                    if (!mCheck.isSelected())
                        this.dispose();
                }
                else JOptionPane.showMessageDialog(null, "Мы не поддерживаем введённый сайт");
            } catch (IllegalArgumentException | IOException ex) {
                //ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Введите корректную ссылку");
            }

        });

        //поле ввода ссылки
        fieldURL = new JTextField(25);
        fieldURL.setToolTipText("Введите ссылку");

        //поля подсказки
        JLabel URL = new JLabel("Ссылка");
        JLabel Name = new JLabel("Название");


        //поле ввода названия
        fieldName = new JTextField(25);
        fieldName.setToolTipText("Введите название");


        //добавление полей и кнопок
        //JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //this.add(fieldURL, BorderLayout.BEFORE_FIRST_LINE);
        //this.add(fieldName, BorderLayout.CENTER);
        //this.add(Add, BorderLayout.SOUTH);

        JPanel grid = new JPanel(new GridLayout(4, 2, 5, 0) );
        grid.add(fieldURL);
        grid.add(URL);
        grid.add(fieldName);
        grid.add(Name);
        grid.add(Add);
        grid.add(mCheck);
        grid.add(jCB);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flow.add(grid);
        Container container = getContentPane();
        container.add(flow, BorderLayout.NORTH);

        this.getRootPane().setDefaultButton(Add);


        this.getContentPane();
        this.setSize(390, 125);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}
