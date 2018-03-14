package sort.complexsort;

import Util.SortUtil;

/**
 * Created by 10353 on 2018/2/8.
 * 索引堆排序
 * 解决的问题：
 * 从N个元素的堆中找到第M个元素如果使用正常的堆需要将其拍好序然后进行获取时间复杂度为O（n logn）
 * 使用索引堆是解决这个问题的时间复杂度为O（1）
 * 基本思想：
 * 创建堆时为元素维护一个索引，将索引数组进行堆化heapify
 */
public class IndexHeapSort<E extends Comparable> {

    private E[] data;    //底层存储元素

    private Integer[] indexes; //底层元素的索引数组

    private int count;   //当前堆元素数量

    private int capacity;  //堆容量

    private static int DEFALUT_CAPACITY = 11; //默认堆容量

    /**
     * 默认构造函数
     */
    public IndexHeapSort(){
        initHeap(DEFALUT_CAPACITY);
    }

    /**
     * 构造一个容量为capacity的空堆
     * @param capacity
     */
    public IndexHeapSort(int capacity){
        initHeap(capacity);
    }



    /**
     * 初始化堆
     * @param capacity
     */
    public void initHeap(int capacity){
        data = (E[])new Comparable[capacity + 1];
        indexes = new Integer[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    /**
     * 返回堆元素数量
     * @return count 堆元素数量
     */
    public int size(){
        return count;
    }

    /**
     * 判断堆是否为空
     * @return
     */
    public boolean isEmpty(){
        return count == 0;
    }


    /**
     * 向最大索引堆中插入一个新的元素, 新元素的索引为index, 元素为item
     * 传入的i对用户而言,是从0索引的
     * @param e
     * @return
     */
    public boolean add(int index, E e){
        if(e == null)
            throw new NullPointerException();
        checkArrayIndexOutOfBounds();
        data[index] = e;
        indexes[count + 1] = index;
        count ++;

        shiftUp(count);
        return true;
    }

    /**
     * 堆序向上调整
     * @param index
     */
    private void shiftUp(int index){
        Integer e = indexes[index];
        //调整堆序需要满足的两个条件：1.到达根节点停止 2.父节点小于子节点
        while(index > 1 && data[index / 2].compareTo(e) < 0){
            indexes[index] = indexes[index / 2];
            index /= 2;
        }
        indexes[index] = e;
    }



    /**
     * 取出堆顶元素
     * @return
     */
    public E extractMaxItem(){
        if(count < 1)
            throw new ArrayIndexOutOfBoundsException();
        E e = data[indexes[1]];
        SortUtil.swap(indexes,1,count);
        count--;
        shiftDown(1);
        return e;
    }

    /**
     * 堆序向上调整
     * @param index
     */
    private void shiftDown(int index) {
        Integer e = indexes[index];
        //调整堆序需要满足的两个条件：1.到达根节点停止 2.父节点小于子节点
        while(index > 1 && data[index / 2].compareTo(e) < 0){
            indexes[index] = indexes[index / 2];
            index /= 2;
        }
        indexes[index] = e;
    }

    /**
     * 检查数组越界
     */
    private void checkArrayIndexOutOfBounds() {
        if(count + 1 > capacity || count + 1 < 1)
            throw new ArrayIndexOutOfBoundsException();
    }


    public static void sort(Comparable[] array, Integer n){

//        HeapSort<Comparable> heapSort = new HeapSort<Comparable>(n);
//        for( int i = 0 ; i < n ; i ++ )
//            heapSort.add(array[i]);

        HeapSort<Comparable> heapSort = new HeapSort<Comparable>(array);

        for( int i = n-1 ; i >= 0 ; i -- )
            array[i] = heapSort.extractMax();
    }

    public static void main(String[] args) {

    }
}
