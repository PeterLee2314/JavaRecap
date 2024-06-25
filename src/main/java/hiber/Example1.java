package hiber;
// One To Many & Many To One (assume getter, setter already defined)
/*
@Entity
public class Laptop {
    @Id
    private int id;
    private String name;
    @ManyToOne
    private Student stud;  // with this line and mappedBy=stud on student
    // , it will have extra column but not extra table
}

@Entity
public class Student {
    @Id
    private int id;
    private String name;
    private int marks;
    // By default, OneToMany responsible to create new table , with mappedBy=stud, the Laptop responsible for handle the table or column creation
    @OneToMany(mappedBy="stud")
    private List<Laptop> laptops;
}
 */


// Many To Many

/*
@Entity
public class Laptop {
    @Id
    private int id;
    private String name;
    @ManyToMany
    private List<Student> studs;
}

@Entity
public class Student {
    @Id
    private int id;
    private String name;
    private int marks;
    @ManyToMany(mappedBy="studs")
    private List<Laptop> laptops;
}

 */