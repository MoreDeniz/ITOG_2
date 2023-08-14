package itog.controller;

import itog.data.Dog;
import itog.data.Cat;
import itog.data.Hamster;
import itog.services.PetList;
import itog.services.PetsCounter;
import itog.utils.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PetController {
    private final PetList<Object> petsList = new PetList<>();
    private final UI ui = new UI();

    // главное меню
    private final Map<String, String> menuMain = new HashMap<String, String>() {{
        put("1", "Список животных");
        put("2", "Количество животных");
        put("3", "Добавить животное");
        put("4", "Показать команды");
        put("5", "Добавить команду");
        put("0", "Выход");
    }};

    private final Map<String, String> menuPet = new HashMap<>() {{
        put("1", "Собака");
        put("2", "Кошка");
        put("3", "Хомяк");
        put("4", "Отмена");
    }};

    private final Map<String, String> menuYesNo = new HashMap<>() {{
        put("1", "ДА");
        put("0", "НЕТ");
    }};

    private enum PETS {DOG, CAT, HAMSTER};

    public void Run() throws Exception {
        String menu;
        do {
            menu = getOperation();

            switch (menu) {
                case "11" -> showPets(PETS.DOG);
                case "12" -> showPets(PETS.CAT);
                case "13" -> showPets(PETS.HAMSTER);
                case "2" -> showCountPets();
                case "31" -> addPet(PETS.DOG);
                case "32" -> addPet(PETS.CAT);
                case "33" -> addPet(PETS.HAMSTER);
                case "41" -> showCommands(PETS.DOG);
                case "42" -> showCommands(PETS.CAT);
                case "43" -> showCommands(PETS.HAMSTER);
                case "51" -> addCommand(PETS.DOG);
                case "52" -> addCommand(PETS.CAT);
                case "53" -> addCommand(PETS.HAMSTER);
            }
        } while (!(menu.isEmpty() || menu.equals("0")));
    }

    // Список животных
    private void showPets(PETS pet){
        List<Object> pets = null;

        switch (pet) {
            case DOG -> pets = petsList.getDogs();
            case CAT -> pets = petsList.getCats();
            case HAMSTER -> pets = petsList.getHamsters();
        }

        for (Object o : pets) {
            //logger.info(o.toString());
            System.out.println(o.toString());
        }
    }

    // Количество животных
    private void showCountPets() throws Exception {
        try(PetsCounter counter = new PetsCounter()) {
            System.out.print("Количество животных: ");
            System.out.println(counter.getCount().toString());
        }
    }

    // Добавить животное
    private void addPet(PETS pet) throws Exception {

        try(PetsCounter counter = new PetsCounter()) {
            counter.add();
        }

        String name = ui.getString("Кличка: ");
        String date = ui.getString("Дата рождения: ");

        List<String> commands = new ArrayList<>();
        System.out.println("Добавить команду?");
        String menu = ui.myMenu(menuYesNo);
        while (menu.equals("1")) {
            String command = ui.getString("Команда: ");
            commands.add(command);
            System.out.println("Добавить команду?");
            menu = ui.myMenu(menuYesNo);
        }

        switch (pet) {
            case DOG -> petsList.addPet(new Dog(name, date, commands));
            case CAT -> petsList.addPet(new Cat(name, date, commands));
            case HAMSTER -> petsList.addPet(new Hamster(name, date, commands));
        }
    }

    // Показать команды
    private void showCommands(PETS pet) {
        String name = ui.getString("Кличка: ");

        Object o = null;

        switch (pet) {
            case DOG -> o = petsList.findDog(name);
            case CAT -> o = petsList.findCat(name);
            case HAMSTER -> o = petsList.findHamster(name);
        }

        if (o == null) {
            System.out.println("Животного с такой кличкой нет");
            return;
        }

        List<String> commands = null;

        switch (pet) {
            case DOG -> commands = ((Dog)o).getCommandList();
            case CAT -> commands = ((Cat)o).getCommandList();
            case HAMSTER -> commands = ((Hamster)o).getCommandList();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String c : commands) {
            stringBuilder.append(c).append(", ");
        }

        System.out.println(stringBuilder.toString());
    }

    // Добавить команду
    private void addCommand(PETS pet) {
        String name = ui.getString("Кличка: ");
        Object obj = null;

        switch (pet) {
            case DOG -> obj = petsList.findDog(name);
            case CAT -> obj = petsList.findCat(name);
            case HAMSTER -> obj = petsList.findHamster(name);
        }

        if (obj == null) {
            System.out.println("Животного с такой кличкой нет");
            return;
        } else {
        String command  = ui.getString("Новая команда: ");

            switch (pet) {
                case DOG -> ((Dog)obj).addCommand(command);
                case CAT -> ((Cat)obj).addCommand(command);
                case HAMSTER -> ((Hamster)obj).addCommand(command);
            }
        }

    }

    // Выбрать пункт меню
    private String getOperation() {
        String menu = ui.myMenu(menuMain);
        if (!menu.isEmpty() && !menu.equals("0") && !menu.equals("2")) {
            menu += ui.myMenu(menuPet);
        }
        return menu;
    }
}
