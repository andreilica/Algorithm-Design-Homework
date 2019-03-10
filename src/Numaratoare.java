import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Numaratoare {
	static class Task {
		public static final String INPUT_FILE = "numaratoare.in";
		public static final String OUTPUT_FILE = "numaratoare.out";

		String seq;
		int s,n,i;
		int[] sol;
		int solution_number = 0; //Numarul de solutii gasite.
		StringBuilder sb = new StringBuilder();
		ArrayList<String> solution = new ArrayList<>();
		/* Comparator folosit pentru a sorta descrescator ArrayList-ul de rezultate
		 * conform criteriului din cerinta.
		 */
		public class CustomComparator implements Comparator<String> {
			@Override
			public int compare(String c1, String c2) {
				int val1, val2;
				StringTokenizer st1 = new StringTokenizer(c1);
				StringTokenizer st2 = new StringTokenizer(c2);
				while (st1.hasMoreTokens()) {
					val1 = Integer.parseInt(st1.nextToken());
					val2 = Integer.parseInt(st2.nextToken());
					if (val1 < val2) {
						return 1;
					}
					if (val1 > val2) {
						return -1;
					}
				}
				return 0;
			}
		}
		
		/* Citirea datelor de intrare */
		
		private void readInput() {
			try {
				
				BufferedReader b = new BufferedReader(new FileReader(INPUT_FILE));
				seq = b.readLine();
				s = Integer.parseInt(seq);
				seq = b.readLine();
				n = Integer.parseInt(seq);
				seq = b.readLine();
				i = Integer.parseInt(seq);
				int max = (int) Math.pow(10, 2) * 2;
				sol = new int[max];
				b.close();
				sb.append(s);
				sb.append("=");
				
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		/* Scrierea in fisierul de output */
		private void writeOutput(String result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				if (i > solution_number) {
					pw.printf("%c\n", '-');
				} else {
					pw.printf("%s\n", result);
				}
				
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		/* Functie folosita pentru adaugarea unei solutii valide in ArrayList-ul de solutii */
		public void adaugare(int l) { 
			int x;
			StringBuilder part = new StringBuilder();
			if (l == n) {
				solution_number++;
				for (x = l; x >= 1; x--) {
					part.append(sol[x]);
					part.append(" ");
				}
				solution.add(part.toString());
			}

		}
		
		/* Functia de backtracking care genereaza toate posibilitatile de a 
		 * descompune un numar s in suma de numere intregi, strict pozitive
		 */
		
		public void back(int k, int sp) { 
			int j;
			if (sp == s) {
				adaugare(k - 1);
			} else {
				for (j = 1 ;j <= s - sp; j++) {
					if (j >= sol[k - 1]) {
						sol[k] = j;
						back(k + 1, sp + j);
					}
				}
			}
		}
		
		/* Functie folosita pentru a lua a i-a solutie din vectorul de solutii
		 * si de a crea string-ul ce trebuie pasat ca rezultat functiei de scriere
		 * in fisier. 
		 */
		private String getResult() {
			
			back(1, 0);
			if (i <= solution_number) {

				Collections.sort(solution, new CustomComparator());
				StringTokenizer s = new StringTokenizer(solution.get(i));
				while (s.hasMoreTokens()) {
					sb.append(s.nextToken());
					sb.append("+");
				}
				String result = sb.toString();
				System.out.println(result);
				return result.substring(0, result.length() - 1);
			} else {
				return null;
			}
		}

		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}

