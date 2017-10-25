package com.simplestestemobile.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.simplestestemobile.R;
import com.simplestestemobile.dao.Dao;
import com.simplestestemobile.enums.Ip;
import com.simplestestemobile.model.Paciente;
import com.simplestestemobile.model.Usuario;
import com.simplestestemobile.util.MeuProgressDialog;
import com.simplestestemobile.util.VolleySingleton;

public class LoginActivity extends Activity {

	private static final String RESOURCE_AUTENTICACAO = "/Autenticacao/Login/";
	private RequestQueue queue;
	private EditText editText_usuario;
	private EditText editText_senha;
	private Button button_entrar;
	private Context context;	
	private Dao dao;
	private Usuario usuarioEnviadoWS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		context = LoginActivity.this;

		dao = new Dao(context);
				
		queue = VolleySingleton.getInstance(this).getRequestQueue();

		button_entrar = (Button) findViewById(R.id.botao_entrar);
		button_entrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				acaoAposClique();			
			}
		});
	}
	
	private void acaoAposClique(){
		
		editText_usuario = (EditText) findViewById(R.id.editTextUsuario);
		String usuario = editText_usuario.getText().toString();

		editText_senha = (EditText) findViewById(R.id.editText_senha);
		String senha = editText_senha.getText().toString();

		if (usuario.equals("")) {

			Toast.makeText(context, "Informe o usuário", Toast.LENGTH_SHORT).show();
		}
		else if (senha.equals("")) {

			Toast.makeText(context, "Informe a senha", Toast.LENGTH_SHORT).show();
		} 
		else {
			
			continuaProcesso(usuario, senha);			
		}
	}
	
	private void continuaProcesso(final String usuario, final String senha){

		Usuario usuarioProcuradoSQLite = (Usuario) dao.devolveObjeto(Usuario.class, "usuario", usuario, "senha", senha);
		
		if (usuarioProcuradoSQLite == null) {
			
			Log.i("tag", "passou pelo Web Service");			
			buscaUsuarioWS(usuario, senha);			
		}
		else{	
			
			Log.i("tag", "abriu direto");
			abreDashboard();
		}
	}
		
	private void buscaUsuarioWS(String usuario, String senha) {
		    
		    final ProgressDialog progressDialog = MeuProgressDialog.criaProgressDialog(context, "Autenticando Usuário");

			String url = Ip.URL_SERVER_REST.getValor() + RESOURCE_AUTENTICACAO + usuario +"/"+ senha;
			
		    usuarioEnviadoWS = new Usuario();
    		usuarioEnviadoWS.setUsuario(usuario);
    		usuarioEnviadoWS.setSenha(senha);
			
			StringRequest stringRequest = new StringRequest(

					Request.Method.GET, url,

					new Response.Listener<String>() {
						@Override
						public void onResponse(String resposta) {
							
							respostaBuscaUsuarioWS(resposta, progressDialog);							
						}
					}, 
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError volleyError) {
						
							MeuProgressDialog.encerraProgressDialog(progressDialog);

							Toast.makeText(context, "Sem acesso a internet ", Toast.LENGTH_SHORT).show();
						}
					});

			queue.add(stringRequest);			
	}
	
	private void respostaBuscaUsuarioWS(String resposta, ProgressDialog progressDialog) {
		
		
		if(resposta.equals("null")){
		
			MeuProgressDialog.encerraProgressDialog(progressDialog);

			Toast.makeText(context, "Não achou usuario", Toast.LENGTH_SHORT).show();
		}
		else{
			
		dao.deletaTodosDados();					
		
		String listaComPacientes = resposta;
		String listaSemColchetes1 = listaComPacientes.replace("[", "");
		String listaSemColchetes2 = listaSemColchetes1.replace("]", "");
		
		for(String paciente : listaSemColchetes2.split(",")){
			
			Paciente pacient = new Paciente();
			
			for(String atributo :paciente.split(";")){
	
				String atributoSemEspacos = atributo.replaceAll("^\\s+", "");

				String[] chaveValor = atributoSemEspacos.split(":");
					
				String chave = chaveValor[0];
				String valor = chaveValor[1];
				
				if(chave.equalsIgnoreCase("nome")){
					
					pacient.setNome(valor);
				}
				if(chave.equalsIgnoreCase("rg")){
				
					pacient.setRg(valor);
				}				
				if(chave.equalsIgnoreCase("cpf")){
				
					pacient.setCpf(valor);
				}
			
			}			
			dao.insereObjeto_final(pacient);
		}
		
						
		dao.insereObjeto_final(usuarioEnviadoWS);
						
		MeuProgressDialog.encerraProgressDialog(progressDialog);

		
		
		Log.i("tag", "login");			
		for(Paciente p : dao.listaTodaTabela(Paciente.class)){
			
			Log.i("tag", ""+p.getIdPosition() +" nome: "+p.getNome() +" RG: "+p.getRg()+" cpf: "+p.getCpf());			
		}

		
		abreDashboard();	
		}
				
	}
	
	private void abreDashboard() {
				
		Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
		startActivity(intent);
		finish();
	}
	
	
}
