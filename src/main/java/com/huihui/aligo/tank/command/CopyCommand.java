package com.huihui.aligo.tank.command;

/**
 * 复制命令
 *
 * @author minghui.y
 * @create 2020-12-19 11:17 上午
 **/
public class CopyCommand extends AbstractCommand {

    private Content content;

    public CopyCommand(Content content) {
        this.content = content;
    }

    @Override
    public void doIt() {
        content.text = content.text + content.text;
    }

    @Override
    public void undo() {
        content.text = content.text.substring( 0, content.text.length() / 2 );
    }
}
