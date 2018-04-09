package binarytree;

/**
 * Created by 10353 on 2018/3/20.
 * 二叉搜索树实现
 * 备注：对于重复数据不做处理
 *      重复数据的正常处理方式为Node添加一个计数器
 */
public class BinarySearchTree<T extends Comparable<T>> {


    private Node root;  //根节点

    public BinarySearchTree(){
        root = null;
    }

    /**
     * 清空二叉树
     */
    public void makeEmpty(){
        root = null;
    }

    /**
     * 判断二叉树是否为空
     * @return
     */
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * 判断二叉树中是否存在某元素
     * @param x
     * @return
     */
    public boolean contains(T x){
        return contains(x, root);
    }

    /**
     * contain函数的内部实现逻辑
     * 1.如果所查元素小于当前节点，继续查找左孩子
     * 2.如果所查元素大于当前节点，继续查找右孩子
     * @param x
     * @param target
     * @return
     */
    private boolean contains(T x, Node target){
        if( target == null)
            return false;

        int comparableResult = x.compareTo(target.element); //优化

        if(comparableResult < 0)
            return contains(x, target.left);

        if(comparableResult > 0)
            return contains(x, target.right);

        return true;
    }

    /**
     * 查找二叉树最大值
     * @return
     */
    public T findMax(){
        if(isEmpty())
            throw new RuntimeException();

        return findMax(root).element;
    }


    /**
     * 查找最大值内部逻辑
     * 循环查最底层的右孩子
     * @param target
     * @return
     */
    private Node findMax(Node target){
        if(target != null)
            while(target.right != null)
                findMax(target.right);
        return target;
    }

    /**
     * 查找二叉树最小值
     * @return
     */
    public T findMin(){
        if(isEmpty())
            throw new RuntimeException();

        return findMin(root).element;
    }

    /**
     * 查找最小值内部逻辑
     * 循环查最底层的左孩子
     * @param target
     * @return
     */
    private Node findMin(Node target){
        if(target != null)
            while(target.left!= null)
                findMax(target.left);
        return target;
    }

    /**
     * 插入元素
     * @param element
     */
    public void insert(T element){
        insert(element, root);
    }

    /**
     * 插入元素内部逻辑
     * @param element
     * @param target
     * @return
     */
    private Node insert(T element, Node target){
        if(target == null)
            target = new Node(element, null, null);

        int comparableResult = element.compareTo(target.element); //优化

        if(comparableResult < 0)
            target.left = insert(element, target.left);
        if(comparableResult > 0)
            target.right = insert(element, target.right);
        else     //重复元素逻辑跳过
            ;

        return target;
    }

    /**
     * 删除任意节点
     * @param element
     * @return
     */
    public void remove(T element){
        remove(element, root);
    }

    /**
     * 删除任意节点内部实现
     * @param element
     * @param target
     * @return
     */
    private  Node remove(T element, Node target){
        if(target == null)
            return null;

        int comparableResult = element.compareTo(target.element); //优化
        //递归查找所要删除的值
        if(comparableResult < 0) {
            target.left = remove(element, target.left);
            return target;
        }

        if(comparableResult > 0) {
            target.right = remove(element, target.right);
            return target;
        }

        else {
            if(target.left == null){
                Node rightNode = target.right;  //暗含了另一种情况即左右子树都为空
                target.right = null;
                return rightNode;
            }
            if(target.right == null){
                Node leftNode = target.left;
                target.left = null;
                return leftNode;
            }

            //左右子树都存在
            //找到最小节点
            Node successor = new Node(findMin(target.right));
            //删除最小节点
            successor.right = removeMin(target.right);
            successor.left = target.left;
            target.left = target.right = null;
            return successor;
        }
    }

    private Node removeMin(Node target){
        if(target.left == null){
            target = target.right;
            target.right = null;
            return target;
        }
        target.left = removeMin(target.left);
        return target;
    }

    private Node removeMax(Node target){
        while(target.right == null){
            target = target.left;
            target.left = null;
            return target;
        }
        target.right = removeMax(target.right);
        return target;
    }


    /**
     * 内部节点类
     * @param
     */
    private class Node{
        private T element; //节点元素

        private Node left;  //左孩子
        private Node right; //右孩子

       public Node(T element, Node left, Node right){
           this.element = element;
           this.left = left;
           this.right = right;
       }

        public Node(Node oldNode){
            this.element = oldNode.element;
            this.right = oldNode.right;
            this.left = oldNode.left;
        }
    }

    public static void main(String[] args) {
        int N = 1000000;

        // 创建一个数组，包含[0...N)的所有元素
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++)
            arr[i] = new Integer(i);

        // 打乱数组顺序
        for(int i = 0 ; i < N ; i ++){
            int pos = (int) (Math.random() * (i+1));
            Integer t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = arr[pos];
        }
        // 由于我们实现的二分搜索树不是平衡二叉树，
        // 所以如果按照顺序插入一组数据，我们的二分搜索树会退化成为一个链表

        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        for(int i = 0 ; i < N ; i ++)
            bst.insert(new Integer(arr[i]));
    }

}
