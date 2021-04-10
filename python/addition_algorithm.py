def add(x, y):
    """Calculates sum of two postive integers expressed as arrays
    and returns the sum array

    Parameters
    ----------
    x : list
        An array
    y : list
        An array
    
    Returns
    -------
    sum_arr : list
        Sum Array
    """

    position = len(x) - 1
    carry = 0
    sum_arr = [0] * (len(x)+1)
    while(position>=0):
        total = x[position] + y[position] + carry
        sum_arr[position+1] = total%10

        if total>9:
            carry=1
        else:
            carry=0
        position -= 1
    sum_arr[0] = carry
    return sum_arr    


def plus(x, y):
    """Calculates sum of two postive integers expressed as arrays
    and returns the sum array

    Parameters
    ----------
    x : list
        An array
    y : list
        An array
    
    Returns
    -------
    sum_arr : list
        Sum Array
    """

    position = len(x)
    carry = 0
    sum_arr = [0] * (len(x)+1)
    while((position-1)>=0):
        total = x[position-1] + y[position-1] + carry

        if total>9:
            sum_arr[position] = total-10
            carry=1
        else:
            sum_arr[position] = total
            carry=0
        position -= 1
    sum_arr[0] = carry
    return sum_arr    

print(add([3, 4, 6], [7, 8, 9]))
print(plus([3, 4, 6], [7, 8, 9]))