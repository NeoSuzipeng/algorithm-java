package divide;

/**
 * Created by 10353 on 2018/5/26.
 * 动态规划设计方案
 *
 * 基本思路
 * 从数组的最左端开始扫描x[0]，一直到最右端x[i-1]为止，并记下所遇到的总和最大的子向量
 * 假设已经解决x[0..i-1]问题，将其拓展为包含x[i]的问题
 * 利用分治时的分析——最大子向量要么在x[0...i-1]中要么在x[i]
 *
 * 初始化条件
 * 最大总和的初始值为0
 */
public class DpMaximunSubvectorSum {

    private int[] array;

    public DpMaximunSubvectorSum(int[] array){
        this.array = array;
    }

    public int maxSubvectorSum(){
        return maxSum(0, array.length - 1);
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    private int maxSum(int start, int end) {

        if(start > end)
            return 0;

        int maxEndingHere = 0; //储存结束位为 i-1的最大子向量
        int maxSoFar = 0; //储存结束位为i的最大子向量

        for(int i = start; i <= end; i++){
            maxEndingHere = max(maxEndingHere + array[i], 0);
            maxSoFar = max(maxEndingHere, maxSoFar);
        }

        return maxSoFar;

    }


    private int max(int i, int j){
        return (i > j) ? i : j;
    }

    public static void main(String[] args) {

            int[] array = {31,-41,59,26,-53,58,97,-93,-23,84};
            DpMaximunSubvectorSum ms = new DpMaximunSubvectorSum(array);
            System.out.print(ms.maxSubvectorSum());

    }
}
