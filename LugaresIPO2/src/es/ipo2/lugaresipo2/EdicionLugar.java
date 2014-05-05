package es.ipo2.lugaresipo2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EdicionLugar extends Activity{

	private EditText nombre;
    private Spinner  tipo;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;
    
	
	private long id;
	private Lugar lugar;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edicion_lugar);
		
		Bundle extras = getIntent().getExtras();
		
		/* Cogemos el elemento de la lista */
		id = extras.getLong("id", -1);
		lugar = Lugares.elemento((int)id);
		
		/* Establecemos el nombre */
		nombre = (EditText)findViewById(R.id.nombre);
		nombre.setText(lugar.getNombre());
		
		/* Establecemos el tipo del sitio */
		tipo = (Spinner)findViewById(R.id.tipo);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
		                    android.R.layout.simple_spinner_item, TipoLugar.getNombres());
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tipo.setAdapter(adaptador);
		tipo.setSelection(lugar.getTipo().ordinal());
		
		/* Establecemos la dirección del sitio */
		direccion = (EditText) findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        
        /* Establecemos el telefono del sitio */
        if (lugar.getTelefono() == 0) {
            findViewById(R.id.telefono).setVisibility(View.GONE);
        } else {
        	telefono = (EditText) findViewById(R.id.telefono);
            telefono.setText(Integer.toString(lugar.getTelefono()));
        }
		
        /* Establecemos la URL del lugar */
        url = (EditText) findViewById(R.id.url);
        url.setText(lugar.getUrl());
        
        /* Establecemos el comentario asociado al sitio */
        comentario = (EditText) findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_guardar_info, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.accion_cancelar:
        	lanzarGuardar(null);
        	return true;
        	
        case R.id.accion_guardar:
        	lanzarGuardar(null);
        	return true;
        	
        default:
        	return super.onOptionsItemSelected(item);
        }
    }
	
	public void lanzarGuardar(View view){
		lugar.setNombre(nombre.getText().toString());
		lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);
		lugar.setDireccion(direccion.getText().toString());
		lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
		lugar.setUrl(url.getText().toString());
		lugar.setComentario(comentario.getText().toString());
		finish();
	}
}
