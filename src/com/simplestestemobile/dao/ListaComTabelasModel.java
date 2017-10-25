package com.simplestestemobile.dao;

import java.util.ArrayList;
import java.util.List;

import com.simplestestemobile.model.*;

public class ListaComTabelasModel{
	
	public static List<String> devolveListaComTabelasModel(){
		
		List<String> lista = new ArrayList<String>(); 
		 			 lista.add(Paciente.class.getSimpleName());		 			 
		 			 lista.add(Usuario.class.getSimpleName());

		 			 
		 return lista;
	}	

}
