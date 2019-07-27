#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
二叉树最大节点
@author sunliangliang
@version 1.0
@date 2018/10/27
'''
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None

def maxNode(root):
    if not root:
        return None
    node_list = [root]
    res_list = []
    while len(node_list) != 0:
        tmp_node = node_list.pop(0)
        res_list.append(tmp_node.val)
        if tmp_node.left != None:
            node_list.append(tmp_node.left)
            res_list.append(tmp_node.left.val)
        if tmp_node.right != None:
            node_list.append(tmp_node.right)
            res_list.append(tmp_node.right.val)
    res = res_list[0]
    for i in res_list:
        if res < i:
            res = i
    return res

if __name__ == '__main__':
    node1 = TreeNode(1)
    node2 = TreeNode(-5)
    node3 = TreeNode(2)
    node4 = TreeNode(0)
    node5 = TreeNode(4)
    node6 = TreeNode(-4)
    node7 = TreeNode(-5)
    node1.left = node2
    node1.right = node3
    node2.left = node4
    node2.right = node5
    node3.left = node6
    node3.right = node7
    print maxNode(node1)