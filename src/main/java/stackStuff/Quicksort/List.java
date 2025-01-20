package stackStuff.Quicksort;
public class List {
    private Node head;
    public List(){
        this.head=null;
    }
    public void push(int[] s){
        this.head = new Node(s, this.head);
    }

    public boolean isEmpty(){
        return this.head==null;
    }

    public Node peek(){
        return this.head;
    }

    public int[] pop(){
        Node t = this.head;
        this.head = t.getNext();
        return t.getData();
    }
}


