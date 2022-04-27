package Data;

import UI.MainWindow;

import javax.swing.*;

public class Training extends JFrame{

    public void start(){
        if(MainWindow.getTraining()==0) {
            JOptionPane.showMessageDialog(null, "Нажмите кнопку добавить, чтобы начать работу");
            MainWindow.setTraining(1);
        }
    }

    public void add(){
        if(MainWindow.getTraining()==1) {
            JOptionPane.showMessageDialog(null, "В поле 'URL' введите ссылку на товар\n Назовите товар, если желаете, в поле 'Имя'\nЧтобы добавить несколько товаров поочерёдно, поставьте галочку у параметра 'Несколько'");
            MainWindow.setTraining(2);
        }
    }

    public void deleting(){
        if(MainWindow.getTraining()==2) {
            JOptionPane.showMessageDialog(null, "Выберите строку (добавленный вами товар) и нажмите кнопку удалить, чтобы удалить его (попробуйте, для продолжения обучения)");
            MainWindow.setTraining(3);
        }
    }

    public void more(){
        if(MainWindow.getTraining()==3) {
            JOptionPane.showMessageDialog(null, "Чтобы посмотреть история цен на какой-либо товар (дату изменения, прошлая цена, и цена после изменеия) кликните дважды по колонке 'Цена' напротив желаемого товара");
            JOptionPane.showMessageDialog(null, "Чтобы импортировать или сохранить имеющуюся базу из файла, необходимо зайти на панели меню в 'Файл', и нажать 'Загрузить' или 'Сохранить как...'");
            JOptionPane.showMessageDialog(null, "Чтобы удалить несколько элементов из таблицы, выделяйте их поочерёдно через Ctrl и нажмите кнопку удалить, или правой кнопкой мыши и в контекстном меню выберите 'Удалить'");
            MainWindow.setTraining(-1);
        }
    }
}
