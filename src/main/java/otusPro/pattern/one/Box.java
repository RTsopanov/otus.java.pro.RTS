package otusPro.pattern.one;


import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;




public final class Box {
    private final Matryoshka red;
    private final Matryoshka green;
    private final Matryoshka blue;
    private final Matryoshka magenta;

    public Box() {
        red = new Matryoshka("red");
        green = new Matryoshka("green");
        blue = new Matryoshka("blue");
        magenta = new Matryoshka("magenta");
    }

    // Итератор для последовательного перебора по уровню
    public Iterator<String> getSmallFirstIterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < 10;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                String result = red.getItems().get(index) + ", " +
                        green.getItems().get(index) + ", " +
                        blue.getItems().get(index) + ", " +
                        magenta.getItems().get(index);
                index++;
                return result;
            }
        };
    }

    // Итератор для перебора по цвету
    public Iterator<String> getColorFirstIterator() {
        return new Iterator<>() {
            private int colorIndex = 0;
            private int partIndex = 0;

            @Override
            public boolean hasNext() {
                return colorIndex < 4 && partIndex < 10;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                List<String> currentList;
                switch (colorIndex) {
                    case 0: currentList = red.getItems(); break;
                    case 1: currentList = green.getItems(); break;
                    case 2: currentList = blue.getItems(); break;
                    case 3: currentList = magenta.getItems(); break;
                    default: throw new IllegalStateException("Unexpected color index: " + colorIndex);
                }
                String result = currentList.get(partIndex);
                if (++colorIndex == 4) {
                    colorIndex = 0;
                    partIndex++;
                }
                return result;
            }
        };
    }
}
