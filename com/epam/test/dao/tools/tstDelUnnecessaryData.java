package com.epam.test.dao.tools;

import com.epam.catalog.dao.tools.DataBaseTools;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class tstDelUnnecessaryData {


     private DataBaseTools dbTools = DataBaseTools.getInstance();


    @DataProvider(name = "dp1")
    public Object[][] dProvider1() {
        return new Object[][] {
                {new HashSet<>(Arrays.asList("a", "b", "c")), "b"},
                {new HashSet<>(Arrays.asList("asdfdjlk", "sdfhlb", "sdffhkc")), "a"},
        };
    }

    @DataProvider(name = "dp2")
    public Object[][] dProvider2() {
        return new Object[][] {
                {new HashSet<>(Arrays.asList("a", "b", "c", "cdgsdf")), "c"},
                {new HashSet<>(Arrays.asList("asdfdjlk", "sdfhlb", "sdffhkc")), "s"},
        };
    }
    @DataProvider(name = "dp3")
    public Object[][] dProvider3() {
        return new Object[][] {
                {new HashSet<>(Collections.emptyList()), "c"},
                {new HashSet<>(Arrays.asList("asdfdjlk", "sdfhlb", "sdffhkc")), ""},
        };
    }


    @Test (dataProvider = "dp1")
    public void f1(Set<String> set, String identifier) {
       Assert.assertEquals(dbTools.delUnnecessaryData(set, identifier).size(), 1);
    }

    @Test (dataProvider = "dp2")
    public void f2(Set<String> set, String identifier) {
        Assert.assertEquals(dbTools.delUnnecessaryData(set, identifier).size(), 2);
    }

    @Test (dataProvider = "dp3")
    public void f3(Set<String> set, String identifier) {
        Assert.assertEquals(dbTools.delUnnecessaryData(set, identifier).size(), 0);
    }

}
