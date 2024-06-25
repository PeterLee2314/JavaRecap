### JDK, JRE, JVM
#### JDK (Java Development Kit)
- software development env used for developing Java apps 
- it includes : JVM, JRE, Loader, Java compiler, Doc generator, Archive(jar), etc 

#### JRE (Java Runtime Env)
- software bundles (lib, class loader) that allows Java program to run (on JVM)

#### JVM (Java Virtual Machine)
- physical env to execute bytecode

### oop
#### Encapsulation
#### Inheritance 
**is/has concept eg (phone,pc,laptop IS computer) , (house has furniture, light)
#### Anonymous object (eg new A()) 
it only created on heap not stack because it didnt create variable eg int a; but simply a new A() object
Anonymous object : new A() // cant reuse
Referenced Object: A a = new A();

#### 2 parents problems (Ambiguity Problem):
when two parent have same function C->A , C->B (thats why Java not allow 2 parents extends)
C->B->A (thats work, because the missing function will find at B first, then A)

#### Overload (same method , diff variable)
#### Override (same method, diff code)

#### packages => folder (src)

#### Access Modifiers
Public, Private, Protected, Default
Same package have no error on not public variable
Assume src\A\A.java src\B.java src\Main.java, Main.java cant access (default) int marks on A.java but B.java
Default modifiers allow same package class to access
Protected allow same java file classes OR same package file to access (eg two classes inside same java file) OR subclass(child)

#### Polymorphism (many behaviour)
#### Compile Time Polymorphism (behaviour defined compile time)
eg Overloading add(int,int) add(float,float) add(int)
#### Runtime Polymorphism (behaviour defined runtime)
eg Overriding A(add())->B(add())
#### Dyanmic Method Dispatch (Polymorphism) (not sure which one to use before runtime)
B(child)->A(parent)  A obj = new B();  , B referenced as A, method on B will execute(show)
A obj = new A(); then obj = new B(); so the A method (show) execute then B method (show) execute
#### Downcast Upcast
Downcast refer A to B, refer float to int 
```double d = 4.5 int i = (int) d;```
Parent no idea child method exist, by upcast, Parent can use child method
Upcast , refer B to A , refer int to float (implicit)
```
A obj = (A) new B();  // its implicit (same as A obj = new B();)
obj.show2() // ERROR, still cant call because the reference A no idea B when not same method name
obj.show() // WORK, it works if B have show() too
// Downcast
B obj2 = (B) obj;
obj2.show2() // WORK, it downcast to B, because the reference is B now you can call B's method
```
#### Primitive vs Wrapper classes
Primitive (int, float, double)
Wrapper classes: Abstract (Object, Integer, String, Float, Double)
Auto-box Auto-unboxing concept 
eg int num = 7; Integer 2 = num; //Auto-box
Auto-boxing (store primitive value in an object)
eg int num3 = num2.intValue(); same as int num3 = num2; Auto-Unboxing (store object value inside primitive type)

Manual unboxing (store String into int)
```
String str = "12";
int num3 = Integer.parseInt(str);
```

#### Abstract
When no idea implementation during the class implementation, so left for it and let child to override it
Abstract method must override by child, and the classes is abstract if abstract method exist
Abstract Classes can't be created (only work if upcast) eg A obj = new B();
Abstract Class not necessarily have abstract method
Abstract method can be passed to future child, meaning some abstract can still be abstract method for child
Abstract Car(abstract drive & fly) -> Abstract Wagon(with drive) -> FlyCar(with fly)


#### Interface (Normal, Functional(SAM: Single Abstract Method), Marker(Blank Interface, update sth to compiler))
interface is for supporting multiple class in a method class 
eg Developer need computer, so if the method accept laptop, it cant accept desktop, all desktop and computer have same
method, thats why we need interface (Computer) and because computer is abstract we need interface than class

#### Functional Interface
In Java, everything have to write inside a class
However, it is tedious when function does not belong to any class
```
class HelloPrinter() {
　　public void hello() {
　　　　System.out.println("hello!");
　　}
}
// To call it
new HelloPrinter().hello();
```
So Functional Interface call it with interface or method reference(::)
```
@FunctionalInterface
interface Predicate<T> {
    public boolean test(T anyType) {
    
    }
} 
public static boolean startsWithS(String s) {
    return s.startsWith("S");
}
public class Main {
    public static void main(String[] args) {
        Predicate<String> sFilter = Main::startsWithS;  // method reference
        Predicate<String> sFilter = (s) -> {
            return s.startsWith("S"); 
        }
    }
}
```

####Marker Interface (For Serialization)
Serialization : store object A,B,C into harddrive , for recreate the object later (Deserialization)

####Error (Runtime, Compile, Logical)

####Exception(Runtime Error)

####--able (interface)
eg throwable, runnable, etc

#### Throwable (Checked:require try/catch block)
Child: Exception(Try/catch), Error(Cant try/catch)
Exception -> RuntimeException(Unchecked) , SQLException(Checked:Force), IOException(Checked:Force) -> ....
RuntimeException -> NullPointerException, ArithmeticException, etc

#### Throw vs Throws
Call catch block even not triggered by Exception 
```
// Outside Class
class CustomException extends Exception {
    public CustomException(String s) {
    
    }
}
// Inside Class
if(j==0) {
    throw new ArithmeticException("Here is custom Exception message")
    throw new CustomException("Hello");
}
...
catch(ArithmeticException e) {
    e output: java.lang.ArithmeticException: Here is custom Exception message
}
```
Throws (Throws the exception to who calling me)
C call the method of d,e and error that happen during d and e execution will go back to c and catch by c
```
c () {
    try {
        d();
        e();
    }
    catch() {
    
    }
}

d() throws Exception {
}
e() throws Exception {

}
```

####BufferedReader
``` 
// Old solution
InputStreamReader in = new InputStreamReader(System.in);
BufferedReader bf = new BufferedReader(in);
int num = Integer.parseInt(bf.readLine());
bf.close();

Scanner sc = new Scanner(System.in);
int num2 = sc.nextInt();
sc.close();
```
Resources is what we need to close (eg, connection, file, reader)

#### try, catch, finally
try catch  // valid
try catch finally // valid
try finally // valid
why try finally (with no catch?) : Because there is some case you dont want to catch in the current method but outside it
and you need some action eg close the scanner
Then you use try that take user input and finally close it
```
put the scanner inside try, it will close automatically
try(Scanner sc = new Scanner(System.in)){

}
```

#### Thread 
(run method must exist)
```
getPriority() // 1(least) to 10(highest) , 5(default)
A obj1 = new A();
obj1.start()
Thread.sleep(millis)
Thread.getPriority
// If want to make A subclass of Z, we can implements Runnable rather than extends Thread
Runnable obj1 = new A();
Thread t1 = new Thread(obj1);
t1.start();
// but now start is not working, make two Thread object (t1,t2) and t1.start() , t2.start()

// We can apply lambda / anonymous class on Runnable 
Runnable obj1 = new Runnable() {
    // Anonymous class : X x = new X() { } ;
    public void run() {....};
};

// Lambda 
Runnable obj1 = () -> {
    for(...){
        sout("xxx");
    };
};
```
####Race Condition
If two thread edit same variable, Race Condition happened
Mutation : value changeable (primitive, object)
Immutable (String)
####Thread Safe (only 1 thread work on 1 point)
t1.join(); // wait the t1 thread to finish execution (Sequence control)
t2.join()
However Race condition when t1,t2 execute same method
// Solution
// Synchronized Method
public synchronized void increment() {

}
// Synchronized code
synchronized () {

}
#### Thread State (New, Runnable, Running, Waiting, Dead)
New -> start() -> Runnable -> run() running on CPU -> Running  -> sleep(),wait() -> Waiting 
-> notify() -> Runnable
Running -> stop() -> Dead// Instability, Deprecated

#### Collection API (Collection, Collections)
Embedded classes (Data Structure eg Queue, LinkedList, Stack)
Collection : Interface
Collections : class
``` 
int nums[] = new int[5];

```
Collection -> List, Queue, Set 
List -> ArrayList, LinkedList
Queue -> Dequeue
Set -> HashSet(unsorted), LinkedHashSet, TreeSet(sorted)

Map not Collection API, but similar concept (HashMap, Map, LinkedHashMap, HashTable(syn) )

Collections class (Collections.sort())
Comparator (interface) vs Comparable (interface)
Comparator (Collections sort custom logic)
Comparable (Natural sort logic) 

eg Comparator: Collections.sort(a, com)
eg Comparable: Collections.sort(a)

#### Stream API (check stream\Stream, stream\Streams2)


#### jshell (similar as python in cmd)
/exit

#### Old compile vs Source Code Launcher(work with single file)
javac Demo.java
java Demo
Source Code Launcher: java Demo.java

#### LVTI (Local Variable Type Inference) : Type Inference (Only Allow local variables)
Type Inference eg (Lambda expression without express type)
Processing purpose : variable inside method
Instance : variable inside method (LVTI not allow)

```
var num = 8;
var list = new ArrayList<>();
```

#### Sealed Class vs Abstract Class, Final Class
Abstract Class(Base Class) define method and abstract method , then a Concrete class to override the abstract method
Sealed Class = Limited Class can inherit the class (Abstract + Final Concept)

#### Record Classes (hold data)
If Object is (1)Immutable and (2) only used for storage, we can create such Object by Record Classes
Generally, its difficult and tedious to create a class that store data (override equals, tostring and getter) [No setter]
Record Class is for Carrying Data, so its not changable in value once created object

#### JUnit5 (Unit Testing) vs Maven Test (Execute on surefire, not on IDE)
Maven Test must need to have maven-surefire-plugin plugin, all files in the test must start with
Test.java in order to work.

#### Abstract = Type perform Operation
Concept associated with Operation with Data => Abstract Data Type

#### JDBC (Java Database Connectivity)
DDL (Structure)
DML (Manipulation) // modification, so use executeUpdate (return int, return 4 mean 4 row affected)
DQL (Data Query Language) //fetching data, use executeQuery (return ResultSet)
TCL (Transaction Control Language)

#### DAO (Data Access Object) [Design Pattern]
Achieve Data fetching from a DAO class, which the DAO perform fetching or modification
#### Static Block & Instance Block
Class.forName() loading the class, so only trigger static block
abc a = new abc(); // it load static block and instance block together
```
class abc {
    static {
        
    }
    
    {
        System.out.println("Hello");
    }
}
```

#### Regular Expression (Regex)
Output simply match the number of match
```
. (just one instance of any character) eg Tom, Tam, Tim => T.m
+ (repeat 1 time above) eg Toom => To+m
* (repeat 0 time above) eg Tom, Tm => To*m => it true even o is not exist 
? (repeat 0 or 1 time)  eg Tom => T?m => True if T only have 1, or no T
\d (any 0~9) eg 123 => \d => output: 3, because 3 matching
\d+ eg 123,123,456 => output:2 , find longest match any no repeat (so 123 count only 1 time)
 
\w (any alphabet in 1 character) eg a~z, A~Z, 0-9 , eg \w+ will imply the longest character until space eg (cat is cute) => output (cat, is, cute)
{} (repeat any time) 
    for example, a => a , get all with a match , aaa => aa => it will exclute last a so output :(aa) 
    with {} , aaa => a{2} => ouput:(aa) , exactly same as above
    a{2,3} meaning match a with 2 or 3 time match  , eg aaaa,aa => a{2,3} => output aaa, aa
    a{2,} meaning a with 2 to unlimited match
    eg aaaa => a{2,} => output: aaaa
() priority calculate 
| OR eg ab,bc => ab => output: ab (1 output), with OR , ab,bc => ab|bc => ab,bc (2 output)
     eg ab, abc, bcc => (ab|bc)c => output: abc, bcc (2 output), because we first find ab or bc, then it match with c
[] match any character eg abcd => [aed] imply a or e or d=> a,d (2 match) 
    [a-c] mean a to c
[^] exclude , eg [^abc] any character exclude abc
\ literal imply the exactly meaning 
    eg $5.99 => $5.99 => cant match because the (.) and ($) imply instace of $ , THEREFORE , $5.99 => \$5\.99

^ start of the line
$ end of the line
Example:
Hello World Hello
Hello Hello
Hello World
Pattern: ^Hello , () imply output, only start of the line match
(Hello) World Hello
(Hello) Hello
(Hello) World
Pattern: Hello$ , end with Hello
Hello World (Hello)
Hello (Hello)
Hello World
Example 2 , H.*d$ , it starts with H and repeat any character, BUT END OF LINE
Hello World Hello
Hello Hello
(Hello World)
(Hello Worldd)
only last (Hello World) is matched, because the (d$) expression, (Hello World Hello) is not end with (d)

\b word boundary (start or end with boundary) 
Example (start boundary):
Jan
Jane
Janet
Jannne
123Jane
Pattern: \bJan
(Jan)
(Jan)e
(Jan)et
(Jan)nne
123Jane (not match because its not start with Jan in every word)
Example (start and end boundary):
Pattern: \bJan\b
(Jan) is the only output, because it arround with space (start and endboundary)


() Not only priority but also Capturing Group , Save the Match item and use it alter
Example:
The dog is big.
The dog is fat.
The cat is thin.
Wow, The cat is cute.
Pattern: The \w+ is \w+\.
(The dog is big.)
(The dog is fat.)
(The cat is thin.)
Wow, (The cat is cute.)

Pattern: (The \w+ is \w+\.) , the output is captured and can use later
Example:
In VS Code, Ctrl F and open Regex ($1, $2 will be the capture group result)
Search: (The \w+ is) (\w+\.) , we now have 2 capture group , $1 and $2 can be repeatedly use
Replace: $1 tiny.
Result:
The dog is tiny.
The dog is tiny.
The cat is tiny.
Wow, The cat is tiny. // Wow will not disappear

(?:) Not use Capture Group eg (?:big|fat|thin) , it will consider () as priority only but not capture group
```

#### Regex Pratice
##### EX1 (find number)
Call us at (555) 123-4567 or (123) 986-6543 , 
Answer : \(\d{3}\) \d{3}-\d{4}
##### EX2 (find email) (the email dont have special symbol except @ and .) 
[a-zA-z0-9.]+@[a-zA-z0-9]+(?:\.[a-zA-Z0-9]+){1,} (only . and @ allow) so first 3 row is searchable but not last one
Contact us at abc@example.com.hk or admin@domain.org..
James.ku@gmail.com
james.ku@abc.org
Jake-kk@gmail.com
abc@not123.com

(the email  have special symbol for - and .)  Step:
allow specifal symbol (but 1 @ only )
[\w.-]+  (it allow ONE character with any alphabet AND '-' , repeat ONE or above time)
(abc)@(not123.com)

Answer: [\w.-]+@[\w.]+\.[a-zA-Z]{2,63}  
why {2,63} because the top domain (com, org ,hk) is at most 63, and [a-zA-Z] prevent . symbol eg(admin@domain.org..)

##### EX3 (change date format from YYYY-MM-DD to MM-DD-YYYY)
2024-06-08
2024-12-25
2024-01-01
to
06/08/2024
12/25/2024
01/01/2024
answer : (\d{4})-(\d{2})-(\d{2})  , replace: $2/$3/$1

How java perform Regex 
case 1:(true,false) by find() , print it to get pattern, region and last match
case 2: matches() and group() for capture group
without capture group () in Pattern, it will only have toString result
```
java.util.regex.Matcher[pattern=[\\w]3Schools region=0,16 lastmatch=W3Schools]
```

```
// Case 1:
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher("Visit W3Schools!");
    boolean matchFound = matcher.find();
    if(matchFound) {
      System.out.println("Match found");
    } else {
      System.out.println("Match not found");
    }
  }
}
// output: java.util.regex.Matcher[pattern=[\w]3Schools region=0,27 lastmatch=W3Schools]
//          Match found
 
// Case 2:
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) throws Exception {
    Pattern p = Pattern.compile("(\\d{1,3}).(\\d{1,3}).(\\d{1,3}).(\\d{1,3})"); 
    Matcher m = p.matcher("127.0.0.1"); 
    if (m.matches()) {   
      System.out.println(m.group(0)); //entire group (127.0.0.1)
      System.out.println(m.group(1));  //127
      System.out.println(m.group(2));  //0
      System.out.println(m.group(1));  //0
      System.out.println(m.group(2));  //1
    }
  }
}



```