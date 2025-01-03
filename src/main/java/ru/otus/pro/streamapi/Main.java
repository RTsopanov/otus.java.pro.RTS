package ru.otus.pro.streamapi;

public class Main {
    public static void main(String[] args) {
        TaskFilter taskFilter = new TaskFilter();


        System.out.println(taskFilter.getTasksByStatus("Done"));
        System.out.println();

        System.out.println(taskFilter.isTaskExist(1));
        System.out.println();

        System.out.println(taskFilter.getCon());
        System.out.println();

        System.out.println(taskFilter.countTask("To Do"));

    }


}
