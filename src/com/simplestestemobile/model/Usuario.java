package com.simplestestemobile.model;

public class Usuario {
	
	private String usuario;
	private String senha;
	
    public static final String COLUMN_TEXT_USUARIO = "usuario";
    public static final String COLUMN_TEXT_SENHA = "senha";

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
