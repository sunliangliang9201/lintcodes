#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
字符串循环左移n位，暴力求解以及三段翻转求解

...
@author sunliangliang
@version 1.0
@date 2018/11/19
'''
#切片求解
def func1(s, n):
    if n >= len(s):
        return s
    else:
        return s[n:] + s[0:n]
#三段翻转
def func2(s, n):
    if n >= len(s):
        return s
    else:
        a = reverse(s, 0, 3)
        b = reverse(s, 3, len(s))
        return reverse(a + b, 0, len(s))
    return s
def reverse(s, fro, to):
    tmp = ''
    for i in xrange(fro, to):
        tmp = s[i] + tmp
    return tmp


if __name__ == '__main__':
    print func1('abcdefg', 3)
    print func2('abcdefg', 3)