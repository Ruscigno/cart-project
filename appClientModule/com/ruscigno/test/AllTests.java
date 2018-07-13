package com.ruscigno.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ItemTest.class, ShoppingCartFactoryTest.class, ShoppingCartTest.class, ProductTest.class })
public class AllTests {

}
