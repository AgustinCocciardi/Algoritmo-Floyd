package floyd;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Floyd {

	private int cantidadNodos;
	private int cantidadAristas;
	private int nivelActual;
	private int[][] matrizActual;
	private int[][] matrizAnterior;
	private final int MAXIMO=1000;
	
	public Floyd(Scanner entrada) {
		int nodo1, nodo2, costo;
		this.cantidadNodos = entrada.nextInt();
		this.cantidadAristas = entrada.nextInt();
		this.nivelActual = 1;
		this.matrizActual = new int[this.cantidadNodos][this.cantidadNodos];
		this.matrizAnterior = new int[this.cantidadNodos][this.cantidadNodos];
		for (int[] row : matrizAnterior) {
			Arrays.fill(row, MAXIMO);
		}
		for(int i=0; i<this.cantidadAristas; i++) {
			nodo1 = entrada.nextInt()-1;
			nodo2 = entrada.nextInt()-1;
			costo = entrada.nextInt();
			this.matrizAnterior[nodo1][nodo2] = costo;
		}
		for(int i=0; i< this.cantidadNodos; i++) {
			this.matrizAnterior[i][i] = 0;
		}
		this.matrizActual = this.matrizAnterior;
	}
	
	public static int encontrarMenor(int num1, int num2) {
		return (num1<num2) ? num1 : num2;
	}
	
	public void calcularFloyd() {
		while(this.nivelActual <= this.cantidadNodos) {
			for(int i=0; i<this.cantidadNodos; i++) {
				for(int j=0; j<this.cantidadNodos; j++) {
					if(i != j && i != this.nivelActual-1 && j != this.nivelActual-1) {
						this.matrizActual[i][j] = encontrarMenor(this.matrizAnterior[i][j],
						(this.matrizAnterior[i][this.nivelActual-1] + this.matrizAnterior[this.nivelActual-1][j]) );
					}
				}
			}
			this.matrizAnterior = this.matrizActual;
			this.nivelActual++;
		}
	}
	
	public void mostrarMatriz() {
		for(int i=0; i<this.cantidadNodos; i++) {
			for(int j=0; j<this.cantidadNodos; j++) {
				System.out.print(this.matrizActual[i][j] + " ");
			}
			System.out.print('\n');
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner entrada = new Scanner(new FileReader("floyd.in"));
		Floyd floyd = new Floyd(entrada);
		entrada.close();
		floyd.calcularFloyd();
		floyd.mostrarMatriz();
	}

}
