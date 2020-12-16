package com.huihui.aligo.tank.composite;

/**
 * 测试
 *
 * @author minghui.y
 * @create 2020-12-16 4:34 下午
 **/
public class TreeMain {

    public static void main( String[] args ) {

        BranchNode root = new BranchNode("root");
        BranchNode bn1 = new BranchNode( "目录1" );
        BranchNode bn2 = new BranchNode( "目录2" );
        BranchNode bn3 = new BranchNode( "目录3" );
        LeafNode<String> leaf1 = new LeafNode<>( "叶子节点1", "1" );
        LeafNode<String> leaf2 = new LeafNode<>( "叶子节点2", "2" );
        LeafNode<String> leaf3 = new LeafNode<>( "叶子节点3", "3" );
        LeafNode<String> leaf4 = new LeafNode<>( "叶子节点4", "4" );

        root.addNode( bn1 );
        root.addNode( bn2 );
        root.addNode( leaf4 );

        bn1.addNode( leaf1 );
        bn3.addNode( leaf2 );
        bn1.addNode( bn3 );
        bn2.addNode( leaf3 );

        Node.printTree( root, 0 );

    }

}
