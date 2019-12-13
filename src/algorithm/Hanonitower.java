package algorithm;

/**
 * 分治法是一种很重要的算法。字面上的解释是“分而治之”，就是把一个复杂的问题分成两个或更多的相同或 相似的子问题，再把子问题分成更小的子问题......
 * 直到最后子问题可以简单的直接求解，原问题的解即子问题 的解的合并。这个技巧是很多高效算法的基础，如排序算法(快速排序，归并排序)，傅立叶变换(快速傅立叶变 换)......
 *
 * */
public class Hanonitower {

    public static void main(String[] args){
          hanoiTower(3,'A','B','C');
    }

    /**
     * 汉诺塔的移动方法
     * 使用分治算法
     * */
    public static void  hanoiTower(int num,char a,char b,char c){
        if(num == 1){
            //如果只有一个盘，直接把a移动到c
            System.out.println("从" + a +" -> "  + c);
        }else{
//            如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的盘 2. 上面的盘
//            2) 先把最上面的盘A->B
//            3) 把最下边的盘 A->C
//            4) 把B塔的所有盘从B->C

            //先把最上面的所有盘a->B,移动过程会使用c
            hanoiTower(num - 1,a,c,b);
            //把最下面的盘a->c
            System.out.println("从" + a +" -> "  + c);
            //把b塔的所有盘从B->C ,移动过程使用a塔
            hanoiTower(num - 1,b,a,c);
        }
    }

}
