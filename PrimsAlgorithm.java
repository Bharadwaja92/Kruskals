package module5_Prims;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class PrimsAlgorithm {

	public static void main(String[] args) {
		System.out.println("input pls...");
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		//		int num = 7;
		String nod = sc.nextLine();
		String[] nodes = nod.substring(1,nod.length()-1).split(",");
		String src = sc.nextLine();
//		String src = "B";
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
		doPrimsAlgorithm(adjMat,src,nodes);
		sc.close();
	}

	private static void doPrimsAlgorithm(int[][] adjMat, String src, String[] nodes) {
		int len = adjMat.length;
		int min = 0,total = 0,v1 = 0,v2 = 0;
		int[] dists = new int[len];    Arrays.fill(dists, 999);			// Initially distances are infinity
		int[] parents = new int[len];	Arrays.fill(parents,-1);		// Parents are not defined
		int srcIndex = findIndex(nodes, src);
		boolean[] visited = new boolean[adjMat.length];	Arrays.fill(visited, false);
		String[] temp = nodes;

		ArrayList<String> al = new ArrayList<String>();					// Store the path in it
		Hashtable<String, String> ht = new Hashtable<String, String>();		// Every node and how we reached it

		visited[srcIndex] = true;   dists[srcIndex] = 0;   parents[srcIndex] = 0;
		al.add(nodes[srcIndex]); 	ht.put(nodes[srcIndex], nodes[srcIndex]);
		int x = 0;

		while(x < adjMat.length-1) {		// Iterate (n-1) times to get the spanning tree with (n-1) edges
			min = 999;
			for (int j = 0; j < adjMat.length; j++) {
				if(visited[j]) {
					for (int k = 0; k < visited.length; k++) {		// To find the parent of parent of....
						if(!visited[k]) {
							if(min > adjMat[j][k]) {
								min = adjMat[j][k];
								v1 = j;
								v2 = k;
							} else if(min == adjMat[j][k] && nodes[j].equals(temp[nodes.length-1])) {
								v1 = j;
								v2 = k;
							}
						}
					}
				}
			}
			x++;
			visited[v2] = true;
			al.add(nodes[v2]);
			total += min;
			ht.put(nodes[v2], nodes[v1]);
		}

		System.out.print("(");

		for (int i = 0; i < nodes.length-1; i++) {
			System.out.print(ht.get(nodes[i])+",");
		}
		System.out.println(ht.get(nodes[nodes.length-1])+")");
		System.out.print("(");
		for (int i = 0; i < nodes.length-1; i++) {
			System.out.print(al.get(i)+",");
		}
		System.out.println(al.get(nodes.length-1)+")");
		System.out.println(total);
	}

	private static int findIndex(String[] nodes, String src) {

		for (int i = 0; i < nodes.length; i++) {
			if(nodes[i].equals(src)) {
				return i;
			}
		}
		return 0;
	}

}
