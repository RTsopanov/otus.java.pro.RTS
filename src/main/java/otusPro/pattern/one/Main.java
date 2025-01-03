package otusPro.pattern.one;


import java.util.Iterator;


public class Main {
    public static void main(String[] args) {


        Box box = new Box();

        Iterator<String> smallFirstIterator = box.getSmallFirstIterator();
        while (smallFirstIterator.hasNext()) {
            System.out.println(smallFirstIterator.next());
        }

        System.out.println("-----");

        Iterator<String> colorFirstIterator = box.getColorFirstIterator();
        while (colorFirstIterator.hasNext()) {
            System.out.println(colorFirstIterator.next());
        }
    }
}