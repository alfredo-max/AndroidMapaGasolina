package com.example.v4mapagasolina;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
public class ListAdapter extends ArrayAdapter<Estudiante>{

    private List<Estudiante> mlist;
    private Context mContext;
    private int resourceLayout;

    public ListAdapter(@NonNull Context context, int resource, List<Estudiante> objects) {
        super(context, resource, objects);
        this.mlist = objects;
        this.mContext = context;
        this.resourceLayout = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);

        Estudiante est = mlist.get(position);

        TextView txtcodigo = view.findViewById(R.id.txtcodigo);
        txtcodigo.setText(est.getNombre());

        TextView txtnombre = view.findViewById(R.id.txtnombre);
        txtnombre.setText(String.valueOf(est.getLatitud()));
        TextView txtempresa = view.findViewById(R.id.txtempresa);
        txtempresa.setText(String.valueOf(est.getLongitud()));

       /* TextView txtnombre = view.findViewById(R.id.txtnombre);
        txtnombre.setText(est.getNombre());
        TextView txtempresa = view.findViewById(R.id.txtempresa);
        txtempresa.setText(est.getEmpresa());
       */

        return view;

    }
}
