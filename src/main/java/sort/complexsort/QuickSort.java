package sort.complexsort;

import Util.RandomUtil;
import Util.SortUtil;
import sort.simplesort.InsertSort;

/**
 * Created by 10353 on 2018/2/4.
 * 普通快速排序算法思想及优化过程
 * 思想：对序列进行从前到后扫描，将序列分为以一个数A为界大于A，小于A等于A的三部分。
 *
 */
public class QuickSort {

    public static void sort(Comparable[] array, Integer n){
        __quickSort(array, 0, n-1);
    }

    /**
     * 快速排序算法主体逻辑
     * @param array
     * @param l
     * @param r
     */
    public static void __quickSort(Comparable[] array, int l, int r){

        if(r - l < 16){
            InsertSort.sort(array, l, r);
            return;
        }

        int p = __partition(array, l, r);

        __quickSort(array, l, p-1);
        __quickSort(array, p+1, r);

    }
    /**
     * 快速排序算法核心逻辑
     * 排序范围array[l...r]
     * @param array
     * @param l
     * @param r
     */
    private static int __partition(Comparable[] array, int l, int r){
//         问题：划分子序列时不平均
//        Comparable v = array[l];
//        优化1：
        Comparable v = array[(int)(Math.random()*(r-l+1) + l)];

        // array[l+1...j] < v ; array[j+1...i) > v
        // 扫描过程
        int j = l;
        for(int i = l+1; i <= r; i++){
            if(array[i].compareTo(v) < 0)
                SortUtil.swap(array, ++j, i);
        }
        SortUtil.swap(array, l, j);

        return j;
    }

    public static void main(String[] args) {
//        Integer[] array = RandomUtil.generateRandomData(10, 0, 10);
//        sort(array,array.length);
//        MergeSort running time : 105ms
//        QuickSort running time : 99ms
//          Integer[] array = RandomUtil.generateRandomData(100000, 0, 1000);
//          Integer[] copyArray = RandomUtil.copyGenerateRandomData(array);
//        优化前
//        MergeSort running time : 59ms
//        QuickSort running time : 1414ms
//        优化1后：
//        MergeSort running time : 90ms
//        QuickSort running time : 145ms
          Integer[] array = RandomUtil.generateNearlyOrderdData(100000, 10);
          Integer[] copyArray = RandomUtil.copyGenerateRandomData(array);
          SortUtil.testSortTime("sort.complexsort.MergeSort",null,array);
          SortUtil.testSortTime("sort.complexsort.QuickSort",null,copyArray);
    }
}
