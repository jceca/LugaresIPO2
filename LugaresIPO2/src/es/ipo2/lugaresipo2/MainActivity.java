package es.ipo2.lugaresipo2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity{
	BaseAdapter adaptador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		adaptador = new MiAdaptador(this);
		setListAdapter(adaptador);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override 
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.acercaDe:
               lanzarAcercaDe(null);
               break;
        }
        return true; /** true -> consumimos el item, no se propaga*/
	}
	
	public void lanzarAcercaDe(View view){
		Intent i = new Intent(this, AcercaDe.class);
	    startActivity(i);
	}
	
	public void accionSalir(View view){
		finish();
	}
	
	@Override 
	protected void onListItemClick(ListView listView, 
	                         View vista, int posicion, long id) {
		super.onListItemClick(listView, vista, posicion, id);
		Intent intent= new Intent(this, VistaLugar.class);
		intent.putExtra("id", id);
		startActivity(intent);
	}
}
