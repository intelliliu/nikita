package org.intelliliu.nikita.util;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ye
 * Date: 13-3-18
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */
public class GenericsUtils {
    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz The class to introspect
     */
    public static Type getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     */
    public static Type getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
//        if (!(params[index] instanceof Class)) {
//            return Object.class;
//        }
        return (Class)params[index];
    }

    @Test
    public void test(){
        Map<String,String> map=new HashMap<String,String>();
        Type tmp=map.getClass();
        System.out.println("第二个泛型："+getSuperClassGenricType(map.getClass(),1).toString());
    }
}
