set1={"a", "b", "c"}
set2={1, 2, 3, 4,}
set2.update({2, 3, 5, 6})
set3= set1 | set2
set3.discard(2)
set3.remove("b")
set4=set1 & set3
set2.clear()
set2=set3-set4
print(set1)
print(set2)
print(set3)
print(set4)
