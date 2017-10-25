package com.simplestestemobile.enums;

public enum Ip {

	//local
	URL_SERVER_REST("http://172.16.4.151:8080/TesteModaAntigaRS");
	
	private String valor;
	
	private Ip(String valor){
		this.valor = valor;
	}
	
	public String getValor(){
		return valor;
	}
}
