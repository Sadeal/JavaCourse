package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    public MainForm() {
        setTitle("XO game");
        setLocationRelativeTo(null);
        setBounds(850,400,250, 70);
        setResizable(false);
        MainGameField gameField = MainGameField.getInstance();
        // Создаём панель для кнопок
        JPanel buttonPanel = new JPanel(new GridLayout());
        add(gameField, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        JButton btnStart = new JButton("Начать новую игру");
        //JButton btnEnd = new JButton("Закончить игру");
        buttonPanel.add(btnStart);
        //buttonPanel.add(btnEnd);
        /*btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
         */
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBounds(850,400,455, 525);
                GameSettingsForm gameSettingsForm = new GameSettingsForm();
            }
        });
        setVisible(true);
    }
}
