package com.huihui.aligo.io.chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 聊天客户端
 *
 * @author minghui.y
 * @create 2020-12-21 10:45 上午
 **/
public class ChatClientFrame extends Frame {

    private TextArea ta = new TextArea();
    private TextField tf = new TextField();
    private NettyClient client;

    public ChatClientFrame() {
        this.setResizable( false );
        this.setSize( 800, 600 );
        this.setTitle( "chat client @yiminghuihui77" );
        this.setLocation( 300, 100 );
        //添加单行文本&多行文本
        this.add( ta, BorderLayout.CENTER );
        this.add( tf, BorderLayout.SOUTH );
        ta.setBackground( Color.LIGHT_GRAY );
        ta.setEditable( false );
        //输入框监听回车
        tf.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                String currentData = tf.getText();
                //显示框显示客户端的输入
                ta.setText( ta.getText() + "\n" +  "client: " + currentData );
                //清空输入框
                tf.setText( null );
                //通过netty客户端，向服务端发送数据
                client.send2Server( currentData );
            }
        } );

        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                //向服务端发送消息，通知关闭
                client.send2Server( "EXIT" );
                System.exit( 0 );
            }
        } );
        //初始化完成后，显示界面
        this.setVisible( true );

        //客户端连接服务端
        client = new NettyClient(tf, ta);
        client.connect();

    }



    public static void main( String[] args ) {
        new ChatClientFrame();
    }

}
