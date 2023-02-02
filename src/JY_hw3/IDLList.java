package JY_hw3;

import java.util.*;

/**
 * Assignment : 3
 * Name: Jiayin Huang
 * CWID: 10477088
 * Course: CS-570
 */

public class IDLList<E> {
    //The Inner Class Node<E>
    private class Node<E>{
        E data;
        Node<E> next;
        Node<E> prev;

        //a constructor that creates a node holding elem with error checks
        public Node(E elem) throws IllegalArgumentException{
            if(elem != null && elem instanceof E){
                this.data = elem;
                next = null;
                prev = null;
            }else{
                throw new IllegalArgumentException("Elem is invalid");
            }
        }

        //a constructor that creates a node holding elem, with next as next and prev as prev
        //with error checks
        public Node(E elem, Node<E> prev, Node<E> next) throws IllegalArgumentException {
            if (elem instanceof E && (prev instanceof Node<E> || prev == null)&& (next instanceof Node<E> || next == null)) {
                this.data = elem;
                this.prev = prev;
                this.next = next;
            }else{
                throw new IllegalArgumentException("Invalid parameters");
            }
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    //indices is an array-based list of references to nodes
    private ArrayList<Node<E>> indices;

    //creates an empty double-linked list 
    public IDLList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.indices = new ArrayList<Node<E>>();
    }

    //adds elem at position index (counting from wherever head is). It uses the index for fast access
    public boolean add (int index, E elem){
        Node<E> newNode;

        if(index < 0 || index > size){
            System.out.println("Invalid index");
            return false;
        }

        if(head == null){
            newNode = new Node<E>(elem);
            head = newNode;
            tail = newNode;
            size++;
            indices.add(0,newNode);
            return true;
        }

        if(index == 0){
            newNode = new Node<E>(elem);
            indices.get(0).prev = newNode;
            newNode.next = indices.get(0);
            head = newNode;
            size++;
            indices.add(0,newNode);
            return true;
        }

        if(index == size) {
            newNode = new Node<E>(elem);
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
            size++;
            indices.add(index, newNode);
            return true;
        }

        newNode = new Node<E>(elem);
        indices.get(index -1).next = newNode;
        newNode.prev = indices.get(index -1);
        newNode.next = indices.get(index);
        indices.get(index).prev = newNode;
        size++;
        indices.add(index,newNode);
        return true;
    }

    //adds elem at the head
    public boolean add (E elem){
        Node<E> newNode = new Node<E>(elem);
        if(size == 0){
            head = newNode;
            tail = newNode;
            size++;
            indices.add(0,newNode);
            return true;
        }
        newNode.next = indices.get(0);
        indices.get(0).prev = newNode;
        head = newNode;
        indices.add(0,newNode);
        size++;
        return true;
    }

    //adds elem as the new last element of the list (i.e. at the tail)
    public boolean append (E elem){
        Node<E> newNode = new Node<E>(elem,null,null);

        if(size == 0){
            head = newNode;
            tail = newNode;
            indices.add(0,newNode);
            size++;
            return true;
        }
        newNode.prev = indices.get(size - 1);
        indices.get((size -1)).next = newNode;
        tail = newNode;
        size++;
        indices.add(size -1,newNode);
        return true;
    }

    //returns the object at position index from the head
    //Indexing starts from 0, thus get(0) returns the head element of the list.
    public E get (int index){
        if(index < 0 || index >= size){
            System.out.println("Index out of Bounds.");
            return null;
        }else{
            Node<E> getNode = indices.get(index);
            return getNode.data;
        }
    }

    //returns the object at the head
    public E getHead (){
        if(size == 0){
         System.out.println("Empty List");
         return null;
        }else{
         return indices.get(0).data;
        }
    }

    //returns the object at the tail
    public E getLast (){
        if(size == 0){
            System.out.println("Empty List");
            return null;
        }else{
            return indices.get(size -1).data;
        }
    }

    //returns the list size.
    public int size(){
        return size;
    }

    //removes and returns the element at the head
    public E remove(){
        if(size == 0){
            System.out.println("Empty List");
            return null;
        }else if(size == 1){
            Node<E> headNode = indices.get(0);
            head = null;
            tail = null;
            size--;
            indices.remove(0);
            return headNode.data;
        }
        Node<E> headNode = indices.get(0);
        head = head.next;
        head.prev = null;
        size--;
        indices.remove(0);
        return headNode.data;
    }

    //removes and returns the element at the tail
    public E removeLast (){
        if(size == 0){
            System.out.println("Empty List");
            return null;
        }else if(size == 1){
           Node<E> tailNode = indices.get(size -1);
           head = null;
           tail = null;
           size--;
           indices.remove(0);
           return tailNode.data;
        }
        Node<E> tailNode = indices.get(size -1);
        tail = tail.prev;
        tail.next = null;
        size--;
        indices.remove(size);
        return tailNode.data;
    }

    //removes and returns the element at the index 'index'. Use the index for fast access
    public E removeAt (int index){
        if(index < 0 || index >= size){
            System.out.println("Index out of Bounds.");
            return null;
        }
        Node<E> indexNode = indices.get(index);
        if(index == 0){
            return remove();
        }
        if(index == size -1){
            return removeLast();
        }
        indexNode.prev.next = indexNode.next;
        indexNode.next.prev = indexNode.prev;
        indices.remove(index);
        size--;
        return indexNode.data;
    }

    //removes the first occurrence of elem in the list and returns true. Return false if elem was not in the list.
    public boolean remove (E elem){
        for(int i = 0; i < size; i++){
            if(indices.get(i).data.equals(elem)){
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    //presents a string representation of the list.


    @Override
    public String toString() {
        Node<E> StringNode;
        String result ="";
        if(size == 0){
           return "Empty list";
        }
        for(int i = 0; i < size; i++){
         StringNode = indices.get(i);
         result = result + StringNode.data + " ";
        }

        return result;
    }
}
