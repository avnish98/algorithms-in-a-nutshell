# This script implements stack

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
    
class Stack:
    """
    This class used to represent a Stack.
    Leading Node of Stack is called Top and ending Node of Stack is called Bottom.

    ...

    Attributes
    ----------
    top : Node
        Leading Node of Stack

    Methods
    -------
    display()
        Prints complete Stack
    convert(list)
        Converts an array into Stack
    push(value)
        Pushes value to the top of Stack
    pop()
        Removes value from the top of Stack
    """   

    def __init__(self, top=Node()):
        self.top = top
    
    def display(self):
        """Prints complete Stack
        """

        temp = self.top
        print("{}".format(temp.value))
        while(temp.next != None):
            temp = temp.next
            print("-------------")
            print("{}".format(temp.value))
    
    def convert(self, arr):
        """Converts an array into Stack

        Parameters
        ----------
        arr : list
            An array
        """

        for a in arr:
            if self.top.value == None:
                self.top.value = a
            elif self.top.next == None:
                self.top.next = Node(a)
            else:
                temp = self.top
                while(temp.next != None):
                    temp = temp.next
                temp.next = Node(a)

    def push(self, value):
        """Pushes value to the top of Stack

        Parameters
        ----------
        value : int
            Value to be pushed to Stack
        """

        self.top = Node(value, self.top)

    def pop(self):
        """Removes value from the top of Stack

        Returns
        -------
        self.top.value : int
            Value removed from stack
        """

        self.top = self.top.next
        return self.top.value 

arr = [5, 6, 3, 3, 2, 1, 3, 21]
ll = Stack()
ll.convert(arr)
ll.push(56)
ll.push(4)
ll.push(32)
ll.pop()
ll.push(21)
ll.display()