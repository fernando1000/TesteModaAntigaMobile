package com.simplestestemobile.view;

import java.io.Serializable;
import java.util.List;
import com.example.simplestestemobile.R;
import com.simplestestemobile.dao.Dao;
import com.simplestestemobile.model.Paciente;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListaDePacientesActivity extends Activity {

	private Context context;
	private Dao crudSqliteDAO;
	private List<Paciente> listaDepacientes;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_de_pacientes);
		
		context = this;

		crudSqliteDAO = new Dao(context);
			
		listaDepacientes = crudSqliteDAO.listaTodaTabela(Paciente.class);
		
		for(Paciente p : listaDepacientes){
			
			Log.i("tag", ""+p.getIdPosition() +" nome: "+p.getNome() +" RG: "+p.getRg()+" cpf: "+p.getCpf());			
		}
		
		
		
		ListView listView = (ListView) findViewById(R.id.listCondominoRelatorio);
				 listView.setAdapter(new PacienteAdapter(context, R.layout.adapter_paciente, listaDepacientes));
				 listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				acaoAposClickNoItemDaLista(view.getId());
			}
		});		
	}
	
	private void acaoAposClickNoItemDaLista(int viewId){

		for (Paciente paciente : listaDepacientes) {

			if (paciente.getIdPosition() == viewId) {
				
			       Intent intent = new Intent(context, DetalhePacienteActivity.class);		
			       
					Bundle data = new Bundle();
						   data.putSerializable("paciente", (Serializable) paciente);
						   
						   	  intent.putExtras(data);
				startActivity(intent);
				
				break;
			}
		}			
	}

}
