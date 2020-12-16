package com.huihui.aligo.tank.composite;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * 分支节点
 *
 * @author minghui.y
 * @create 2020-12-16 4:08 下午
 **/
@Getter
@Setter
public class BranchNode extends Node {

    public BranchNode(String name) {
        this.name = name;
    }

    /**
     * 子节点
     */
    private List<Node> subNodes = new LinkedList<>();

    /**
     * 打印分支节点
     */
    @Override
    public void print() {
        System.out.println(name);
    }

    /**
     * 添加节点
     * @param node
     */
    public void addNode(Node node) {
        subNodes.add( node );
    }
}
