package stackStuff.Quicksort;

public class Node  {
    private int[] d;
    private Node next;
    public Node(int[] d, Node next){
        this.d = d;
        this.next = next;
    }
    public boolean setNext(Node nextNode){
        this.next=nextNode;
        return true;
    }
    public int[] getData(){
        return this.d;
    }
    public Node getNext(){
        return this.next;
    }
}
