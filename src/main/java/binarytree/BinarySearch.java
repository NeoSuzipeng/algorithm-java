package binarytree;



/**
 * Created by 10353 on 2018/3/14.
 * 二分查找法
 * 运用分治的思想实现简单二分查找及二分查找变体
 */
public class BinarySearch {

    /**
     * 条件：数组有序,不可重复
     * @param array
     * @param n
     * @param target
     */
    public static int binarySearch1(Comparable[] array, int n, Comparable target){
        //定义边界[l...r]中查找元素
        int l = 0, r = n - 1;
        while(l <= r){
            //int mid = ( l + r ) / 2;
            //int mid = (r - l) / 2 + l;
            int mid  = (r - l) / 2 + l;
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


    /**
     * 条件：数组有序,可重复,查找第一个target对应的下标
     * @param array
     * @param n
     * @param target
     * @return target index
     */
    public static int binaraySearch2(Comparable[] array, int n, Comparable target){

        //定义边界[l...r]中查找元素
        int left = 0, right = n - 1;
        int mid;
        while(left < right){
            mid  = (right - left) / 2 + left;
            if(array[mid].compareTo(target) < 0)
                left = mid + 1;
            else
                right = mid;
        }
        if(array[left].compareTo(target) == 0)
            return left;

        return -1;
    }

    /**
     * 条件：数组有序，可重复，查找最后一个target元素
     * @param array
     * @param n
     * @param target
     * @return
     */
    public static int binaraySearch3(Comparable[] array, int n, Comparable target){

        int left = 0, right = n - 1;
        int mid;
        while(left < right){

            mid = (right - left) / 2 + left;

            if(array[mid].compareTo(target) < 0)
                //如果array[mid]小于target说明最后一个target在[mid + 1...right]，数组长度减少mid-left+1至少为1
                left = mid +1;

            else if(array[mid].compareTo(target) == 0){
                //如果array[mid]等于target说明最后一个target在[mid...right]，数组长度减少mid-1-left+1=mid-left
                //但是当right - left 等于 1时mid = (right - left) / 2 + left = left即数组长度减少0将会出现死循环
                if(left == mid)
                    return right;
                else
                    left = mid;

            }else{
                //如果array[mid]大于target说明最后一个target在[left...mid-1]，数组长度减少right-mid+1至少为1
                right = mid -1;
            }
        }

        if(array[right].compareTo(target) == 0)
            return right;

        return -1;

    }

    public static void main(String[] args) {
//        double startTime = System.currentTimeMillis();
//        Integer[] array = RandomUtil.generateOrderdData(10000000);
//        int index = binarySearch1(array, array.length, array[10]);
//        double endTime = System.currentTimeMillis();
//        System.out.println((endTime - startTime)/1000 + "s");

    }
}
