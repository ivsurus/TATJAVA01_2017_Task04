package com.epam.test.service.impl;


import com.epam.catalog.service.impl.BookServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class tstFindSearchCriterionValue {

//тестируем private метод findSearchCriterionValue через Reflection
    private final String EXPECT = "Title";
    private BookServiceImpl object = new BookServiceImpl();
    private Class c = object.getClass();
    private Class[] paramTypes = new Class[] {String.class};

    @DataProvider(name = "dp1")
    public Object[][] dProvider1() {
        return new Object[][] {
                {new String("b$%$Title$%$Sur$%$2011")},
                {new String("sad!b$%$Title$%$Sugfhfghgfr$%$201sdf1")},
                {new String("$%$Title$%$S")},
        };
    }
    @DataProvider(name = "dp2")
    public Object[][] dProvider2() {
        return new Object[][] {
                {new String("1$%$2$%$f")},
                {new String("sad!b$%$Year$%$Title$%$201sdf1")},
                {new String("$%$Title$%$Year1")},
        };
    }

    @Test(dataProvider = "dp1")
    public void f1(String request) {
        try {
            Method method = c.getDeclaredMethod("findSearchCriterionValue", paramTypes);
            method.setAccessible(true);
            Object[] arguments = new Object[] { new String(request) };
            Assert.assertEquals(method.invoke(object, arguments), EXPECT);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    @Test(dataProvider = "dp1")
    public void f2(String request) {
        try {
            Method method = c.getDeclaredMethod("findSearchCriterionValue", paramTypes);
            method.setAccessible(true);
            Object[] arguments = new Object[] { new String(request) };
            Assert.assertNotEquals(method.invoke(object, arguments), EXPECT);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
