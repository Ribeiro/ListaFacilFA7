package br.com.androidos.listafacil.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.androidos.listafacil.R;
import br.com.androidos.listafacil.model.Item;

public class ItemAdapter extends BaseAdapter{

	private Context context;
	private List<Item> itens;

	public ItemAdapter(Context context, List<Item> itens) {
		this.context = context;
		this.itens = itens;
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public Object getItem(int position) {
		return itens.get(position);
	}

	@Override
	public long getItemId(int position) {
		return itens.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Item item = itens.get(position);
		
		HolderView holder = null;
		
		if(convertView == null){
			
			holder = new HolderView();
			
			LayoutInflater layoutInflater = (LayoutInflater) 
					context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView = layoutInflater.inflate(R.layout.item_layout, null);
			
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvQuantity = (TextView) convertView.findViewById(R.id.tvQuantity);
			holder.tvUnitPrice = (TextView) convertView.findViewById(R.id.tvUnitPrice);
			holder.tvTotaValue = (TextView) convertView.findViewById(R.id.tvTotalValue);
			
			convertView.setTag(holder);
			
		} else {
			
			holder = (HolderView) convertView.getTag();
			
		}
		
		holder.tvName.setText(item.getName());
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));
        holder.tvUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        holder.tvTotaValue.setText(String.valueOf(item.getTotalValue()));
		
		return convertView;
	}
	
	static class HolderView{
		TextView tvName;
		TextView tvQuantity;
		TextView tvUnitPrice;
		TextView tvTotaValue;
	}

}