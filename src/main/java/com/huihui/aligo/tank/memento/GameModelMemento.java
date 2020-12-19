package com.huihui.aligo.tank.memento;

import com.huihui.aligo.tank.model.GameModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * GameModel的快照
 *
 * @author minghui.y
 * @create 2020-12-19 1:02 下午
 **/
public class GameModelMemento {

    private static final String SAVE_PATH = "src/main/resources/gameModel.data";


    /**
     * 将GameModel存储文件
     * @param gameModel
     */
    public static void save( GameModel gameModel ) {
        if (gameModel == null) {
            return;
        }

        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream( new FileOutputStream( SAVE_PATH ) );
            oos.writeObject( gameModel );
            System.out.println("完成GameModel在" + System.currentTimeMillis() + "时刻的快照");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 将GameModel从文件中加载到内存
     * @return
     */
    public static GameModel load() {
        ObjectInputStream ois = null;
        GameModel gameModel = null;

        try {
            ois = new ObjectInputStream( new FileInputStream( SAVE_PATH ) );
            gameModel = (GameModel) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return gameModel;
    }

    public static void main( String[] args ) {

        System.out.println(System.getProperty( "user.dir" ));

    }

}
