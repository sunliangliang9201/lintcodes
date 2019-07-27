#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
一个数组里面乱序保存着N-1个连续的正整数，如果存在另一个数组表示的是后面比当前数组值小的有多少个如下
[4,6,2,5,3,1]  对应着[3,4,1,2,1,0]
请问如果一直cantor数组如何复原原数组
@author sunliangliang
@version 1.0
@date 2018/12/11
'''

#算法就是第一个肯定能确定，然后从额外数组中删除那个值即可...
def cantor(nums):
    tmp = [x for x in xrange(1, len(nums)+1)]
    for i in xrange(len(nums)):
        a = nums[i]
        nums[i] = tmp[a]
        tmp.remove(tmp[a])

if __name__ == '__main__':
    nums = [3, 4, 1, 2, 1, 0]
    cantor(nums)
    print nums