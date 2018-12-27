package com.example.erick.cardview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.erick.cardview.adapters.Myadapter;
import com.example.erick.cardview.R;
import com.example.erick.cardview.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();//Se rellena la lista
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        //Cambiar la vista grid y la vista list
       //mLayoutManager = new GridLayoutManager(this,2); //El 2 es el número de columnas
        //mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);//Va el número de columnas y la orientación

        //Implementamos muestro OnItemClicklistener propio, sobreescribiendo el método que nosotros
        //definimos en el adaptador, y recibiendo los parámetros que necesitamos
        mAdapter = new Myadapter(movies, R.layout.recycler_view_item, new Myadapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                //Toast.makeText(MainActivity.this,name+" - "+position,Toast.LENGTH_SHORT).show();
                //Cuando se hace click va eliminar
                removemovie(position);
            }
        });

        //Si sabemos que el layout no se va hacer más grande se usa esto, pero si se va usar GridView no se va utilizar por que no va tener el mismo tamaño
        //mRecyclerView.setHasFixedSize(true);
        //Para añadir efectos
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager );
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflar
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                this.addmovie(0);//0 por que se va añadir el nombre en la parte superior de la vista
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private List<Movie> getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Avatar",R.drawable.avatar));
            add(new Movie("Jumanji",R.drawable.jumanji));
            add(new Movie("Rapido y furioso",R.drawable.rapido));
            add(new Movie("Liga de la justicia",R.drawable.justice));
        }};
    }

    private void addmovie(int position){
        //Se va poner como predeterminada para otras imágenes
        movies.add(position,new Movie("New image "+(++counter),R.drawable.ironman)); //Se añadel array y la posición que queremos
        //Se le va decir al adaptador que algo ha cambiado
        mAdapter.notifyItemInserted(position);
        //Hacemos el scroll hacia la posición donde el elemento nuevo se aloja
        mLayoutManager.scrollToPosition(position);
    }

    private void removemovie(int position){
        movies.remove(position);
        //Notificamos de un item borrado en nuestra colección
        mAdapter.notifyItemRemoved(position);

    }

}
