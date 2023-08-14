package itog.services;

import itog.data.Pet;
import itog.data.Dog;
import itog.data.Cat;
import itog.data.Hamster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PetList<Pet> {
    private ArrayList<Pet> pets = new ArrayList<>();

    // Добавить животное в список
    public void addPet(Pet pet) {
        pets.add(pet);
    }

    // Получить список животных
    public ArrayList<Pet> getPets(){
        return pets;
    }

    // Получить список собак
    public List<Pet> getDogs() {
        return pets.stream().filter(x -> x instanceof Dog).collect(Collectors.toList());
    }

    // Получитьсписок кошек
    public List<Pet> getCats() {
        return pets.stream().filter(x -> x instanceof Cat).collect(Collectors.toList());
    }

    // Получить список хомяков
    public List<Pet> getHamsters() {
        return pets.stream().filter(x -> x instanceof Hamster).collect(Collectors.toList());
    }

    // Найти собаку по кличке
    public Dog findDog(String name) {
        List<Dog> dogs = (List<Dog>) this.getDogs();
        Dog dog = null;

        try {
            dog = dogs.stream().filter(c -> c.getName().equals(name)).findFirst().get();
        }
        catch (NoSuchElementException ex) {
            dog = null;
        }
        return dog;
    }

    // Найти кошку по кличке
    public Cat findCat(String name) {
        List<Cat> cats = (List<Cat>) this.getCats();
        Cat result = null;

        try {
            result = cats.stream()
                    .filter(c -> name.equals(c.getName()))
                    .findFirst().get();
                     //.filter(c -> c.getName().equals(name)).findFirst().get();
        }
        catch (NoSuchElementException ex) {
            result = null;
        }


        return result;
    }

    // Найти хомяка по кличке
    public Hamster findHamster(String name) {
        List<Hamster> hamsters = (List<Hamster>) this.getHamsters();
        Hamster hamster = null;

        try {
            hamster = hamsters.stream().filter(c -> c.getName().equals(name)).findFirst().get();
        }
        catch (NoSuchElementException ex) {
            hamster = null;
        }
        return hamster;
    }
}
