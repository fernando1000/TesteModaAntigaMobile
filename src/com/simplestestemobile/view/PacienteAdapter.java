package com.simplestestemobile.view;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.simplestestemobile.R;
import com.simplestestemobile.dao.Dao;
import com.simplestestemobile.model.Paciente;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

public class PacienteAdapter extends ArrayAdapter<String> {

	private Context context;
	private List<Paciente> listaDepaciente;
	private List<Paciente> listaDepacienteTemporaria;
	private Filter pacienteFilter;
	private Paciente paciente;
	private LayoutInflater layoutInflater;

	public PacienteAdapter(Context activitySpinner, int textViewResourceId, List _listpaciente) {
		super(activitySpinner, textViewResourceId, _listpaciente);

		context = activitySpinner;
		listaDepaciente = _listpaciente;
		listaDepacienteTemporaria = _listpaciente;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return listaDepaciente.size();
	}
	
	public long getItemId(int position) {
		return listaDepaciente.get(position).hashCode();
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {

		View view_row = layoutInflater.inflate(R.layout.adapter_paciente, parent, false);

		paciente = null;
		paciente = listaDepaciente.get(position);
		
		TextView textView_1 = (TextView) view_row.findViewById(R.id.textView_texto1);
		 		 textView_1.setText(""+position);

		TextView textView_2 = (TextView) view_row.findViewById(R.id.textView_texto2);
		 		 textView_2.setText( paciente.getNome());
		
		ImageView imageView = (ImageView) view_row.findViewById(R.id.image_item_relatorioImpresso);
				  imageView.setImageResource(R.drawable.ic_launcher);
		
		
		
		paciente.setIdPosition(position);
		
		Dao dao = new Dao(context);
			dao.insereOUatualiza(paciente, Paciente.COLUMN_TEXT_CPF, paciente.getCpf());
	
		view_row.setId(paciente.getIdPosition());

		return view_row;
	}

	public void resetData() {
		listaDepaciente = listaDepacienteTemporaria;
	}

	@Override
	public Filter getFilter() {
		if (pacienteFilter == null)
			pacienteFilter = new SIM_Filter();

		return pacienteFilter;
	}

	private class SIM_Filter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence charSequence_constraint) {
			FilterResults filterResults = new FilterResults();
			
			if (charSequence_constraint == null || charSequence_constraint.length() == 0) {
				
				filterResults.values = listaDepacienteTemporaria;
				filterResults.count = listaDepacienteTemporaria.size();
			} 
			else {	
				List<Paciente> listaDepaciente_Local = new ArrayList<Paciente>();

				for (Paciente paciente : listaDepaciente) {
					
					if (containsIgnoreCase(removerAcentos(""+paciente.getIdPosition()), charSequence_constraint.toString())) {

						listaDepaciente_Local.add(paciente);
					}					
				}

				filterResults.values = listaDepaciente_Local;
				filterResults.count = listaDepaciente_Local.size();
			}
			return filterResults;
		}
		
		public CharSequence removerAcentos(CharSequence str) {
		    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			
				listaDepaciente = (List<Paciente>) results.values;
				
				notifyDataSetChanged();
		}
		
		public boolean containsIgnoreCase(CharSequence haystack, String needle) {
			if (needle.equals(""))
				return true;
			if (haystack == null || needle == null || haystack.equals(""))
				return false;

			Pattern p = Pattern.compile(needle, Pattern.CASE_INSENSITIVE
					+ Pattern.LITERAL);
			Matcher m = p.matcher(haystack);
			return m.find();
		}

	}
}