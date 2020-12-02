package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList lst;

    public Queue() {
        this.lst = new ImmutableLinkedList();
    }

    public Object peek() {
        return this.lst.getFirst();
    }

    public Object dequeue() {
        Object el = this.lst.getFirst();
        this.lst = this.lst.removeFirst();
        return el;
    }

    public void enqueue(Object e) {
        this.lst = this.lst.addLast(e);
    }

    public int size(){
        return this.lst.size();
    }
}
