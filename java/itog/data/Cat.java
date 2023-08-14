package itog.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cat implements Pet{
    private String name;
    private String dateBirth;
    private List<String> commands;

    public Cat() {
        this("", "", new ArrayList<>());
    }

    public Cat(String name, String dateBirth, List<String>commands) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.commands = commands;
    }

    // Установить имя
    @Override
    public void setName(String name) {
        this.name = name;
    }

    // Получить имя
    @Override
    public String getName() {
        return this.name;
    }

    // Установить дату рождения
    @Override
    public void setDateBirth(String date) {
        this.dateBirth = date;
    }

    // Получить дату рождения
    @Override
    public String getDateBirth() {
        return this.dateBirth;
    }

    // Добавить команду
    @Override
    public void addCommand(String newCommand) {

        if (commands.stream().filter(x -> x.equals(newCommand)).findFirst().isEmpty()) {
            return;
        }
        commands.add(newCommand);
    }

    // Получить список команд
    @Override
    public List<String> getCommandList() {
        return commands;
    }

    // Получить количество команд
    @Override
    public int getCommandCount() {
        return commands.size();
    }

    public String toString(){
        return String.format("Кличка: %s;  Дата рождения: %s; Команды: %d: %s",
                getName(), getDateBirth(), getCommandCount(), getCommandList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
