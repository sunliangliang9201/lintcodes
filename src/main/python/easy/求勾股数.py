import csv

def isCoprime(a, b):
    '''计算两个数是否互质'''
    if a > b:
        a, b = b, a
    while a != 0:
        a, b = b % a, a
    return b == 1

def getMaxValue(maxRetry):
    '''获取用户希望搜索勾股数的范围，默认从1开始'''
    maxRetry = maxRetry
    flag = 1
    info = "请输入搜索最大值（例如 10000）："
    maxValue = -1
    while flag and maxRetry > 0:
        try:
            maxValue = int(input(info))
        except Exception as e:
            info = "输入数值格式错误，请重新输入："
        else:
            flag = 0
            break
        maxRetry -= 1
    #如果尝试三次均未成功或者输入的值非正则推出
    if flag == 1 or maxValue <= 0:
        exit(1)
    return maxValue

def saveCSV(resPath, resList, resSeparator):
    '''将结果存储指定定路径下，以csv文件格式存储'''
    with open(resPath, "w", newline="") as file:
        writer = csv.writer(file, delimiter=',')
        for row in resList:
            writer.writerow(row)

if __name__ == "__main__":
    #获取用户希望搜索的范围值，默认从1开始
    maxValue = getMaxValue(3)
    resList = []
    start = 1
    #寻找符合条件的勾股数，算法有待优化！
    for i in range(1, maxValue):
            for j in range(i + 1, maxValue, 2):
                res = i ** 2 + j ** 2
                if (res ** 0.5) > maxValue:
                    break
                elif not isCoprime(i, j) or int(res ** 0.5) != (res ** 0.5):
                    continue
                else:
                    resList.append((i, j, int(res ** 0.5)))

    saveCSV(r"e:\pythagorean.csv", resList, ",")