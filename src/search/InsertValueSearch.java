package search;

/**
 * 插入查找
 *
 *  1) 插值查找原理介绍: 插值查找算法类似于二分查找，不同的是插值查找每次从自适应 mid 处开始查找。
 * 2) 将折半查找中的求mid索引的公式,low表示左边索引left,high表示右边索引right. key 就是前面我们讲的 findVal
 *   3) int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]) ;
 *    插值索引
 *    对应前面的代码公式:
        int mid=left+(right – left)*(findVal – arr[left])/(arr[right] – arr[left])

 8.4.2 插值查找注意事项:
 1) 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找, 速度较快.
 2) 关键字分布不均匀的情况下，该方法不一定比折半查找要好
 * */
public class InsertValueSearch {

    public static void main(String[] args){
        int arr[] = { 1, 8, 10, 89,1000, 1001,1005,1100,1234 };
        int index = insertValueSearch(arr,0,arr.length - 1,1000);
        System.out.println("index = " + index);

    }

    /**
     * @param arr  要查找的数组
     * @param  left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     *
     *
     * */
     public static int insertValueSearch(int[] arr,int left,int right,int findVal){
         System.out.println("插入查找法~~~");
         //注意:findVal < arr[0] 和 findVal > arr[arr.length - 1] 必须需要
         // 否则我们得到的 mid 可能越界
         if(left > right || findVal < arr[0] || findVal > arr[arr.length -1]){
             return -1;
         }
         int mid =  left + (findVal - arr[left])*(right - left) / (arr[right] - arr[left]);
         int midValue = arr[mid];
         if(findVal > midValue){
             //说明应该向右边递归
             return  insertValueSearch(arr,mid+1,right,findVal);
         }else if(findVal < midValue){
             //说明向左递归查找
             return  insertValueSearch(arr,left,mid -1,findVal);
         }else{
             return  mid;
         }

    }

}
