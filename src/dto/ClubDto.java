/**
 * 
 */
package dto;

import java.time.LocalDateTime;

/**
 * Datos de club
 * @author nrojlla 161024
 * 
 */
public class ClubDto {
	
	long idClub;
	String nombre_club = "aaaaa";
	int miembros_club = 00000;
	long sedeClub_id = 00000;
	long eventosClub_id = 00000;
	LocalDateTime creacionFcha_club;
	String descripcion_club = "aaaaa";
	
	
	
	/**
	 * 
	 */
	public ClubDto() {
		super();
	}
	/**
	 * @return the idClub
	 */
	public long getIdClub() {
		return idClub;
	}
	/**
	 * @param idClub the idClub to set
	 */
	public void setIdClub(long idClub) {
		this.idClub = idClub;
	}
	/**
	 * @return the nombre_club
	 */
	public String getNombre_club() {
		return nombre_club;
	}
	/**
	 * @param nombre_club the nombre_club to set
	 */
	public void setNombre_club(String nombre_club) {
		this.nombre_club = nombre_club;
	}
	/**
	 * @return the miembros_club
	 */
	public int getMiembros_club() {
		return miembros_club;
	}
	/**
	 * @param miembros_club the miembros_club to set
	 */
	public void setMiembros_club(int miembros_club) {
		this.miembros_club = miembros_club;
	}
	/**
	 * @return the sedeClub_id
	 */
	public long getSedeClub_id() {
		return sedeClub_id;
	}
	/**
	 * @param sedeClub_id the sedeClub_id to set
	 */
	public void setSedeClub_id(long sedeClub_id) {
		this.sedeClub_id = sedeClub_id;
	}
	/**
	 * @return the eventosClub_id
	 */
	public long getEventosClub_id() {
		return eventosClub_id;
	}
	/**
	 * @param eventosClub_id the eventosClub_id to set
	 */
	public void setEventosClub_id(long eventosClub_id) {
		this.eventosClub_id = eventosClub_id;
	}
	/**
	 * @return the creacionFcha_club
	 */
	public LocalDateTime getCreacionFcha_club() {
		return creacionFcha_club;
	}
	/**
	 * @param creacionFcha_club the creacionFcha_club to set
	 */
	public void setCreacionFcha_club(LocalDateTime creacionFcha_club) {
		this.creacionFcha_club = creacionFcha_club;
	}
	/**
	 * @return the descripcion_club
	 */
	public String getDescripcion_club() {
		return descripcion_club;
	}
	/**
	 * @param descripcion_club the descripcion_club to set
	 */
	public void setDescripcion_club(String descripcion_club) {
		this.descripcion_club = descripcion_club;
	}
	
	

}
