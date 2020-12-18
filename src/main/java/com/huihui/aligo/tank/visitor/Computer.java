package com.huihui.aligo.tank.visitor;

/**
 * 电脑（表示数据结构的类）
 *
 * @author minghui.y
 * @create 2020-12-18 1:46 下午
 **/
public class Computer {

    private Cpu cpu = new Cpu(12.21);
    private Board board = new Board(23.32);
    private Memory memory = new Memory(34.43);

    public void accept(Visitor visitor) {
        visitor.visitCpu( cpu );
        visitor.visitBoard( board );
        visitor.visitMemory( memory );
    }

    public void printFinalPrice() {
        System.out.println("cpu折扣价：" + cpu.price);
        System.out.println("board折扣价：" + board.price);
        System.out.println("memory折扣价：" + memory.price);
    }





    public static class Cpu {
        public double price;

        public Cpu( double price ) {
            this.price = price;
        }
    }

    public static class Board {
        public double price;
        public Board( double price ) {
            this.price = price;
        }
    }

    public static class Memory {
        public double price;
        public Memory( double price ) {
            this.price = price;
        }
    }

    public static void main( String[] args ) {
        Computer computer = new Computer();

        Visitor visitorS = new StudentVisitor();
        Visitor visitorC = new CompanyVisitor();

        computer.accept( visitorS );
        computer.printFinalPrice();

        computer.accept( visitorC );
        computer.printFinalPrice();

    }

}

