package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {

    private static class Node {
        private Object value;
        private Node next;

        Node(Object value) {
            this.value = value;
            this.next = null;
        }
    }

    private int size;
    private final Node head;

    public ImmutableLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private ImmutableLinkedList(Node node, int size) {
        this.head = node;
        this.size = size;
    }

    public ImmutableLinkedList(Object[] array) {
        if (array.length > 0) {
            this.head = new Node(array[0]);
            this.size = 1;

            Node tempNode = this.head;
            for (int i = 1; i < array.length; i++) {
                Node oNode = new Node(array[i]);
                tempNode.next = oNode;
                tempNode = oNode;
                this.size++;
            }
        } else {
            this.size = 0;
            this.head = null;
        }
    }

    private void checkId(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return this.addAll(this.size, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        return this.addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return this.addAll(this.size, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (this.head == null) {
            return new ImmutableLinkedList(c);
        }


        Node headCopy = new Node(this.head.value);
        Node tempNode = this.head;
        Node tempCopyNode = headCopy;

        for (int i = 0; i < index - 1; i++) {
            Node newNode = new Node(tempNode.next.value);
            tempCopyNode.next = newNode;
            tempCopyNode = newNode;
            tempNode = tempNode.next;
        }

        for (Object o : c) {
            Node oNode = new Node(o);
            tempCopyNode.next = oNode;
            tempCopyNode = oNode;
        }

        while (tempNode.next != null) {
            Node newNode = new Node(tempNode.next.value);
            tempCopyNode.next = newNode;
            tempCopyNode = newNode;
            tempNode = tempNode.next;
        }
        if (index == 0) {
            ImmutableLinkedList preAns = new ImmutableLinkedList(headCopy,
                    this.size + c.length);
            Object firstEL = preAns.getFirst();
            ImmutableLinkedList preAnswer = preAns.remove(0);
            return preAnswer.add(c.length, firstEL);
        }
        return new ImmutableLinkedList(headCopy, this.size + c.length);
    }

    @Override
    public Object get(int index) {
        checkId(index);
        Node tempNode = this.head;
        int i = 0;
        while (tempNode.next != null && i < index) {
            tempNode = tempNode.next;
            i++;
        }
        return tempNode.value;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        checkId(index);

        if (this.size == 1) {
            return new ImmutableLinkedList();
        }

        Node tempNode = this.head;
        if (index == 0) {
            tempNode = tempNode.next;
        }
        Node headCopy = new Node(tempNode.value);
        Node newCopyNode = headCopy;

        for (int i = 0; i < index - 1; i++) {
            Node newNode = new Node(tempNode.next.value);
            newCopyNode.next = newNode;
            newCopyNode = newNode;
            tempNode = tempNode.next;
        }

        if (index != 0) {
            tempNode = tempNode.next;
        }

        while (tempNode.next != null) {
            Node newNode = new Node(tempNode.next.value);
            newCopyNode.next = newNode;
            newCopyNode = newNode;
            tempNode = tempNode.next;
        }
        return new ImmutableLinkedList(headCopy, this.size - 1);
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (this.size == 0) {
            return new ImmutableLinkedList(new Node(e), 1);
        }

        Node tempNode = this.head;
        Node headCopy = new Node(tempNode.value);
        Node newCopyNode = headCopy;

        for (int i = 0; i < index; i++) {
            Node newNode = new Node(tempNode.next.value);
            newCopyNode.next = newNode;
            newCopyNode = newNode;
            tempNode = tempNode.next;
        }

        newCopyNode.value = e;

        while (tempNode.next != null) {
            Node newNode = new Node(tempNode.next.value);
            newCopyNode.next = newNode;
            newCopyNode = newNode;
            tempNode = tempNode.next;
        }
        return new ImmutableLinkedList(headCopy, this.size);
    }

    @Override
    public int indexOf(Object e) {
        Node tempNode = this.head;
        for (int i = 0; i < this.size; i++) {
            if (tempNode.value.equals(e)) {
                return i;
            }
            tempNode = tempNode.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public Object[] toArray() {
        Object[] ansArr = new Object[this.size];
        Node tempNode = this.head;
        for (int i = 0; i < this.size; i++) {
            ansArr[i] = tempNode.value;
            tempNode = tempNode.next;
        }
        return ansArr;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray()).substring(1,
                Arrays.toString(this.toArray()).length() - 1);
    }

    public ImmutableLinkedList addFirst(Object e) {
        return this.addAll(0, new Object[]{e});
    }

    public ImmutableLinkedList addLast(Object e) {
        return this.addAll(this.size, new Object[]{e});
    }

    public Object getFirst() {
        return this.get(0);
    }

    public Object getLast() {
        return this.get(this.size - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return this.remove(this.size - 1);
    }
}