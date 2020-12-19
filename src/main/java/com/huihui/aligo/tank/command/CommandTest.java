package com.huihui.aligo.tank.command;

/**
 * @author minghui.y
 * @create 2020-12-19 11:23 上午
 **/
public class CommandTest {

    public static void main( String[] args ) {

        Content content = new Content("I am a command");

        InsertCommand insertCommand = new InsertCommand( content );
        CopyCommand copyCommand = new CopyCommand( content );
        DeleteCommand deleteCommand = new DeleteCommand( content );

        insertCommand.doIt();
        copyCommand.doIt();
        deleteCommand.doIt();

        System.out.println(content.text);

        deleteCommand.undo();
        copyCommand.undo();
        insertCommand.undo();

        System.out.println(content.text);



    }
}
