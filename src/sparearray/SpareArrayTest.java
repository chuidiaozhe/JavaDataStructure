package sparearray;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * 稀疏数组学些
 * @link {https://www.bilibili.com/video/av54029771/?p=9}
 * */
public class SpareArrayTest {

    public static void main(String[] args){
      int[][]  dataArr  = new int[11][11];
      dataArr[2][3] = 1;
      dataArr[3][4] = 2;
      dataArr[4][5] = 1;
      dataArr[10][10] = 2;

      System.out.println(" ===== 遍历原始数据 =====");
      for (int[] row : dataArr){
          for (int item : row){
              System.out.print(String.format("%d\t",item));
          }
          System.out.println();
      }

      //统计数组里面有几个非空数据
        int count = 0;
        for (int[] row : dataArr){
            for (int item : row){
                if(item != 0){
                     count ++;
                }
            }
         }

        //创建稀疏数组
        int spareArr[][] = new int[count+1][3];

        //给稀疏数组中的值赋值
        spareArr[0][0] = 11;
        spareArr[0][1] = 11;
        spareArr[0][2] = count;

        int line =1;
        for (int i = 0; i < 11 ; i++) {
            for (int j = 0; j <  11; j++) {
                if(dataArr[i][j] != 0){
                    spareArr[line][0] = i;
                    spareArr[line][1] = j;
                    spareArr[line][2] = dataArr[i][j];
                    line++;
                }
            }
        }


        System.out.println(" ===== 遍历稀疏数据 =====");

        for (int i = 0; i < count +1; i++) {
            System.out.println(String.format("%d\t%d\t%d\t",spareArr[i][0],spareArr[i][1],spareArr[i][2]));
        }

        //还原数组

        int restoreData[][] = new int[spareArr[0][0]][spareArr[0][1]];

        for (int i = 1; i <  count + 1; i++) {
            restoreData[spareArr[i][0]][spareArr[i][1]] = spareArr[i][2];
        }

        System.out.println("数组长度：" + spareArr.length);

        System.out.println(" ===== 遍历还原后的数据 =====");
        for (int[] row : restoreData){
            for (int item : row){
                System.out.print(String.format("%d\t",item));
            }
            System.out.println();
        }

    }


}
