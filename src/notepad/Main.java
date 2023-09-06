package notepad;

import Clases.Metodos;

import java.util.Scanner;
import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner (System.in);
		
		//boolean salir = false;
	/*En Java, los parámetros se pasan por valor, si uso este parámetro booleano para el metodo método volverAlMenu
	se crea una copia del valor original y las modificaciones dentro del método solo afectan a esa copia
	Para poder actualizar el valor de salir en el bucle while, tengo que usar un objeto mutable, */
		
		AtomicBoolean salir = new AtomicBoolean(false);
	/*La clase AtomicBoolean proporciona métodos para manipular el valor booleano de manera mutable*/
		
		String titulo = "";

 while (!salir.get()) {	
		System.out.println("Hola! Bienvenido a tus notas, ingrese segun corresponda: ");
		System.out.println("1. Crear una nueva nota");
		System.out.println("2. Consultar nota existente	");
		System.out.println("3. Actualizar titulo");
		System.out.println("4. Actualizar contenido");
		System.out.println("5. Mostrar todas las notas");
		System.out.println("6. Enlazar notas");
		System.out.println("7. Desenlazar notas");
		System.out.println("8. Importar notas");
		System.out.println("9. Eliminar nota");
		System.out.println("0. Salir");
		
		int opcion = scanner.nextInt(); /* nextInt solo consume el número en sí y deja el carácter de nueva línea (salto de línea) 
		en el búfer de entrada. Este es capturado por la siguiente llamada a nextLine() y se interpreta como una línea vacía*/
		
		
		
		switch(opcion) {
		
		case 1 : //Crear 
			
			  Metodos crear = new Metodos();
			  
			  System.out.println("Selecciono 'Crear una nueva nota'");		
			  scanner.nextLine(); /* adicional, para consumir el salto de línea pendiente, que quedo en el búfer de entrada por el nextInt anterior.
			   Prepara correctamente el búfer de entrada para que la siguiente llamado, evitando que el nextLine() del titulo, 
			   lea ese salto de línea (y devuelva una cadena vacía) en lugar de la línea ingresada por el usuario (el título) */
			  
			  System.out.println("Ingrese el titulo que desea para su nueva nota");
			  titulo = (scanner.nextLine()).trim(); //eliminar los espacios en blanco al principio y al final de un String
			  
              System.out.println("Escriba el contenido de su nota");
              String contenido = scanner.nextLine();
			  crear.crearNota(titulo, contenido);
			  
			  volverAlMenu(salir);
			
			break;
			
		case 2: //Consultar 
			
			System.out.println("Selecciono 'Consultar nota existente'");		
			scanner.nextLine(); 
			Metodos consultar = new Metodos();
			
			System.out.println("Ingrese el titulo de la nota que desea consultar");
			titulo = (scanner.nextLine()).trim();
			
			consultar.consultarNota(titulo);
			
			volverAlMenu(salir);
			
			break;
			
		case 3: //Actualizar Titulo
			
			System.out.println("Selecciono 'Actualizar Titulo'");
			scanner.nextLine(); 
			
			Metodos actualizarTitulo = new Metodos();
			
			System.out.println("Ingrese el titulo de la nota que desea consultar");
			titulo = (scanner.nextLine()).trim();
			
			actualizarTitulo.actualizarTitulo(titulo);
			
			volverAlMenu(salir);
			
			break;
			
        case 4: //Actualizar Contenido
			
			System.out.println("Selecciono 'Actualizar nota'");
			scanner.nextLine(); 
			
			Metodos actualizarContenido = new Metodos();
			
			System.out.println("Ingrese el titulo de la nota que desea consultar");
			titulo = (scanner.nextLine()).trim();
			
			actualizarContenido.actualizarContenido(titulo);
			
			volverAlMenu(salir);
			
			break;
			
			
		case 5: //Listar
			
			System.out.println("Selecciono 'Mostrar todas las notas'");
			scanner.nextLine(); 
			
			Metodos listar = new Metodos();
			
			listar.listarNotas();
			
			volverAlMenu(salir);
			
			break;
			
		case 6: //Enlazar
			
			System.out.println("Selecciono 'Enlazar nota'");
			scanner.nextLine(); 
			
			Metodos enlazar = new Metodos();
			
			System.out.println("Ingrese el titulo de la nota que desea enlazar");
			titulo = scanner.nextLine();
			
			System.out.println("Ingrese el archivo al cual desea enlazar la nota");
			String archivoEnlace = scanner.nextLine();
			
			enlazar.enlazarNota(titulo, archivoEnlace);
			
			volverAlMenu(salir);
			
			break;
			
		
			
		case 7: //Desenlazar
			
			System.out.println("Selecciono 'Desenlazar nota'");
			scanner.nextLine(); 
			
            Metodos desenlazar = new Metodos();
			
			System.out.println("Ingrese el titulo de la nota que desea Desenlazar");
			titulo = scanner.nextLine();
			
			System.out.println("Ingrese el archivo al cual se encuentra la nota enlazada");
			String archivoEnlace2 = scanner.nextLine();
			
			desenlazar.desenlazarNota(titulo, archivoEnlace2);
			
			volverAlMenu(salir);
			
			break;
			
       case 8: //Importar
			
			System.out.println("Selecciono 'Importar nota'");
			scanner.nextLine(); 
			
			Metodos importar = new Metodos();
		
			System.out.println("Ingrese la ruta absoluta de la nota que desea importar, incluyendo la unidad de disco y el nombre de usuario");
			System.out.println("Un ejemplo de guia podria ser: C:\\Users\\nombre_de_usuario\\Desktop\\nombre_del_archivo");
			String rutaAbsoluta = scanner.nextLine();
			
			System.out.println("Ingrese el titulo de la nota que desea importar");
			titulo = scanner.nextLine();
			
			importar.importarNota(titulo, rutaAbsoluta);
			
			volverAlMenu(salir);
			
			break;
			
        case 9: //Eliminar
			
			System.out.println("Selecciono 'Eliminar nota'");
			scanner.nextLine(); 
			
			Metodos eliminar = new Metodos();
			
			System.out.println("Ingrese el titulo de la nota que desea eliminar");
			titulo = (scanner.nextLine()).trim();
			
			eliminar.eliminarNota(titulo);
			
			volverAlMenu(salir);
			
			break;
			
		case 0: //Salir
			
			Metodos copiaDeSeguridad = new Metodos();
			copiaDeSeguridad.copiaDeSeguridad();
			
			System.out.println("Hasta Pronto!");	
			salir.set(true);;
			break;	
		
		default:
			
			System.out.println("Opcion inválida! Vuelva a intentarlo");
			salir.set(false);
		}
 }	
		
		
	}
	
	public static void volverAlMenu(AtomicBoolean salir) {
	/*Utilizo AtomicBoolean para mantener el estado mutable de la variable salir. 
	 * La función toma el AtomicBoolean como parámetro y puede modificar su valor utilizando el método set().*/
		
		Scanner scanner = new Scanner (System.in);
		
		  System.out.println("Desea volver al menu (si/no)?");
		  String volver = scanner.next();
		  
		  if(volver.equalsIgnoreCase("si")) {
			  salir.set(false);
		  }else {
			  
			  Metodos copiaDeSeguridad = new Metodos();
				copiaDeSeguridad.copiaDeSeguridad();
				
			  System.out.println("Hasta pronto!");
			  salir.set(true);
		  }
	}
}
