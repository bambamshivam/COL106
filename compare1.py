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
diff=0
for i in range(len(case_b)):
	count+=1
	if(case_b[i]!=case_a[i]):
		diff+=1
		for j in range(10):
			print(case_b[i+j],end="")
		print()
		for j in range(10):
			print(case_a[i+j],end="")
			print()
    
print(count)
print(diff)