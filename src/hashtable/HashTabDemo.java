package hashtable;

import java.util.EnumMap;
import java.util.Scanner;

/**
 * Hash表的学习
 * <p>
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,名字,住址..),当输入该员工的 id 时,
 * 要求查找到该员工的 所有信息.
 * 要求:
 * 1) 不使用数据库,,速度越快越好=>哈希表(散列)
 * 2) 添加时，保证按照 id 从低到高插入 [课后思考:如果 id 不是从低到高插入，但要求各条链表仍是从低到
 * 高，怎么解决?]
 * 3) 使用链表来实现哈希表, 该链表不带表头[即: 链表的第一个结点就存放雇员信息]
 */
public class HashTabDemo {

    public static void main(String[] args) {
//创建哈希表
        HashTab hashTab = new HashTab(7);
//写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入 id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next(); //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的 id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
 }
 }


//创建 HashTab 管理多条链表
   class HashTab {
    EmpLinkedList[] empLinkedListArray;
    private int size; //表示有多少条链表

    public HashTab(int size) {
        this.size = size;
        //初始化
        empLinkedListArray = new EmpLinkedList[size]; //?留一个坑, 这时不要分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的 id ,得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将 emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }


    //遍历所有的链表,遍历 hashtab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }


    //根据输入的 id,查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {//找到
            System.out.printf("在第%d 条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }


    public int hashFun(int id) {
        return id % size;
    }
}


/**
 * 创建EmpLinkedList 表示链表
 */
   class EmpLinkedList {
    //头指针，执行第一个 Emp,因此我们这个链表的 head 是直接指向第一个 Emp
    private Emp head;

    /**
     * 添加雇员到链表
     * <p>
     * 雇员按id的大小排列，如果id相同则更新名称
     */
    public void add(Emp emp) {
        if (emp == null) {
            return;
        }
        if (head == null) {
            head = emp;
        } else {
            //按从小到大的顺序插入
            Emp curEmp = head;
            while (true) {
                if(emp.id < head.id){
                    //插入到头部
                    System.out.println("插入到第一个位置！");
                    emp.next = head;
                    head = emp;
                    break;
                } else  if (curEmp.id < emp.id && (curEmp.next == null || curEmp.next.id > emp.id)) {
                    //执行插入
                    emp.next = curEmp.next;
                    curEmp.next = emp;
                    break;
                }
                if (curEmp.id == emp.id) {
                    System.out.println("相同的id执行更新");
                    curEmp.name = emp.name;
                    break;
                } else {
                    curEmp = curEmp.next;
                }
            }

        }

    }

    /**
     * 遍历链表雇员信息
     */
    public void list(int no) {
        if (head == null) { //说明链表为空
            System.out.println("第 " + (no + 1) + " 链表为空");
            return;
        }
        System.out.print("第 " + (no + 1) + " 链表的信息为");
        Emp curEmp = head; //辅助指针
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {//说明 curEmp 已经是最后结点
                break;
            }
            curEmp = curEmp.next; //后移，遍历 }
            System.out.println();
        }
    }

    /**
     * 根据id来查找雇员
     */
      Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return null;
        }

        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}


   class Emp {
    public int id;
    public String name;

    public Emp next; //next默认为null


    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}



