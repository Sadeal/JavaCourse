package UI;

import History.HBase;
import Table.MyTableHistory;
import Table.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class HistoryWindow extends JFrame {
    private JTable jTable;
    public static MyTableHistory myTableHistory = new MyTableHistory(new HBase());
    MyTableModel myTableModel;
    private TableRowSorter<TableModel> sorter;

    static JTextField fieldSearch = new JTextField(15);

    static String URL = "";

    public HistoryWindow() throws MalformedURLException {
        super("История цен продукта");

        jTable = new JTable(myTableHistory);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        /* добавление картинки
        ImageIcon ico = new ImageIcon(new URL(URL)); //Sets the image to be displayed as an icon

        Image image = ico.getImage(); // transform it
        Image newimg = image.getScaledInstance(320, 320,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ico = new ImageIcon(newimg);  // transform it back

        JLabel label = new JLabel(ico);
        this.add(label, BorderLayout.CENTER);
        */

        //fieldSearch.setText(myTableHistory.getName(jTable.getSelectedRow()));
        //sorter.setRowFilter(RowFilter.regexFilter("(?i)" + fieldSearch.getText()));

        //объявление сортировки для вывода истории только для того, что было выбрано
        sorter = new TableRowSorter<>(jTable.getModel());
        jTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(fieldSearch.getText(),0));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        jTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        jTable.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        TableColumnModel columnModel = jTable.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(30);

        this.setMinimumSize(new Dimension(730,300));
        this.getContentPane();
        this.add(jScrollPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void loadDefault(){
        myTableHistory.loadDefault();
    }

    public static void add(int id, String Date, String CurrPrice, String PrevPrice, String Name){
        myTableHistory.add(id, Date, CurrPrice, PrevPrice, Name);
    }

    public static void newIcon(String url) throws MalformedURLException {
        URL=url;
    }

    public static void setFieldName(int ID){
        fieldSearch.setText(Integer.toString(ID));
    }


}
