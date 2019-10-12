package tree.threadedbinarytree;

/**
 * HeroNode 结点
 * */
public class HeroNode {

    private int no;
    private String name;
    private HeroNode left; //默认为null
    private HeroNode right; //默认null

    //如果leftType == 0表示指向的是左子树，如果是1表示指向的是前驱结点
    private int leftType;
    //如果rightType == 0 表示指向的是右子树，如果1表示指向后继结点
    private int rightType;


    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode[no="+no+",name="+name+"]";
    }
}
