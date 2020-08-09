import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*
 * Create the Student and Priorities classes here.
 */
class Student implements Comparable<Student> {
        private int id;
        private String name;
        private double cgpa;

        public Student(int id, String name, double cgpa) {
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getCgpa() {
            return cgpa;
        }

        @Override
        public int compareTo(Student o) {
            return java.util.Comparator.comparing(Student::getCgpa).reversed()
                    .thenComparing(Student::getName).reversed()
                    .thenComparing(Student::getId).reversed()
                    .compare(this, o);
        }
    }

    class Priorities {

        public List<Student> getStudents(List<String> events) {
            java.util.PriorityQueue<Student> priorityQueue = new java.util.PriorityQueue<>();
            events.forEach(event -> {
                String[] input = event.split("\\s");
                switch (input[0]) {
                    case "ENTER":
                        priorityQueue.add(new Student(Integer.parseInt(input[3]), input[1], Double.parseDouble(input[2])));
                        break;
                    case "SERVED":
                        priorityQueue.poll();
                }
            });

            List<Student> students = new ArrayList<>();
            while (!priorityQueue.isEmpty()) {
                students.add(priorityQueue.poll());
            }
            return students;
        }
    }


public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}