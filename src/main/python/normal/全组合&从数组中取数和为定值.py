#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
一个数组，从里面取出人一个数使得他们的和为定值，求结果
@author sunliangliang
@version 1.0
@date 2018/11/23
'''
#其实这种方法也是穷举法,循环2^n*n次指数增长,但是受限于系统位数

def func(nums, target):
    res = []
    for i in xrange(1, 1 << len(nums)):
        sum = 0
        temp = ''
        for j in xrange(len(nums)):
            if (i & 1 << j) != 0:
                sum += nums[j]
                temp += str(nums[j]) + "+"
        if sum == target:
            t = temp.split('+')
            p = []
            for j in xrange(len(t)):
                if (t[j] != ''):
                    p.append(t[j])
            res.append(p)
    return res

#每局所有组合，不是全排列，之后会介绍全排列
#用递归来做，将所有的可能性考虑到,但是这个代码中会有重复的情况！！，9个数的组合总数是512，而这个代码计算了2047次
def func2(nums, target, indexs, result):
    if len(indexs) > len(nums):
        return
    sum = 0
    res = []
    for i in xrange(len(indexs)):
        if indexs[i]:
            sum += nums[i]
            res.append(nums[i])
    if sum == target:
        result.append(res)
    indexs.append(True)
    func2(nums, target, indexs, result)
    indexs.pop()
    indexs.append(False)
    func2(nums, target, indexs, result)
    indexs.pop()
#剪枝，在上述的递归中进行剪枝复杂度不变但是计算次数会减少
def func3(nums, target):
    pass

if __name__ == '__main__':
    nums = [1, 21, 3, -1, 7, 33, 11, -10, -9]
    target = 36
    print func(nums, target)
    result = []
    indexs = []
    func2(nums, target, indexs, result)
    print result
    #func4(nums, target)