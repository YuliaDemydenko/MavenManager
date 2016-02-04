package model;

import java.io.*;

class LinkedList implements Cloneable, Serializable {
    Task task;
    LinkedList next = null;
    LinkedList prev;
    LinkedList() {
        this.next = null;  
    }
    LinkedList(LinkedList prev, Task task) {
        this.task = task;
        this.prev = prev;
    }
    public LinkedList clone() throws CloneNotSupportedException {
        LinkedList cloned = (LinkedList)super.clone();	
		return cloned;
    }
}