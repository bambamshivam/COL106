choose=input("Original [press 0] or New [press 1]?")
if(choose=="1"):
	filename1 = input("input file: ") 
	filename2 = input("Output file: ") 
else:
	filename1 = "file1.txt"
	filename2 = "file2.txt"

file1 = open(filename1).readlines() 
 
file1_line = [] 
 
for lines in file1: 
	file1_line.append(lines) 
 
file2 = open(filename2).readlines() 
 
file2_line = [] 
 
for lines in file2: 
	file2_line.append(lines) 
import difflib

case_a = file2_line[0]
case_b = file1_line[0]

#output_list = [li for li in difflib.ndiff(case_a, case_b) if li[0] != ' ']

count=0
diff = 0

if (len(case_b) <= len(case_a)):

	if (len(case_b) == len(case_a)):
		print("both files have same words")
	else:
		print("second file have less words")
		print("left out words")
		for i in range(len(case_b),len(case_a)):
			print(case_a[i],end="")

	for i in range(len(case_b)):
		count += 1
		if (case_b[i] != case_a[i]):
			diff += 1
			for j in range(10):
				print(case_b[i+j],end="")
			print()
			for j in range(10):
				print(case_b[i+j],end="")
			print()
else:
	print("first file have less words")
	for i in range(len(case_a)):
		count += 1
		if (case_b[i] != case_a[i]):
			diff += 1
			for j in range(10):
				print(case_a[i+j],end="")
			print()
			for j in range(10):
				print(case_a[i+j],end="")
			print()

	print("left out words")
	for i in range(len(case_a), len(case_b)):
		print(case_b[i],end="")
	print()

    
print("no of words checked: ", count)
print("no of words unmatched: ",diff)