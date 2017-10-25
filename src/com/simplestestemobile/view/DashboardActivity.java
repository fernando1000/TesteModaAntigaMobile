package com.simplestestemobile.view;

import com.example.simplestestemobile.R;
import com.simplestestemobile.util.MeuProgressDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends Activity {

	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		context = DashboardActivity.this;				 
	}

	public void selecionarOpcao(View view) {
		
		switch (view.getId()) {
	
			case R.id.tv_coletas:
				startActivity(new Intent(context, ListaDePacientesActivity.class));
				break;

			case R.id.tv_relatorios:
				startActivity(new Intent(context, ListaDePacientesActivity.class));
				break;

			case R.id.tv_clientes:
				startActivity(new Intent(context, ListaDePacientesActivity.class));
				break;
				
			case R.id.tv_comunicar:			  
				startActivity(new Intent(context, ListaDePacientesActivity.class));	
				break;			
		}
	}
		
	@Override
	public void onBackPressed() {

		solicitacaoDeConfirmacao();
	}

	public void solicitacaoDeConfirmacao() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context, 1);//R.style.dialogSucess
		builder.setTitle("Sair")
			   .setMessage("Deseja sair do aplicativo?")
			   .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Saindo...");

						new android.os.Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								
								MeuProgressDialog.encerraProgressDialog(progressDialog);	

								finishAffinity();							
							}
						}, 1000);
					}
				})
			   .setNegativeButton("Não", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});		
		builder.show();		
	}

}
