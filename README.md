Программа, для мониторинга (периодической проверки) цены и хранения истории сохранения цен.
  В программе предоставленно на выбор 4 магазина: Wildberries, Ozon, Market.Yandex, Citilink (yandex маркет работает некоректно, в связи с ограничениями сайта)

Что необходимо, чтобы запустить программу (jar файл)
1. Установить:
     1 Java ([java.com](https://www.java.com/ru/download/))
     2 JDK 18 ([oracle.com](https://www.oracle.com/java/technologies/downloads/#jdk18-windows))
     3 JRE 8 ([oracle.com](https://www.oracle.com/java/technologies/downloads/#jre8-windows))
2. Запустить [jar файл (github)](https://github.com/Sadeal/Market-Parser/blob/main/Course.jar)
4. Чтобы понять, как пользоваться программой откройте: Утилиты -> Пройти обучение


Известные проблемы (issues):
  1. Фильтр по № в главной таблице фильтрует строку, поэтому номер 10, будет считаться меньшим, чем 2
  2. Показ ID в таблице истории (т.к. фильтр по таблице, а не по ArrayList)
  3. not recognize

![Основное окно](https://github.com/Sadeal/JavaCourse/blob/main/img/main.png)
![Окно истории](https://github.com/Sadeal/JavaCourse/blob/main/img/history.png)
