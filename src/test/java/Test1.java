import org.junit.jupiter.api.*;
import test.Calc;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// PER_METHOD is the default behaviour, it create new instance on each Test
// PER_CLASS (so we no need static method, only 1 instance create when Test1 execute)  (IT EXECUTE Constructor before BeforeALL somehow)
public class Test1 {
    Test1() {
        System.out.println("Constructor execute in each test (in First test it creates After BeforeAll but Before BeforeEach");
        // Constructor execute after BeforeAll if PER_CLASS
        // Constructor execute before BeforeAll if PER_METHOD
    }
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test");
    }
    @AfterAll
    static void afterAll() {
        System.out.println("After all test");
    }
    @BeforeEach
    void init(){
        System.out.println("Test Execute");
        // it will init in each test
    }
    @Test
    public void calcTest() {
        Calc c = new Calc();
        assertEquals(2,c.divide(10,5));
    }
    @Test
    public void calcTest2() {
        Calc c = new Calc();
        assertEquals(2,c.divide(10,5), "Wrong Answer");
    }
    // Functional Interface for message  (supplier interface)
    // Pros : if not fail, the message will not evaluate, more efficient (lazy evaluation)
    // Compare to the above string passing, no matter fail or pass, the "Wrong Answer"
    // still pass to method
    @Test
    public void calcTest3() {
        Calc c = new Calc();
        assertEquals(2,c.divide(15,5), () -> "Wrong Answer");
    }
    // assertNotEquals(a,b) // only pass when a != b
    // assertTrue(a) // pass if a return true
    // assertFalse(a) // pass if return false
    // assertArraysEquals() // pass if all arrays element equals (include idx)
    // eg {2,4,6,8} vs {4,8,6,2} false , {2,4,6} vs {2,4,6} true
    // assertEquals not work on two array, because its checking the reference variable
    // but not checking the data

    // assertThrows(Exception.class, method)
    // assertTimeout(Duration, Supplier with method)
    public void execute() {
        for (int i =0; i < 10; i--) {
        }
    }
    // JUnit5 not support @Test(timeout=100)
    @Test
    public void testPerformance() {
        assertTimeout(Duration.ofMillis(1000), () -> execute());
    }

    @AfterEach
    void end() {
        System.out.println("Test Ended");
    }

}
