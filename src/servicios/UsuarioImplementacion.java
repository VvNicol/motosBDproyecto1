package servicios;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import dto.UsuarioDto;

/**
 * Logica de metodos de Usuario interfaz
 * 
 * @author nrojlla
 */
public class UsuarioImplementacion implements UsuarioInterfaz {
	Scanner sc = new Scanner(System.in);
	ConexionInterfaz ci = new ConexionPostgresqlImplementacion();

	@Override
	public void DarAltaUsuario() throws IOException {
		// Primero hacer la conexión con la bd
		Connection conexion = ci.GenerarConexion();

		// Verificar si la conexión no es null
		if (conexion != null) {
			// Recibir los datos del nuevo usuario
			System.out.println("Ingrese nombre:");
			String nombre = sc.nextLine();
			System.out.println("Ingrese apellidos:");
			String apellido = sc.nextLine();
			System.out.println("Ingrese dni:");
			String dni = sc.nextLine();
			System.out.println("Ingrese correo:");
			String correo = sc.nextLine();
			System.out.println("Ingrese contraseña:");
			String contrasenia = sc.nextLine();
			System.out.println("Ingrese telefono:");
			int tel = sc.nextInt();
			sc.nextLine(); // Consumir la línea nueva

			Long id = util.Util.GenerarIdUsuario(conexion); // Generar un ID único

			if (id != null) {

				// Consulta SQL para insertar el nuevo usuario
				String insertQuery = "INSERT INTO \"dlk_motos\".usuario (id_usuario, nombre_usuario, apellidos_usuario, dni_usuario ,correo_usuario, contra_usuario, tel_usuario, \"esClub\") VALUES (?, ?, ?, ?, ?, ?, ?,?)";

				// Ejecutar la inserción a la base de datos
				try (PreparedStatement ps = conexion.prepareStatement(insertQuery)) {
					ps.setLong(1, id);
					ps.setString(2, nombre);
					ps.setString(3, apellido);
					ps.setString(4, dni);
					ps.setString(5, correo);
					ps.setString(6, contrasenia);
					ps.setInt(7, tel);
					ps.setBoolean(8, false);

					// Ejecutar la inserción
					ps.executeUpdate();
					System.out.println("Usuario creado y guardado con éxito en la base de datos.");
				} catch (SQLException e) {
					System.out.println("Error al insertar usuario en la base de datos: " + e.getMessage());
				}
			} else {
				System.out.println("No se pudo generar un nuevo ID para el usuario.");
			}

		} else {
			System.out.println("No se pudo establecer la conexión.");
		}
	}

	@Override
	public void ModificarDtoUsuario() throws IOException {
		MenuInterfaz mi = new MenuImplementacion();

		// try catch para la conexion
		try (Connection conexion = ci.GenerarConexion()) {
			if (conexion != null) {// verificar que no sea la null
				System.out.println("Ingrese su dni:");
				String dni = sc.nextLine();

				UsuarioDto u = util.Util.BuscarUsuarioPorDni(dni, conexion); // Buscar por la BD dni

				if (u != null) { // Verifica que no sea null la busqueda del dni
					byte opcion;
					boolean esCerrado = false;

					do {
						opcion = mi.MenuDatos();// menu

						switch (opcion) {
						case 0:
							System.out.println("Volviste");
							esCerrado = true;
							break;
						case 1:
							System.out.println("Ingrese su nuevo nombre");
							String nuevoNombre = sc.nextLine();

							// Definir la consulta SQL para actualizar el nombre en la base de datos.
							// El símbolo "?" actúa como un parámetro que será reemplazado por valores
							// específicos más adelante.
							String updateQuery = "UPDATE \"dlk_motos\".usuario SET nombre_usuario = ? WHERE dni_usuario = ?";

							// try-catch para asegurarse de que el PreparedStatement se cierre
							// automáticamente.
							try (PreparedStatement ps = conexion.prepareStatement(updateQuery)) {
								ps.setString(1, nuevoNombre);// Asignar el nuevo nombre al primer parámetro de la
																// consulta (el "?")
								ps.setString(2, dni);// DNI como el segundo parámetro para identificar el registro a
														// actualizar

								// Ejecutar la consulta de actualización en la base de datos
								// Este método devuelve el número de filas afectadas por la actualización.
								int filasActualizadas = ps.executeUpdate();

								// Comprobar si la actualización fue exitosa verificando cuántas filas se han
								// actualizado
								if (filasActualizadas > 0) {// Si se actualizó al menos una fila, informar al usuario
															// que la operación fue exitosa
									System.out.println("Nombre modificado con éxito.");
								} else {
									System.out.println("No se ha podido modificar el nombre.");
								}
							} catch (SQLException e) {
								System.out.println(
										"Error al actualizar el nombre en la base de datos: " + e.getMessage());
							}

							break;

						case 2:

							System.out.println("Ingrese su nuevo apellido");
							String nuevoApellido = sc.nextLine();

							String updateQueryApellido = "UPDATE \"dlk_motos\".usuario SET apellidos_usuario = ? WHERE dni_usuario = ?";

							try (PreparedStatement ps = conexion.prepareStatement(updateQueryApellido)) {
								ps.setString(1, nuevoApellido);
								ps.setString(2, dni);

								int filasActualizadas = ps.executeUpdate();
								if (filasActualizadas > 0) {
									System.out.println("Apellido modificado con éxito.");
								} else {
									System.out.println("No se ha podido modificar el apellido.");
								}
							} catch (SQLException e) {
								System.out.println(
										"Error al actualizar el apellido en la base de datos: " + e.getMessage());
							}
							break;

						case 3:

							System.out.println("Ingrese su nuevo dni");
							String nuevoDni = sc.nextLine();

							String updateQueryDni = "UPDATE \"dlk_motos\".usuario SET dni_usuario = ? WHERE dni_usuario = ?";
							try (PreparedStatement ps = conexion.prepareStatement(updateQueryDni)) {
								ps.setString(1, nuevoDni);
								ps.setString(2, dni);

								int filasActualizadas = ps.executeUpdate();
								if (filasActualizadas > 0) {
									System.out.println("Dni modificado con éxito.");
								} else {
									System.out.println("No se ha podido modificar el dni.");
								}
							} catch (SQLException e) {
								System.out.println("Error al actualizar el dni en la base de datos: " + e.getMessage());
							}

							System.out.println("Para seguir modificando ingrese de nuevo con su dni");
							esCerrado = true;// expulsar para poder seguir modificando

							break;

						case 4:

							System.out.println("Ingrese su nuevo correo");
							String nuevoCorreo = sc.nextLine();

							String updateQueryCorreo = "UPDATE \"dlk_motos\".usuario SET correo_usuario = ? WHERE dni_usuario = ?";

							try (PreparedStatement ps = conexion.prepareStatement(updateQueryCorreo)) {
								ps.setString(1, nuevoCorreo);
								ps.setString(2, dni);

								int filasActualizadas = ps.executeUpdate();
								if (filasActualizadas > 0) {
									System.out.println("Correo modificado con éxito.");
								} else {
									System.out.println("No se ha podido modificar el correo.");
								}
							} catch (SQLException e) {
								System.out.println(
										"Error al actualizar el correo en la base de datos: " + e.getMessage());
							}

							break;

						case 5:
							System.out.println("Ingrese el nombre de la foto");
							String nuevaFoto = sc.nextLine();

							String updateQueryFoto = "UPDATE \"dlk_motos\".usuario SET foto_usuario = ? WHERE dni_usuario = ?";

							try (PreparedStatement ps = conexion.prepareStatement(updateQueryFoto)) {
								ps.setString(1, nuevaFoto);
								ps.setString(2, dni);

								int filasActualizadas = ps.executeUpdate();
								if (filasActualizadas > 0) {
									System.out.println("Foto modificado con éxito.");
								} else {
									System.out.println("No se ha podido modificar el Foto.");
								}
							} catch (SQLException e) {
								System.out
										.println("Error al actualizar la Foto en la base de datos: " + e.getMessage());
							}

							break;

						case 6:

							System.out.println("Ingrese su nueva contraseña");
							String nuevaPwd = sc.nextLine();

							String updateQueryPwd = "UPDATE \"dlk_motos\".usuario SET contra_usuario = ? WHERE dni_usuario = ?";

							try (PreparedStatement ps = conexion.prepareStatement(updateQueryPwd)) {
								ps.setString(1, nuevaPwd);
								ps.setString(2, dni);

								int filasActualizadas = ps.executeUpdate();
								if (filasActualizadas > 0) {
									System.out.println("Contraseña modificado con éxito.");
								} else {
									System.out.println("No se ha podido modificar el Contraseña.");
								}
							} catch (SQLException e) {
								System.out.println(
										"Error al actualizar la contraseña en la base de datos: " + e.getMessage());
							}

							break;

						case 7:

							System.out.println("Ingrese su nuevo telefono");
							int nuevoTel = sc.nextInt();

							String updateQueryTel = "UPDATE \"dlk_motos\".usuario SET tel_usuario = ? WHERE dni_usuario = ?";

							try (PreparedStatement ps = conexion.prepareStatement(updateQueryTel)) {
								ps.setInt(1, nuevoTel);
								ps.setString(2, dni);

								int filasActualizadas = ps.executeUpdate();
								if (filasActualizadas > 0) {
									System.out.println("Telefono modificado con éxito.");
								} else {
									System.out.println("No se ha podido modificar el numero telefonico.");
								}
							} catch (SQLException e) {
								System.out.println("Error al actualizar el numero telefonico en la base de datos: "
										+ e.getMessage());
							}

							break;

						default:
							System.out.println("Error, opción no válida: " + opcion);
							break;
						}
					} while (!esCerrado);
				} else {
					System.out.println("No se encontró un usuario con el DNI ingresado.");
				}
			} else {
				System.out.println("No se pudo realizar la conexión.");
			}
		} catch (SQLException e) {
			System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
		}
	}
}
