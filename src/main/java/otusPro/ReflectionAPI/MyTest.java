package otusPro.ReflectionAPI;

import org.apache.log4j.*;

public class MyTest {
    private static final Logger logger = LogManager.getLogger(MyTest.class.getName());



    @Test  (priority = 1)
    public  static  void myTestOne(int a, int b){
             try {
                 System.out.println("@Test1   a + b = " + (a + b));
             } catch (Exception e) {
                 logger.info("Неккоректные данные: " + a + " " + b + ". \n", e);
             }
    }

    @Test  (priority = 2)
    public  static  void myTestTwo(int a, int b){
            try{System.out.println("@Test2   a - b = " + (a - b));}
            catch(Exception e)
            {logger.info("Неккоректные данные: " + a + " " + b + ". \n", e);}
    }




    @BeforeSuite
    public  static void myBeforeSuite(){
            System.out.println("BeforeSuite");
    }

    @AfterSuite
    public static void myAfterSuite(){
            System.out.println("@AfterSuite");
    }

}
