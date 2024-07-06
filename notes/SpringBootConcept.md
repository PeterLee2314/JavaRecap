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



#### ShortCut
Ctrl+Alt+O , clean unused import

##### Login/Register Design
Normally, Login and Register involves:
Model: User,Token, Role
Service: AuthenticationService(for register method inside Controller method)
Controller: AuthenticationController (for register request) (for generate Token for activation)
Request : RegistrationRequest(for the accept ResponseBody in Controller)


#### Principal interface , Transactional