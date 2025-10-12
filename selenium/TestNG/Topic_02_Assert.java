package TestNG;

import graphql.Assert;
import org.testng.annotations.*;

public class Topic_02_Assert {

    @Test
    public void TC_01(){
        Assert.assertTrue(3 <5);
    }

}