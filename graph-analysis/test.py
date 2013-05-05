import networkx as nx;
import MySQLdb as db;

f = open("allLines.txt")
lines = f.readlines();
f.close();

#print len(lines)

'''
for line in lines:
	try:
		temp = line.encode("utf-8")
	except Exception:
		print "EXCEPTION!"
'''

G = nx.DiGraph();
for line in lines:
	try:
		temp = line.encode("utf-8");

		words = line.split(" ");
		prev = words[0];
		for i in range(1, len(words)) :
			G.add_edge(prev, words[i])
			prev = words[i]
	except Exception:
		continue;

print str(G.number_of_nodes())
print str(G.number_of_edges())

print str(nx.eigenvector_centrality(G))

nx.write_pajek(G, "craigslist.net")
