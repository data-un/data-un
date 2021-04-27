import java.util.Scanner;

import java.sql.*;

public class VData {

    public static void Datamanagment() {

        String flag = "S";

        do {

            System.out.println("Seleccione la operación a realizar");

            System.out.println("1. Insertar");

            System.out.println("2. Actualizar");

            System.out.println("3. Eliminar");

            System.out.println("4. Mostrar datos");

            System.out.println("5. Salir");

            Scanner reader = new Scanner(System.in);

            int n = reader.nextInt();

            Connection c = null;
            Statement stmt = null;

            try {

                String path = "jdbc:sqlite:dbase.db";
                c = DriverManager.getConnection(path);

                c.setAutoCommit(false);

                stmt = c.createStatement();
                String name = "", sql = "";

                int id;

                Scanner scanName;

                switch (n) {

                case 1:

                    scanName = new Scanner(System.in);

                    System.out.println("Enter Name:");

                    name = scanName.nextLine();

                    sql = "INSERT INTO SampleTable (s_name) " + "VALUES ('"+name+"')";

                    stmt.executeUpdate(sql);

                    System.out.println("Datos insertados correctamente");

                    break;

                case 2:

                    System.out.println("Enter id:");

                    scanName = new Scanner(System.in);

                    id = scanName.nextInt();

                    System.out.println("Enter name:");

                    scanName = new Scanner(System.in);

                    name = scanName.nextLine();

                    sql = "UPDATE SampleTable SET s_name = '"+name+"' WHERE id=" +id;

                    stmt.executeUpdate(sql);

                    System.out.println("Datos actualizados correctamente");

                    break;

                case 3:

                    System.out.println("Enter Product id:");

                    scanName = new Scanner(System.in);

                    id = scanName.nextInt();

                    sql = "DELETE FROM SampleTable WHERE id=" + id + ";";

                    stmt.executeUpdate(sql);

                    System.out.println("Datos eliminados correctamente");

                    break;

                case 4:

                    ResultSet rs = stmt.executeQuery("SELECT * FROM SampleTable;");

                    System.out.println("ID\t Name\t");

                    while (rs.next()) {

                        id = rs.getInt("id");

                        name = rs.getString("s_name");

                        System.out.println(id + "\t " + name + " \t " );

                    }

                    rs.close();

                    break;

                case 5:

                    System.exit(0);

                    break;

                default:

                    System.out.println("Opcion incorrecta");

                    break;

                }

                stmt.close();

                c.commit();

                c.close();

            }

            catch (Exception e) {

                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);

            }

            System.out.println("Continuar S ó N?");

            reader = new Scanner(System.in);

            flag = reader.nextLine();

        }

        while (flag.equalsIgnoreCase("S"));
    }

}