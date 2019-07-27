#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
与LCS的区别在于LCString是最长公共子串，是连续的
...
@author sunliangliang
@version 1.0
@date 2018/10/27
'''

def lcstring(s1, s2):
    res = [([0] * (len(s2) + 1)) for i in range(len(s1) + 1)]
    maxL = 0
    for i in xrange(len(s1) + 1):
        for j in xrange(len(s2) + 1):
            if i == 0 or j == 0:
                res[i][j] = 0

            elif s1[i - 1] == s2[j - 1]:
                res[i][j] = res[i - 1][j - 1] + 1
                maxL = max(res[i][j], maxL)
            else:
                res[i][j] = 0
    return maxL

if __name__ == '__main__':
    s1 = 'ABCBDAB'
    s2 = 'BDCABA'
    print lcstring(s1, s2)
