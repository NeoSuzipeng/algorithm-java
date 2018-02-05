package sort.complexsort;

import Util.RandomUtil;
import Util.SortUtil;
import sort.simplesort.InsertSort;

/**
 * Created by 10353 on 2018/2/5.
 * 三路快速排序思想及优点
 * 思想：对序列从前到后和从后到前进行扫描，从前到后扫描时发现大于A的数值放到大于A部分，等于A的数值放到等于A部分，从后到前小于A的数值放到小于A部分，
 * array[l...lt]<v ; array[lt...gt-1]==v ; array[gt...r] > v
 * 优点：处理大量重复元素的序列的性能提升
 */
public class ThreeWayQuickSort {

    public static void sort(Comparable[] array, Integer n){
        __threeWayQuickSort(array, 0, n-1);
    }

    /**
     * 三路快速排序算法主体逻辑
     * @param array
     * @param l
     * @param r
     */
    private static void __threeWayQuickSort(Comparable[] array, int l, int r){

        if(r - l < 16){
            InsertSort.sort(array, l, r);
            return;
        }

        //partition start

        //随机确定标量，避免子序列不平衡问题
        SortUtil.swap(array,l, (int)(Math.random()*(r-l+1) + l));
        Comparable v = array[l];

        //array[l+1...lt]<v ; array[lt+1...i)==v ; array[gt...r] > v
        int lt = l;
        int gt = r + 1;
        int i = l+1;
        while(i < gt){
            if(array[i].compareTo(v) < 0){
                SortUtil.swap(array,i,lt+1);
                i++;
                lt++;
            }
            else if(array[i].compareTo(v) > 0){
                SortUtil.swap(array,i,gt - 1);
                gt--;
            }else{
                i++;
            }
        }

        SortUtil.swap(array, l , lt);
        lt--;

        //partition end

        __threeWayQuickSort(array, l, lt);
        __threeWayQuickSort(array, gt, r);

    }

    public static void main(String[] args) {
//        MergeSort running time : 76ms
//        DoubleStandardQuickSort running time : 94ms
//        ThreeWayQuickSort running time : 31ms
        Integer[] array = RandomUtil.generateRandomData(100000,0,10);
        Integer[] copyArray1 = RandomUtil.copyGenerateRandomData(array);
        Integer[] copyArray2 = RandomUtil.copyGenerateRandomData(array);
        SortUtil.testSortTime("sort.complexsort.MergeSort",null,array);
        SortUtil.testSortTime("sort.complexsort.DoubleStandardQuickSort",null,copyArray1);
        SortUtil.testSortTime("sort.complexsort.ThreeWayQuickSort",null,copyArray2);

    }

}
