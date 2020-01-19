package com.hasitha;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/* When we  use below annotation only one instance of MathUtilsTest will be created. Unless for each @Test annotation new instance of MathUtilsTest will be created. */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest {
    public  MathUtilsTest(){
        System.out.println("Constructer.....");
    }

    MathUtils mathUtils=null;

    /*This method will run before any of other test methods runs.
    * Which means this method runs even before an instance of MathUtilsTest created.
    * That is why this method has declared as static method.
    */
    @BeforeAll
    static void initBeforeAll(){
        System.out.println("Testing started...");
    }

    /*This method will run after all other methods runs.
     * Which means at the time of running this method there is no instance of  MathUtilsTest.
     * That is why this method has declared as static method.
     */
    @AfterAll
    static  void initAfterAll(){
        System.out.println("Testing finished...");
    }

    //This method will run before  each @Test annotated method.
    @BeforeEach
    void initBeforeEach(){
        System.out.println("initBeforeEach runs...");
        mathUtils= new MathUtils();
    }

    //This method will run after  each @Test annotated method.
    @AfterEach
    void initAfterEach(){
        System.out.println("initAfterEach runs...");
    }

    /*
    * Junit will create a new instance of MathUtilsTest each and every time when a method with @Test runs.
    * Which means if there are 4 test methods inside this test class, each and every time runs a test method
    * junit will create a new instance of MathUtilsTest class and runs that test method.
    *
    * */

    @Test
    void testAdd() {
        /*
        * MathUtils instance creation part has commented since method with @BeforeEach is creating an instance of MathUtils.
        * */
      //  MathUtils mathUtils= new MathUtils();
        int expectedResult=100;
        int actual=mathUtils.add(50, 50);
        //assertEquals(expectedResult,actual);
        //If ypu want to display a meaningfull message when a test case is failed add a string message as below 3 rd argument of the method.
        assertEquals(expectedResult,actual,"add method should add two numbers.");
    }


    @Test
    void testSubtract() {
      //  MathUtils mathUtils= new MathUtils();
        int expectedResult=7;
        int actual=mathUtils.subtract(10,3);
        assertEquals(expectedResult,actual,"add method should subtract two numbers.");
    }

    @Test
    void testMultiply() {
       // MathUtils mathUtils= new MathUtils();
        int expectedResult=30;
        int actual=mathUtils.multiply(10,3);
        assertEquals(expectedResult,actual,"multiply method should multiply two numbers.");
    }

    @Test
    void testDevide() {
      //  MathUtils mathUtils= new MathUtils();
        int expectedResult=2;
        int actual=mathUtils.devide(10,5);
        assertEquals(expectedResult,actual,"devide method should devide two numbers.");
    }

    /*
    * Below test method is used to test whether devide method throws an ArithmeticException when we devide by zero.
    * */
    @Test
    void testDevideArithmaticException() {
        //MathUtils mathUtils= new MathUtils();
        assertThrows(ArithmeticException.class, () -> mathUtils.devide(10,0),"Devide by 0 should return an ArithmeticException");
    }


    @Test
    //By using @Disabled annotation we can skip a test case.
    @Disabled
    //By using @DisplayName annotation we can display given message instead of method name in test report.
    @DisplayName("Disabled method.Should not run.")
    void testDisabled(){
        fail("This test should not be run.");
    }

    //By using assertAll we can test several test cases once.
    @Test
    void testMultiplyWithAssertAll() {

        assertAll(
                ()->assertEquals(2,mathUtils.multiply(1,2)),
                ()->assertEquals(0,mathUtils.multiply(1,0)),
                ()->assertEquals(-2,mathUtils.multiply(1,-2)),
                ()->assertEquals(2,mathUtils.multiply(-1,-2))


        );

    }

    //By using @Nested annotation we can group several test cases for a single method.
    @Nested
    @DisplayName("add method")
    class AddTest {

        @Test
        @DisplayName("when adding two positive numbers")
        void testAddPositive() {
            assertEquals(4, mathUtils.add(2, 2), "add method should add two positive numbers correctely.");
        }

        @Test
        @DisplayName("when adding two negative numbers")
        void testAddNegative() {
            assertEquals(-3, mathUtils.add(-2, -1), "add method should add two negative numbers correctely.");
        }

        @Test
        @DisplayName("when adding a positive number and negative number")
        void testAddNegativeAndPositive() {
            assertEquals(3, mathUtils.add(5, -2), "add method should add positive  number and negative number  correctely.");
        }
    }


    //By using @RepeatedTest annotation we can run a  testcase repeatedely.
    @RepeatedTest(4)
    void testAddMethodUsingRepetition(RepetitionInfo repetitionInfo){
        if(repetitionInfo.getCurrentRepetition()==1){
            assertEquals(2,mathUtils.add(1,1));
        }else if(repetitionInfo.getCurrentRepetition()==2){
            assertEquals(5,mathUtils.add(5,0));
        }else if(repetitionInfo.getCurrentRepetition()==3){
            assertEquals(-2,mathUtils.add(-1,-1));
        }else if(repetitionInfo.getCurrentRepetition()==4){
            assertEquals(1,mathUtils.add(-1,2));
        }
    }






}