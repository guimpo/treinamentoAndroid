package com.example.paulo.myapiapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.paulo.myapiapplication.Interface.AdapterPositionOnClickListener;
import com.example.paulo.myapiapplication.Model.Usuario;
import com.example.paulo.myapiapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UsuarioAdapter  extends RecyclerView.Adapter<UsuarioAdapter.MyViewHolder> {

    private Context mContext;
    private AdapterPositionOnClickListener mAadapterPositionOnClickListener;
    public List<Usuario> mList;

    public UsuarioAdapter(Context context, List<Usuario> list){
        this.mContext = context;
        this.mList = list;
    }

    //Inserir uma função de contato entre a activity e o Adapter
    public void setAdapterPositionOnClickListener(AdapterPositionOnClickListener click){
        mAadapterPositionOnClickListener = click;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View holder = inflater.inflate(R.layout.item_usuario, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(holder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Usuario usuario = mList.get(position);

        holder.mNome.setText(usuario.name);
        holder.mEmail.setText(usuario.email);
        String imageUri = usuario.picture;
        Picasso.get().load(imageUri).into(holder.mFotoBtn);
    }

    public Usuario getUsuario(int position)
    {
        return mList.get(position);
    }

    public void deleteItem(int positon){
        mList.remove(positon);
        notifyItemRemoved(positon);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView mFotoBtn;
        public TextView mNome;
        public TextView mEmail;

        public MyViewHolder(View itemView) {
            super(itemView);

            mFotoBtn = itemView.findViewById(R.id.foto);
            mNome = itemView.findViewById(R.id.lb_nome);
            mEmail = itemView.findViewById(R.id.lb_email);

            //Aplica a função do click na view toda
            //itemView.setOnClickListener(this);

            // Aplica a função de click somente na foto
            mFotoBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            //Verificar se a ponte de contato entre o activity e o adapter foi passado
            if(mAadapterPositionOnClickListener != null){

                mAadapterPositionOnClickListener.setAdapterPositionOnClickListener(view, getPosition());
            }
        }
    }
}