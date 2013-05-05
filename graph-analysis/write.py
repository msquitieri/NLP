f = open("badlinenumbers");
data = f.readlines();
f.close();

dataInt = [int(line) for line in data]

print dataInt;

allLines = open("allLines.txt");
usefulLines = open("allUsefulLines.txt", "a");

i=1
for line in allLines.readlines() :
	if i not in dataInt :
		usefulLines.write(line);
	i = i+1

allLines.close();
usefulLines.close();
