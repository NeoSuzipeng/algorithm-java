package sort.simplesort;

import Util.SortUtil;

import java.util.Arrays;

/**
 * Created by 10353 on 2018/4/11.
 * 字典排序
 * 功能：查找第一个大于某个数本身的数字
 */
public class LexicographicSort {

    /**
     * 查找第一个大于某个数本身的数字
     * 主体流程：
     * 1.从后向前查找逆序部分，使用a表示（如123654中的654）
     * 2.找到a中第一个大于a前一个数b的数,将b和c交换位置
     * 3.将a逆序
     * @param numbers 原始数组代表一个具体数字如12345 {1,2,3,4,5}
     * @return
     */
    public static int[] findNearestNumber(int[] numbers){

        int[] copyNumbers = Arrays.copyOf(numbers,numbers.length);

        int index = findReverseNumbers(copyNumbers);

        //表示这个数已经全部逆序，没有更大的值
        if (index == 0)
            return copyNumbers;

        swapHead(index, copyNumbers);

        reverseNumbers(index,copyNumbers);

        return copyNumbers;

    }

    /**
     * 查询逆序部分
     * @param copyNumbers
     * @return 返回逆序部分从左向右首数字的下标
     */
    private static int findReverseNumbers(int[] copyNumbers) {
        for(int i = copyNumbers.length-1; i > 0; i--){
            if(copyNumbers[i]>copyNumbers[i-1])
                return i;
        }
        return 0;
    }

    /**
     *
     * @param index
     * @param copyNumbers
     */
    private static void swapHead(int index, int[] copyNumbers) {
        int heap = index;

        //寻找[index...length-1]中大于index-1的数值小标
        //注意此时[index...length-1]部分已经保持a>b>c关系
        for(int i = copyNumbers.length-1; i >= index; i--){
            if(copyNumbers[i] > copyNumbers[index -1]){
                heap = i;
                break;
            }
        }

        //交换
        int temp = copyNumbers[heap];
        copyNumbers[heap] = copyNumbers[index - 1];
        copyNumbers[index - 1] = temp;

    }

    /**
     * 将逆序部分再逆序
     * @param index
     * @param copyNumbers
     */
    private static void reverseNumbers(int index, int[] copyNumbers) {
        int length = copyNumbers.length - index + 1;

        int offset = index + length / 2;

        for(int i = copyNumbers.length-1, j = index ; i >= offset; i--, j++){

            int temp = copyNumbers[i];
            copyNumbers[i] = copyNumbers[j];
            copyNumbers[j] = temp;

        }
    }

    public static void main(String[] args) {
        int[] nums = {5,4,3,2,1};
        int[] results = findNearestNumber(nums);
        for(int i = 0; i < results.length; i++)
            System.out.print(results[i]);
    }
}
