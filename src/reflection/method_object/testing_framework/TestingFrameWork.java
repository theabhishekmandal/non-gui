package reflection.method_object.testing_framework;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Testing framework in which
 *  1.  a beforeClass method is called once, before running all the testCases
 *      -   it should be public, static and should return void as the type
 *  2.  a afterClass method is called once, after running all the testCases
 *      -   it should be public, static and should return void as the type
 *  3.  a setupTest method is called before running each test case
 *      -   it should be public and should return void as the type.
 *  4.  a testCase method with starting string as 'test'
 *      -   it should be public and should return void as the type.
 *
 *  -   Default constructor is present, and if any of the method is not present then don't invoke
 *      the method.
 */
public class TestingFrameWork {

    private Map<String, List<Method>> methodNameToMethod;
    private static final String TEST_PREFIX = "test";
    private static final String BEFORE_CLASS = "beforeClass";
    private static final String AFTER_CLASS = "afterClass";
    private static final String SETUP = "setupTest";



    public void runTestSuite(Class<?> testClass) throws Throwable {
        // get all methods
        Method[] methods = testClass.getDeclaredMethods();
        populateMethodNameToMethod(methods);

        // create constructor
        Constructor<?> ctor = testClass.getConstructor();
        Object newInstance = ctor.newInstance();

        // invoke beforeClass only once.
        Method beforeClassMethod = getBeforeClassMethod();
        Method setupMethod = getSetupMethod();
        Method afterClassMethod = getAfterClassMethod();

        List<Method> allMethodsList = getTestMethods();

        if (beforeClassMethod != null) {
            beforeClassMethod.invoke(null);
        }

        for (Method method : allMethodsList) {
            if (setupMethod != null) {
                setupMethod.invoke(newInstance);
            }

            method.invoke(newInstance);
        }


        if (afterClassMethod != null) {
            afterClassMethod.invoke(null);
        }
    }

    private Method getBeforeClassMethod() {
        var beforeClassMethodList = methodNameToMethod.get(BEFORE_CLASS);
        if (beforeClassMethodList == null) {
            return null;
        }

        return beforeClassMethodList.stream().filter(this::validateStaticMethod).findAny().orElse(null);
    }

    private Method getAfterClassMethod() {
        var afterClassMethod = methodNameToMethod.get(AFTER_CLASS);
        if (afterClassMethod == null) {
            return null;
        }

        return afterClassMethod.stream().filter(this::validateStaticMethod).findAny().orElse(null);
    }

    private Method getSetupMethod() {
        var setupMethodList = methodNameToMethod.get(SETUP);
        if (setupMethodList == null) {
            return null;
        }
        return setupMethodList.stream().filter(this::validateInstanceMethod).findAny().orElse(null);
    }


    private List<Method> getTestMethods() {
        return methodNameToMethod.entrySet().stream()
                .filter(x -> x.getKey().startsWith(TEST_PREFIX))
                .flatMap(x -> x.getValue().stream())
                .filter(this::validateInstanceMethod)
                .collect(Collectors.toList());
    }


    private boolean validateInstanceMethod(Method method) {
        return method.getParameterCount() == 0 &&
                method.getReturnType().equals(void.class) && Modifier.isPublic(method.getModifiers());
    }

    private boolean validateStaticMethod(Method method) {
        return validateInstanceMethod(method) && Modifier.isStatic(method.getModifiers());
    }

    private void populateMethodNameToMethod(Method[] methods) {
        this.methodNameToMethod = Arrays.stream(methods)
                .collect(Collectors.groupingBy(Method::getName, LinkedHashMap::new, Collectors.toList()));
    }
}
