package com.huihui.aligo.tank.composite;

import lombok.Getter;
import lombok.Setter;

/**
 * 树节点的抽象基类
 *
 * @author minghui.y
 * @create 2020-12-16 4:04 下午
 **/
@Getter
@Setter
public abstract class Node {

    protected String name;

    public abstract void print();

    /**
     * 打印节点
     * @param node
     * @param level
     */
    public static void printTree(Node node, int level) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < level;i++) {
            builder.append( "-" );
        }
        System.out.print(builder.toString());
        node.print();
        level++;
        //分支节点，则继续递归遍历
        if (node instanceof BranchNode) {
            for (Node subNode : ((BranchNode)node).getSubNodes()) {
                printTree( subNode, level);
            }
        }

    }
}
