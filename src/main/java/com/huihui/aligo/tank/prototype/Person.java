package com.huihui.aligo.tank.prototype;

/**
 * @author minghui.y
 * @create 2020-12-19 11:57 上午
 **/
public class Person implements Cloneable {

    private String name;
    private Integer age;
    private Location location;

    public Person( String name, Integer age, Location location ) {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", location=" + location +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person p = (Person) super.clone();
        //深度复制处理
        p.location =  (Location) this.location.clone();
        return p;
    }




    public static void main( String[] args ) throws CloneNotSupportedException {
        Person personOld = new Person( "张三", 23, new Location( "浙江省杭州市", 123 ) );

        Person personNew = (Person) personOld.clone();
        personNew.location.setAddress( "浙江省台州市" );

        System.out.println(personOld);
        System.out.println(personNew);

        System.out.println(personNew == personOld);
        System.out.println(personNew.location == personOld.location);



    }
}
