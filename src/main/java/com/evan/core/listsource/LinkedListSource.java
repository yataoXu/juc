package com.evan.core.listsource;

import java.util.Objects;

/**
 * @Description
 * @ClassName LinkedListSource
 * @Author Evan
 * @date 2020.03.23 18:56
 */
public class LinkedListSource<E> {

    transient int size = 0;


    transient Node<E> first;

    transient Node<E> last;

    int modCount = 0;


    E unlink(Node<E> e) {
        // 保存指定节点的值
        final E unlinkNode = e.item;
        // 获取指定节点的下个节点prev
        final Node prev = e.prev;
        // 获取指定节点的下个节点next
        final Node next = e.next;

        // 说明e为头节点
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            e.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            e.next = null;
        }
        e.next = null;
        size--;
//        modCount++;
        //返回被删除的节点的值
        return unlinkNode;
    }

    void add(Node<E> e) {
        //使节点l指向原来的尾结点
        final Node<E> l = last;
        final Node<E> newNode = new Node(l, e, null);

        last = newNode;

        if (l == null) {
            first = last;
        } else {
            l.next = newNode;
        }

        size++;
        modCount++;

    }


    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(E item, Node<E> prev, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}



