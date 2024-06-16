package node;


public class Runner {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(13);
        list.insert(15);
        list.insert(56);
        list.insertAtStart(77);
        list.insertAt(1,14); // 0 base
        list.insertAt(0,1);
        list.deleteAt(4);
        // add, remove , iterator
        list.show();
    }
}
