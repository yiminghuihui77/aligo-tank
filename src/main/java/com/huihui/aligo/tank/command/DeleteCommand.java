package com.huihui.aligo.tank.command;

/**
 * 删除命令
 *
 * @author minghui.y
 * @create 2020-12-19 11:20 上午
 **/
public class DeleteCommand extends AbstractCommand {

    private Content content;
    private String deleteStr;

    public DeleteCommand(Content content) {
        this.content = content;
    }

    @Override
    public void doIt() {
        deleteStr = content.text.substring( 0, 5 );
        content.text = content.text.substring( 5, content.text.length() );
    }

    @Override
    public void undo() {
        content.text = deleteStr + content.text;
    }
}
