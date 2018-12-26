package com.example.erick.cardview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;

    public Myadapter(List<Movie> movies, int layout, OnItemClickListener listener){
        this.movies = movies;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //Inflamos el layout y se lo pasamos al constructor del ViewHolder, donde manejaremos
        //toda la lógica como extraer los datos, referencias....
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        //Se crea nuestro ViewHolder
        ViewHolder vh = new ViewHolder(v);//Se le pasa la vista inflada
        return  vh;
    }

    //Va hacer el volcado de datos
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //Llamamos al método Bind del ViewHolder pasándole objeto y listener
        viewHolder.bind(movies.get(position),itemClickListener);

    }

    @Override
    public int getItemCount() {
        //Número de lista a obtener
        return movies.size(); //tamaño de la lista
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Elementos UI a rellenar
        public TextView textViewname;
        public ImageView imageViewPoster;

        public ViewHolder(View itemView){
            //Recibe la view completa. La pasa al constructor padre y enlazamos referencias UI
            //con nuestras propiedades. ViewHolder declarados justo arriba
            super(itemView);
            textViewname = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Movie movie, final OnItemClickListener listener ){
            //Procesamos los datos a renderizar
            textViewname.setText(movie.getName());
            //Para renderizar la imagen con piccasso
            Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);
            //Como se usa el picasso se comenta la siguiente línea de código
           // imageViewPoster.setImageResource(movie.getPoster());
            //Definimos que por cada elemento de nuestro recycler view, tenemos un click listener
            //que se comporta de la siguiente manera..
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(movie,getAdapterPosition());//Se pasa el nombre y la posición actual
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(Movie movie, int position);
    }

}
