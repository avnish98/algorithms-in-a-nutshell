# This script implements double linked list

class Node:
    """
    This class is used to represent a Node.
    A Node has a value and a pointer to next Node.

    ...

    Attributes
    ----------
    prev : Node
        Pointer to previous node
    value : int
        Value of the node
    next : Node
        Pointer to next node

    Methods
    -------
    connect_next(next=Node)
        Connects current node to the node specified in parameter as next node
    connect_prev(prev=Node)
        Connects current node to the node specified in parameter as previous node
    """

    def __init__(self, prev=None, value=None, next=None):
        self.prev = prev
        self.value = value
        self.next = next
    
    def connect_next(self, next):
        """Connects current node to the node specified in parameter
        as next node

        Parameters
        ----------
        next : Node
            Node to be connected
        """

        self.next = next
    
    def connect_prev(self, prev):
        """Connects current node to the node specified in parameter
        as previous node

        Parameters
        ----------
        prev : Node
            Node to be connected
        """

        self.prev = prev
        prev.next = self
    
class DoubleLinkedList:
    """
    This class used to represent a DoubleLinkedList.
    It has a leading Node called Head.

    ...

    Attributes
    ----------
    head : Node
        Leading Node of DoubleLinkedList

    Methods
    -------
    display()
        Prints complete DoubleLinkedList
    convert(list)
        Converts an array into DoubleLinkedList
    add(value)
        Adds value as a new Node into DoubleLinkedList
    remove(value)
        Removes Node with same value from DoubleLinkedList
    """   

    def __init__(self, head=Node()):
        self.head = head
    
    def display(self):
        """Prints complete DoubleLinkedList
        """

        temp = self.head
        print(" Head({}) <->".format(temp.value), end="")
        while(temp.next != None):
            temp = temp.next
            print(" {} <->".format(temp.value), end="")
    
    def convert(self, arr):
        """Converts an array into DoubleLinkedList

        Parameters
        ----------
        arr : list
            An array
        """

        for a in arr:
            if self.head.value == None:
                self.head.value = a
            elif self.head.next == None:
                self.head.next = Node(self.head, a)
            else:
                temp = self.head
                while(temp.next != None):
                    temp = temp.next
                temp.next = Node(temp, a)

    def add(self, value):
        """Adds value as a new Node into DoubleLinkedList

        Parameters
        ----------
        value : int
            Value to be added to DoubleLinkedList
        """

        temp = self.head 
        if(temp.value == None):
            self.head.value = value
        else:
            while(temp.next != None):
                temp = temp.next
            temp.next = Node(temp, value)
    
    def remove(self, value):
        """Removes Node with same value from DoubleLinkedList

        Parameters
        ----------
        value : int
            Value to be removed from DoubleLinkedList
        """

        temp = self.head
        if(temp.value == value):
            self.head = temp.next
            self.head.prev = None
        else:    
            while(temp.next.value != value):
                temp = temp.next
            temp.next = temp.next.next
            temp.next.prev = temp
         

arr = [5, 6, 3, 3, 2, 1, 3, 21]
ll = DoubleLinkedList()
ll.convert(arr)
ll.add(56)
ll.remove(5)
ll.display()