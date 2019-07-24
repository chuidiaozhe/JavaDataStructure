package queue;

import java.util.Scanner;

public class TestQueueArray {
   public static void  main(String[] args){
       QueueArray<Integer> queueArray = new QueueArray(3);
       char key = ' ';//接收用户输入
       Scanner scanner = new Scanner(System.in);
       boolean loop = true;
       while (loop){
           System.out.println("s(show):显示队列");
           System.out.println("e(sxit):退出程序");
           System.out.println("a(add):添加数据到队列");
           System.out.println("g(get):从队列取出数据");
           System.out.println("h(head):查看队头的数据");

           key = scanner.next().charAt(0);//接收一个字符

           switch (key){
               case 's':
                   queueArray.showQueue();
                   break;

               case 'a':
                   System.out.println("输出一个数");
                   int value = scanner.nextInt();
                   queueArray.addQueue(value);
                   break;

               case 'g':
                   //取出数据

                   try {
                       int res = queueArray.getQueue();
                       System.out.println("取出的数据是："+res);
                   }catch (Exception e){
                       System.out.println(e.getMessage());
                   }
               break;

               case 'h':
                   int res = queueArray.headQueue();
                   System.out.println("队列头的数据："+res);
                   break;
               case 'e':
                   scanner.close();
                   loop = false;
                   System.exit(0 );
                   break;
                   default:
                       break;
           }
       }
   }

}
