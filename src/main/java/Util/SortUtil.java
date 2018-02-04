package Util;

import java.lang.reflect.Method;

/**
 * Created by 10353 on 2018/1/30.
 * 元素交换工具
 */
public class SortUtil {

    //此处需注意Java函数参数传递问题
    //基本类型为值传递，对象为引用传递
    //还有一点关于Java泛型擦除，Object[]等效于C++中的T array[]
    public static void swap(Object[] array, int i, int j){
        Object temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 使用反射机制测试排序算法性能
     * @param className
     * @param array
     */
    public static void testSortTime(String className , String methodName,Comparable[] array){
        String defalutMethodName = "sort";
        if(methodName != null)
            defalutMethodName = methodName;
        try {
            Class c = Class.forName(className);
            Method sortMethod = c.getMethod(defalutMethodName, new Class[]{Comparable[].class,Integer.class});
            long beginTime = System.currentTimeMillis();
            sortMethod.invoke(null,array,array.length);
            long endTime = System.currentTimeMillis();
            System.out.println( c.getSimpleName() + " running time : " + (endTime - beginTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//        Comparable[] array = {1, 2};
//        SortUtil.swap(array ,0, 1);
//        System.out.println("i : "+ array[0] + " j :" + array[1]);
//    }
}
