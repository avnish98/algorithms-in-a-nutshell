# This script implements simple linked list

class Node:
    """
    This class is used to represent a Node.
    A Node has a value and a pointer to next Node.

    ...

    Attributes
    ----------
    value : int
        Value of the node
    next : Node
        Pointer to next node

    Methods
    -------
    connect(next=Node)
        Connects current node to the node specified in parameter
    """

    def __init__(self, value=None, next=None):
        self.value = value
        self.next = next
    
    def connect(self, next):
        """Connects current node to the node specified in parameter

        Parameters
        ----------
        next : Node
            Node to be connected
        """

        self.next = next
    
class LinkedList:
    """
    This class used to represent a LinkedList.
    It has a leading Node called Head.

    ...

    Attributes
    ----------
    head : Node
        Leading Node of LinkedList

    Methods
    -------
    display()
        Prints complete LinkedList
    convert(list)
        Converts an array into LinkedList
    add(value)
        Adds value as a new Node into LinkedList
    remove(value)
        Removes Node with same value from LinkedList
    """   

    def __init__(self, head=Node()):
        self.head = head
    
    def display(self):
        """Prints complete LinkedList
        """

        temp = self.head
        print(" Head({}) ->".format(temp.value), end="")
        while(temp.next != None):
            temp = temp.next
            print(" {} ->".format(temp.value), end="")
    
    def convert(self, arr):
        """Converts an array into LinkedList

        Parameters
        ----------
        arr : list
            An array
        """

        for a in arr:
            if self.head.value == None:
                self.head.value = a
            elif self.head.next == None:
                self.head.next = Node(a)
            else:
                temp = self.head
                while(temp.next != None):
                    temp = temp.next
                temp.next = Node(a)

    def add(self, value):
        """Adds value as a new Node into LinkedList

        Parameters
        ----------
        value : int
            Value to be added to LinkedList
        """

        temp = self.head 
        if(temp.value == None):
            self.head.value = value
        else:
            while(temp.next != None):
                temp = temp.next
            temp.next = Node(value)
    
    def remove(self, value):
        """Removes Node with same value from LinkedList

        Parameters
        ----------
        value : int
            Value to be removed from LinkedList
        """

        temp = self.head
        if(temp.value == value):
            self.head = temp.next
        else:    
            while(temp.next.value != value):
                temp = temp.next
            rem_node = temp.next
            temp.next = temp.next.next
            rem_node.next = None
         

arr = [5, 6, 3, 3, 2, 1, 3, 21]
ll = LinkedList()
ll.convert(arr)
ll.add(56)
ll.remove(5)
ll.display()