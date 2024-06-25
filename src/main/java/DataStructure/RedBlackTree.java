package DataStructure;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;
// treemap is ordered , TreeMap use RedBlackTree
public class RedBlackTree {
        public static void main(String[] args) {
            TreeMap<Person, Integer> map = new TreeMap<>(Comparator.comparingInt(o -> o.age));
            map.put(new Person("John", "Smith", 17), 0);
            map.put(new Person("Ivan", "Petrenko", 65), 0);
            map.put(new Person("Pedro", "Escobar", 32), 0);
            map.put(new Person("Radion", "Pyatkin", 14), 0);
            map.put(new Person("Sergey", "Vashkevich", 19), 0);
            // the map is ordered by age
            NavigableSet<Person> navigableSet = map.navigableKeySet();
            System.out.println(navigableSet);

            Person firstAdultPerson = map.navigableKeySet().stream().filter(person -> person.age>18).findFirst().get();
            System.out.println(firstAdultPerson);  // Person{nickName='Sergey', lastName='Vashkevich', age=19} we get the first result in the stream filter
            // headMap(key, false=exclusive true = inclusive)
            Map<Person, Integer> youngPeopleMap = map.headMap(firstAdultPerson, false); // we get everything before firstAdultPerson which are the young one
            Map<Person, Integer> adultPeopleMap = map.tailMap(firstAdultPerson, true); // we get from first to last in tailMap
            System.out.println(youngPeopleMap);
            //{Person{nickName='Radion', lastName='Pyatkin', age=14}=0, Person{nickName='John', lastName='Smith', age=17}=0}
            System.out.println(adultPeopleMap);
            //{Person{nickName='Sergey', lastName='Vashkevich', age=19}=0, Person{nickName='Pedro', lastName='Escobar', age=32}=0, Person{nickName='Ivan', lastName='Petrenko', age=65}=0}

            //showAdvertisementToYoung(youngPeopleMap);
            //showAdvertisementToAdult(adultPeopleMap);
        }

}

class Person {
    public String nickName,lastName;
    int age;
    Person(String nickName, String lastName, int age) {
       this.nickName = nickName;
       this.lastName = lastName;
       this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nickName='" + nickName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
