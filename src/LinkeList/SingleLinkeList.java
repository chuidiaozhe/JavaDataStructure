package LinkeList;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.w3c.dom.ls.LSException;

import java.util.Currency;
import java.util.Stack;

public class SingleLinkeList {
    private HeroNode mHead;


    public HeroNode getmHead() {
        return mHead;
    }

    public SingleLinkeList() {
        mHead = new HeroNode(0, "", "");
        mHead.setNext(null);
    }

    //添加一条记录
    public void add(HeroNode node) {
        HeroNode temp = mHead;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(node);
    }

    /**
     * 根据排名将英雄添加到指定位置
     */
    public void addByOrder(HeroNode node) {
        HeroNode temp = mHead;
        while (temp.getNext() != null) {
            if (temp.getId() == node.getId() || temp.getNext().getId() == node.getId()) {
//                throw new RuntimeException("不能加入相同id的数据！");
                System.out.println("不能加入相同id的数据！");
                return;
            }
            if (node.getId() > temp.getId() && node.getId() < temp.getNext().getId()) {
                node.setNext(temp.getNext());
                temp.setNext(node);
                return;
            }
            temp = temp.getNext();
        }
        temp.setNext(node);

    }

    /**
     * 将一个列表添加到此列表的后面
     */
    public void addAllList(SingleLinkeList linkeList){
        if(linkeList.getLength() == 0){
            //如果被添加的链表为空，则不做任何操作
            return;
        }
        HeroNode currentNode = mHead;
        while (currentNode.getNext() != null){
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(linkeList.getmHead().getNext());
    }

    /**
     * 将两个有序到队列按有序到顺序拼接到一起
     * */
//    public SingleLinkeList addByOrderList(SingleLinkeList linkeList){
//           SingleLinkeList singleLinkeList = new SingleLinkeList();
//
//           HeroNode currentNode = mHead.getNext();
//           HeroNode nextCurrentNode ;
//           HeroNode otherNode = linkeList.getmHead().getNext();
//           HeroNode nextOtherNode ;
//           while (currentNode.getNext() != null ||   otherNode.getNext() != null){
//
//                nextCurrentNode = currentNode.getNext();
//                nextOtherNode = otherNode.getNext();
//                if(currentNode == null || otherNode == null){
//                    break;
//                }
//               if(currentNode.getId() < currentNode.getId()){
//                   currentNode.setNext(null);
//                  singleLinkeList.add(currentNode);
//                  currentNode = nextCurrentNode;
//              }else{
//                   otherNode.setNext(null);
//                   singleLinkeList.add(otherNode);
//                   otherNode = nextOtherNode;
//               }
//           }
//
//           return  singleLinkeList;
//    }

    /**
     * 根据heronode的no来修改数据
     */
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (mHead.getNext() == null) {
            System.out.println("链表为空～");
            return;
        }
        //找到需要修改的节点，根据id编号
        //定义一个辅助变量
        HeroNode temp = mHead.getNext();
        //表示是否找到该节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;//已遍历完链表
            }

            if (temp.getId() == newHeroNode.getId()) {
                //找到
                flag = true;
                break;
            }

            temp = temp.getNext();
        }

        //根据flag是否找到要修改的节点
        if (flag) {
            temp.setName(newHeroNode.getName());
            temp.setNickName(newHeroNode.getNickName());
        } else {
            //没有找到
            addByOrder(newHeroNode);
        }

    }

    /**
     * 根据id来删除节点
     * */
    public void delete(int no){
       HeroNode temp = mHead;
       //用于标记是否有指定id的数据
       boolean flag = false;
       while(true){
           if(temp.getNext() == null){
               break;
           }
           if(temp.getNext().getId() == no){
               //找到存在的id
               flag = true;
               break;
           }
           temp = temp.getNext();
       }
       if(flag){
           temp.setNext(temp.getNext().getNext());
       }else{
           System.out.println("删除的节点不存在");
       }

    }


    /**
     * 反转数据列表
     * */
    public void reverseHead(){
        if(mHead.getNext() == null || mHead.getNext().getNext() == null){
            return;
        }
        HeroNode cur = mHead.getNext();
        HeroNode next;
        HeroNode reverseHead = new HeroNode(0,"","");
        while (cur != null){
            next = cur.getNext(); //先暂时保存当前节点的下一个节点
            cur.setNext(reverseHead.getNext()); //将cur的下一个节点指向新的链表的最前端
            reverseHead.setNext(cur); //将cur添加到新链表上
            cur = next;//让cur后移
         }

        mHead.setNext(reverseHead.getNext());
    }

    /**
     * 逆序打印
     * 说明：可以利用栈这个数据结构，将各个节点压入栈中，然后利用栈的先进后出的特点
     * */
    public void reversePrint(){
      if(mHead.getNext() == null ){
          //空的列表不做任何操作
          return;
      }
        Stack<HeroNode> stack = new Stack<>();

      //首先将数据压入栈中
      HeroNode currentNode = mHead;
      while (currentNode.getNext() != null){
          stack.push(currentNode.getNext());
          currentNode = currentNode.getNext();
      }
      //出栈打印数据
       while (!stack.isEmpty()){
           HeroNode temp = stack.pop();
           System.out.println(temp);
       }

    }





    /***
     * 获得链表的长度
     */
    public int getLength(){
        if(mHead.getNext() == null){
            return  0;
        }
        int length = 0;
        HeroNode cur = mHead.getNext();
        while (cur != null){
            length ++;
            cur = cur.getNext();
        }
        return  length;
    }

    public HeroNode findLastIndexNode(int index){
        int length = getLength();
        if(index > length){
            return  null;
        }
        HeroNode temp = mHead.getNext();
        int lastIndex = length - index;
        while (true){
            if(lastIndex == 0){
                return  temp;
            }
            lastIndex--;
            temp = temp.getNext();
        }
    }

    //显示所有集合数据
    public void list() {
        HeroNode temp = mHead;
        while (temp.getNext() != null) {
            System.out.println(temp.getNext().toString());
            temp = temp.getNext();
        }
    }
}
