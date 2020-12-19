package com.huihui.aligo.tank.command;

/**
 * 插入命令
 *
 * @author minghui.y
 * @create 2020-12-19 11:13 上午
 **/
public class InsertCommand extends AbstractCommand {
    private Content content;
    private String targetStr = "hello tank!";

    public InsertCommand(Content content) {
        this.content = content;
    }

    @Override
    public void doIt() {
        content.text = content.text + targetStr;
    }

    @Override
    public void undo() {
        content.text = content.text.substring( 0, content.text.length() - targetStr.length());
    }
}
