package packInterfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class ManejoFicheros {

	/**
	 * @return String con el path, si no existe nos devolvera "No existe", en
	 *         este caso, obligaremos al usuario cargar una nueva fuente de
	 *         datos
	 * 
	 * 
	 * 
	 */
	public String[] cargarFConfiguracion() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String[] datos = new String[3];

		try {
			// Apertura del fichero y creacion de BufferedReader
			// para poder
			// hacer una lectura comoda (disponer del metodo
			// readLine()).
			archivo = new File("configuracion");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero. [0]=host [1]=user [2]=psw;
			String linea;
			int i = 0;
			while ((linea = br.readLine()) != null || i < 2) {
				datos[i] = linea;
				i++;
			}
			return datos;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return datos;
	}

	/**
	 * Guardar en el fichero de configuracion la ruta
	 */
	public void guardarFConfiguracion(String host, String user, String psw) {
		/**
		 * Guardar ruta para la proxima vez que abramos el programa
		 */
		File archivo = null;
		FileWriter fr = null;
		PrintWriter pw = null;
		try {
			fr = new FileWriter("configuracion", false);
			pw = new PrintWriter(fr);

			for (int i = 0; i < 3; i++) {
                switch (i) {
				case 0:
					pw.println(host);
					break;

				case 1:
					pw.println(user);
					break;
					
				case 2:
					pw.println(psw);
					break;
				}
                
			}
			System.out.println("Datos guardados en configuracion");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fr)
					fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * menu para cargar la fuente de datos
	 * 
	 * @return archivoElegido
	 */
	public File menuCargaFuenteDatos() {
		File archivoElegido = null;
		// Crear un objeto FileChooser
		JFileChooser fc = new JFileChooser();
		// Mostrar la ventana para abrir archivo y recoger la
		// respuesta
		// En el parámetro del showOpenDialog se indica la ventana
		// al que estará asociado. Con el valor this se asocia a la
		// ventana que la abre.
		int respuesta = fc.showOpenDialog(null);
		// Comprobar si se ha pulsado Aceptar
		if (respuesta == JFileChooser.APPROVE_OPTION) {
			// Crear un objeto File con el archivo elegido
			archivoElegido = fc.getSelectedFile();
			System.out.println(archivoElegido.getPath());

			// Mostrar el nombre del archvivo en un campo de texto
			// archivoElegido.setText(archivoElegido.getName());

		}
		/*
		 * Guardamos path de la fuente de datos en archivo de configuracion
		 */
		//guardarFConfiguracion(archivoElegido);

		return archivoElegido;
	}

	public String[] leerFichero(String path) {
		File archivo;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<String> listaArtistas = new ArrayList();
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(path);
			// Compruebo si el archivo ya existe
			if (archivo.exists()) {
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);

				// Lectura del fichero
				String linea;
				/*
				 * while ((linea = br.readLine()) != null)
				 * listaArtistas.add(linea);
				 */
				int cont = 0;
				while ((linea = br.readLine()) != null) {
					for (int i = 0; i < linea.length(); i++) {
						// System.out.println(linea.charAt(i));
						if (linea.charAt(i) == ',') {
							cont++;
						}
					}

				}

			} else {
				System.out.println("Aun no hay archivo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		String[] lista = new String[listaArtistas.size()];
		int i = 0;
		for (String artista : listaArtistas) {
			lista[i++] = artista;
		}
		return lista;
	}

}
