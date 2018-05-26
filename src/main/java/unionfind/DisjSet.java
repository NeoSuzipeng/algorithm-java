package unionfind;

/**
 * Created by 10353 on 2018/4/30.
 * 基本并查集操作
 *
 */
public class DisjSet {

    private int[] array;

    public DisjSet(int capacity){
        array = new int[capacity];
        for (int i = 0; i < capacity; i++)
            array[i] = -1;
    }

    public boolean unin(int root1, int root2){
        return false;
    }

    public int find(int key){
        return 0;
    }
}
