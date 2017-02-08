package com.epam.test.service.impl;


import com.epam.catalog.service.impl.BookServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class tstFindSearchCriterionCategory {
    //тестируем private метод findSearchCriterionCategory через Reflection
    private final String EXPECT = "Year";
    private BookServiceImpl object = new BookServiceImpl();
    private Class c = object.getClass();
    private Class[] paramTypes = new Class[] {String.class};

    @DataProvider(name = "dp1")
    public Object[][] dProvider1() {
        return new Object[][] {
                {new String("b$%$Title$%$Year$%$2011")},
                {new String("sad!b$%$Title$%$Year$%$201sdf1")},
                {new String("$%$Title$%$Year")},
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
            Method method = c.getDeclaredMethod("findSearchCriterionCategory", paramTypes);
            method.setAccessible(true);
            Object[] arguments = new Object[] { new String(request) };
            Assert.assertEquals(method.invoke(object, arguments), EXPECT);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "dp2")
    public void f2(String request) {
        try {
            Method method = c.getDeclaredMethod("findSearchCriterionCategory", paramTypes);
            method.setAccessible(true);
            Object[] arguments = new Object[] { new String(request) };
            Assert.assertNotEquals(method.invoke(object, arguments), EXPECT);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
