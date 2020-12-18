package com.huihui.aligo.tank.visitor;

/**
 * 企业折扣访问者
 * 企业团购打95折
 * @author minghui.y
 * @create 2020-12-18 1:57 下午
 **/
public class CompanyVisitor implements Visitor {
    @Override
    public void visitCpu( Computer.Cpu cpu ) {
        cpu.price = cpu.price * 0.95;
    }

    @Override
    public void visitBoard( Computer.Board board ) {
        board.price = board.price * 0.95;
    }

    @Override
    public void visitMemory( Computer.Memory memory ) {
        memory.price = memory.price * 0.95;
    }
}
