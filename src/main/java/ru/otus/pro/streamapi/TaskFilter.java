package ru.otus.pro.streamapi;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TaskFilter {


    public class Task {
        private int id;
        private String name;
        private Condition condition;


        public Task(int id, String name, Condition condition) {
            this.id = id;
            this.name = name;
            this.condition = condition;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Condition getCondition() {
            return condition;
        }

        @Override
        public String toString() {
            return "\nTask{" +
                    "id = " + id +
                    ", name = " + name  + ", condition = " +  condition + "}";
        }
    }

        public List<Task> getTaskSortToCondition(List<Task> tasks, Condition condition) {
            return tasks
                    .stream()
                    .filter(task -> task.getCondition().equals(condition))
                    .toList();
        }

    public Stream<Task> createTask() {
        return Stream.of(
                new Task(1, "task1", Condition.TO_DO),
                new Task(2, "task2", Condition.DONE),
                new Task(3, "task3", Condition.DONE),
                new Task(4, "task4", Condition.IN_REVIEW),
                new Task(5, "task5", Condition.TO_DO)
        );
    }


        public List<Task> getTasksByStatus(String con) {
            return createTask()
                    .filter(task -> task.getCondition().getDescription().equals(con))
                    .toList();
        }


        public boolean isTaskExist(int id) {
            return createTask()
                    .anyMatch(task -> task.getId() == id);
        }


        public List<Task> getCon() {
            return createTask()
                    .sorted(Comparator.comparing(Task::getCondition))
                    .toList();
        }

        public int countTask(String con) {
            int count = (int) createTask()
                    .filter(task -> task.getCondition().getDescription().equals(con))
                    .count();
            return count;
        }


}




