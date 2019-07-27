#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
字符串大小写转换
@author sunliangliang
@version 1.0
@date 2018/10/27
'''
def lower2upper(s):
    new_str = ""
    for i in s:
        if ord(i) >= 97:
            new_str += chr(ord(i) - 32)
        else:
            new_str += chr(ord(i) + 32)
    return new_str

if __name__ == '__main__':
    print lower2upper("a")