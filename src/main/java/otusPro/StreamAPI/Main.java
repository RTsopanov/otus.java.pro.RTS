package otusPro.StreamAPI;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskFilter taskFilter = new TaskFilter();


        System.out.println(taskFilter.getTaskSortToCondition("Done"));
        System.out.println();
        System.out.println(taskFilter.getTaskSortToId(1));
        System.out.println();
        System.out.println(taskFilter.getCondition());
        System.out.println();
        System.out.println(taskFilter.countTask("To Do"));

    }


}
