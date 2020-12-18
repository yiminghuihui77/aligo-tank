package com.huihui.aligo.tank.builder;

/**
 * 构建器
 * 注意：对每个属性的赋值方法，返回值都是Builder，以便流式调用
 * @author minghui.y
 * @create 2020-12-18 5:33 下午
 **/
public class PersonBuilder {

    /**
     * Builder设计为单例模式
     */
    private static final PersonBuilder INSTANCE = new PersonBuilder();
    private PersonBuilder(){
    }
    public static PersonBuilder getInstance() {
        return INSTANCE;
    }

    private Person person;

    public PersonBuilder name(String name) {
        if (person == null) {
           person = new Person();
        }
        person.setName( name );
        return this;
    }

    public PersonBuilder age(int age) {
        if (person == null) {
            person = new Person();
        }
        person.setAge( age );
        return this;
    }

    public PersonBuilder sex(String sex) {
        if (person == null) {
            person = new Person();
        }
        person.setSex( sex );
        return this;
    }

    public PersonBuilder weight(double weight) {
        if (person == null) {
            person = new Person();
        }
        person.setWeight( weight );
        return this;
    }

    public PersonBuilder height(double height) {
        if (person == null) {
            person = new Person();
        }
        person.setHeight( height );
        return this;
    }

    /**
     * 最终构建的方法
     * @return
     */
    public Person build() {
        Person result = person;
        //下次构建，确保使用的是新的person对象，而不是上一次的！！
        person = null;
        return result;
    }


    public static void main( String[] args ) {

        Person personH = PersonBuilder.getInstance().name( "灰灰" )
                .age( 24 )
                .sex( "男" )
                .weight( 65 )
                .height( 178 )
                .build();
        Person personF = PersonBuilder.getInstance().name( "菲菲" )
                .age( 24 )
                .sex( "女" )
                .weight( 90 )
                .height( 158 )
                .build();

        System.out.println(personH);
        System.out.println(personF);

    }

}
