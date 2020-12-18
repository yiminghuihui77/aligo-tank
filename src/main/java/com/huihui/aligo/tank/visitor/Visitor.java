package com.huihui.aligo.tank.visitor;
import static com.huihui.aligo.tank.visitor.Computer.*;

/**
 * 访问者接口
 *
 * @author minghui.y
 * @create 2020-12-18 1:49 下午
 **/
public interface Visitor {

    void visitCpu( Cpu cpu );

    void visitBoard( Board board );

    void visitMemory(Memory memory);
}
