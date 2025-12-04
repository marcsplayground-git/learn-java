package com.personalplayground.streams;

import java.util.List;

public class PersonWithHobbies {

    private final String name;
    private final List<String> hobbies;

    public PersonWithHobbies(String name, List<String> hobbies) {
        this.name = name;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    @Override
    public String toString() {
        return "PersonWithHobbies{name='" + name + "', hobbies=" + hobbies + "}";
    }
}
