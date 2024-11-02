package otusPro.StreamAPI;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskFilter {


    public class Task{
        private int id;
        private String name;
        private String condition;

        public Task(int id, String name, String condition) {
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

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", condition='" + condition + '\'' +
                    '}';
        }
    }

    public Stream<Task> createTask() {
        return Stream.of(
                new Task(1, "task1", "To Do"),
                new Task(2, "task2", "Done"),
                new Task(3, "task3", "Done"),
                new Task(4, "task4", "In review"),
                new Task(5, "task5", "To Do")
        );
    }

    public List<Task> getTaskSortToCondition(String con) {
        return createTask()
                .filter(task -> task.getCondition().equals(con))
                . collect(Collectors.toList());
    }


    public boolean getTaskSortToId(int id) {
        return createTask()
                .anyMatch(task -> task.getId() == id);
    }

    public String getCondition(){
        return createTask()
                .map(Task::getCondition)
                .sorted()
                .toList().toString();
    }

    public String countTask(String con){
        String string = "Количество задач: " + (int)createTask()
                .filter(task -> task.getCondition().equals(con)).count();
        return string;
    }

}




