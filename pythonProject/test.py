def main():
    pass
    # print("hello world")
    fin1 = open("actual.txt", "r")
    actual = []
    for line in fin1:
        actual.append(float(line))
    fin1.close()

    fin2 = open("expected.txt", "r")
    expected = []
    for line in fin2:
        expected.append(float(line))
    fin2.close()

    actual.sort()
    expected.sort()

    # fout1 = open("actual.txt", "w");
    # for num in actual:
    #     fout1.write(str(num))
    #     fout1.write('\n')
    # fout1.close()
    #
    #
    # fout2 = open("expected.txt", "w");
    # for num in expected:
    #     fout2.write(str(num))
    #     fout2.write('\n')
    # fout2.close()

    for i in range(len(actual)):
        actual[i] = round(actual[i], 8)

    for i in range(len(expected)):
        expected[i] = round(expected[i], 8)

    # print(actual)
    # print(expected)

    i = 0;
    j = 0;

    origActualLen = len(actual)
    origExpectedLen = len(expected)
    while i < origActualLen and j < origExpectedLen:
        pass
        # if (actual[i] != expected[j]):
        #     print(expected[j])
        #     j += 1
        if (actual[i] != expected[j]):
            print(actual[i])
            i += 1

        i+=1
        j+=1

main()

