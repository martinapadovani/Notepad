package Clases;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Scanner;


public class Metodos {
	
	Scanner scanner = new Scanner (System.in);
	
	String rutaCarpetaNotas = "C:\\Users\\Living\\Desktop\\Java\\notepad\\notas\\";
	
	//Crear 

	public void crearNota(String titulo, String contenido) {
		
		String nombreArchivo = titulo.replace(" ", "_") + ".txt";
		/*.replace reemplaza todos los espacios en blanco en el título con guiones bajos (_). 
		 * Mejorando la interaccion con el usuario, ya que este puede ingresar títulos con espacios 
		 * y se reemplazarán por guiones bajos en el nombre del archivo.*/
		
		File archivo = new File(rutaCarpetaNotas, nombreArchivo);
		
		//File archivo = new File("notas" + "\\" + titulo + ".	txt");
		
		try {
			PrintWriter escritor = new PrintWriter(archivo); 
			escritor.println(contenido);
			escritor.close();
			
		}catch(FileNotFoundException error){
			System.out.println(error.getMessage());
		}
		
		System.out.println("Nota creada exitosamente!");	
	}
	
	//Consultar 
	
	public void consultarNota(String titulo) {
		
		String nombreArchivo = titulo.replace(" ", "_") + ".txt";
		File archivo = new File(rutaCarpetaNotas, nombreArchivo);
		
		
		
        try {
			
			FileReader detalleDelArchivo = new FileReader(archivo); 
		
			BufferedReader buffer = new BufferedReader (detalleDelArchivo); 
			
			String lineaActual;
			
			while((lineaActual = buffer.readLine())!= null) { 
				System.out.println(lineaActual);
			}
			
			buffer.close();
			
		} catch(IOException error) {
			
			System.out.println(error.getMessage());
		} 
		
	}
	
	//Actualizar Titulo
	
	public void actualizarTitulo(String titulo) {
		
		//Referencio el archivo original
		String nombreArchivo = titulo.replace(" ", "_") + ".txt";
		File archivo = new File(rutaCarpetaNotas, nombreArchivo);
			
		  try{
			
			//Creo un nuevo archivo con el titulo deseado
			System.out.println("Ingrese el nuevo titulo");
			String nuevoTitulo = (scanner.nextLine()).trim();
			nuevoTitulo = nuevoTitulo.replace(" ", "_") + ".txt";
			File nuevoArchivo = new File(rutaCarpetaNotas, nuevoTitulo);
			
			//Reemplazo el archivo original por el nuevo archivo, con el titulo deseado
			
			boolean cambioDeNombre = archivo.renameTo(nuevoArchivo);
			
			System.err.println("El cambio de titulo fue exitoso!");
			
		  }catch(NullPointerException error) {
		
		        System.out.println(error.getMessage());
	     }
	   }
	
	//Actualizar Contenido
	
      public void actualizarContenido(String titulo) {
    	  
    	  String nombreArchivo = titulo.replace(" ", "_") + ".txt";
  		  File archivo = new File(rutaCarpetaNotas, nombreArchivo);
    	  
    try {	
  		  System.out.println("Ingrese el nuevo contenido");
  		  String contenido = scanner.nextLine();
  		  
  		  System.out.println("Desea que el contenido reemplace el preexistente? si/no");
  		  String reemplazarContenido = scanner.next();
  		  boolean realizarAppend;
  		  
  		  if(reemplazarContenido.equalsIgnoreCase("si")) {
  			realizarAppend = false;
  		  }
  		  
  		  else {
  			realizarAppend = true;
  		  }
  		  
  		  FileWriter detalleDelArchivo = new FileWriter(archivo, realizarAppend);
  		
  		  PrintWriter escritor = new PrintWriter(detalleDelArchivo);
  		  escritor.println(contenido);
  		  escritor.close();
  		  
  		  System.out.println("El contenido fue actualizado exitosamente!");
  		
    } catch(IOException error) { 
    	
		System.out.println(error.getMessage());
	  } 	
		
	}
	
	//Listar
	
	public void listarNotas() {
		
		File directorioNotas = new File (rutaCarpetaNotas);
		
		String[] archivos = directorioNotas.list();
		
		int i = 0;
		
		try {
		
		for (String nombreArchivo : archivos) {
			   
		//Obtener la ruta completa de los archivos dentro del directorio notas/.
			   
			String rutaArchivo = directorioNotas.getPath() + "\\" + nombreArchivo; 
			/*Concateno la ruta del directorio notas/, el separador de archivos de Windows (\), y el nombre del archivo, 
	          para formar la ruta completa del archivo.*/
			
			 Path path = Paths.get(rutaArchivo); 
        /*   Proporciono la ruta del archivo como un objeto Path, ya que Files.readAttributes,
			 requiere como 1er parametro un objeto de tipo Path (que especifique la ruta del archivo) */
			 
		// Obtener atributos básicos del archivo:	 
			 
	    /*   Accedo al metodo estatico .readAttributes() de Files para leer los atributos básicos del archivo especificado por path.
	          El método utiliza la ruta del archivo para determinar sobre qué archivo debe leer sus atributos. 
			 Este devuelve un objeto de tipo BasicFileAttributes, por lo tanto, 
			 asigno los atributos basicos en una variable de este tipo (BasicFileAttributes)*/	
			
			 BasicFileAttributes atributosBasicos = Files.readAttributes(path, BasicFileAttributes.class);
			 
		/*   El 2do parametro `type`: Especifica el tipo de atributos que deseas obtener.
			 En este caso, que queremos obtener los atributos básicos del archivo, utilizando BasicFileAttributes.class,
			  que proporciona un conjunto básico de atributos de archivos (incluida la fecha de creación).*/
			 
		// Obtener la fecha de creación del archivo:
		 
	/*      Accedo al método .creationTime (de la interfaz BasicFileAttributes), en los atributos, para obtener la fecha de creación.
		    Este devuelve un objeto FileTime (clase en Java que representa una marca de tiempo asociada a un archivo.)
		    por lo tanto, lo guardo en una variable de este tipo*/
			 
			 FileTime fechaDeCreacionDelArchivo = atributosBasicos.creationTime();
			 
			 
	   // Muestro por consola los datos	 
             System.out.println("Nota " + i + ": " + nombreArchivo);
             System.out.println("Fecha de creacion: "  + fechaDeCreacionDelArchivo);
             
             i++;
             
         }
		} catch(IOException error) {
			System.out.println(error.getMessage());
		}
		
	}
		
	
	//Enlazar
	
	public void enlazarNota(String tituloNotaEnlazada, String nombreArchivo) {
	
		String nombreNotaEnlazada = tituloNotaEnlazada.replace(" ", "_") + ".txt";
		  File notaEnlazada = new File(rutaCarpetaNotas, nombreNotaEnlazada);
		
		try {
			
			FileReader detalleNota = new FileReader(notaEnlazada); 
		
			BufferedReader buffer = new BufferedReader (detalleNota); 
			
			String lineaActual;
			
		    String nombreArchivoEnlace = nombreArchivo.replace(" ", "_") + ".txt";
			File archivoEnlace = new File(rutaCarpetaNotas, nombreArchivoEnlace);
			
			FileWriter detalleArchivoEnlace = new FileWriter(archivoEnlace, true);
			
			PrintWriter escritor = new PrintWriter(detalleArchivoEnlace); 
			
			escritor.println("Enlace de la nota: "  );
			
			while((lineaActual = buffer.readLine())!= null) { 
				
				escritor.println(lineaActual);
			}
			
			buffer.close();
		    escritor.close();
			
			System.out.println("La nota: " + "\"" + tituloNotaEnlazada + "\"" + ", fue enlazada con exito");
			
		} catch(IOException error) {
			
			System.out.println(error.getMessage());
		} 
	}
	
	// Desenlazar 
	
	public void desenlazarNota(String tituloNotaDesenlazada, String nombreArchivo) {
		
		String nombreArchivoEnlace = nombreArchivo.replace(" ", "_") + ".txt";
		File archivoEnlace = new File(rutaCarpetaNotas, nombreArchivoEnlace);
		
	try {	
		
		FileReader detalleArchivo = new FileReader(archivoEnlace); 
		
		BufferedReader buffer = new BufferedReader (detalleArchivo); 
		
		/* StringBuilder es una clase mutable que se utiliza para construir y manipular cadenas de caracteres de manera eficiente
		  proporciona métodos para agregar, insertar y eliminar caracteres en una cadena de caracteres mutable.*/
		
		StringBuilder contenidoCopiado = new StringBuilder(); // utilizo StringBuilder para construir el contenido actualizado del archivo de enlace.
		String lineaActual;
	    boolean dentroDeLaNota = false; // indica si estamos dentro del bloque de la nota a desenlazar o no.
	    
		
		
		while((lineaActual = buffer.readLine())!= null) { 
			
			if(lineaActual.equals("Enlace de la nota: " + "\"" + tituloNotaDesenlazada + "\"")){
				dentroDeLaNota = true;
				
			} else if (lineaActual.startsWith("Enlace de la nota:")) { 
				dentroDeLaNota = false;
				//Si la linea comienza con la declaracion del enlace de otra nota, ya estamos fuera del bloque a eliminar
			}
			
		    if (!dentroDeLaNota) { //Si no estamos dentro del bloque, agregamos las líneas al StringBuilder mediante el método append()
		    	
		    	contenidoCopiado.append(lineaActual).append("\n"); //se agrega la línea actual al StringBuilder nuevoContenido
				 //"\n" agrega un salto de línea al final de cada línea para mantener el formato del contenido original.	
	        }	 
		}
		
		buffer.close();
		
		FileWriter salida = new FileWriter (archivoEnlace, false); //Elijo false para que el contenido se sobreescriba y reemplace el preexistente
		
		salida.write(contenidoCopiado.toString()); //Sobreescribo el contenido preexistente con el contenido del StringBuilder
		//utilizo toString para obtener una representación de tipo String del contenido del StringBuilder
		//permitiendo utilizarlo como una cadena de texto regular en cualquier contexto, donde se requiera una cadena de caracteres.
		
		salida.close();
	
		System.out.println("La nota: " + "\"" + tituloNotaDesenlazada + "\"" + ", fue desenlazada con exito");
		
	} catch(IOException error) {
		
		System.out.println(error.getMessage());
	  } 	
	}
		
	//RESUMEN:
	/*Se crea un StringBuilder llamado contenidoCopiado y se van agregando las líneas del archivo original excluyendo las líneas del bloque de la nota que se desea desenlazar
	  Una vez que se ha recorrido todo el archivo original y se ha construido el nuevo contenido en el StringBuilder, 
	  se utiliza el método toString() para obtener una cadena de texto que representa el contenido final.
	  Posteriormente, se puede utilizar esta cadena de texto para sobrescribir el contenido del archivo original
	  La sobreescritura se realiza mediante la apertura de un FileWriter con el archivo original y el parámetro false,
	  Luego se utiliza el método write() para escribir la cadena de texto obtenida del StringBuilder en el archivo*/
	
	//Importar
	
	public void importarNota(String titulo, String rutaAbsoluta) {
		
		String tituloNotaOriginal = titulo.replace(" ", "_") + ".txt";
		File notaOriginal = new File(rutaAbsoluta, tituloNotaOriginal);
		 Path pathNotaOriginal = Paths.get(notaOriginal.getAbsolutePath()); 
		 
		try {
			
			//Instancio el archivo que contendra la nota importada
			
			File notaImportada = new File(rutaCarpetaNotas, tituloNotaOriginal);
			Path pathNotaImportada = Paths.get(notaImportada.getPath()); 
			
		//	// Establezco algunos atributos de la nota original
			
		//	BasicFileAttributes atributosBasicosNotaOriginal = Files.readAttributes(pathNotaOriginal, BasicFileAttributes.class);
			
		//	BasicFileAttributeView atributosDeTiempo = Files.getFileAttributeView(pathNotaImportada, BasicFileAttributeView.class);
			/* Files.getFileAttributeView obtengo una instancia de BasicFileAttributeView para la nota importada,
			para poder aplicarle luego el metodo .setTimes que pertenece a esta clase (BasicFileAttributeView)*/
			
		//	atributosDeTiempo.setTimes(atributosBasicosNotaOriginal.lastModifiedTime(), atributosBasicosNotaOriginal.lastAccessTime(), atributosBasicosNotaOriginal.creationTime());
            // .setTimes de BasicFileAttributeView establece los atributos de tiempo en el archivo de destino.
			
			//Establezco el contenido de la nota original a la nota importada
			
			FileReader detalleNotaOriginal = new FileReader(notaOriginal); 
			
			BufferedReader buffer = new BufferedReader (detalleNotaOriginal); 
			
            FileWriter detalleNotaImportada = new FileWriter(notaImportada, true);
			
			PrintWriter escritor = new PrintWriter(detalleNotaImportada); 
			
			String lineaActual;
			
			while((lineaActual = buffer.readLine())!= null) { 
				
				escritor.println(lineaActual); //Sobreescribo cada linea de la nota que quiero importar, en el nuevo archivo dentro de la carpeta notas
			}
			
			buffer.close();
		    escritor.close();
			
			System.out.println("La nota: " + "\"" + titulo + "\"" + " fue importada con exito");
			
		} catch(IOException error) {
			
			System.out.println(error.getMessage());
		}  catch(UnsupportedOperationException error) {
			System.out.println(error.getMessage());
		} catch(SecurityException error) {
			System.out.println(error.getMessage());
		} 
	}
	
	//Eliminar
	
	public void eliminarNota(String titulo) {
		
		String nombreArchivo = titulo.replace(" ", "_") + ".txt";
		File archivo = new File(rutaCarpetaNotas, nombreArchivo);
		
         try {
			archivo.delete();
			System.out.println("La nota: " + "\"" + titulo + "\"" + " fue eliminada con exito");
			
		} catch (Exception error) { 
			System.out.println("Ocurrio un error al intentar eliminar el archivo");
			error.printStackTrace();
		}
			
	}
	
	//Copia de Seguridad
	
	public void copiaDeSeguridad() {
		
        File carpetaNotas = new File (rutaCarpetaNotas);
		
		String[] notas = carpetaNotas.list();
		
		try {
		
		for (String nota : notas) {
			
			File notaOriginal = new File(rutaCarpetaNotas, nota);
			File copiaDeSeguridad = new File ("C:\\Users\\Living\\Desktop\\Java\\notepad\\Backup\\", nota);
			
		    FileReader detalleNotaOriginal = new FileReader(notaOriginal); 
			BufferedReader buffer = new BufferedReader (detalleNotaOriginal); 
			
            FileWriter detallecopiaDeSeguridad = new FileWriter(copiaDeSeguridad, true);
			PrintWriter escritor = new PrintWriter(detallecopiaDeSeguridad); 
			
			String lineaActual;
			
			while((lineaActual = buffer.readLine())!= null) { 
				
				escritor.println(lineaActual); //Sobreescribo cada linea de la nota que quiero importar, en el nuevo archivo dentro de la carpeta notas
			}
			
			buffer.close();
		    escritor.close();
			}
		
		} catch (Exception error) { 
			error.printStackTrace();
		}
	}
}