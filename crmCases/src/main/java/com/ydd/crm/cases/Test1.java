package com.ydd.crm.cases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 {
    @Test
    public void test1(){
        Assert.assertEquals(1,1);
        System.out.println("success 后");
    }

    @Test
    public void test2(){
        Assert.assertEquals(1,2);
        System.out.println("fali后" );
        }
}
