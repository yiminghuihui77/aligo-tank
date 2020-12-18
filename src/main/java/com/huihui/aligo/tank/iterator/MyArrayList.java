package com.huihui.aligo.tank.iterator;

import java.util.NoSuchElementException;

/**
 * @author minghui.y
 * @create 2020-12-17 6:02 下午
 **/
public class MyArrayList<T> implements MyCollection<T> {

    private Object[] list = new Object[10];

    private int size;



    @Override
    public void add( T t ) {
        list[size++] = t;
    }

    @Override
    public T get(int index) {
        return (T) list[index];
    }

    @Override
    public Iterator<T> iterator() {
        //返回一个迭代器
        return new ArrayListIterator();
    }


    /**
     * 一个容器的迭代器实现，一般仅该容器自己使用，因此定义为内部类形式
     * 迭代器需要知道容器的内部结构，因此需要访问到容器内部，使用内部类或者迭代器持有容器的引用均可实现
     */
    private class ArrayListIterator implements Iterator<T> {

        /**
         * 偏移
         */
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (cursor > size) {
                throw new NoSuchElementException();
            }
            return (T) list[cursor++];
        }
    }


    public static void main( String[] args ) {

        MyArrayList<String> list = new MyArrayList<>();
        list.add( "a" );
        list.add( "b" );
        list.add( "c" );

        System.out.println(list.get(0));

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}
