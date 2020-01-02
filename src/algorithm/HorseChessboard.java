package algorithm;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘算法
 */
public class HorseChessboard {
    private static int X; // 棋盘的列数
    private static int Y; // 棋盘的行数

    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性，标记是否棋盘的所有位置都被访问
    private static boolean finished;//如果为true，表示成功

    public static void main(String args[]) {
     System.out.println("骑士周游算法，开始运行~~");
     //测试骑士周游算法是否正确
        X  = 8;
        Y = 8;

        int row  =1;//马儿初始位置的行，从1开始编号
        int column = 1;//马儿初始位置的列，从1开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X*Y]; //初始值都是false
        //测试一下耗时
        long start = System.currentTimeMillis();
         traversalChessboard(chessboard,row-1,column-1,1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "  ms");

        //输出棋盘的最后情况
        for (int[] rows : chessboard){
            for (int step: rows){
                System.out.print(step+"\t");
            }
            System.out.println();
        }
    }


    public static void traversalChessboard(int[][] chessboard,int row,int column,int step){
        chessboard[row][column] =  step;

        visited[row*X  + column] = true;//标记该位置已经访问
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column,row));
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()){
            Point p = ps.remove(0);//取出下一个可以走的位置
            //判断该节点是否已经访问过
            if (!visited[p.y*X + p.x]){
                //说明还没有访问过
                traversalChessboard(chessboard,p.y,p.x,step+1);
            }
        }

        //判断马儿是否完成了任务，使用step和应该走的步数比较
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        //说明: step < X * Y 成立的情况有两种
       //1. 棋盘到目前位置,仍然没有走完
       //2. 棋盘处于一个回溯过程

        if (step < X*Y && !finished){
            chessboard[row][column] = 0;
            visited[row*X + column] = false;
        }else {
            finished = true;
        }
    }

    /**
     * 功能：根据当前位置（Point对象），计算马儿还能走那些位置（Point）,并放入到一个集合中，最多有8个位置
     *
     * @param curPoint
     * */
    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<>();
        //创建一个Point
        Point p1 = new Point();
        //表示马儿可以走5这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
             ps.add(new Point(p1));
        }
        //判断马儿可以走6这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
             ps.add(new Point(p1));
        }
        //判断马儿可以走 7 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
             ps.add(new Point(p1));
        }
        //判断马儿可以走 0 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
             ps.add(new Point(p1));
        }
        //判断马儿可以走 1 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
             ps.add(new Point(p1));
        }
        //判断马儿可以走 2 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
             ps.add(new Point(p1));
        }
        //判断马儿可以走 3 这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
             ps.add(new Point(p1));
        }
        //判断马儿可以走 4 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
             ps.add(new Point(p1));
        }
        return ps;

    }

    public static  void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步所有位置个数
                int count1 = next(o1).size();
                //获取到o2的下一步所有位置个数
                int count2 = next(o2).size();
                return count1- count2;
            }
        });
    }
}
