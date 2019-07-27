#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
链表节点计数
@author sunliangliang
@version 1.0
@date 2018/09/21
'''
class ListNode(object):
    def __init__(self, val, next=None):
        self.val = val
        self.next = next

def countNodes(head):
    if not head:
        return 0
    count = 0
    tmp = head
    while tmp.next != None:
        count += 1
        tmp = tmp.next
    return count
if __name__ == '__main__':
    node3 = ListNode(1,next=None)
    node2 = ListNode(1, next=node3)
    node1 = ListNode(1, next=node2)
    print countNodes(node1)