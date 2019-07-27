#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
求回文子串，有三个目的，第一个就是最长回文子串、第二个是求所有回文子串的集合、第三个就是要计算一个二维数组，里面存放ture和false
表示[m][n] 字符串从m到n是不是一个回文子串，之后很多地方会用到这个二维数组
@author sunliangliang
@version 1.0
@date 2018/12/11
'''
#暴力求解，时间复杂度是O（n3），是把所有的子串都列出来了，然后依次进行判断是否为回文串
#求解所有可能子串的n2的，都进行一次会问判断是n次的所有n3

#动态规划,如果知道了一个s[start:end]是一个回文串，如果s[start-1] == s[end+1]那么s[start-1:end+1]也是一个回文串，否则则抛弃时间复杂度是O（n2）
def longestPalindrome(s):
    tf= [([False]*(len(s))) for i in xrange(len(s))]
    res_all = []
    res = 1
    for i in xrange(len(s)):
        count = 0
        start, end = i, i
        while(start>=0 and end<len(s)):
            if s[start] != s[end]:
                break
            res_all.append(s[start:end+1])
            tf[start][end] = True
            count += 1
            start -= 1
            end += 1
        if res < count:
            res = count
    res = 1
    for i in xrange(len(s) - 1):
        count = 0
        start, end = i, i+1
        while (start >= 0 and end < len(s)):
            if s[start] != s[end]:
                break
            res_all.append(s[start:end + 1])
            tf[start][end] = True
            count += 1
            start -= 1
            end += 1
        if res < count:
            res = count
    for i in tf:
        print i
    print res_all
    print res

#Manacher算法时间复杂度是O（n），思想也是动态规划，是利用已经存在的tf来做的
def longestPalindrome2(s):
    l = [0 for i in xrange(len(s))] #记录回文串长度+1的数组
    l[0] = 1
    p = 0 #遍历时到达最右边的边界位置
    p0 = 0 #p所对应的回文串的中间位置
    ans = 0 #结果
    for i in xrange(1, len(s)):
        l[i] = 1
        j = p0 - (i-p0)
        if i < p:
            if l[j] < (p-i):
                l[i] = l[j]
            else:
                while (i-l[i])>=0 and (i+l[i])<len(s) and s[i-l[i]] == s[i+l[i]]:
                    l[i] = l[i] + 1
                if (l[i] +i) >p:
                    p = l[i]+i
                    p0=i
        else:
            while (i - l[i]) >= 0 and (i + l[i]) < len(s) and s[i - l[i]] == s[i + l[i]]:
                l[i] = l[i] + 1
            if (l[i] + i) > p:
                p = l[i] + i
                p0 = i
        if ans < l[i]:
            ans = l[i]-1
    print l
    return ans


if __name__ == '__main__':
    s = '12332112577511655611'
    #longestPalindrome(s)
    s2 = '#'
    for i in s:
        s2 = s2 + i + '#'
    print s2
    print longestPalindrome2(s2)