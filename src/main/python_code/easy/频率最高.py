#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
找频率最高的项
@author sunliangliang
@version 1.0
@date 2018/10/27
'''

def highestFrequency(ip_lines):
    ip_set = set(ip_lines)
    res = []
    tmp = 1
    for i in ip_set:
        if ip_lines.count(i) == tmp:
            res.append(i)
        if ip_lines.count(i) > tmp:
            tmp = ip_lines.count(i)
            res = []
            res.append(i)
    return res

if __name__ == '__main__':
    lines = ["192.168.1.1", "192.118.2.1", "192.168.1.1"]
    print highestFrequency(lines)