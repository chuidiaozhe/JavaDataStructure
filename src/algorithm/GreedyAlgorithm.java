package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法
 * <p>
 * <p>
 * 贪心算法介绍
 * 1.贪婪算法(贪心算法)是指在对问题进行求解时，在每一步选择中都采取最好或者最优(即最有利)的选择，从而
 * 希望能够导致结果是最好或者最优的算法
 * 2.贪婪算法所得到的结果不一定是最优的结果(有时候会是最优解)，但是都是相对近似(接近)最优解的结果
 */
public class GreedyAlgorithm {
    public static void main(String args[]) {
        //创建广播电台,放入到 Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到 broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入到 map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);
        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        //创建 ArrayList, 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();
        //定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();
       //定义给 maxKey ， 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的 key
      // 如果 maxKey 不为 null , 则会加入到 selects
        String maxKey = null;

        while (allAreas.size() != 0){
            //如果allAreas不为0，则表示还没有覆盖所有的地区
            maxKey = null;
            //遍历broadcasts,取出对应的key
            for (String key : broadcasts.keySet()){
                //每次进行一次for
                tempSet.clear();
                //当前这个key能够覆盖的地区
                HashSet<String> areas =  broadcasts.get(key);
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多，就需要重置maxkey
                //tempSet.size() > boradcasts.get(maxKey).size()体现贪心算法的特点，每次都选择最优的
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }

            }

            //maxKey != null, 就应该将 maxKey 加入 selects
            if(maxKey != null){
                selects.add(maxKey);
                //将 maxKey 指向的广播电台覆盖的地区，从 allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }

        System.out.println("得到的选择结果是" + selects);//[K1,K2,K3,K5]
    }
}
