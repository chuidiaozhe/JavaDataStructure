package LinkeList;

public class TestSingleLinke {
    public static void main(String[] args){
        SingleLinkeList singleLinkeList = new SingleLinkeList();
        SingleLinkeList otherSingleLinkeList = new SingleLinkeList();
        HeroNode node1= new HeroNode(1,"宋江","及时雨");
        HeroNode node2= new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode node3= new HeroNode(3,"吴用","智多星");
        HeroNode node4= new HeroNode(4,"林冲","豹子头");
        HeroNode node5= new HeroNode(5,"武松","行者");

        HeroNode node52= new HeroNode(5,"武松2","行者2");

        HeroNode node6= new HeroNode(6,"时迁","鼓上骚");

        HeroNode node7 = new HeroNode(7,"花荣","小李广");
        HeroNode node8 = new HeroNode(8,"燕青","浪子");
        HeroNode node9 = new HeroNode(9,"鲁智深","花和尚");


//        singleLinkeList.add(node1);
//        singleLinkeList.add(node2);
//        singleLinkeList.add(node3);
//        singleLinkeList.add(node4);


        singleLinkeList.addByOrder(node4);
        singleLinkeList.addByOrder(node1);
        singleLinkeList.addByOrder(node3);
        singleLinkeList.addByOrder(node5);
        singleLinkeList.addByOrder(node8);
        singleLinkeList.addByOrder(node7);

        otherSingleLinkeList.addByOrder(node9);
        otherSingleLinkeList.addByOrder(node2);
        otherSingleLinkeList.addByOrder(node6);

        //两个列表拼接
//      SingleLinkeList addList =  singleLinkeList.addByOrderList(otherSingleLinkeList);
//      System.out.println("拼接后的列表  start");
//      addList.list();
//      System.out.println("拼接后的列表  end");

        singleLinkeList.addByOrder(node5);
        singleLinkeList.update(node52);
        singleLinkeList.update(node6);

        singleLinkeList.list();

        singleLinkeList.reverseHead();
        System.out.println();
        System.out.println("反转后的数据");
        singleLinkeList.list();
        //逆序打印数据
        System.out.println("逆序打印数据！");
        singleLinkeList.reversePrint();

        //将两个链表拼接到一起
        singleLinkeList.addAllList(otherSingleLinkeList);
        System.out.println("拼接后的数据");
        singleLinkeList.list();


        System.out.println("倒数第3个值：" + singleLinkeList.findLastIndexNode(3).getName());
        System.out.println("倒数第2个值：" + singleLinkeList.findLastIndexNode(2).getName());
        System.out.println("倒数第5个值：" + singleLinkeList.findLastIndexNode(5).getName());
        System.out.println("倒数第6个值：" + singleLinkeList.findLastIndexNode(6).getName());
        HeroNode heroNode = singleLinkeList.findLastIndexNode(8);
        if(heroNode == null){
            System.out.println("倒数第8个值：" + "空值");
        }

        System.out.println("长度：" + singleLinkeList.getLength());


        //删除测试
        singleLinkeList.delete(1);
        singleLinkeList.delete(6);
        singleLinkeList.delete(5);
        singleLinkeList.delete(2);
        singleLinkeList.delete(3);
        singleLinkeList.delete(4);
        System.out.println("删除后的数据");
        singleLinkeList.list();
        System.out.println("长度：" + singleLinkeList.getLength());

    }
}
