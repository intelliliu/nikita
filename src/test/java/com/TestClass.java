package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: ye
 * Date: 13-3-17
 * Time: обнГ2:38
 * To change this template use File | Settings | File Templates.
 */
public class TestClass {
    static private final Log logger = LogFactory.getLog(TestClass.class);

    @Test
    public void test() {
        logger.info(String.class.getCanonicalName());
        logger.info(String.class.getName());
        logger.info(String.class.getPackage().getName());
        logger.info(String.class.getSimpleName());
    }
}
