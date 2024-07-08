### Complete Spring Boot
#### Modules Design

##### Spring web flow, Spring MVC, Spring ORM

#### IOC (Inversion of Control Container)
Responsible for managing life cycle of object, increase configurability
IOC use DI pattern to provide object reference during runtime

#### AOP (Aspect Oriented Programming)
more modularity to the cross cutting concerns which are function that across the application
eg logging, cache, security, management

#### DAF (Data Access Framework)
eg jdbc, hibernate, JPA
resource managing, exception handling, resource wrapping

#### Spring MVC Framework
request based framework, create customized MVC, suits their needs
Core component => Dispatcher Servlet , handle user request to controller
Then controller process the request and provide information for user

#### Spring Bean
An Object that is managed by the Spring Framework
Spring create these Bean manager their life cycle, organize their denpendencies with other bean
eg instantiation, configuration, wiring
Spring Bean can be configured by XML, Annotation, Java code

#### Object LifeCycle  , Bean managed by Spring Container
How & how it born
How it behave
When & How it die

#### Step of Spring
Program Run -> Spring Container started -> create instance and inject dependencies -> destroy bean when container close

### Annotation
#### @Configuration
declare a class as full configuration
CLASS MUST BE NON-FINAl & PUBLIC

#### @Bean
declare bean configuration inside configuration class
Method MUST BE non-final & non-private (allow public, protected, package-private aka default)
eg 
```
@Configuration
public class AppConfig {
    @Bean
    public PaymentService paymentService(
        AccountRepository accountRepository // it is dependency
    )
    return new PaymentServiceImpl(accountRepository);
    
    @Bean
    public PaymentService paymentService(
    )
    return new PaymentServiceImpl(accountRepository()); 
    
    // accountRepositroy is the dependency
}
```
@Configuration and @Bean, Spring will create a Bean that name PaymentService paymentService = return Object

By @Bean, Spring will know which to execute first eg multiple Bean
Spring know B need A, C need B 
```
@Bean
    return new C(B) // last
   
@Bean return new B(A) // next

@Bean return new A // first 
```

#### @Component
mark class as Component, meaning make this class as a Spring Bean

#### @Autowired (Constrcutor-dependency injection)
automatically , by injecting constructor parameters

#### Component VS Bean
1. @Component
public class DemoClass 
It will create a Reference, DemoClass democlass = new DemoClass()
2. @Bean
public Printer myPrinter() {return new HpPrinter}
It will create a reference too, Printer myPrinter = new HpPrinter()

#### Component + Autowired (aka Dependency Injection) (Class A have object B injected by Spring)
when the class need Bean, it will also become Component and add @Autowired to have linking between them
So the DemoClass will instantiated on ShowDemo inside private Printer automatically
```
@Component
public class ShowDemo {
    @Autowired
    private DemoClass{建議使用介面}
}

@Component 
public class Printer {
    xxxx 
}
```
#### Qualifier
when two child (HpPrinter, CanonPrinter) implements Printer
@Autowired 
private Printer (this case, will error)
THEREFORE
```
1. variable
@Autowired
@Qualifier("hpPrinter") // first letter lowercase
private Printer // valid

2. parameter
@Qualifier("hpPrinter") Printer printer
3. method
@Bean
@Qualifier("hpPrinter")
public Printer hpPrinter() {
    return new hpPrinter();
}
4. use @Primary instead of Qualifier
```



#### 可以使用@Configuration+@Bean的方式來取代@Component

#### 4 Type of Dependency Injection
1. Constuctor Injection
2. Field Injection
3. Configuration Method
4. Setter Method Injection
#### Constructor Injection
create Constructor and then pass parameter and set the value (no new keyword need to use)
```
public class JdbcRepository implements AccountRepository {
    private final Datasource datasource;
    public JdbcRepository(Datasource datasource) {
        this.datasource = datasource
    }
}
```
#### Field Injection (Discourage, complex in testing)
```
@Autowired
private AccountRepository accountRepository
```
#### Method Injection (Configuration Method)
allow setting many dependencies by 1 method
```
@Autowired
public void configureClass(A a, B b) {

}
```
#### Setter Injection (follow Java name invention)
use method name setXXX, setXXX for Setter Injection
```
@Autowired
public void setAccountRepository(AccountRepository accountRepository) {

}
```
#### Constructor Injection vs Setter Injection
USE Constructor Injection for Mandatory dependencies
USE Setter Injection or Configuration Method for optional dependencies
Why?
because Constructor Ensure required dependencies is not NULL, also the components are always return to client
in fully initalized state, also good to note if constructor with too many parameter, need to refactor it
While for Setter
it is good for class amenable to reconfiguration or re-injection later
#### Bean Scope (Bean scope default is Singleton)
determine life cycle of Spring Bean
##### Bean Scope (all uppercase)
1. Singleton
2. Prototype (new instance created each time) eg user info that cant share
3. Request (only valid in the context of web in single HTTP request, created new every new HTTP request)
4. Session (only valid in web HTTP session, new Session have new Bean)
5. Application (only valid in web in Servlet Context, so scoped at Application level)
6. Websocket (valid in web socket , The bean scoped at web socket life cycle)
Two way of implement, @Scope("singleton") OR @Name+Scope eg @SessionScope, @PrototypeScope

#### Environment Abstraction
to decouple application code from env wit the support of bean eg local, dev, cloud 
proprties for external sources eg database configure, crendtials 
Enviornment is injectable 
```
To access the Environment Variables from the application.properties file, we have one annotation that is @Autowired.

@Autowried
final Environment environment
@Bean
public PaymentService paymentService() {
    var profile = Profiles.of("cloud") 
    var isOkay = this.environment.acceptsProfiles(profile);
    this.environment.getProperty("data.driver");
    return ..
}
```
#### Bean Profiles
named logical grouping, useful when bean should be active or registered and used in certain environment
eg configuration in production, development, want certain bean only work in development
```
work on class and method , so that only cloud profile will work 
@Service
@Profile("cloud")

@Configuration
@Profile("cloud")

@Configuration 
public class xxx

    @Bean
    @Profile("cloud")


// How to activate the profile
BY CODE  (AnnotationConfigApplicationContext
OR BY File
yaml 
spring:
    profiles:
        active:cloud
proprties:
spring.profiles.active = cloud
```
#### @Value (value into variable in class)
Value can come from yaml,proprties or hardcode , also dynamic expression , also system proprties
```
@Configuration
@PropertySource("classpath:database.proprties")
class A {
    @Value("{jdbc.url}")
    private String url;
}

@Value("#{systemProperties['user.region']}") // system properties
```
#### Best Pratices (SPLIT CONFIGURATION)
split configuration classes
import configuration @Import
```
@Configuration
class ServiceConfig {
    @Bean 
    public PaymentService
}
@Configuration
class RepoConfig {
    @Bean 
    public RepoXXX
}

@Configuration
@Import({ServiceConfig.class, RepoConfig.class})
class XXX {
    @Bean
    public DataSource ....
}
```
#### Why Spring Boot?
- standalone app
- embeded server
- starters
- auto configuration
- production ready feature (metric, health check)
- no XML configuration

#### @Bean on method, @Component on class
```
var ctx = SpringApplication.run(DemoApplication.class, args);
Hello hello = ctx.getBean("Hello", Hello.class);

@Bean
public Hello createHelloClass() {
    return new Hello();
}
```
After @Component, we don't need @Bean on createHelloClass 
```
Hello hello = ctx.getBean(Hello.class);
```
@Component, @Service, @Repository, all create the class as Spring Bean

#### @Config/ Configuration file (use to store @Bean)  (create Class Object)
with @Configuration, Spring will scan the class first before execution
Three way for getBean, getBean(Hello.Class) , getBean(Bean Method name, Hello.class)  , getBean(Bean name, Hello.class) 
in this case is getBean("createHelloClass", Hello.class)
OR @Bean("createHello") ->  getBean("createHello", Hello.class)

#### @Service , use other service / bean inside every method  (Use Class Object (by Dependency Injection using @Autowired))
all Object store reference should be "private final" when using Constructor
As for @Autowired on Object, should be "private" only
@Autowired on Object OR on Constructor
Reminder: When Start, the Spring create Bean for looking @Component class or @Bean method
then when there is service.java using @Autowired, it will try to find where the Bean it need inside the method OR variable
then automatically injected
Reminder 2: we no longer need @Autowired on Constructor Injection, Spring will try to inject anything injectable
```
//MyFirstService.java
@Service
public class MyFirstService {

    private final MyFirstClass myFirstClass;

    public MyFirstService(MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    public String tellAStory() {
        return "The bean is saying : " + myFirstClass.sayHello() ;
    }
}
```
#### 2 Bean (Primary, Secondary)
when two method return the same object with @Bean annotation, Dependency Injection will not work, too ambiguous
use @Qualifier under @Bean to change Bean name
then add @Qualifier after the parameter inside Service (parameter inside METHOD, not variable!)
OR
use @Primary under @Bean

```
// Qualifier declared during Config method
public MyFirstService(
       @Qualifier("Bean1") MyFirstClass myFirstClass
) {
    this.myFirstClass = myFirstClass;
}
```
#### Qualifier by default use @Bean method name
@Qualifier("firstBean")

#### Method Injection (allow multiple Bean injection)
```
@Autowired
public void initBean(@Qualifier("Bean1") MyFirstClass myFirstClass) {
    this.myFirstClass = myFirstClass;
}
```
#### Setter Injection
```    
@Autowired
    public void setMyFirstClass(@Qualifier("Bean1") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
}
```

#### Injection for Existing Spring Class
do as above, just simply make @Autowired and setter injection/method injection/ constructor injection
because the Spring class we can see in document already @Service, @Configuration OR @Component OR @Bean

#### Read Environment Property (Environement OR @Value and @PropertySource)
java.version, os.name, custom properties name
By
1. private Environment environment;
2. @Annotation eg @Value(${}) for custom name, #{} for env 
3. If in another properties file, we need to locate it first BY @PropertySource("classpath:custom.properties")
custom.properties is the file name, classpath: is a must
@PropertySources take arrays of @PropertySource value
```
@PropertySources({
        @PropertySource("classpath:custom.properties"),
        @PropertySource("classpath:custom2.properties")
})
```
#### Spring Profiles (Change Feature, Configuration, Component by Current Profiles)
3 way (on properties file, on Intellji, on Program)
Community dont have this option, use variable (spring.profiles.active=dev) to switch to dev profiles
- Provide way to segregate parts of your application configuration and make it available on certain env
only allow certain Bean to execute in the development environment BUT NOT production env
- ALSO, can be used for component switching can use profiles to switch out entire component or services
EG you can use in-memory database in dev env, full database in production env
- ALSO, can be used for toggling features, enable or disable features, put this in own profile until it's ready

Its fine to have non-exist profile in properties eg spring.profiles.active=dev,test,custom

By default if you dont set variable in Intellji spring.profiles.active=dev, the latter in properties will overwrite
eg dev,test SO test will override it 

by setDefaultProperties, can add properties during run time
```
var app = new SpringApplication(DemoApplication.class);
app.setDefaultProperties(Collections.singletonMap("spring.profiles.active", "dev"));
var ctx = app.run(args);
```

#### Make Spring to ava to specific profiles (make Whole Method OR Whole Class for specific profile)
@Profile under @Bean  OR @Profile under Class
eg @Profile("dev") , and use the Bean , only current profile is dev can use that bean, else error happen
WHAT IF? Class is "dev" , method inside that class is "test" ?
THEN, only when spring.profiles.active=dev,test, it will be valid
Bean will not exist, if not under correct profile defined

#### Spring Rest (Representational State Transfer)
Treat network resources as object , access by HTTP methods
Stateless (each HTTP request from client should contain all necessary information to server, so server dont store any data bewteen requests, keep requests isolated)
Cacheable (prevent client reuse data)
Layered System (architecture allows for layers within the system architecture, a client cannot ordinarily tell whether it is connected directly to an end server, for load balancing, security)
Code on Demand (Optional) , allows server to extend functionality of a client by transfering executable code
Uniform Interface (fundamental to the design of any restful system, simplifies and decouples the architecture, enable each part to evolve independently ) (4 principles)
- Identification of resources
- Manipulation of resources through these representations
- Self-descriptive messages
- Hypermedia As The Engine Of Application States (HATEOS)
Rest not suitable for graphql or grpc

#### Spring Rest Overview
- Web architecture principle
- Unique Identification of resources (URI)
- Different resource representation
- Hypermedia/ Linking of resources
- Stateless communication
- Standard method (GET, POST, PUT DELETE) , responses (200 OK, 404 Not Found)

#### Spring Rest Design
- resource should always be Plural Nouns in the API endpoint, if one instance resouce should be retrieved, pass the id in the URL
eg GET /accounts , GET /accounts/1
- In case of nested resources (resource under resource), get it by follow structure
eg GET /accounts/1/payments/56
- USE HTTP methods, to achieve CRUD
with the same URL, /accounts, different HTTP methods (aka verbs) have different act on it , eg GET(fetch), PUT(Update), POST(insert), DELETE(delete)

#### HTTP Method (aka verbs)
resource = record
- GET (does not affect state of resource)
- POST (send data to server, create new resource)
- PUT (update existing resource, or create if not exist)
- DELETE (delete)
- PATCH (apply partial modification to a resource, PUT is for full update, PATCH is for partial update)
- OPTIONS (return HTTP method that the server support for the specific URL)
- HEAD (similar to GET, only return the header of response, eg return header only without body, useful when checking whether resource exists before download OR check is it being modified )

#### HTTP Status Code
- 1XX Informational 
- 2XX Success
- 3XX Redirection
- 4XX Client Error
- 5XX Server Error

##### 2XX Success
- 200 OK mean everything went as plan in GET
- 201 CREATED mean new resources created as a result, response send after POST or PUT (if creating resource)
- 204 NO CONTENT mean the server successfully processed the request and there is no additional cotent to send in the response payload body 
  - (Often DELETE) OR GET/POST/PUT (when no partifuclar info send back in response body)
  - Its also good when server tell client , the server don't want to return any thing

##### 3XX Redirection
- 304 NOT MODIFIED (client send request include header eg if modified , if non-match) , ask the server to validate if the client's cached version of the resources is still up to date
  - if modified tag : compare the timestamp of client fetch and server update
  - non-match tag : compare the version token and updated token
This 3XX redirection good for being stateless but allow for optimizing, becaues it save bandwidth and make web application faster by not resending cached info to client

##### 4XX Client Error
- 400 BAD REQUEST, server unable to understand request due to syntax
- 401 UNAUTHORIZED, the request requires user authentication , expect authentication but failed OR not yet provided
- 403 FORBIDDEN, client does not have the permission for the request resources

##### ResponseEntity<XX> 
XX will be the body of response
##### @ResponseStatus
annotate method, only use @ResponesStatus code when whole method is successfully executed (without throwing exception)

##### 5XX Server Error
- 500 INTERAL SERVER ERROR, when unexpected condition encountered by the server no more specific message is suitable
- 503 SERVICE UNAVAILABLE, currently unavailable to handle request, becaues it's temporarily overloading or down for maintenance

##### URL enter is always GET request, thats why Postman to use
##### @RequestBody on parameter
convert HTTP request body to speicfic Java object
@RequestBody String message, it will convert HTTP request to String
When send request with JSON format contain all the name and value, however WITHOUT setter,getter for CLASS, it will not able to modify it.
Getter : when an instance of an object is serialized into a format like JSON (the getter method are called to access the current state of the object)
the value return by Getter method are then written into serialized format
Setter: populate the field of a newly created object with the data from the serialized format
##### JsonProperty (jackson library)
JsonProperty for rename the json name in request match the variable
@JsonProperty("c-name")
String customerName, now the json post use c-name 
##### Record and Java Class
JSON property work in Record, so we can send data by record

#### WHEN to use Record OR Class (POJO)
for data transfer object
Java records : immutable, fixed set of value, no setter
POJO: used many years, require more code
WHEN without any additional logics in DTO, record is better
If dto include logic, before Java 16, need mutable object (use POJO)

requestMappingHandlerMapping, scan controller class and map between HTTP and method
HTTP -> Dispatcher Servlet -> Hanlder Mapping -> Mapping Registry(the map) -> return found controller to Hanlder Mapping
-> Controller -> Business Logic -> return to Controller -> return response to Dispatcher Servlet -> return response to users

Mapping Registry store  in format (Method + Post +[Path variable(type)])
eg GET-/somepath/{String}/{Integer}
POST-/somepath/abc/1 //valid
GET-/somepath/abc/1 //valid
GET-/somepath/abcd/1 //invalid beucase already 1 controller method defined and duplicated for somepath

#### @Entity , @Table
Entity refer A java object that is meant to be persistent in a relational database using JPA
Entity represetnt table in database, each instance = 1 row
The Entity class must have no args constructor with public or protected
Contain @Id, @Column, @Table to specify table name which by default is class name
#### Repository (all interface)
JpaRepository extends ListPagingAndSortingRepository, ListCrudRepository
ListPagingAndSortingRepository extends PagingAndSortingRepository
ListCrudRepository extends CrudRepository
PagingAndSortingRepository extends Repository
CrudRepository extends Repository

#### @OneToOne
attribute : mappedBy = "field in that class"
@OneToOne(mappedBy="student", cascade=CascadeType.ALL)
private StudentProfile studentProfile // inside Student.java
cascade => if delete Student, related studentProfile will be deleted too
student is the primary entity, because of mappedBy
for secondary entity, @OneToOne and @JoinColumn(name="student_id") // add extra field rather than new table


#### Recursion in Entity Relationship 
Recursion in @ManyToOne and @OneToMany
eg fetch school object, contain list<Student> however inside List<Student> have school ... keep repeat
solve by @JsonManagedReference in School.java (tell Spring School is in charge of seralizing the child, prevent child serializing the parent)
@JsonBackReference in Student.java, tell student do not need to serialize(load) the parent

#### 
by ManyToOne, it will expose too many details in request and make the object more complex
because the object have too many details and relationship with other entity

#### DTO (Data Transfer Object) (return the response with just enough data from what client requested)
Main purpose: encapsulate and structure data that needs to be transfer between different parts of a system or different systems entirely
DTO only inclues simple data field or also called attributes and lacks the behaviour of the model or entity it represents
eg Student store private information eg phone number, address, etc
dont need to expose student, but want to expose school information
WE Need a Mapper between Entities and Outside world, by using Mapper we can have many Representations (1,2,3)
for example, representation 1 expose first name, last name, 2 expose or recieve outside world to create new student , etc
Therefore, different representation can responsible for read , write (eg Wrapper)

##### Why important?
- Data Separation (seperate the internal model from what is exposed to API, changed Interal without changing External representation)
- Abstraction (give clear structure, what api will provide to client)
- Performance Improvement (send DTO rather than whole entity)
- Flexibility (DTO separate from domain model, allow to tailor your API response to exactly what your client need)
- Versioning (DTO make it easy to maintain different version, by using different set of DTO, support different version of DTO simultaneously)

#### DTO can use Record when it is GET method
For example StudentDTO, have relationship with School.java (ManyToOne relationship, One School Many Student)
so if we want a Representation only return data that is ("firstname", "lastname", "email") , we also need to include the school id
because later on, if we want other information, we can use school id to fetch more from other relationship Entity

ALSO
Controller shouldn't directly accept and return Entity but using DTO
eg @ReponseBody Student student -> @ResponseBody StudentDto dto 
WHY? then Client no need to include empty attribute, while server no need to return empty attribute but required data
THEN during the controller we can either have service class OR method that convert DTO to Student class, so we can GET/POST/PUT/DELETE on the student class by the use of DTO
WHY no need to add whole Object to Student Class? Because the relationship use foreign key (school_id) inside Student table, so that just ID, student can still fetch everything about his school
```
POST request that want to add new Student and add school ID inside Student class
private Student toStudent(StudentDTO dto) {
  var student = new Student();
  student.setFirstName(dto.firstName());
  ...
  var school = new School();
  school.setId(dto.schoolId());
  student.setSchool(school);
}
```

Originally server return JSON format include everything, OR client send JSON format inclue everything
AFTER DTO, many null thing is hidden, so server stop exposed everything
```
BEFORE DTO
{
  "id":1,
  "firstName": "Ali,
  "lastName": "Bob",
  "email": "bob@mail.com",
  "age": 0,
  "studentProfile" : null
  
}
```
eg School return all student information, so its bad , therefore with ResponseDto, only can create or fetch school name information

#### DTO (StudentDto + StudentResponseDto aka StudentRepresentationDto)
StudentDto, get only required data from Client, convert the Client JSON data to real Entity data, eg StudentDTO -> Student -> then save
StudentResponse, only return required data from Server to Client , eg Student -> StudentResponse -> return limited data

#### Service Layer (Rest API)
Controller.java will increase its responsbility and size, therefore we need new layer to make code more reusable
Which is service layer, so that Controller only take request and return , any detailed thing will be handled by Service class
Presentation layer: Controller
Data access Layer: Repository
Intermediate layer between Controller and Repositroy => Service
Service layer can be unit tested, because it decouple with controller and repository by mocking or stabbing the dependencies

##### Mapper (Service Layer)
StudentMapper , Responsible mapping from StudentDto -> Student type, its a type of XXXService.class
StudentService, Responsible storing Detailed step inside,  StudentMapper(toStudent, toStudentResponseDto) , repository.save(xx) ,all will stored inside Student Service
**Always make Controller only responsible for request and response

#### Best way to organize file structure (4 Way)
1. By feature (eg product,order,customer,payment)  (each contain controller,service, repo) : good for large team, easy to locate code
2. By layer  (controller, service, repo, model, utils) (change a package would affect multiple packages)
3. By domain (Domain Driven Design aka DDD) (eg A health care system have : patientManagement, biliing, scheduling, medicalRecords) focus on the busines domain packages
packages are formed around different bounded context of subdomains
4. By Component, (top level packages for high level component, within these packages can further organize by feature layer)
eg (Usercomponent store controllers package and service package) (product component store controllers package, services package, etc)
By feature is the best

#### Data Validation in Rest API
1. Data Integrity
2. Preventing Attacks
3. Error Prevention
4. UX
5. Performance
6. Business Logic Compliance

Add Validation on first entry point object that we use to interact with Rest API (eg On the Dto Level becaues it is @RequestBody)
by @NotEmpty under every Dto variable
add @Valid near the parameter of @RequestBody of that Dto
eg @Valid @RequestBody StudentDto dto
##### All error validation Annotation 
@NotEmpty(message="please no empty")
@Max
@Min
@Email
@AssertFalse (that boolean var must be false)
@Future (date,time should be in the future not past)
@Negative
@NegativeOrZero
@NotBlank
@NotNull
@Null
@Past
@Pattern (follow regex)
etc

##### Handle Exception in Controller
```
@ExceptionHandler(XXXException.class)
public ResponseEntity<?> handleMethodXXXException(XXXException exp) {
    var error = new HashMap<String, String>();
    exp.getBindingResult().getAllErrors().forEach(error ->  
                            {
                            var fieldName = ((FieldError)error).getField(); // get which field is error
                            var errorMsg = error.getDefaultMessage(); // get its error message
                            errors.put(fieldName,errorMsg);
                          });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUSET);
}
```
#### Test Spring
Why?
- Quality Assurance (work expected)
- Regression Testing (add new features, ensure exists feature continue to work)
- Documentation 
- Code Maintainability (easy to maintain)
- Refactoring Confidence (make changes, know if something breaks)
- Collaboration
- CI CD, keep push new code and delopy automatically, catch error automatically
- Reduced Debugging time
- Scability

#### Spring Boot Test
Spring provide provide Utilities & Annotation
Two modules:
- spring-boot-test contains core items
- spring-boot-test-autoconfigure cotains auto-config
Spring-boot-starter-tests imports:
- Spring boot test modules
- JUnit, AssertJ, Hamcrest, etc
@SpringBootTest, if you want to load ApplicationContext on the test
JUnit4 -> @RungWith(SpringRunner.class)
JUnit5 -> no need add the equivalent @ExtendWith(SpringExtension)

#### How to Test
for Mapper Class, create a MapperTest class
KEEP THE SAME PACKAGE NAMING, make it easy to find
Ctrl+Shift+T (create new Test class of the current class)
@BeforeAll, useful when init data in-memory db
@BeforeEach
@AfterEach
Ctrl+Alt+L reformat data
```
//StudentMapperTest
class StudentMapperTest{
  @Test
  public void test1() {
  
  }
}
```
#### Test Isolation with Mockito
if no real database, use Mockito to mock 
useful to isolate and mock the service
eg mock StudentRepository and StudentMapper to run StudentService, because StudentService rely on them
similarly, StudentController need mock StudentService 
@Mock  (from org.mockito)
```
class StudentServiceTest
{
  @InjectMocks (try to find mocks that is compatible to the student service)
  private StudentService studentService;
  
  @Mock
  private StudentRepository repository;
  @Mock
  private StudentMapper studentMapper;
  
  // When StudentService instance is created, it will try to find the mocks object that used for StudentService 
  
  @BeforeEach
  void SetUp() {
    MockitoAnnotations.openMocks(this); // open mock for current class 
  }
  
  //first test
  @Test
  public void should_successfully_save_a_student() {
    //Given
    StudentDto  dto = new ... {John,Doe,xx@mail.com,1}
    Student student = new xxx
    //Mock the behaviour of repo and mapper, WHY? becuase studentService.saveStudent() used mapper.toStudent(dto) repo.save()
    Mockito.when(studentMapper.toStudent(dto)).thenReturn(student); // THEREFORE, inside studentService.saveStudent(dto) that mapper to student will be replaced by Mockito
    Mockito.when(repository.save(student)).thenReturn(student);
    Mockito.when(studentMapper.toResponseDto(student)).thenReturn(new StudentResponseDto(lastname,firstname,...));
    
    //When
    call saveStudent, expect StudentResponseDto
    StudentResponseDto responseDto = studentService.saveStudent(dto);
    //Then
    assertEquals(dto.firstname(), responseDto.firstname());
    
    Mockito.verify(studentMapper, Mockito.times(1)).toStudent(dto); // should only executed 1 only, this prevent when toStudent(dto) execute twice
  }
}
```
when(studentMapper.toStudentResponseDto(any(Student.calss)).thenReturn
any(Student.class)) // mean pass any Student class is acceptable, eg List<Student> student, any student object inside that list is OK
findById() return Optional<Student> so thenReturn(Optional.of(student));

### Chapter 2 (JPA, Hibernate)  TOLEARN in future(Kafak)
#### Spring Data JPA, JPA , Hibernate
Spring Data Jpa: abstraction layer on top of JPA to reduce boilerplate code required to implement DAO
JPA: is just a specification that facilities ORM to manage relational data in Java applications
JPA as Java interface where we have define the methods want to implement by any class, because so many implementation in the world, so to unity them

Hibernate : JPA implementation, Hibernate generate SQL query and execute using JDBC

#### @GeneratedValue(strategy)
strategy type: 
- Auto (by auto, it will check is the db compatible to Sequence? NO? Then next one Table? No? ....)
- Sequence (just like a variable, store the value)
- Table (a whole table to just store that column for id)
- Identity

Custom Sequence by generator() , we create a SequenceGenerator with same name with Annotation
@GeneratedValue(
strategy = GenerationType.SEQUENCE,
generator = "author_sequence"
)
@SequenceGenerator(
  name = "author_sequence"
  sequenceName = "author_sequence",
  allocationSize = 1 (by default 50 , so increment by 1)  
) 

Ctrl+Shift+N for open find document class
#### CommandLineRunner good for running code at the start up

#### Entity Lifecycle
- By get(),load() -> directly enter Persistent without new Authour() object
- after new Author() -> enter Transient state
- after save(), persist(), saveOrUpdate() , update() -> enter Persistent state
- after detach(), close(), clear(), evict() -> enter Detached
- WHEN save(), saveOrUpdate(), merge(), lock() at Detached stage -> back to Persistent state
- During Persistent state, delete() -> enter Removed state

No affect on DB during Detached state, only when back to Persistent state

#### ManyToMany MappedBy
if we want Course to be main , we put MappedBy on Author.java
```
// inside Course.java
    @ManyToMany
    @JoinTable(
            name = "authors_courses",
            joinColumns = {
                    @JoinColumn(name = "course_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id")
            }
    )
```

#### ManyToOne, need JoinColumn
put MappedBy on who is One
put JoinColumn on who is Many
```
    //section.java
    @OneToMany(mappedBy = "section")
    private List<Lecture> lectures;
    
    
    //lecture.java
    @ManyToOne
    @JoinColumn(
            name = "section_id"
    )
    private Section section;

```

#### OneToOne
owner of relationship can be uni-directional eg Lecture can access the Resouce, but Resouce cant access Lecture
```
under Lecture.java
    @OneToOne
    @JoinColumn(
            name = "resource_id"
    )
    private Resource resource;
```

To achieve bi-directional
```
//under resource.java
    @OneToOne
    @JoinColumn(
            name = "lecture_id"
    )
    private Lecture lecture;
    
    //under Lecture.java
    @OneToOne
    @JoinColumn(
            name = "resource_id"
    )
    private Resource resource;
```

#### Inheritance vs Composition
Inheritance : pros :code reuse, cons: scability bad
Composition : more flexible and easy to change
Inheritance speaclized subclass base on base class, make the code more difficult to change (eg make change to base class)
Composition: creating a class that has reference to one or more subject and delegate task to these subject
(this allow combine functionality of multiple classes into a single class without the inheritance hierarchy of a base)
So that when changing, just simply change the subject in the class

#### BaseEntity (aka Abstract Entity) @MappedSuperclass
@MappedSuperclass (this is a super class map to database table, so for define common property share by multiple entity, without create separable table for superclass)
try to collect common field so reduce the duplicate field
eg createdAt, lastModifiedAt, createdBy, lastModifiedBy
When have any inheritance with BaseEntity and other Entity , change all @Builder to @SuperBuilder
add @EqualsAndHashCode(callSuper = true) when using @Data on child Entity

THEN the database with BaseEntity, will include column on their table
so every table extends BaseEntity have all LocalTiemDate column

#### Single Table Strategy (with parent and child class)
map an inheritance hierachy to entities to a single Database table, this strategy is used JPA and hibernate mainly
define how inheritance is implemented in the database, all sub-classes of the inheritance hierarchy are mapped to the same table

##### Discriminator Column
Used to distinguish between the different subclasses, contain a value indicate which sub-class a particular row in the table belongs to 
##### Problem and when to use
- lead to large table size
- inefficient query
Good when:
- only few subclasses
- The inheritance hierarchy is not so deep

#### Different between @MappedSuperClass & @Inheritance
@MappedSuperClass is just common field of all the table entity, that class will not show on DB
@Inheritance, when SIngle Table Strategy, will make a large table contain all the row of sub-class
and use Discriminator Column to identify which row belongs to which sub-classes
```
//Parent.java
@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resource_type")
public class Resource{
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private int size;
    private String url;
    @OneToOne
    @JoinColumn(
            name = "lecture_id"
    )
    private Lecture lecture;
}

// Video.java
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@DiscriminatorValue("V")
public class Video extends Resource{
    private int length;
}

```
#### Join Table Strategy (with parent and child class)
good for many class with many differences and optimize performance
not suit for query the entire inheritance hierarchy at once
the key will be the primary and the foreign key at the same time in child
change the child id by @PrimaryKeyJoinColumn(name = "video_id")
```
// Resources.java (Parent)
@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
```

#### Table per Class Strategy (with parent and child class)
Good for few sub class with many differences
each concrete sub-class in inheritance hierarchy is mapped to a separate table 
abstract class is not mapped to table and inherit by sub-class

#### Diff between Table per Class & Join Table
Join Table : The main class table contains(name,size,url,etc) and sub-class table contain only sub-class element (eg length)
Table per Class : main class field will copy inside sub-class table

#### Polymorphic queries
query database class will retrieve all sub-class (file,text,video)
if dont want sub-class return, 
eg exclude file.java by @Polymorphism(type = "PolymorphismType.EXPLICIT") (BUT DEPRECATED)

#### Embedded entities (Composite Primary Key)
composite primary key to define an entry in a table (use more than 1 column to identify the row : aka Composite Primary Key)
Composite primary key useful when user have many records and buy at the same time so (user_name + order_date columns as primary key is good to solve)
Embedded class is not a table but similar as SuperClass that be used for others (Other Entity with Embedded class, Child with Superclass)

```
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderId implements Serializable {
        private String username;
        private LocalDateTime orderDate;
}


public class Order {
    @EmbeddedId
    private OrderId id;
}
```

#### Embeddable Entities (increase granularity of code)
what if we want to add new Column call address inside many entity, how?
- copy and paste on every entity that need? (NO)
- create a superClass to extend it? (what if already extends BaseEntity?)
- make Embeddable Entities
```
// Embeddable Entities
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String streetName;
    private String houseNumber;
    private String zipCode;
}

  
    // under embedded ID Entity;
    @Embedded
    private Address address;
    
```
#### DAO (Data Access Object)
Use for Repository data fetch or behaviour


#### ShortCut
Ctrl+Alt+O , clean unused import

##### Login/Register Design
Normally, Login and Register involves:
Model: User,Token, Role
Service: AuthenticationService(for register method inside Controller method)
Controller: AuthenticationController (for register request) (for generate Token for activation)
Request : RegistrationRequest(for the accept ResponseBody in Controller)


#### Principal interface , Transactional