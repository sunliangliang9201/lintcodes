#!/usr/bin/env python2.7
#coding=utf-8

'''
lincode
@desc
给出一段字符串，给出最短编码长度(按照字母来的，不是字母组合)
@author sunliangliang
@version 1.0
@date 2018/12/05
'''
#是根据二叉树来判断编码长度的，特点是不唯一，无损压缩，而且有效避免了前缀问题
class Node(object):

    def __init__(self, left=None, right=None, weight=0, value=None, parent=None):
        self.left = left
        self.right = right
        self.weight = weight
        self.value = value
        self.parent = parent

    def __str__(self):
        # return 'value: %s, weight: %d, parent: %s, left: %s, right: %s' \
        # % (self.value, self.weight,self.parent, self.left, self.right)
        return '%s, %s' % (self.value, self.weight)

    __repr__ = __str__

class Huffman(object):

    def code(self, s):
        #为每个字母创建Node并给出weight
        tf = {}
        for i in s:
            if i not in tf.keys():
                node = Node(value=i, weight=1)
                tf[i] = node
            else:
                tf[i].weight += 1
        nodes = sorted(tf.values(), key=lambda n:n.weight)
        while len(nodes) > 1:
            node1 = nodes.pop(0)
            node2 = nodes.pop(0)
            parent = Node(weight=node1.weight+node2.weight)
            if node1.weight > node2.weight:
                parent.right = node1
                parent.left = node2
            else:
                parent.right = node2
                parent.left = node1
            #把新增的父节点插入list
            for i in xrange(len(nodes)):
                if nodes[i].weight >= parent.weight:
                    nodes.insert(i,parent)
                    break
                elif i == len(nodes)-1:
                    nodes.append(parent)
            if len(nodes) == 0:
                nodes.append(parent)
            #nodes.insert(0, parent)
        #开始回溯编码，左1右0，用递归比较方便
        word_map = {}
        self._get_code(nodes[0], word_map,'')
        res = ''
        for i in s:
            res = res + word_map[i]
        print word_map
        return res, nodes[0]

    def _get_code(self, root, word_map, code):
        if not root:
            return
        if root.value:
            word_map[root.value] = code
        code = code + '0'
        self._get_code(root.left, word_map, code)
        code = code[0:-1] + '1'
        self._get_code(root.right, word_map, code)

    def decode(self, scode, root):
        res = ''
        cur = 0
        root_tmp = root
        while len(scode) and cur < len(scode) > 0:
            if scode[cur] == '1':
                root_tmp = root_tmp.right
            else:
                root_tmp = root_tmp.left
            if root_tmp.value:
                res += root_tmp.value
                scode = scode[cur:]
                cur = 0
                root_tmp = root
            cur += 1
        return res

if __name__ == '__main__':
    s = 'abcabffffttoa'
    huffman = Huffman()
    code,root = huffman.code(s)
    print code
    s2 = huffman.decode(code, root)
    print s2