#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
...
@author sunliangliang
@version 1.0
@date 2018/12/04
'''
#求最接近0的子数组
#依次进行加和，然后排序，最后计算差值并取绝对值，找出差值最小的，结果就是两个结果的下表对应的原数组和最接近0
def func1(nums):
    sum = []
    for i in xrange(len(nums)):
        if i == 0:
            sum.append((nums[i], i))
        else:
            sum.append((sum[i-1][0] + nums[i], i))
    #sum.sort(key= lambda t:t[0])
    print sum
    sum.sort(key= lambda t : t[0])
    print sum
    min_index = (-1, sum[0][1])
    min_diff = abs(sum[0][0])
    for i in xrange(1,len(sum)-1):
        if min_diff > abs(sum[i+1][0] - sum[i][0]):
            min_diff = abs(sum[i+1][0] - sum[i][0])
            min_index = (sum[i+1][1], sum[i][1])
    print min_diff
    print min_index


#求和最大子数组,可以穷举，复杂度是O(n3)
def func2(nums):
    res = nums[0]
    for i in xrange(len(nums)):
        for j in xrange(i, len(nums)):
            sum = 0
            for k in xrange(i, j+1):
                sum += nums[k]
            if sum > res:
                res = sum
    print res

#对于穷举稍作改动,因为穷举的时候第三层循环很多都是重复计算
def func3(nums):
    res = nums[0]
    for i in xrange(len(nums)):
        sum = 0
        for j in xrange(i, len(nums)):
            sum += nums[j]
            if sum > res:
                res = sum
    print res


#动态规划，每一步都保证最大和
def func4(nums):
    max_add = nums[0]
    sum = 0
    for i in xrange(1, len(nums)-1):
        sum = max(sum + nums[i], nums[i])
        max_add = max(max_add, sum)
    print max_add

#求和最小子数组,与求最大和子数组的思路一模一样
def func5(nums):
    min_add = nums[0]
    sum = 0
    for i in xrange(1, len(nums) - 1):
        sum = min(sum + nums[i], nums[i])
        min_add = min(min_add, sum)
    print min_add


if __name__ == '__main__':
    nums = [-1, 20, 15, -11, -3, 7, -10]
    #func1(nums)
    #func2(nums)
    #func3(nums)
    #func4(nums)
    func5(nums)

