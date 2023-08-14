package itog.data;

/**
 * Интерфейс Домашнее животное. Животное имеет кличку,
 * умеет выполнять определённые команды
 * */

import java.util.List;

public interface Pet {
    // Установить имя
    void setName(String name);
    // Получить имя
    String getName();
    // Установить дату рождения
    void setDateBirth(String date);
    // Получить дату рождения
    String getDateBirth();
    // Обучить животное команде
    void addCommand(String newCommand);
    //Получить список команд
    List<String> getCommandList();
    // Получить количество команд
    int getCommandCount();
}
