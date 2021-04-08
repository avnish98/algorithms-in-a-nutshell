# This script implements queue

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
    
class Queue:
    """
    This class used to represent a Queue.
    Leading Node of Queue is called Front and backing Node of Queue is called Back.

    ...

    Attributes
    ----------
    front : Node
        Leading Node of Queue

    Methods
    -------
    display()
        Prints complete Queue
    convert(list)
        Converts an array into Queue
    enqueue(value)
        Pushes value at the back of Queue
    dequeue()
        Removes value from the front of Queue
    """   

    def __init__(self, front=Node()):
        self.front = front
    
    def display(self):
        """Prints complete Queue
        """

        temp = self.front
        print("| {} |->".format(temp.value), end='')
        while(temp.next != None):
            temp = temp.next
            print("| {} |->".format(temp.value), end='')
    
    def convert(self, arr):
        """Converts an array into Queue

        Parameters
        ----------
        arr : list
            An array
        """

        for a in arr:
            if self.front.value == None:
                self.front.value = a
            elif self.front.next == None:
                self.front.next = Node(a)
            else:
                temp = self.front
                while(temp.next != None):
                    temp = temp.next
                temp.next = Node(a)

    def enqueue(self, value):
        """Pushes value at the back of Queue

        Parameters
        ----------
        value : int
            Value to be enqueueed to Queue
        """

        temp = self.front
        while(temp.next != None):
            temp = temp.next
        temp.next = Node(value)

    def dequeue(self):
        """Removes value from the front of Queue

        Returns
        -------
        self.front.value : int
            Value removed from stack
        """

        self.front = self.front.next
        return self.front.value 

arr = [5, 6, 3, 3, 2, 1, 3, 21]
ll = Queue()
ll.convert(arr)
ll.enqueue(56)
ll.enqueue(4)
ll.enqueue(32)
ll.dequeue()
ll.enqueue(21)
ll.display()