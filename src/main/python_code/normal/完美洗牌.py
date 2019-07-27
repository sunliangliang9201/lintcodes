#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
一个数组[a1,a2,a3...an,b1,b2,b3...bn] 洗牌之后变成[a1,b1,a2,b2....an,bn]
三种方法：
暴力移动时间复杂度0（n2） 空间复杂度O（1）
找规律 时间复杂度O（n） 空间复杂度O（n）
完美洗牌 时间复杂度O（n） 空间复杂度O（1）走圈，完美结论2*n = 3^k -1，有k的环，根据规律将数据进行移动到环所指向的位置即可，走一次所以是O（n）
不需要额外空间所以是O（1）
...
@author sunliangliang
@version 1.0
@date 2018/11/19
'''
import math
#暴力求解，直接后一半的数据一个一个向前移动确定的位数
def func1(l):
    tmp = len(l) / 2
    for i in xrange(tmp, len(l)):
        for j in xrange(i, 2*i - len(l),-1):
            tmp2 = l[j-1]
            l[j-1] = l[j]
            l[j] = tmp2
    return l

#再来一个数组，通项公式：2*i % (2*len(l)+1)
def func2(l, n):
    tmp = [i for i in xrange(0,2*n)]
    for i in xrange(1, 2*n + 1):
        tmp[2*i % (2*n+1)-1] = l[i-1]
    return tmp

#以下是走圈算法，利用交换，所以空间复杂度比上一个小，为O（1）
def perfectShuffle(l, n):
    #如果m=n的话直接根据圈来调用交换即可，如果m<n的话需要移动n-m位，对前面的执行交换，对后面2*(n-m)位继续使用这种洗牌方式
    # 先找到最大的m,以及圈数k
    start = 0
    while n > 1:
        k = 0
        m = 1
        while 3 <= (2*n)/m:
            k += 1
            m = m*3
        m = m/2
        swap(l, start, m, n)
        tou = 1
        for i in xrange(k):
            cycleleader(l, tou, m*2 + 1)
            tou = tou * 3
        start = start + m*2
        n = n - m

#当l的长度不能满足条件时，需要移动若干位到后面
def swap(l, start, m, n):
    reverse(l, start+m, start+n-1)
    reverse(l, start+n, start+n+m-1)
    reverse(l, start+m, start+n+m-1)

def reverse(l, start, end):
    while start < end:
        tmp = l[start]
        l[start] = l[end]
        start += 1
        l[end] = tmp
        end -= 1

def cycleleader(l, start, mod):
    i = start*2 % mod
    last = l[start]
    while i != start:
        tmp = l[i-1]
        l[i-1] = last
        last = tmp
        i = i*2 % mod
    l[start] = last

if __name__ == '__main__':
    l0 = [1, 2, 3, 4, 5, 6]
    l1 = [1, 2, 3, 4, 5, 6, 7, 8]
    l2 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    perfectShuffle(l1,4)
    print l1