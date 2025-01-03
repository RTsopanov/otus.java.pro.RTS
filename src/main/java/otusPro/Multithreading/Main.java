package otusPro.Multithreading;



public class Main {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(5); // Создаем пул из 5 потоков


        pool.execute(() -> System.out.println("--- Task 1 executed"));
        pool.execute(() -> System.out.println("*** Task 2 executed"));


        pool.shutdown();
    }
}
