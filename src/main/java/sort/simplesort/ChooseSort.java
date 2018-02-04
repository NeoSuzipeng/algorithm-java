package sort.simplesort;

import Util.PrintUtil;
import Util.RandomUtil;
import Util.SortUtil;

/**
 * Created by 10353 on 2018/1/30.
 * 选择排序的简单实现及代码演进
 * 基本思想
 * 通过循环依次找到当前最大或者最小元素将其放置上一个最大或者最小值之后
 *
 */
public class ChooseSort {

    /**
     * 从小到大进行排序
     * @param array
     */
    public static void sort(Comparable[] array, Integer n){  //Java不允许使用泛型数组，因为Java泛型的类型擦除等效于Object[]
        int minIndex = 0;

        for(int i = 0; i < n; i++){

            for(int j = i + 1; j < n; j++){
                if(array[j].compareTo(array[minIndex]) < 0)
                    minIndex = j;
            }
            //交换元素
            SortUtil.swap(array, i, minIndex);
        }
    }

    public static void main(String[] args) {
        Integer[] array = RandomUtil.generateRandomData(10, 0, 10);
        ChooseSort.sort(array, array.length);
        PrintUtil.printArray(array);
    }

}
