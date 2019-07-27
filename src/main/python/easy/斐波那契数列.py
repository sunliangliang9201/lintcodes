#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
斐波那契数列的第N项
@author sunliangliang
@version 1.0
@date 2018/10/27
'''

class Solution:
    """
    @param n: an integer
    @return: an ineger f(n)
    """
    def fibonacci(self, n):
        # write your code here
        numList = [0, 1]
        for i in range(n - 2):
            numList.append(numList[-2] + numList[-1])
        return numList[-1]

if __name__ == '__main__':
    print Solution().fibonacci(40)