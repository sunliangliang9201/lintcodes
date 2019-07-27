#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
...
@author sunliangliang
@version 1.0
@date 2018/10/27
'''
import math

def cycleLeader(a, f, mod):
    i = 2 * f % mod
    while (i != f):
        t = a[i - 1]
        a[i - 1] = a[f - 1]
        a[f - 1] = t
        i = 2 * i % mod

def reverseString(s, f, to):
    while (f < to):
        t = s[f]
        s[f] = s[to]
        s[to] = t
        f += 1
        to -= 1

def leftRotateString(s, n, m):
    m %= n
    reverseString(s, 0, m - 1)
    reverseString(s, m, n - 1)
    reverseString(s, 0, n - 1)

def perfectShuffle2(a, n):
    final = []
    while (n > 1):
        n2 = n * 2
        m = 1
        k = 0
        while ((n2 + 1) / m >= 3):
            k += 1
            m *= 3
        m = m / 2
        t1 = a[:m]
        t2 = a[m:m + n]
        t3 = a[m + n:]
        leftRotateString(t2, n, n - m)
        i = 0
        t = 1
        a = t1 + t2 + t3

        while (i < k):
            cycleLeader(a, t, m * 2 + 1)
            i += 1
            t *= 3
        final += a[:2 * m]
        a = a[2 * m:]
        n -= m
    if n == 1:  # 仅剩两个元素时
        t = a[0]
        a[0] = a[1]
        a[1] = t
        final += a
    print(final)


l3=[1,2,3,4,5,6,7,8,'a','b','c','d','e','f','g','h']

perfectShuffle2(l3,int(len(l3)/2))
