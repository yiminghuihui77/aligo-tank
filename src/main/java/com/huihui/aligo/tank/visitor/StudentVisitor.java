package com.huihui.aligo.tank.visitor;

/**
 * 学生折扣访问者
 * 学生打85折
 * @author minghui.y
 * @create 2020-12-18 1:54 下午
 **/
public class StudentVisitor implements Visitor {
    @Override
    public void visitCpu( Computer.Cpu cpu ) {
        cpu.price = cpu.price * 0.85;
    }

    @Override
    public void visitBoard( Computer.Board board ) {
        board.price = board.price * 0.85;
    }

    @Override
    public void visitMemory( Computer.Memory memory ) {
        memory.price = memory.price * 0.85;
    }
}
