package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储对应的邻结矩阵
    private int numOfEdges;//表示边的数目
    //定义给数组boolean[]，记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args){
        int n = 8; // 结点的个数
//        String vertexs[] = {"A","B","C","D","E"};
        String vertexs[] = {"1","2","3","4","5","6","7","8"};

        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertex:vertexs){
            graph.insertVertex(vertex);
        }
        //添加边
//        graph.insertEdge(0,1,1);
//        graph.insertEdge(0,2,1);
//        graph.insertEdge(1,2,1);
//        graph.insertEdge(1,3,1);
//        graph.insertEdge(1,4,1);

        //更新边的关系
         graph.insertEdge(0, 1, 1);
         graph.insertEdge(0, 2, 1);
         graph.insertEdge(1, 3, 1);
         graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);


        graph.showGraph();

        System.out.println("深度遍历");
        graph.dfs();
        System.out.println("广度遍历");
        graph.bfs();
     }


     /**
      * 深度优先遍历算法
      * i第一个就是0
      * */
     private void dfs(boolean[] isVisited,int i){
        //首先我们访问该结点，输出
         System.out.print(getValueByIndex(i)+"->");
         //将结点设置为已经访问
         isVisited[i] = true;
         //查找结点i的第一个邻接结点w
         int w = getFirstNeighbor(i);
         while (w != -1){
             //说明有
             if(!isVisited[w]){
                 dfs(isVisited,w);
             }
             //如果w结点已经被访问过
             w = getNextNeighbor(i,w);
         }
     }

     /**
      * 对dfs进行一个重载，遍历我们所有的结点，并进行dfs
      **/
     public void dfs(){
         isVisited = new boolean[vertexList.size()];
         //遍历所有的结点，进入dfs[回溯]
         for (int i = 0;i < getNumOfVertex();i++){
             if(!isVisited[i]){
                 dfs(isVisited,i);
             }
         }
     }


     /**
      * 对一个结点进行广度优先遍历
      * */
     private void bfs(boolean[] isVisited, int i){
         int u;//表示队列的头结点对应下标
         int w;//邻接结点w
         //队列，记录结点访问的顺序
         LinkedList queue = new LinkedList();
         //访问结点，输出结点信息
         System.out.print(getValueByIndex(i)+"=>");
         //标记为已访问
         isVisited[i] = true;
         //将结点加入到队列
         queue.add(i);

         while (!queue.isEmpty()){
             //取出队列的头结点下标
              u = (int) queue.removeFirst();
              //得到第一个邻接结点的下标w
              w = getFirstNeighbor(u);
              while (w != -1){
                  //找到
                  //是否访问过
                  if(!isVisited[w]){
                      System.out.print(getValueByIndex(w)+"=>");
                      //标记已经访问
                      isVisited[w] =true;
                      //入队
                           queue.addLast(w);
                  }
                  //已u为前驱点，找w后面的一个邻接点
                  w =  getNextNeighbor(u,w);//体现出我们的广度优先
              }
         }
     }


    /**
     * 遍历所有的结点，都进行广度优先搜索
     */
    public void bfs(){
           isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
     }

     /**
      * 得到第一个邻接结点的下标w
      *
      * @param  index
      * @return 如果存在就返回对应的下标，否则返回-1
      * */
     public int getFirstNeighbor(int index){
         for (int i = 0; i <  vertexList.size(); i++) {
             if (edges[index][i] > 0){
                 return i;
             }
         }
         return -1;
     }

     /**
      * 根据前一个邻接结点的下标来获取下一个邻接结点
      * */
     public int getNextNeighbor(int v1,int v2){
         for (int j = v2 +1; j < vertexList.size() ; j++){
             if(edges[v1][j] > 0){
                 return j;
             }
         }
         return -1;
     }

    //构造器
    public Graph(int n){
        //初始化矩阵和vertexlist
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }


    /**
    * 返回结点的个数
    * */
    public int getNumOfVertex(){
        return vertexList.size();
    }

    /**
     * 显示对应的矩阵
     * */
    public void showGraph(){
        for (int[] link: edges){
            System.err.println(Arrays.toString(link));
        }
    }

    /**
     * 获得边的数目
     * */
    public int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     * 返回结点i（下标）对应的数据
     * */
    public String getValueByIndex(int i){
      return vertexList.get(i);
    }

    /**
     * 返回v1和v2的值
     * */
    public int getWeight(int v1,int v2){
         return edges[v1][v2];
    }

    /**
     * 插入结点
     * */
    public void insertVertex(String vertext){
        vertexList.add(vertext);
    }

    /**
     * 插入边
     * @param v1 表示点的下标，即第几个顶点
     * @param v2 表示第二个顶点对应的下标
     * @param weight 表示是否可以连接（也可以是权重）
     * */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
