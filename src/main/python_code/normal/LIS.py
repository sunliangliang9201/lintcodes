#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
最长递增子序列
@author sunliangliang
@version 1.0
@date 2018/10/27
'''
#暴力求解
def lis(l):
    res = [0] * len(l)
    maxl = 0
    for i in xrange(len(l)):
        res[i] = 1
        for j in xrange(i):
            if l[i] >= l[j] and (res[j]+1) >= res[i]:
                res[i] = res[j] + 1
        if maxl < res[i]:
            maxl = res[i]
    return maxl

#贪心+二分法
def binary_search(l, a):
    end = len(l)
    start = 1
    while end > start:
        mid = (end + 1) >> 1
        if l[mid] <= a:
            start = mid + 1
        else:
            end = mid - 1
    return start

def lis2(l):
    res = []
    for i in xrange(len(l)):
        if len(res) == 0 or res[-1] < l[i]:
            res.append(l[i])
        elif res[-1] > l[i]:
            index = binary_search(res, l[i])
            res[index-1] = l[i]
    return len(res)

if __name__ == '__main__':
    l = [7,2,5,8,9]
    print lis2(l)