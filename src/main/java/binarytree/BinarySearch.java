package binarytree;

import Util.RandomUtil;

import javax.naming.InsufficientResourcesException;

/**
 * Created by 10353 on 2018/3/14.
 * 二分查找法
 * 运用分治的思想
 */
public class BinarySearch {

    /**
     * 条件：数组有序
     * @param array
     * @param n
     * @param target
     */
    public static int binarySearch(Comparable[] array, int n, Comparable target){
        //定义边界[l...r]中查找元素
        int l = 0, r = n - 1;
        while(l <= r){
            //int mid = ( l + r ) / 2;
            int mid = (r - l) / 2 + l;
            if(array[mid].compareTo(target) == 0)
                return mid;

            //当target大于array[mid]时在[mid+1...r]中寻找
            if(array[mid].compareTo(target) < 0)
                l = mid + 1;

            //当target小于array[mid]时在[l...mid-1]中寻找
            if(array[mid].compareTo(target) > 0)
                r = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        double startTime = System.currentTimeMillis();
        Integer[] array = RandomUtil.generateOrderdData(10000000);
        int index = binarySearch(array, array.length, array[10]);
        double endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime)/1000 + "s");
    }
}
