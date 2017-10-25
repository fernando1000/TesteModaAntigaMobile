package com.simplestestemobile.model;

import java.io.Serializable;

public class Paciente implements Serializable{

	private int idPosition;
	private String nome;
	private String rg;
	private String cpf;

    public static final String COLUMN_INTEGER_ID_POSOTION = "idPosition";
    public static final String COLUMN_TEXT_NOME = "nome";
    public static final String COLUMN_TEXT_RG = "rg";
    public static final String COLUMN_TEXT_CPF = "cpf";

	

	
	public int getIdPosition() {
		return idPosition;
	}

	public void setIdPosition(int idPosition) {
		this.idPosition = idPosition;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	

}
