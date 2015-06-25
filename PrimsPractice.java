package module5_Prims;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class PrimsPractice {

	public static void main(String[] args) {	// Get the input here and send it for processing
		
		System.out.println("input pls...");
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		//		int num = 7;
		String nod = sc.nextLine();
		String[] nodes = nod.substring(1,nod.length()-1).split(",");
		String src = sc.nextLine();
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

		int len = nodes.length;
		boolean[] visited = new boolean[len];	Arrays.fill(visited, false);
		int[] dists = new int[len];				Arrays.fill(dists, 999);
		int[] parents = new int[len];		Arrays.fill(parents, -1);
		ArrayList<String> al = new ArrayList<String>();
		Hashtable<String, String> ht =new Hashtable<String, String>();
		int srcIndex = findIndex(nodes, src);
		visited[srcIndex] = true;  dists[srcIndex] = 0;  parents[srcIndex] = 0;
		al.add(src);			// Path to source is itself					 		
		ht.put(src, src);		// Parent of source is source itself  :P
		int u = 0,v = 0;		// u is for the source vertex and v is for the destination
		int total = 0,min = 0;	// Total is the total edge weight and min is the minimum edge weight.
		int edgeCount = 0;
		
		while(edgeCount < adjMat.length-1) {
			for (int i = 0; i < nodes.length; i++) {
				min = 9999;
				for (int j = 0; j < nodes.length; j++) {
					if(visited[j]) {							// Source visited 
						for (int k = 0; k < nodes.length; k++) {
							if(!visited[k]) {					// Destination not visited yet
								if(min > adjMat[j][k]) {		// Minimum edge weight found
									min = adjMat[j][k];
									u = j;
									v = k;
//								} else if(min > adjMat[j][k] && nodes[j].equals(nodes[nodes.length-1])) {
//									
//									 u = j;
//									 v = k;
								}
							}
						}
					}
				}
			}
			visited[v] = true;
			al.add(nodes[v]);
			ht.put(nodes[v], nodes[u]);
			System.out.println("nodes[v] is "+nodes[v]+" and nodes[u] is "+nodes[u]);
			total += min;
			edgeCount++;
		}
		System.out.println("ht is ");
		for (int i = 0; i < nodes.length; i++) {
			System.out.print(ht.get(nodes[i])+"  ");
		}
		System.out.println("\nAl is ");
		for (int i = 0; i < nodes.length; i++) {
			System.out.print(al.get(i)+"  ");
		}
		System.out.println("\nTotal is "+total);
		
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
