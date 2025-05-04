package collection;

public class MyLinkedList implements MyList {

    private Node first;

    private Node last;

    private int size = 0;

    // No args constructor
    public MyLinkedList() {

    }

    // Constructor based on another collection
    public MyLinkedList(MyList anotherList) {
        for (int i = 0; i < anotherList.size(); i++) {
            add(anotherList.get(i), i);
        }
    }

    // Constructor based on an array
    public MyLinkedList(Car[] anotherArray) {
        for (int i = 0; i < anotherArray.length; i++) {
            add(anotherArray[i], i);
        }
    }

    @Override
    public Car get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).value;
    }

    @Override
    public void add(Car car) {
        if(size == 0) {
            first = new Node(null, car, null);
            last = first;
        } else {
            Node secondLast = last;
            last = new Node(secondLast, car, null);
            secondLast.next = last;
        }
        size++;
    }

    @Override
    public void add(Car car, int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if(index == size) {
            add(car);
            return;
        }
        Node nextNode = getNode(index);
        Node previousNode = nextNode.previous;
        Node newNode = new Node(previousNode, car, nextNode);
        nextNode.previous = newNode;
        if(previousNode != null) {
            previousNode.next = newNode;
        } else {
            first = newNode;
        }
        size++;
    }

    @Override
    public boolean remove(Car car) {
        Node node = first;
        for(int i = 0; i < size; i++) {
            if(node.value.equals(car)) {
                return removeAt(i);
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        if(index < 0 || index >= size) {
            return false;
        }
        Node deletedNode = getNode(index);
        Node previousNode = deletedNode.previous;
        Node nextNode = deletedNode.next;
        if(nextNode != null) {
            nextNode.previous = previousNode;
        } else {
            last = previousNode;
        }
        if(previousNode != null) {
            previousNode.next = nextNode;
        } else {
            first = nextNode;
        }
        size--;
        return true;
    }

    @Override
    public boolean removeAll(MyList anotherList) {
        boolean modified = false;
        for(int i = 0; i < anotherList.size(); i++) {
            Car element = anotherList.get(i);
            while(this.contains(element)) {
                this.remove(element);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(MyList anotherList) {
        boolean modified = false;
        Node current = first;
        int index = 0;
        while(current != null) {
            Node next = current.next;
            if(!anotherList.contains(current.value)) {
                this.removeAt(index);
                // index should not increase because we removed one element
                modified = true;
            } else {
                index++; // index should increase because the element has not been removed
            }
            current = next;
        }
        return modified;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getCapacity() {
        return size; // Linked list has no fixed capacity
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void trimToSize() {
        // Not applicable for linked list
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Car car) {
        Node node = first;
        for(int i = 0; i < size; i++) {
            if(node.value.equals(car)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(MyList list) {
        for (int i = 0; i < list.size(); i++) {
            if (!this.contains(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int indexOf(Car car) {
        Node node = first;
        for(int i = 0; i < size; i++) {
            if(node.value.equals(car)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Car car) {
        Node node = last;
        for(int i = size - 1; i >= 0; i--) {
            if(node.value.equals(car)) {
                return i;
            }
            node = node.previous;
        }
        return -1;
    }

    @Override
    public MyList clone() {
        MyList clonedArray = new MyLinkedList();
        for (int i = 0; i < this.size(); i++) {
            clonedArray.add(this.get(i));
        }
        return clonedArray;
    }

    private Node getNode(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node {
        private Node previous;
        private Node next;
        private Car value;

        public Node(Node previous, Car value, Node next) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }
}
