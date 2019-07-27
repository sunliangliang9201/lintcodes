#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
给定一组整数进行升序排序
@author sunliangliang
@version 1.0
@date 2018/10/27
'''

def sortIntegers(arr):
    for i in xrange(0, len(arr)):
        tmp = arr[i]
        for j in xrange(i, len(arr)):
            if tmp > arr[j]:
                tmp = arr[j]
                arr[j] = arr[i]
                arr[i] = tmp
    return arr


if __name__ == '__main__':
    print sortIntegers([5,3,2,1,4])