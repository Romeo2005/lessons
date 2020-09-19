package Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestMaker {
    private static final TreeMap<Integer, Method> testMethods = new TreeMap<>();
    private static final List<Method> beforeMethods = new ArrayList<>();
    private static final List<Method> afterMethods = new ArrayList<>();
    private static final List<Method> defaultTestMethods = new ArrayList<>();

    public static void start(Class<?> testClass) throws InvocationTargetException, IllegalAccessException {
        Object obj;

        try {
            obj = testClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Class should contain default constructor");
        }

        Method[] methods = testClass.getDeclaredMethods();

        sortMethods(methods);

        if (beforeMethods.size() > 1 || afterMethods.size() > 1)
            throw new RuntimeException("It couldn't be more than one before and after method");

        if (beforeMethods.size() != 0)
            beforeMethods.get(0).invoke(obj);

        for (Method m : defaultTestMethods)
            m.invoke(obj);

        for (Method m : testMethods.values())
            m.invoke(obj);

        if (afterMethods.size() != 0) {
            afterMethods.get(0).invoke(obj);
        }
    }

    private static void sortMethods(Method[] methods) {
        final int DEFAULT_PRIORITY_VALUE = -1;

        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                if (m.getParameterCount() != 0)
                    throw new RuntimeException("Test methods couldn't have any args");

                final int PRIORITY = m.getAnnotation(Test.class).priority();

                if (PRIORITY == DEFAULT_PRIORITY_VALUE)
                    defaultTestMethods.add(m);

                else if (testMethods.get(PRIORITY) == null && PRIORITY >= 0)
                    testMethods.put(m.getAnnotation(Test.class).priority(), m);

                else if (testMethods.get(PRIORITY) != null)
                    throw new RuntimeException("It couldn't exist more than one method with priority level: " + PRIORITY);

                else if (PRIORITY < 0)
                    throw new IllegalArgumentException("Priority couldn't be lower than 0. Received: " + PRIORITY);

                m.setAccessible(true);

            } else if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (m.getParameterCount() != 0)
                    throw new RuntimeException("Test methods couldn't have any args");
                beforeMethods.add(m);
                m.setAccessible(true);

            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                if (m.getParameterCount() != 0)
                    throw new RuntimeException("Test methods couldn't have any args");
                afterMethods.add(m);
                m.setAccessible(true);
            }
        }
    }
}
