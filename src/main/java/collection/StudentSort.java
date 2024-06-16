package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student implements Comparable<Student> {
    int age;
    String name;
    Student(int age, String name) {
        this.age =age;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        return this.age> o.getAge() ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
public class StudentSort {
    public static void main(String[] args) {
        // 2 way , Comparable and Comparator(Functional Interface)
//        Comparator<Student> com = new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                //1 (true) = swap
//                //0 (false) = not swap
//                return o1.getAge()> o2.getAge() ? 1 : -1;
//            }
//        };
        //Comparator also support Lambda expression
        Comparator<Student> com = (o1, o2) -> { return o1.getAge()> o2.getAge() ? 1 : -1; };
        List<Student> list = new ArrayList<>();
        list.add(new Student(21, "Bob"));
        list.add(new Student(11, "John"));
        list.add(new Student(17, "Peter"));
        list.add(new Student(19, "Tom"));
        Collections.sort(list, com);
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);
        // If only Collections.sort(list) its not work, because its not implement Comparable
    }
}
