package divide;

/**
 * Created by 10353 on 2018/4/30.
 * 分治算法设计
 * 最大子向量和问题
 * 例如 输入 【31 -41 59 26 -53 58 97 -93 -23 84】
 * 输出 187
 * 定义：
 * 1.向量允许负值
 * 2.零向量的最大子序列为0
 * 3.全负向量的最大子序列为0
 * 4，单一向量如果为正的最大子序列本身,如果为负最大子序列为0
 */
public class DivMaximunSubvectorSum {

    private int[] array;

    public DivMaximunSubvectorSum(){

    }

    public DivMaximunSubvectorSum(int[] array){
        this.array = array;
    }

    public int maxSubvectorSum(){
        return maxSum(0, array.length - 1);
    }

    private int maxSum(int left, int right){
        if(left > right)
            return 0;
        if(left == right)
          return max(0,array[left]);

        int mid = left + (right - left) / 2;

        int lsum = 0;
        int lmaxSum = 0;   //从边界mid开始左半边最大子向量

        for(int i = mid; i >= left; i--){
            lsum += array[i];
            lmaxSum = max(lmaxSum, lsum);
        }

        int rsum = 0;
        int rmaxSum = 0;   //从边界mid开始右半边最大子向量

        for(int i = mid+1; i <= right; i++){
            rsum += array[i];
            rmaxSum = max(rmaxSum, rsum);
        }

        return max(rmaxSum + lmaxSum, maxSum(left,mid), maxSum(mid+1, right));
    }

    /**
     * 两数比较返回最大值
     * @param i
     * @param j
     * @return maximun of j or i
     */
    private int max(int i, int j){
        return (i > j) ? i : j;
    }

    /**
     * 三数比较返回最大值
     * @param i
     * @param j
     * @param k
     * @return maximun of j or i or k
     */
    private int max(int i, int j,int k){
        int uncertainMax = (k > j) ? k : j;
        return (uncertainMax > i ) ? uncertainMax : i;
    }


    public static void main(String[] args) {

        int[] array = {31,-41,59,26,-53,58,97,-93,-23,84};
        DivMaximunSubvectorSum ms = new DivMaximunSubvectorSum(array);
        System.out.print(ms.maxSubvectorSum());

    }
}
