#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
最长公共子序列
abcdef  abfndnaf  结果是abdf
@author sunliangliang
@version 1.0
@date 2018/10/27
'''
#穷举法 暴力求解
#将所有的可能的子序列全部列出来然后比对，显然不可行

#分治思想
#只求最长子序列的长度肯定是最后的那个最大值
def lcs(s1, s2):
    res = [([0] * (len(s2) + 1)) for i in range(len(s1) + 1)]
    flag = [([0] * (len(s2) + 1)) for i in range(len(s1) + 1)]
    for i in xrange(len(s1) + 1):
        for j in xrange(len(s2) + 1):
            if i == 0 or j == 0:
                res[i][j] = 0

            elif s1[i - 1] == s2[j - 1]:
                res[i][j] = res[i - 1][j - 1] + 1
                flag[i][j] = 'o'
            else:
                if res[i - 1][j] < res[i][j - 1]:
                    res[i][j] = res[i - 1][j]
                    flag[i][j] = 'l'
                else:
                    res[i][j] = res[i][j - 1]
                    flag[i][j] = 'u'

    #return res[len(s1)][len(s2)]
    return res, flag

#如果不仅仅需要最长子序列的长度，还需要知道最长的子序列的值应该怎么往回回溯
#从后往前回溯，遇到左上
def lcs2(s1, s2):
    flag = lcs(s1, s2)
    res = ''
    i = len(flag)-1
    j = len(flag[0])-1
    while i != 0 and j != 0:
        print i,j
        if flag[i][j] == 'o':
            res += s1[i-1]
            i -= 1
            j -= 1
        elif flag[i][j] == 'l':
            j -= 1
        else:
            i -= 1
    return ''.join(reversed(res))
if __name__ == '__main__':
    s1 = 'ABCBDAB'
    s2 = 'BDCABA'
    print lcs2(s1, s2)


