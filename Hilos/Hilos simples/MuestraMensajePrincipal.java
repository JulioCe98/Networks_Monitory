package Hilos;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.text.Utilities;



public class MuestraMensajePrincipal {
	
	public static void main(String[] args) {
	/*	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Numero de hilos a crear?");
	
		try {
			int numeroHilos = Integer.parseInt(br.readLine());
			
			MuestraMensaje[] arregloHilos = new MuestraMensaje[numeroHilos];
			for (int i = 0; i < arregloHilos.length; i++) {
				arregloHilos[i] = new MuestraMensaje("hilo" + (i +1));
			}
			
			for (int i = 0; i < arregloHilos.length; i++) {
				arregloHilos[i].start();
			}
			
			for (int i = 0; i < arregloHilos.length; i++) {
				try {
					arregloHilos[i].join();
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				
			int mayor = -1, posicionMayor = -1;
			for (int j = 0; j < arregloHilos.length; j++) {
				if (arregloHilos[j].getTiempoEspera() > mayor) {
					mayor = arregloHilos[j].getTiempoEspera();
					posicionMayor = j;
				}
			}
				
			System.out.println("El hilo mas demorado fue :" + arregloHilos[posicionMayor].getName() 
					+ " con un tiempo de : " + mayor + " milisegundos");
			
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		*/
		
		//crear un grupo de hilos
		
		ThreadGroup grupoHilos = new ThreadGroup("Grupo Hilos");
		
		//Crear los hilos y guardarlos en un arreglo
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Numero de hilos a crear?");
		try {
			int numeroHilos = Integer.parseInt(br.readLine());
			Thread[] arregloHilos = new Thread[numeroHilos];
			for (int i = 0; i < arregloHilos.length; i++) {
				arregloHilos[i] = new MuestraMensaje(grupoHilos, "hilo" + (i+1));
			}
			
			//arrancar los hilos
			for (int i = 0; i < arregloHilos.length; i++) {
				arregloHilos[i].start();
			}
			//se queda en un ciclo hasta que algun hilo deja de estar activo
			
			while(grupoHilos.activeCount() == arregloHilos.length) {
				
			}
			
			//interrumpir todos los hilos
			grupoHilos.interrupt();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
}
