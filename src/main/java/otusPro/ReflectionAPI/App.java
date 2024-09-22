package otusPro.ReflectionAPI;


import org.apache.logging.log4j.*;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class App {
    private static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {


        Class cl1 = MyTest.class;

        starTest(cl1, 3, -5);

    }


    public static void starTest(Class cl, int a, int b) throws InvocationTargetException, IllegalAccessException {
        int passedOne = 0;
        int passedTwo = 0;
        int failedOne = 0;
        int failedTwo = 0;
        Method[] methods = cl.getDeclaredMethods();

        for (Method met : methods) {
            if (met.isAnnotationPresent(BeforeSuite.class)) {
                met.invoke(null);
            }
        }

        for (Method met : methods) {
            if ((met.isAnnotationPresent(Test.class)) && (met.getAnnotation(Test.class).priority() == 1)) {
                met.invoke(null, a, b);
                passedOne++;
                if(passedOne == 0){
                    failedOne++;
                }
            }

        }

        for (Method met : methods) {
            if ((met.isAnnotationPresent(Test.class)) && (met.getAnnotation(Test.class).priority() == 2)) {
                met.invoke(null, a, b);
                passedTwo++;
                if(passedTwo == 0){
                    failedTwo++;
                }
            }
        }

        for (Method met : methods) {
            if (met.isAnnotationPresent(AfterSuite.class)) {
                met.invoke(null);
            }
        }
        System.out.println("\nВсего тестов: " +  (passedOne + passedTwo + failedTwo + failedOne) + "\nПройдено тестов: " + (passedOne  + passedTwo) + "\nПровалено тестов: " + (failedOne + failedTwo));
    }
}
