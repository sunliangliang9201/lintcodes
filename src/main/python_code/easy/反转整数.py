#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
反转整数。
...
@author sunliangliang
@version 1.0
@date 2018/10/22
'''

def reverse_integer(num):
    l = len(str(num)) - 1
    count = 1
    new_integer = 0
    while num * 10 / 10 ** count:
        new_integer += (num % 10 **count) / (10 ** (count -1)) * ((10 ** l))
        count += 1
        l -= 1
    return new_integer

if __name__ == '__main__':
    print reverse_integer(346)