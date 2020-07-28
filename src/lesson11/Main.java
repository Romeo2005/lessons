package lesson11;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        task1();
        System.out.println("*****************************************************************");
        task2();
    }

    private static void task2() {
        LinkedHashMap<String, ArrayList<String>> numbers = new LinkedHashMap<>();
        numbers.put("Bob", new ArrayList<>(Arrays.asList("123", "456")));
        numbers.put("Sam", new ArrayList<>(Collections.singletonList("789")));
        numbers.put("Peter", new ArrayList<>(Collections.singletonList("321")));

        PhoneBook book = new PhoneBook(numbers);
        book.add("Peter", "987");
        book.add("John", "654");

        System.out.println(book.get("Bob"));
        System.out.println(book.get("Sam"));
        System.out.println(book.get("Peter"));
        System.out.println(book.get("John"));
    }

    private static void task1() {
        String[] wordsArray = new String[20];
        String[] uniqueWords = new String[]{"Hello", "Dog", "Cat", "Student", "Pilot"};

        for (int i = 0; i < wordsArray.length; i++)
            wordsArray[i] = uniqueWords[(int) (Math.random() * uniqueWords.length)];

        System.out.println("Full array: " + Arrays.toString(wordsArray));

        Set<String> wordsFounded = new HashSet<>(Arrays.asList(wordsArray));

        System.out.println("Unique words: " + wordsFounded);

        Map<String, Integer> repeatNumber = new HashMap<>();

        for (String str : wordsArray) {
            if (repeatNumber.get(str) != null)
                repeatNumber.replace(str, repeatNumber.get(str) + 1);
            else repeatNumber.put(str, 1);
        }

        System.out.println("Repeats founded: " + new ArrayList<>(repeatNumber.entrySet()));
    }


}
