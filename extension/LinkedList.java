import java.util.Iterator;

/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: Implementation of a Linked List
 */

public class LinkedList<T> implements Iterable<T>, Queue<T>{

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * A private class representing a node in the linked list
     * @param <T> the type of the item stored in the node
     */
    private static class Node<T>{
        private Node<T> next;
        private T item;

        /**
         * Constructor for the Node class
         * @param item  the item to be stored in the node
         */
        public Node(T item){
            next = null;
            this.item = item;
        }

        /**
         * Returns the item stored in the node
         * @return the item stored in the node
         */
        public T getData(){
            return this.item;
        }

        /**
         * Sets the item stored in the node
         * @param item the item to be stored in the node
         */
        public void setNext (Node<T> n){
            next = n;
        }

        /**
         * Returns the next node in the linked list
         * @return the next node in the linked list
         */
        public Node<T> getNext(){
            return next;
        }
    }

    /**
     * A private class representing an iterator for the linked list
     */
    private class LLIterator implements Iterator<T>{
        private Node<T> currNode;

        /**
         * Constructor for the LLIterator class
         * @param head the head of the linked list
         */
        public LLIterator(Node<T> head){
            currNode = head;
        }

        /**
         * Returns weather the linked list has a next node
         * @return true if the linked list has a next node, false otherwise
         */
        public boolean hasNext(){
            return currNode != null;
        }

        /**
         * Returns the next item in the linked list
         * @return the next item in the linked list
         */
        public T next(){
            T item =  currNode.getData();
            currNode = currNode.getNext();
            return item;
        }

        /**
         * Does nothing as it is not required part of the iterator interface
         */
        public void remove(){
        }
    }

    /**
     * Constructor for the LinkedList class
     */
    public LinkedList(){
        clear();
    }

    /**
     * Returns the size of the linked list
     */
    public int size(){
        return size;
    }

    /**
     * Clears the linked list
     */
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns true if the linked list is empty, false otherwise
     * 
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty(){
        if (size==0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Adds the given item to the end of the linked list
     * @param item the item to be added to the end of the linked list
     */
    public void offer(T item){
        addLast(item);
    }

    /**
     * Returns the item at the front of the linked list
     * @return the item at the front of the linked list
     */
    public T peek(){
        if (head == null){
            return null;
        }
        else{
            return head.getData();
        }
    }

    /**
     * Returns and removes the item at the front of the linked list
     * @return the item at the front of the linked list
     */
    public T poll(){
        return remove();
    }

    /**
     * Returns a string representation of the linked list
     * 
     * @return a string representation of the linked list
     */
    public String toString(){
        String stringRepresentation = "";
        Node<T> currNode = head;

        if (currNode == null){
            return stringRepresentation;
        }
        
        while(currNode.getNext() != null){
            stringRepresentation += currNode.getData() + " ";
            currNode = currNode.getNext();
        }
        stringRepresentation += currNode.getData();

        return stringRepresentation;
    }

    /**
     * Returns true if the linked list contains the object o, false otherwise
     * @param o the object to be checked
     * @return true if the linked list contains the object o, false otherwise
     */
    public boolean contains(Object o){
        Node<T> currNode = head;
        int currIndex = 0;
    
        while(currIndex < size){
            if (currNode.getData().equals(o)){
                return true;
            }
            currNode = currNode.getNext();
            currIndex++;
        }
        return false;
    }
        
    /**
     * Adds an item to the linked list
     * @param item the item to be added
     */
    public void add(T item){
        Node<T> newNode = new Node<>(item);
        if(size==0){
            head = newNode;
            tail = newNode;
            size++;
        }
        else{
            newNode.setNext(head);
            head = newNode;
            size++;
        }
    }

    /**
     * Adds an item to the end of the linked list
     * @param item the item to be added
     */
    public void addLast(T item){
        Node<T> newNode = new Node<T>(item);
        if (head == null){
            head = newNode;
            tail = newNode;
            size++;
        }
        else{
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    /**
     * Removes and returns the last item in the linked list
     * @return the last item in the linked list
     */
    public T removeLast(){
        if (head == null){
            return null;
        }
        else{
            Node<T> currNode = head;
            Node<T> prevNode = null;
            while(currNode.getNext()!=null){
                prevNode = currNode;
                currNode = currNode.getNext();
            }
            T lastItem = currNode.getData();
            prevNode.setNext(null);
            tail = prevNode;
            size--;
            return lastItem;
        }
    }

    /**
     * Returns the last item in the linked list
     * @return the last item in the linked list
     */
    public T getLast(){
        return tail.getData();
    }

    /**
     * Returns the item at the specified index
     * @param index the index of the item to be returned
     * @return the item at the specified index
     */
    public T get(int index){
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return null; // returning null as a default value
        }
        int currIndex = 0;
        Node<T> currNode = head;
        while(currIndex < index){
            currNode = currNode.getNext();
            currIndex++;
        }
        return currNode.getData();
    }

    /**
     * Removes the first item from the linked list
     * @return the first item from the linked list
     */
    public T remove(){
        if (head == null){
            return null;
        }
        else if(size==1){
            T firstItem = head.getData();
            head = null;
            tail = null;
            size--;
            return firstItem;
        }
        else{
            T firstItem = head.getData();
            head = head.getNext();
            size--;
            return firstItem;
        }
    }

    /**
     * Adds an item to the linked list at the specified index
     * @param index the index at which the item is to be added
     * @param item the item to be added
     */
    public void add(int index, T item){

        if(index == 0){
            add(item); 
        }
        else if(index == size){
            addLast(item);
        }
        else{
            Node<T> currNode = head;
            
            for(int currIndex = 0; currIndex < index - 1; currIndex++){
                currNode = currNode.getNext();
            }
            Node<T> newNode = new Node<>(item);
            newNode.setNext(currNode.getNext());
            currNode.setNext(newNode);
            size++;
        }
    }

    /**
     * Removes the item at the specified index
     * @param index the index of the item to be removed
     * @return the item at the specified index
     */
    public T remove(int index){
        Node<T> prevNode = null;
        Node<T> currNode = head;
        int currIndex = 0;

        if(index==0){
            return remove();
        }
        else if (index == size-1){
            return removeLast();
        }
        else{
            for(currIndex = 0; currIndex < index; currIndex++){
                prevNode = currNode;
                currNode = currNode.getNext();
            }
            T removedItem = currNode.getData();
            prevNode.setNext(currNode.getNext());
            size--;
            return removedItem;
        }
    }

    /**
     * Returns true if the linked list is equal to the object o, false otherwise
     * @param o the object to be compared
     * @return true if the linked list is equal to the object o, false otherwise
     */
    public boolean equals(Object o){
        if (!(o instanceof LinkedList)){
            return false;
        }
        // If I have reached this line, o must be a LinkedList
        LinkedList oAsALinkedList = (LinkedList) o;
        // Now I have a reference to something Java knows is a LinkedList!

        Node<T> currNodeList = head;
        Node<T> currNodeO = oAsALinkedList.head;

        for (int i=0; i<size; i++){
            if(currNodeList.getData()!=currNodeO.getData()){
                return false;
            }
            currNodeList = currNodeList.getNext();
            currNodeO = currNodeO.getNext();
        }
        return true;
    }

    // Return a new LLIterator pointing to the head of the list
    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    }

}