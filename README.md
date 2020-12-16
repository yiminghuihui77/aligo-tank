#坦克大战
_@Author_: minghui.y  
_@Date_: 2020-12-13  
_@Github_: <https://github.com/yiminghuihui77>  
_@CSDN_: <https://blog.csdn.net/SomeoneMH>  

![效果图](src/main/resources/images/效果图2.png)

##技术栈:

###1、AWT桌面程序

###2、设计模式
    * 单例模式： 生产产品族的工厂，如SimpleGameFactory、MultiGameFactory
 
    * 策略模式： 根据坦克是敌是友，采用不同的开火策略
 
    * 抽象工厂&工厂方法：创建产品族（坦克、子弹、爆炸），抽象工厂不易于扩展新的产品
 
    * 模板方法：坦克、子弹、爆炸的抽象基类中，定义了获取样式的抽象方法，由子类实现
 
    * 责任链: 各类model之间的碰撞检测，通过责任链处理
    
    * 门面模式(Facade): View(TankFrame)和各类model（坦克、子弹、爆炸）之间都存在交互
                       引入GameModel作为门面，相当于隔离了V和M
    * 调停者模式(Mediator): 所有model之间的冲突检测，都交给GameModel处理，各个model之间无直接接触
    
    * 装饰器模式(Decorator): 对各类model进行装饰，被装饰物为BaseModel。
                           但由于冲突检测中，对BaseModel存在类型检测，因此会导致碰撞检测失败（待解决）
                           （暂不应用）

    * 观察者模式(Observer): 事件的处理通常通过观察者模式（观察=监听）和责任链模式处理。
                          观察者模式中，多个观察者都能对事件进行处理.
                          而责任链模式中，事件在责任链传递，直到某个节点有能力处理
                          一个事件源（BaseTank）可以注册多个监听器
                          一个监听器可以监听多个事件源
                          坦克作为被观察对象，开火动作出发开火事件，FrameFireObserver和WallFireObserver被注册用于监听开火事件

    * 组合模式(Composite): 类似于目录结构，文件夹中有子文件夹和文件。组合模式用于处理树状结构

###3、网络编程（Netty）












