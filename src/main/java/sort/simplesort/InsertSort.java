package sort.simplesort;

import Util.RandomUtil;
import Util.SortUtil;

/**
 * Created by 10353 on 2018/1/30.
 * 插入排序实践及其优化策略
 */
public class InsertSort {

    /**
     * 优化前的插入排序算法
     * @param array
     * @param n
     */
//    public static void sort(Comparable[] array, int n){
//        for(int i = 1; i < n; i++)
//            for(int j = i; j > 0; j--){
//                if(array[j].compareTo(array[j-1]) < 0)
//                    SortUtil.swap(array, j, j - 1);
//                else
//                    break;  //插入排序算法的优点
//            }
//    }

    /**
     * 优化后的插入排序算法
     * @param array
     * @param n
     */
    public static void sort(Comparable[] array, Integer n){
        for(int i = 1; i < n; i++)
            for(int j = i; j > 0 && array[j].compareTo(array[j-1]) < 0; j--)
                SortUtil.swap(array, j, j - 1);
    }


    public static void main(String[] args) {
        Integer[] array = RandomUtil.generateRandomData(10000, 0, 1000);
        SortUtil.testSortTime("sort.simplesort.InsertSort",null,array);
//        PrintUtil.printArray(array);
    }

}
