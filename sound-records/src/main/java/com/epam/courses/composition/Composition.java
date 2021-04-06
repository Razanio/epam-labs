package com.epam.courses.composition;

public class Composition extends Music {
    public Composition(String singer, String name, Genre genre, Double duration) {
        super(singer, name, genre, duration);
    }

    @Override
    public void display(int i) {
        System.out.printf("%s) Singer: %s, Name: %s, Genre: %s, Duration: %s;\n", i, super.getSinger(), super.getName(), super.getGenre(), super.getDuration());
    }

    @Override
    public String toString() {
        return "Singer: " + getSinger() + ", Name: " + getName()  + ", Genre: " + getGenre() + ", Duration: " + getDuration() + "\n";
    }
}
