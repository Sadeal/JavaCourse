## Программа, для мониторинга (периодической проверки) цены и хранения истории цен. ##

В программе предоставленно на выбор 4 магазина: Wildberries, Ozon, Market.Yandex, Citilink (yandex маркет работает некоректно, в связи с ограничениями сайта)

-------------------------
#### Чтобы понять, как пользоваться программой откройте: Утилиты -> Пройти обучение ####

-------------------------

### Что необходимо, чтобы запустить программу ###

#### Запуск jar файла: ####

1. Установить:
    1) Java ([java.com](https://www.java.com/ru/download/))
    2) JDK 18 ([oracle.com](https://www.oracle.com/java/technologies/downloads/#jdk18-windows))
    3) JRE 8 ([oracle.com](https://www.oracle.com/java/technologies/downloads/#jre8-windows))

2. Запустить [jar файл (github)](https://github.com/Sadeal/Market-Parser/blob/main/etc/Course.jar)

#### Запуск exe файла: ####

1. Установить Java ([java.com](https://www.java.com/ru/download/)) и JRE 8 ([oracle.com](https://www.oracle.com/java/technologies/downloads/#jre8-windows))
2. Запустить [exe файл (github)](https://github.com/Sadeal/Market-Parser/blob/main/etc/MarketParser.exe)

-------------------------

### Известные проблемы (issues): ###

  1. Фильтр по № в главной таблице фильтрует строку, поэтому номер 10, будет считаться меньшим, чем 2
  2. Показ ID в таблице истории (т.к. фильтр по таблице, а не по ArrayList)
-------------------------
### Изображения программы ###

![Основное окно](https://github.com/Sadeal/JavaCourse/blob/main/img/mainwindow.png)
![Окно добавления](https://github.com/Sadeal/JavaCourse/blob/main/img/addwindow.png)
![Окно истории](https://github.com/Sadeal/JavaCourse/blob/main/img/historywindow.png)
