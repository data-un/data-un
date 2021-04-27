/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.structures;

/**
 *
 * @author david
 */
public class LinkedList <T>{
    Node head = null;
    private class Node{
        T data;
        Node next;
        Node(T value){
            this.data = value;
            this.next = null;
        }
    }
    
    public void addEnd(T dat){
        if(this.head == null){
            this.head = new Node(dat);
        }else{
            Node curr = this.head;
            while(curr.next != null)
                curr = curr.next;
            curr.next = new Node(dat);
        }
    }
    public void addStart(T dat){
        Node newHead = new Node(dat);
        newHead.next = this.head;
        this.head = newHead;
    }
    public void Insert(T dat, int i){
        if(i < 0){
            System.out.print(i+" es un número negativo !!!!");
            return;
        }
        if(i == 0){
            this.addStart(dat);
        }else{
            Node curr = head;
            for(int j = 1; j < i; j++){
                curr = curr.next;
            }
            Node nuevo = new Node(dat);
            nuevo.next = curr.next;
            curr.next = nuevo;
        }
    }
    public void printList(){
        Node curr = this.head;
        while(curr != null){
            System.out.println(curr.data);
            curr = curr.next;
        }
    }
    public T getElementAt(int p){
        Node curr = this.head;
        for(int i = 1; i <= p; i++)
            curr = curr.next;
        return curr.data;
    }
    public void updateAt(T dat, int p){
        Node curr = this.head;
        for(int i = 1; i <= p; i++)
            curr = curr.next;
        curr.data = dat;
    }
    public void removeAt(int p){
        if(p == 0){
         this.head = this.head.next;
         return;
        }
        Node curr = this.head;
        for(int i = 1; i < p; i++)
            curr = curr.next;
        curr.next = curr.next.next;
        
    }
    public void dropTail(){
        if(this.head == null)
            return;
        if(this.head.next == null){
            this.head = null;
            return;
        }
        Node curr = this.head;
        while(curr.next.next != null)
            curr = curr.next;
        curr.next = null;
    }
    
    public void dropHead(){
        this.removeAt(0);
    }
}
