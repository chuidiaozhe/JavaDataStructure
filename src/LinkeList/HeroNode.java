package LinkeList;

public class HeroNode {
    private int id;
    private String name;
    private String nickName;
    private HeroNode next;

    public HeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    public HeroNode getNext() {
        return next;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        return "["+ "id =  "+id +",name = " + name+ "，nickName="+nickName+ "]";
    }
}
