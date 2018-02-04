package sort.complexsort;

import Util.PrintUtil;
import Util.RandomUtil;
import Util.SortUtil;
import sort.simplesort.InsertSort;

import java.net.SocketTimeoutException;

/**
 * Created by 10353 on 2018/2/4.
 * 归并排序的思想及逐步优化的过程
 * 思想：采用分治法将两个已经排序的序列合并成一个序列的操作。
 * 算法时间复杂度为O(n log n)及将序列分为log n层，每一层的时间复杂度为n
 * 实现：递归法
 * 重点：申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 */
public class MergeSort {


    public static void sort(Comparable[] array, Integer n){
        __mergeSort(array, 0, n-1);
    }

    /**
     * 归并排序算法实现
     * @param array
     * @param left
     * @param right
     */
    private static void __mergeSort(Comparable[] array, int left, int right){
//        优化2：底层使用插入算法
//        if(left >= right)
//            return;
        if(right - left < 16){
            InsertSort.sort(array, left, right);
            return;
        }

        int mid = (left + right)/2;
        //排序范围array[l...mid]
        __mergeSort(array, left, mid);
        //排序范围array[mid...right]
        __mergeSort(array, mid+1, right);
        //优化场景：两个子序列已经有序
        if(array[mid].compareTo(array[mid + 1]) < 0)
            //两个排好序的序列进行合并
            __merge(array, left, mid, right);
    }

    /**
     * 两个子序列合并实现
     * 前提为子序列已经完成排序
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    private static void __merge(Comparable[] array, int left,int mid, int right){
        //申请辅助空间
        Comparable[] aux = new Comparable[right - left + 1];

        //初始化辅助空间
        for(int i = left; i <= right; i++)
            aux[i - left] = array[i];

        int i = left, j = mid + 1;

        for(int k = left ; k <= right; k++){
            //i, j越界处理
            //场景：子序列A[98, 99, 100, 101] 子序列B[1, 2, 3, 4],在k循环结束之前j一直处于递增状态导致数组越界
            if(i > mid){
                array[k] = aux[j - left];
                j++;
            }else if(j > right){
                array[k] = aux[i - left];
                i++;
            }

            //归并序列的核心
            else if(aux[i - left].compareTo(aux[j - left]) < 0){
                array[k] = aux[i - left];
                i++;
            }else {
                array[k] = aux[j - left];
                j++;
            }
        }
    }

    public static void main(String[] args) {
//        测试场景1：100000个随机数据重复率低,有序率低
          Integer[] array = RandomUtil.generateRandomData(100000, 0, 1000);
          Integer[] copyArray = RandomUtil.copyGenerateRandomData(array);
//        MergeSort running time : 24ms
//        InsertSort running time : 9ms
          SortUtil.testSortTime("sort.complexsort.MergeSort",null,array);
          SortUtil.testSortTime("sort.simplesort.InsertSort",null,copyArray);
//        测试场景2：100000个随机数据重复率低，有序率高
//        未优化
//        MergeSort running time : 226ms
//        InsertSort running time : 24ms
//        优化1
//        MergeSort running time : 124ms
//        InsertSort running time : 26ms
//        优化2
//        MergeSort running time : 68ms
//        InsertSort running time : 13ms
//        Integer[] array = RandomUtil.generateNearlyOrderdData(100000, 10);
//        Integer[] copyArray = RandomUtil.copyGenerateRandomData(array);
//        SortUtil.testSortTime("sort.complexsort.MergeSort",null,array);
//        SortUtil.testSortTime("sort.simplesort.InsertSort",null,copyArray);
    }
}
