package modelo;

import java.sql.Date;

public class Reserva 
{
   private  Integer id;
   private  Date fechaEn;
   private  Date fechaSal;
   private  String valor;
   private  String formaPago;
   
   
   
   
   
   
public Reserva(Date fechaEn, Date fechaSal, String valor, String formaPago)  /*Constructor sin ID */
{
	super();
	this.fechaEn = fechaEn;
	this.fechaSal = fechaSal;
	this.valor = valor;
	this.formaPago = formaPago;
}


public Reserva(Integer id ,Date fechaEn, Date fechaSal, String valor, String formaPago)  /*Constructor sin ID */
{
	super();
	this.id = id;
	this.fechaEn = fechaEn;
	this.fechaSal = fechaSal;
	this.valor = valor;
	this.formaPago = formaPago;
}


public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Date getFechaEn() {
	return fechaEn;
}
public Date getFechaSal() {
	return fechaSal;
}
public String getValor() {
	return valor;
}
public String getFormaPago() {
	return formaPago;
}
	
	
   
   
   
}
