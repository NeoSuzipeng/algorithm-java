package Util;

import java.util.Arrays;

/**
 * Created by 10353 on 2018/1/31.
 * 随机生成数据最佳实践
 */
public class RandomUtil {

    /**
     * 随机生成范围[rangeL,...,rangR]的序列
     * @param n 数据量
     * @param rangeL 数据左阈值
     * @param rangeR 数据右阈值
     * @return
     */
    public static Integer[] generateRandomData(int n, int rangeL, int rangeR){
        assert rangeR >= rangeL;
//        Random random = new Random();
//        for(int i = 0; i < n; i++){
//            random.nextInt(rangeR)返回的数据范围为[0,rangeR)，灵活性欠佳
//            System.out.println(random.nextInt(rangeR - rangeL + 1) +rangeL);
//        }
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++){
            //返回带正号的 double 值，该值大于等于 0.0 且小于 1.0
            //比较Random生成随机数的方法这个方法不需要引入包
            array[i] = new Integer((int)(Math.random()*(rangeR - rangeL + 1) + rangeL));
        }
        return array;
    }

    /**
     * 生出有序度高，重复率低序列
     * @param n
     * @param noOrderNum
     * @return
     */
    public static Integer[] generateNearlyOrderdData(int n, int noOrderNum){
        Integer[] array = new Integer[n];
        for(int i = 0; i < n; i++)
            array[i] = i;
        for(int i = 0; i < noOrderNum; i++){
            SortUtil.swap(array, (int)(Math.random()*n), (int)(Math.random()*n));
        }
        return  array;
    }
    /**
     * 拷贝随机生成的数组
     * @param array
     * @return
     */
    public static Integer[] copyGenerateRandomData(Integer[] array){
        return Arrays.copyOf(array, array.length);
    }

//    public static void main(String[] args) {
//        double i = 1.9999d;
//        System.out.println((int)i);
//
//        generateRandomData(100,0,100);
//    }
}
