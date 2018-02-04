package sort.complexsort;

import Util.PrintUtil;
import Util.RandomUtil;
import Util.SortUtil;
import sort.simplesort.InsertSort;

/**
 * Created by 10353 on 2018/2/4.
 * 双基准快速排序算法思想：
 * 对序列从前到后和从后到前进行扫描，从前到后扫描时发现大于等于A的数值放到大于A部分，从后到前小于等于A的数值放到小于A部分，
 * 将序列分为以一个数A为界大于A，小于A等于A的三部分。
 * 解决的问题：出现大量重复数据时重复数据分配不平均导致普通快速排序性能太差
 */
public class DoubleStandardQuickSort {


    public static void sort(Comparable[] array, Integer n){
        __doubleStandardQuickSort(array, 0, n-1);
    }

    /**
     * 双基准快速排序算法主体逻辑
     * @param array
     * @param l
     * @param r
     */
    private static void __doubleStandardQuickSort(Comparable[] array, int l, int r){

        if(r - l < 16){
            InsertSort.sort(array, l, r);
            return;
        }


        int p = __partition(array, l, r);

        __doubleStandardQuickSort(array, l, p-1);
        __doubleStandardQuickSort(array, p+1, r);

    }
    /**
     * 双基准快速排序算法核心逻辑
     * 排序范围array[l...r]
     * @param array
     * @param l
     * @param r
     */
    private static int __partition(Comparable[] array, int l, int r){

        SortUtil.swap(array,l, (int)(Math.random()*(r-l+1) + l));
        Comparable v = array[l];

        //array[l+1, i) <= v ; array(j...r] >= v
        int i = l+1, j = r;
        while(true){

            //从前先后扫描
            while(i <= r && array[i].compareTo(v) < 0)
                i++;
            //从后向前扫描
            while(j >= l+1 && array[j].compareTo(v) > 0)
                j--;
            //扫描结束条件即 j == i停止  这里是个坑！！！
            if(i > j) break;

            //两个扫描过程都停止以后交换
            SortUtil.swap(array, i, j);
            i++;
            j--;
        }
        //将标量值交换
        SortUtil.swap(array, l, j);
        return j;
    }

    public static void main(String[] args) {
//          Integer[] array = RandomUtil.generateRandomData(5, 0, 10);
//          DoubleStandardQuickSort.sort(array, array.length);

//        处理有序性高的数据
//        Integer[] array = RandomUtil.generateNearlyOrderdData(100000, 10);
//        Integer[] copyArray = RandomUtil.copyGenerateRandomData(array);
//        MergeSort running time : 77ms
//        DoubleStandardQuickSort running time : 35ms
//        处理重复率高的数据
//        MergeSort running time : 135ms
//        DoubleStandardQuickSort running time : 78ms

        Integer[] array = RandomUtil.generateRandomData(100000,0,10);
        Integer[] copyArray = RandomUtil.copyGenerateRandomData(array);
        SortUtil.testSortTime("sort.complexsort.MergeSort",null,array);
        SortUtil.testSortTime("sort.complexsort.DoubleStandardQuickSort",null,copyArray);
    }
}
