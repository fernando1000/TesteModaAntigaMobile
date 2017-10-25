package com.simplestestemobile.view;

import com.simplestestemobile.model.Paciente;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetalhePacienteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Context context = DetalhePacienteActivity.this;
		
		LinearLayout linearLayoutTela = new LinearLayout(context);
		linearLayoutTela.setOrientation(LinearLayout.VERTICAL);
		
		Paciente paciente = (Paciente) getIntent().getExtras().getSerializable("paciente");

		TextView tv1 = new TextView(context); 
		TextView tv2 = new TextView(context); 
		TextView tv3 = new TextView(context); 
		TextView tv4 = new TextView(context); 
		
		tv1.setText("Id: "+paciente.getIdPosition());
		tv2.setText("nome: "+paciente.getNome());
		tv3.setText("RG: "+paciente.getRg());
		tv4.setText("CPF: "+paciente.getCpf());
		
		linearLayoutTela.addView(tv1);
		linearLayoutTela.addView(tv2);
		linearLayoutTela.addView(tv3);
		linearLayoutTela.addView(tv4);
		
		setContentView(linearLayoutTela);
	}
	
}
