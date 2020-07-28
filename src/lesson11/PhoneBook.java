package lesson11;

import java.util.*;

public class PhoneBook {
    private final LinkedHashMap<String, ArrayList<String>> Surname_Number;

    public PhoneBook(LinkedHashMap<String, ArrayList<String>> Number_Surname) {
        this.Surname_Number = Number_Surname;
    }

    public PhoneBook() {
        this(new LinkedHashMap<>());
    }

    public void add(String surname, String number) {
        if (Surname_Number.get(surname) == null)
            Surname_Number.put(surname, new ArrayList<>(Collections.singletonList(number)));
        else Surname_Number.get(surname).add(number);
    }

    public ArrayList<String> get(String surname) {
        return Surname_Number.get(surname);
    }
}
