package module6_Kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class KruskalsAlgorithm {

	public static void main(String[] args) {
		System.out.println("input pls...");
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		String nod = sc.nextLine();
		String[] nodes = nod.substring(1,nod.length()-1).split(",");
		int[][] adjMat = new int[num][num];
		for (int i = 0; i < adjMat.length; i++) {
			String[] s1 = sc.nextLine().split(" ");
			for (int j = 0; j < adjMat.length; j++) {
				adjMat[i][j] = Integer.parseInt(s1[j]);
				if(adjMat[i][j] == 0) {
					adjMat[i][j] = 999;
				}
			}
		}
		
		doKruskalsAlgorithm(adjMat,nodes); 
		sc.close();
	}

	private static void doKruskalsAlgorithm(int[][] adjMat, String[] nodes) {
		
		ArrayList<Integer> srcs = new ArrayList<Integer>();			// Source nodes
		ArrayList<Integer> dests = new ArrayList<Integer>();		// Destination nodes	
		ArrayList<Integer> dists = new ArrayList<Integer>();		// Distances
		
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				if(adjMat[i][j] != 999) {
					if(!dists.contains(adjMat[i][j])) {
						dists.add(adjMat[i][j]);
					}
				}
			}
		}
		
		Collections.sort(dists);
		
		for (int i = 0; i < dists.size(); i++) {
			for (int j = 0; j < nodes.length; j++) {
				for (int k = 0; k < nodes.length; k++) {
					int d = dists.get(i);
					if(adjMat[j][k] == d) {
						srcs.add(j);   
						dests.add(k);
					}
				}
			}
		}
		
//		System.out.println(dists);
//		System.out.println(srcs);
//		System.out.println(dests);
		UnionFind uf = new UnionFind(nodes.length);
		int total = 0;
		
		for (int i = 0; i < srcs.size(); i++) {
			int[] ar = uf.union(srcs.get(i), dests.get(i));
			if(ar[0] != ar[1]) {
				System.out.println("("+nodes[ar[0]]+"," +nodes[ar[1]]+")");
				total += adjMat[ar[0]][ar[1]];
			}
		}
		
		System.out.println(total);
		
	}
}

class UnionFind {
	int n;
	int[] parents,rank;
	
	public UnionFind(int num) {
		n = num;
		parents = new int[n];
		rank = new int[n];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		Arrays.fill(rank, 0);
	}
	
	public int[] union(int i,int j) {
		int[] tr =new int[2];
		if(find(i) != find(j)) {
			tr[0] = i; tr[1] = j;
			link(find(i),find(j));
		}
		return tr;
	}

	private void link(int i, int j) {
		if(rank[i] < rank[j]) {
			parents[i] = j;
		} else {
			parents[j] = i;
			if(rank[i] == rank[j]) {
				rank[i]++;
			}
		}
		
	}

	private int find(int i) {
//		System.out.println("in find");
		int i1 = 0;
//		System.out.println(parents[i] +"    "+ i);
		if(parents[i] == i) {
//			System.out.println(parents[i] +"  = "+ i);
			return i;
		} else {
			i1 = find(parents[i]);
//			parents[i] = i1;
			return i1;
//			System.out.println(parents[i] +" !=  "+ i);
		}
//		return i1;
	}
	
	
}
