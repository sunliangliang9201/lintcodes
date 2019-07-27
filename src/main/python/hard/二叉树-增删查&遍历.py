#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
二叉树的增删改查，包括递归于非递归方式
@author sunliangliang
@version 1.0
@date 2018/12/12
'''
from collections import deque

class Node(object):

    def __init__(self, value=None, left=None, right=None):
        self.left = left
        self.right = right
        self.value = value

    def __cmp__(self, other):
        return self.value - other.value

    def __str__(self):
        return str(self.value)

    __repr__ = __str__

class BSTree(object):

    root = None

    def __init__(self, root=None):
        self.root = root

    def _add1(self, root, num):
        if not root:
            root = Node(num)
        elif root.value < num:
            self._add1(root.right, num)
        elif root.value > num:
            self._add1(root.left, num)
        else:
            return "insert failed"

    def _add2(self, root, num):
        pass

    def add(self, num):
        self._add1(self.root, num)

    def _delete1(self):
        pass

    def _delete2(self):
        pass

    def delete(self):
        pass

    def _search1(self):
        pass

    def _search2(self):
        pass

    def search(self):
        pass

    def _preorder1(self, root):
        pass

    def _preorder2(self, root):
        pass

    def preorder(self):
        pass

    def _inorder1(self, root):
        pass

    def _inorder2(self, root):
        pass

    def inorder(self):
        pass

    def _postorder1(self, root):
        pass

    def _postorder2(self, root):
        pass

    def postorder(self):
        pass

    def __str__(self):
        return str(self.root)

    __repr__ = __str__

if __name__ == '__main__':
    tree = BSTree(Node(2))
