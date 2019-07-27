#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
可以直接调用itertools里面的函数
字符串全排列，之前的全组合问题是利用递归+额外truefalse数组来标识是否取到的问题，全排列的解决方法依然是递归，但是利用的是动态规划
具体思想是，每次把一个字符提出来，剩下的在进行全排列....递归进行知道剩下1个字符进行回溯
@author sunliangliang
@version 1.0
@date 2018/12/07
'''
#从数学的角度出发全排列问题是n!个结果，但是从代码的角度时间复杂度应该是多少呢？
#无重复字符情况的全排列,此题的例子是6个字符，4！是24个
def func1(s, start, end, res):
    if start == end:
        res.append(''.join(s))
    for i in xrange(start, end):
        s[start], s[i] = s[i], s[start]
        func1(s, start + 1, end, res)
        s[start], s[i] = s[i], s[start]

#有重复情况,可以直接用一个set进行去重
def func2(s, start, end, res):
    if start == end:
        res.add(''.join(s))
    for i in xrange(start, end):
        s[start], s[i] = s[i], s[start]
        func2(s, start + 1, end, res)
        s[start], s[i] = s[i], s[start]


#有重复情况，不用set，有一个易错点就是，我们很容易想到当遇到需要交换的两个字符相等时就continue，但是这样的话还是会存在重复的结果，因为当abb的情况下只有abb bab bba,但是bab
#b和a交换的时候得到的结果abb bba与之前的重复！！！！
#解决的办法就是需要交换的区间内每一个字符都不能与马上要被交换的相同也就是a[start]-a[i] 之间的不能与a[i]相等
def func3(s, start, end, res):
    if start == end:
        res.append(''.join(s))
    for i in xrange(start, end):
        if is_same(s, start, i):
            continue
        s[start], s[i] = s[i], s[start]
        func3(s, start + 1, end, res)
        s[start], s[i] = s[i], s[start]

def is_same(s, start, end):
    for i in xrange(start, end):
        if s[i] == s[end]:
            return True
    return False

#非递归实现，先进行从小到大排序
#后找、小大、交换、翻转
#后找：从后往前找第一个递增的两个数
#小大：从后面找比替换点大的最小的那个数
#两者交换：得到这个数是一个结果，但是这个结果不需要插入res集，因为之后会重复
#将后面翻转，还是一个结果，目的是保证翻转之后的数是后部分是升序的，这样就达到了得到
def func4(s, res):
    res.append(''.join(s))
    while 1:
        start = is_increase(s)
        if start == -1:
            return res
        index = [(x, s[x]) for x in xrange(start+1, len(s)) if s[x] > s[start]]
        if len(index) == 0:
            continue
        index.sort(key=lambda x:x[1])
        tmp = index[0][0]
        s[tmp], s[start] = s[start], s[tmp]
        swap(s, start+1, len(s)-1)
        res.append(''.join(s))


def is_increase(s):
    for i in xrange(len(s)-1, 0, -1):
        if s[i] > s[i-1]:
            return i-1
    return -1

def swap(s, start, end):
    while end > start:
        s[start], s[end] = s[end], s[start]
        end -= 1
        start += 1

if __name__ == '__main__':
    s = ['a', 'b', 'd', 'c']
    s2 = ['a', 'b', 'c', 'c']
    #直接调用包
    # from itertools import permutations
    # for i in permutations(s):
    #     print i
    # res = []
    # func1(s, 0, len(s), res)
    # print len(res)
    # res = set()
    # func2(s2, 0, len(s2), res)
    # print res
    # print len(res)
    # res = []
    # func3(s2, 0, len(s2), res)
    # print res
    s.sort()
    res = []
    func4(s, res)
    print res
    print len(res)
    print len(set(res))
