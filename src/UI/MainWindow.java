package UI;

import Data.*;
import Game.*;
import Table.MyTableModel;
import db.DBWorker;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainWindow extends JFrame {

    private JTable jTable;
    private  JFileChooser fileChooser = null;
    private JLabel searchLbl;
    private TableRowSorter<TableModel> sorter;
    static int training = -1;
    private boolean added = false;

    MainClass theMainClass;

    JTextField  fieldSearch;

    public static MyTableModel myTableModel;

    public MainWindow() throws IOException {
        super("Контроль цен");
        init();
    }

        public void init() throws IOException {

        HistoryWindow theHistoryWindow;

        //объявление таблицы
        myTableModel = new MyTableModel(new Base());
        jTable = new JTable(myTableModel);

        //объявление сортировки
        sorter = new TableRowSorter<>(jTable.getModel());
        jTable.setRowSorter(sorter);

        //скролл для таблицы
        JScrollPane jScrollPane = new JScrollPane(jTable);

        //обучение
        Training trn = new Training();
        //final boolean[] training = {false};
        JButton buttonStopTraining = new JButton("Остановить обучение");
        buttonStopTraining.addActionListener(e -> {
            training = 10;
            buttonStopTraining.setVisible(false);
        });

        //выбор файла
        fileChooser = new JFileChooser();

        UIManager.put(
                "FileChooser.saveButtonText", "Сохранить");
        UIManager.put(
                "FileChooser.cancelButtonText", "Отмена");
        UIManager.put(
                "FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put(
                "FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put(
                "FileChooser.lookInLabelText", "Директория");
        UIManager.put(
                "FileChooser.saveInLabelText", "Сохранить в директории");
        UIManager.put(
                "FileChooser.folderNameLabelText", "Путь директории");
        UIManager.put(
                "OptionPane.yesButtonText", "Да");
        UIManager.put(
                "OptionPane.noButtonText", "Нет");

        //меню
        Font font = new Font("Verdana", Font.PLAIN, 11);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Файл");
        fileMenu.setFont(font);

            JMenuItem load = new JMenuItem("Загрузить");
            load.setFont(font);
            fileMenu.add(load);
            load.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileopen = new JFileChooser();
                    int ret = fileopen.showDialog(null, "Открыть");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        try {DBWorker.deleteDB();} catch(SQLException ex) { ex.printStackTrace(); }
                        myTableModel.load(fileopen.getSelectedFile());
                        added=true;
                    }
                }
            });

            JMenuItem save = new JMenuItem("Сохранить как...");
            save.setFont(font);
            fileMenu.add(save);
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileopen = new JFileChooser();
                    int ret = fileopen.showDialog(null, "Сохранить");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        myTableModel.save(fileopen.getSelectedFile());
                    }
                }
            });

            JMenuItem clear = new JMenuItem("Очистить таблицу");
            clear.setFont(font);
            fileMenu.add(clear);
            clear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить всю таблицу?","Удаление",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                        while (myTableModel.getRowCount() != 0)
                            myTableModel.delete(0);
                        try {
                            DBWorker.deleteDB();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            fileMenu.addSeparator();

            JMenuItem exitItem = new JMenuItem("Закрыть программу");
            exitItem.setFont(font);
            fileMenu.add(exitItem);
            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "Закрыть программу?",
                            "Выход",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        try { DBWorker.closeDB(); } catch(SQLException ex) { ex.printStackTrace(); }
                        System.exit(0);
                    }
                }
            });

        JMenu utilMenu = new JMenu("Утилиты");
        utilMenu.setFont(font);

            JMenuItem trainItem = new JMenuItem("Пройти обучение");
            trainItem.setFont(font);
            utilMenu.add(trainItem);
            trainItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    training = 0;
                    buttonStopTraining.setVisible(true);
                    trn.start();
                }
            });

            /*
            JMenuItem gameItem = new JMenuItem("Играть");
            gameItem.setFont(font);
            utilMenu.add(gameItem);
            gameItem.addActionListener(e -> new MainClass());
            */


        JMenu aboutMenu = new JMenu("Справка");
        aboutMenu.setFont(font);

            JMenuItem aboutItem = new JMenuItem("О программе");
            aboutItem.setFont(font);
            aboutMenu.add(aboutItem);
            aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Программа для контроля цен на продукты разных интернет магазинов\n\nВерсия 1.0.0\n(c) Sadeal"));

        menuBar.add(fileMenu);
        menuBar.add(utilMenu);
        menuBar.add(aboutMenu);

        //поле поиска
        fieldSearch = new JTextField(12);
        fieldSearch.setToolTipText("Введите название");

        //поиск
        fieldSearch.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = fieldSearch.getText();
                if (str.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    //(?i) значит ищет и маленькие и большие, если ввели любой регистр (только на английском) (case intensive search)
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = fieldSearch.getText();
                if (str.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        //Лейбл поиска
        searchLbl = new JLabel("Поиск");

        //кнопка удаления
        JButton buttonDelete = new JButton("Удалить");
        buttonDelete.addActionListener(e -> {
            int[] a = jTable.getSelectedRows();
            for(int i=jTable.getSelectedRows().length-1; i>=0; i--)
                a[i]+=1;
            if(JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить "+Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(", "))+", строку(-и)","Удаление",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){

                for(int i=jTable.getSelectedRows().length-1; i>=0; i--) {  // удаление в таблице истории
                    int modelIndex = jTable.convertRowIndexToModel(a[i]-1);
                    for (int j = 0; j < HistoryWindow.myTableHistory.data.getCount(); j++) {
                        if (Objects.equals(myTableModel.data.getObj(modelIndex).getName(), HistoryWindow.myTableHistory.data.getObj(j).getName()))
                            HistoryWindow.myTableHistory.delete(j);
                    }
                }

                for(int i=jTable.getSelectedRows().length-1; i>=0; i--) { // удаление в основной таблице
                    int modelIndex = jTable.convertRowIndexToModel(a[i]-1);
                    try {
                        DBWorker.delete(myTableModel.data.getObj(modelIndex).getID());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    myTableModel.delete(modelIndex);
                }
            }
                trn.more();
            if(training==-1) buttonStopTraining.setVisible(false);

        });

        //кнопка добавления
        JButton buttonAdd = new JButton("Добавить");
        buttonAdd.addActionListener(e -> {
            try {
                new AddWindow();
                trn.add();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //добавление кнопок
        JPanel grid = new JPanel(new GridLayout(3, 2, 5, 10) );
        grid.add(searchLbl);
        grid.add(fieldSearch);
        grid.add(buttonAdd);
        grid.add(buttonDelete);
        grid.add(buttonStopTraining);
        buttonStopTraining.setVisible(false);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(grid);
        Container container = getContentPane();
        container.add(flow, BorderLayout.SOUTH);

        //popup menu
        JPopupMenu popupMenu = new JPopupMenu();

            JMenuItem copyItem = new JMenuItem("Скопировать ссылку");
            copyItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int modelIndex = jTable.convertRowIndexToModel(jTable.getSelectedRow());
                    StringSelection stringSelection = new StringSelection(myTableModel.data.getObj(modelIndex).getURL());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                }
            });

            JMenuItem priceHistoryItem = new JMenuItem("История цен");
            priceHistoryItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //HistoryWindow.newIcon(PriceParse.imgURL(myTableModel.data.getObj(table.getSelectedRow()).getURL()));
                    int modelIndex = jTable.convertRowIndexToModel(jTable.getSelectedRow());
                    HistoryWindow.setFieldName(myTableModel.data.getObj(modelIndex).getID());
                    try {
                        new HistoryWindow();
                    } catch (MalformedURLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            JMenuItem deleteItem = new JMenuItem("Удалить");
            deleteItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int[] a = jTable.getSelectedRows();
                    for(int i=jTable.getSelectedRows().length-1; i>=0; i--)
                        a[i]+=1;
                    if(JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить "+Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(", "))+", строку(-и)","Удаление",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){

                        for(int i=jTable.getSelectedRows().length-1; i>=0; i--) {  // удаление в таблице истории
                            int modelIndex = jTable.convertRowIndexToModel(a[i]-1);
                            for (int j = 0; j < HistoryWindow.myTableHistory.data.getCount(); j++) {
                                if (Objects.equals(myTableModel.data.getObj(modelIndex).getName(), HistoryWindow.myTableHistory.data.getObj(j).getName()))
                                    HistoryWindow.myTableHistory.delete(j);
                            }
                        }

                        for(int i=jTable.getSelectedRows().length-1; i>=0; i--) { // удаление в основной таблице
                            int modelIndex = jTable.convertRowIndexToModel(a[i]-1);
                            myTableModel.delete(modelIndex);
                            try { DBWorker.delete(modelIndex); } catch(SQLException ex) { ex.printStackTrace(); }
                        }
                    }
                }
            });

        popupMenu.add(copyItem);
        popupMenu.add(priceHistoryItem);
        popupMenu.addSeparator();
        popupMenu.add(deleteItem);
        jTable.setComponentPopupMenu(popupMenu);
        
        //эвент на дабл клик по прайсу
        jTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                int column = table.getSelectedColumn();
                int modelIndex = jTable.convertRowIndexToModel(jTable.getSelectedRow());
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 && column==2) {
                    try {
                        //HistoryWindow.newIcon(PriceParse.imgURL(myTableModel.data.getObj(table.getSelectedRow()).getURL()));
                        HistoryWindow.setFieldName(myTableModel.data.getObj(modelIndex).getID());
                        new HistoryWindow();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (mouseEvent.getClickCount() == 10 && table.getSelectedRow() != -1 && column==3) {
                        new MainClass();
                }
            }
        });
        
        
        //закрытие программы (сохранение)
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if(JOptionPane.showConfirmDialog(null, "Закрыть программу?", "Выход", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    if(added)
                        myTableModel.saveDefault();
                    System.exit(0);
                }
            }
        });

        //установка максимальной и минимальной ширины и центрирование текста
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        jTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        TableColumnModel columnModel = jTable.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(40);


        this.setMinimumSize(new Dimension(500,300));
        this.getContentPane();
        this.add(jScrollPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setSize(500, 500);
        //this.setBounds(X,Y,WIDTH,HEIGHT); // X и Y - положение на экране
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        myTableModel.loadDefault();
        HistoryWindow.loadDefault();
        myTableModel.refreshPrice();
    }

    static public void setTraining(int trn){
        training = trn;
    }

    static public int getTraining(){
        return training;
    }
}