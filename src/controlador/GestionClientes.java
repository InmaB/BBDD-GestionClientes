package controlador;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Inma Balbuena
 */
public class GestionClientes {
	
	public static Scanner ent = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

    	
        DBManager.connect();
  //      DBManager.mostrarBaseDatos(); //llamada al metodo 
  //    DBManager.usarBBDD(); //llamada al metodo de usar base de datos que el usuario ha elegido

        boolean salir = false;
        do {
            salir = menuPrincipal();
        } while (!salir);


        DBManager.close();
        
        

    }
    

    public static boolean menuPrincipal() throws SQLException {
        System.out.println("");
        System.out.println("MENU PRINCIPAL");
        System.out.println("1. Listar clientes");
        System.out.println("2. Ordenar clientes");
        System.out.println("3. Nuevo cliente");
        System.out.println("4. Modificar cliente");
        System.out.println("5. Eliminar cliente");
        System.out.println("6. Volcar tabla");
        System.out.println("7. Crear tabla");
        System.out.println("8. Salir");
            
        int opcion = pideInt("Elige una opción: ");
        
        switch (opcion) {
        
            case 1:
                opcionMostrarClientes();
                return false;
            case 2:
                DBManager.ordenarClientes();
                return false;
            case 3:
                opcionNuevoCliente();
                return false;
            case 4:
                opcionModificarCliente();
                return false;
            case 5:
                opcionEliminarCliente();
                return false;
            case 6:
            	DBManager.volcarTabla();
                return false;
            case 7:
            	DBManager.crearTabla("productos");
                return false;
            case 8:
            	System.out.println("Saliendo del programa");
                return true;
            default:
                System.out.println("Opción elegida incorrecta");
                return false;
        }
        
    }
    
    public static int pideInt(String mensaje){
        
        while(true) {
            try {
                System.out.print(mensaje);
                int valor = ent.nextInt();
                //ent.nextLine();
                return valor;
            } catch (Exception e) {
                System.out.println("No has introducido un número entero. Vuelve a intentarlo.");
            }
        }
    }
    
    public static String pideLinea(String mensaje){
        
        while(true) {
            try {
                System.out.print(mensaje);
                String linea = ent.nextLine();
                return linea;
            } catch (Exception e) {
                System.out.println("No has introducido una cadena de texto. Vuelve a intentarlo.");
            }
        }
    }

    public static void opcionMostrarClientes() {
        System.out.println("Listado de Clientes:");
        DBManager.printTablaClientes();
    }

    public static void opcionNuevoCliente() {

        System.out.println("Introduce los datos del nuevo cliente:");
        String nombre = pideLinea("Nombre: ");
        String direccion = pideLinea("Dirección: ");

        boolean res = DBManager.insertCliente(nombre, direccion);

        if (res) {
            System.out.println("Cliente registrado correctamente");
        } else {
            System.out.println("Error :(");
        }
    }

    public static void opcionModificarCliente() {

        int id = pideInt("Indica el id del cliente a modificar: ");

        // Comprobamos si existe el cliente
        if (!DBManager.existsCliente(id)) {
            System.out.println("El cliente " + id + " no existe.");
            return;
        }

        // Mostramos datos del cliente a modificar
        DBManager.printCliente(id);

        // Solicitamos los nuevos datos
        String nombre = pideLinea("Nuevo nombre: ");
        String direccion = pideLinea("Nueva dirección: ");

        // Registramos los cambios
        boolean res = DBManager.updateCliente(id, nombre, direccion);

        if (res) {
            System.out.println("Cliente modificado correctamente");
        } else {
            System.out.println("Error :(");
        }
    }
    
    /**
     * Eliminar cliente
     */
    public static void opcionEliminarCliente() {

        int id = pideInt("Indica el id del cliente a eliminar: ");

        // Comprobamos si existe el cliente
        if (!DBManager.existsCliente(id)) {
            System.out.println("El cliente " + id + " no existe.");
            return;
        }

        // Eliminamos el cliente
        boolean res = DBManager.deleteCliente(id);

        if (res) {
            System.out.println("Cliente eliminado correctamente");
        } else {
            System.out.println("Error :(");
        }
    }
}
