#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
求字符串a是否含有字符串partern b，也就是匹配问题
经典KMP算法是计算next[j]数组，然后每次移动若干位而不是暴力求解的每次移动到partern的首位来比较
@author sunliangliang
@version 1.0
@date 2018/10/27
'''
#暴力求解时间复杂度为O（m*n）
def bf(s, partern):
    start = 0
    while start <= (len(s) - len(partern)):
        partern_index = 0
        for i in xrange(start, start+len(partern)):
            if partern[partern_index] != s[i]:
                break
            elif partern_index == (len(partern)-1):
                return start
            partern_index += 1
        start += 1
    return -1

def get_next(partern):
    nex = [-1] * len(partern)
    index = 0
    k = -1
    while (index < len(partern) - 1):
        if k == -1 or partern[index] == partern[k]:
            index += 1
            k += 1
            nex[index] = k
        else:
            k = nex[k]
    return nex

def kmp(s, partern):
    nex = get_next(partern)
    index = 0
    start = 0
    while start < (len(partern)-1):
        print 1
        if start == -1:
            index += 1
            start = 0
            continue
        if s[index] == partern[start]:
            index += 1
            start += 1
        else:
            start = nex[start]
    return index - start

#KMP经典的算法比较完善了，有一种情况下：
#当不匹配的时候模式串调到next[j]的位置继续匹配，如果p[next[j]] == p[j]的话那岂不是
#做了无用功，因为a[i] != p[j]的话a[i] 肯定 != p[next[j]]
#所以在kmp求解的时候加上一个判断语句就可疑再降低一下时间复杂度
def kmp2(s, partern):
    nex = get_next(partern)
    index = 0
    start = 0
    while start < (len(partern) - 1):
        print 2
        if start == -1:
            index += 1
            start = 0
            continue
        if s[index] == partern[start]:
            index += 1
            start += 1
        else:
            if parttern[start] == parttern[nex[start]]:
                start = nex[nex[start]]
            start = nex[start]
    return index - start

if __name__ == '__main__':
    s = 'adgababcabcdcfabcabbbaabccc'
    parttern = 'abcabcdcfabcabbb'
    #print bf(s, parttern)
    print kmp(s, parttern)
    print kmp2(s, parttern)