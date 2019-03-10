import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Frati {
	
	
	
	static class Task {
		public static final String INPUT_FILE = "frati.in";
		public static final String OUTPUT_FILE = "frati.out";
		String seq;
		int N;
		ArrayList<Competition> Contest = new ArrayList<>();
		/* Comparator folosit pentru a sorta descrescator ArrayList-ul de perechi de tip
		 * <jocuri, benzi> dupa suma dintre jocuri si benzi, iar daca acestea sunt egale, 
		 * se va sorta descrescator dupa jocuri.
		 */
		public class CustomComparator implements Comparator<Competition> {
			@Override
			public int compare(Competition c1, Competition c2) {
				int sum1 = c1.jocuri + c1.benzi;
				int sum2 = c2.jocuri + c2.benzi;
				if (sum1 > sum2) {
					return -1;
				} else { 
					if (sum1 < sum2) {
						return 1;
					} else {
						if (c1.jocuri > c2.jocuri) {
							return -1;
						} else if (c1.jocuri == c2.jocuri) {
							return 0;
						} else {
							return 1;
						}
					}
				}
			}
		}
		/* clasa de tip pereche folosita pentru a retine fiecare concurs in parte */
		class Competition {
			int jocuri;
			int benzi;
			
			Competition(int jocuri, int benzi) {
				this.jocuri = jocuri;
				this.benzi = benzi;
			}
			
			public String toString() {
				return jocuri + " " + benzi;
			}
		}
		/* Citirea datelor de intrare */
		private void readInput() {
			try {
				int jocuri, benzi;
				StringTokenizer st;
				BufferedReader b = new BufferedReader(new FileReader(INPUT_FILE));
				Competition c;
				if ((seq = b.readLine()) != null) {
					N = Integer.parseInt(seq);
				}
				while ((seq = b.readLine()) != null) {
					st = new StringTokenizer(seq);
					jocuri = Integer.parseInt(st.nextToken());
					benzi = Integer.parseInt(st.nextToken());
					c = new Competition(jocuri, benzi);
					Contest.add(c);
				}
				b.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		/* Scrierea in fisier a rezultatului */
		private void writeOutput(Competition result) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(new File(OUTPUT_FILE)));
				out.write(result.toString());
				out.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		
		/* Functie folosita pentru aflarea rezultatului care functioneaza in felul urmator:
		 * Mai intai se sorteaza ArrayList-ul de concursuri corespunzator criteriului
		 * specificat anterior la comparator. Apoi Jon alege de fiecare data primul concurs
		 * pe care il gaseste in lista de concursuri, iar Sam va alege, in caz ca 2 sau mai multe
		 * concursuri au aceeasi suma, ultimul concurs ce indeplineste acea conditie. Astfel,
		 * se garanteaza respectarea tuturor conditiilor din cerinta.
		 */
		
		private Competition getResult() {
			Collections.sort(Contest, new CustomComparator());	
			boolean[] visited = new boolean[N];
			int Jon = 0;
			int Sam = 0;
			int pos;
			int player = 0;
			int current_sum;
			int new_sum;
			boolean flag = true;
			
			/* In caz ca toate concursurile au fost alese deja, 
			 * nu mai are rost sa continuam cautarea */
			for (int i = 0; i < N; i++) {
				for (int l = 1; i < N && flag; l++) {
					if (visited[l] != true) {
						flag = false;
					}
				}
				
				if (flag) {
					break;
				}
				
				/* Daca jucatorul este Jon: */
				if (player % 2 == 0) {
					for (int k = i; k < N; k++) {
						if (visited[k] == false) {
							Jon += Contest.get(k).jocuri;
							visited[k] = true;
							player++;
							break;
						}	
					}
					/* Daca jucatorul este Sam: */
				} else if (player % 2 == 1) {
					for (int k = i; k < N; k++) {
						if (visited[k] == false) {
							pos = k;
							current_sum = Contest.get(pos).benzi + Contest.get(pos).jocuri;
							new_sum = current_sum;
							
							while (current_sum == new_sum && pos < N - 1) {
								pos = pos + 1;
								new_sum = Contest.get(pos).benzi + Contest.get(pos).jocuri;
							}
							
							if (k != N - 1) {
								if (visited[pos - 1] == true) {
									for (int x = pos - 1; x >= k; x--) {
										if (visited[x] == false) {
											visited[x] = true;
											Sam += Contest.get(x).benzi;
											break;
										}
									}
								} else {
									visited[pos - 1] = true;
									Sam += Contest.get(pos - 1).benzi;
								}
							} else {
								visited[pos] = true;
								Sam += Contest.get(pos).benzi;
							}
							
							player++;
							if (pos - 1 != i) {
								i--;
							}
							break;
						}
					}
				}
					
			}
			return new Competition(Jon, Sam);
		}

		public void solve() {
			readInput();
			Competition Result = getResult();
			writeOutput(Result);
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}

