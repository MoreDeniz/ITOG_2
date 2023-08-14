package itog.utils;

import java.util.Map;
import java.util.Scanner;

public class UI {
    public String myMenu(Map<String, String> menu) {
        Scanner scanner = new Scanner(System.in);
        String answer;

        for (String s : menu.keySet()) {
            System.out.println(s + " - " + menu.get(s));
        }
        System.out.println("> ");

        answer = scanner.next();

        if (!menu.containsKey(answer)) {
            answer = "";
        }
        return answer;
    }

    public int getInt(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(message);;
        return scanner.nextInt();
    }

    public String getString(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(message);;
        return scanner.next();
    }
}
