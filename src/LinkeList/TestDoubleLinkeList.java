package LinkeList;

public class TestDoubleLinkeList {
    public static void main(String args[]){
        DoubleLinkeList doubleLinkeList = new DoubleLinkeList();

        DoubleHeroNode heroNode1 = new DoubleHeroNode(1,"宋江","及时雨");
        DoubleHeroNode heroNode2 = new DoubleHeroNode(2,"卢俊义","玉麒麟");
        DoubleHeroNode heroNode3 = new DoubleHeroNode(3,"吴用","智多星");
        DoubleHeroNode heroNode4 = new DoubleHeroNode(4,"武松","行者");
        DoubleHeroNode heroNode5 = new DoubleHeroNode(5,"林冲","豹子头");

        doubleLinkeList.del(10);

        doubleLinkeList.addById(heroNode5);
        doubleLinkeList.addById(heroNode3);
        doubleLinkeList.addById(heroNode2);
        doubleLinkeList.addById(heroNode4);
        doubleLinkeList.addById(heroNode1);

        doubleLinkeList.addById(new DoubleHeroNode(4,"李逵","黑旋风"));

        System.out.println("添加后的数据：");
        doubleLinkeList.list();

        System.out.println("修改后的数据");
        doubleLinkeList.update(new DoubleHeroNode(3,"鲁智深","花和尚"));
        doubleLinkeList.list();

        System.out.println("删除后的数据：");
        doubleLinkeList.del(3);
        doubleLinkeList.list();

    }
}
