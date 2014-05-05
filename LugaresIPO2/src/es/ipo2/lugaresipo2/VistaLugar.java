package es.ipo2.lugaresipo2;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class VistaLugar extends Activity{
	
	final static int RESULTADO_EDITAR= 1;
	final static int RESULTADO_GALERIA= 2;
	final static int RESULTADO_FOTO= 3;
	
	private long id;
	private Lugar lugar;
	private Uri uriFoto;
	private MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vista_lugar);
		
		Bundle extras = getIntent().getExtras();
		
		/* Cogemos el elemento de la lista */
		id = extras.getLong("id", -1);
		lugar = Lugares.elemento((int)id);
		
		actualizarVistas();
	}
	
		@Override
		protected void onSaveInstanceState(Bundle estadoGuardado){
			super.onSaveInstanceState(estadoGuardado);
			if (mp != null) {
				int pos = mp.getCurrentPosition();
				estadoGuardado.putInt("posicion", pos);   
			}
		}
	 
		@Override
		protected void onRestoreInstanceState(Bundle estadoGuardado){
			super.onRestoreInstanceState(estadoGuardado);
			if (estadoGuardado != null && mp != null) {
				int pos = estadoGuardado.getInt("posicion");
	            mp.seekTo(pos);
	        }
		}
		
        /* Menu contextual */
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
              getMenuInflater().inflate(R.menu.vista_lugar, menu);            
              return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
              switch(item.getItemId()) {
              case R.id.accion_compartir:
                 Intent intent = new Intent(Intent.ACTION_SEND);
                 intent.setType("text/plain");
                 intent.putExtra(Intent.EXTRA_TEXT, lugar.getNombre() + " - "+ lugar.getUrl());
                 startActivity(intent);
                 return true;
                 
              case R.id.accion_editar:
            	  lanzarEdicion(null);                     
            	  return true;
            	  
              case R.id.accion_borrar:
            	  lanzarConfirmacionBorrar(null);
            	  return true;
            	  
              default:
                     return super.onOptionsItemSelected(item);
              }
        }
        
        public void lanzarEdicion(View view){
        	Intent i = new Intent(this, EdicionLugar.class);
      	  	i.putExtra("id", id);
      	  	startActivityForResult(i, RESULTADO_EDITAR);
        }
        
        public void galeria(View view) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, RESULTADO_GALERIA);
     }
        
        public void lanzarConfirmacionBorrar(View view){
        	AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
       	  	alertDialog.setTitle("Borrar lugar"); 
       	  	alertDialog.setMessage("Estás seguro que quieres eliminar este lugar?");
       	  	alertDialog.setIcon(android.R.drawable.ic_dialog_alert); 
       	  	alertDialog.setCancelable(false);
       	  	alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() { 
       	  		public void onClick(DialogInterface dialog, int which){ 
       	  			Lugares.borrar((int)id);
       	    	 	finish();
       	  		} 
       	  	}); 
       	  	alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() 
       	  	{ 
       	  		public void onClick(DialogInterface dialog, int which) 
       	  		{ 
       	    	  
       	  		} 
       	    }); 
       	  	alertDialog.show();
        }
        
        public void actualizarVistas(){
        	/* Establecemos el nombre */
    		TextView nombre = (TextView)findViewById(R.id.t_nombreLugar);
    		nombre.setText(lugar.getNombre());
    		
    		/* Establecemos el logo correspondiente del lugar */
    		ImageView logo_tipo = (ImageView)findViewById(R.id.logo_tipo);
    		logo_tipo.setImageResource(lugar.getTipo().getRecurso());
    		
    		/* Establecemos el tipo del sitio */
    		TextView tipo = (TextView)findViewById(R.id.tipo);
    		tipo.setText(lugar.getTipo().getTexto());
    		
    		/* Establecemos la dirección del sitio */
    		TextView direccion = (TextView) findViewById(R.id.t_direccion);
            direccion.setText(lugar.getDireccion());
            
            /* Establecemos el telefono del sitio */
            if (lugar.getTelefono() == 0) {
                findViewById(R.id.t_telefono).setVisibility(View.GONE);
            } else {
            	TextView telefono = (TextView) findViewById(R.id.t_Telefono);
                telefono.setText(Integer.toString(lugar.getTelefono()));
            }
    		
            /* Establecemos la URL del lugar */
            if (lugar.getUrl() == null){
            	findViewById(R.id.t_URL).setVisibility(View.GONE);
            }else{
            	TextView url = (TextView) findViewById(R.id.t_URL);
                url.setText(lugar.getUrl());
            }
            
            
            /* Establecemos el comentario asociado al sitio */
            if (lugar.getComentario() == null){
            	findViewById(R.id.t_Comentario).setVisibility(View.GONE);
            }else{
            	TextView comentario = (TextView) findViewById(R.id.t_Comentario);
                comentario.setText(lugar.getComentario());
            }
            
            /* Establecemos la fecha */
            if(lugar.getFecha() == 0){
            	findViewById(R.id.t_Calendario).setVisibility(View.GONE);
            }else{
            	TextView fecha = (TextView) findViewById(R.id.t_Hora);
                fecha.setText(DateFormat.getDateInstance().format(
                        new Date(lugar.getFecha())));
            }
            
            
            /* Cargamos la valoración del lugar */
            RatingBar valoracion = (RatingBar)findViewById(R.id.valoracion);
            valoracion.setRating(lugar.getValoracion());
            
            valoracion.setOnRatingBarChangeListener(
            		new OnRatingBarChangeListener() {
                        @Override 
                        public void onRatingChanged(RatingBar ratingBar, float valor, boolean fromUser) {
                            lugar.setValoracion(valor);
                        }
                });
            
            ponerFoto((ImageView) findViewById(R.id.foto), lugar.getFoto());
        }
        
        public void llamadaTelefono(View view) {
            startActivity(new Intent(Intent.ACTION_DIAL,
                                   Uri.parse("tel:" + lugar.getTelefono())));
        }
         

        public void pgWeb(View view) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                                   Uri.parse(lugar.getUrl())));
        }
        
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
               if (requestCode == RESULTADO_EDITAR) {
                     actualizarVistas();
                     findViewById(R.id.scrollView1).invalidate();
               }else if(requestCode == RESULTADO_GALERIA && resultCode == Activity.RESULT_OK){
            	   lugar.setFoto(data.getDataString());
            	   ponerFoto((ImageView)findViewById(R.id.foto), lugar.getFoto());
               }else if(requestCode == RESULTADO_FOTO && resultCode == Activity.RESULT_OK
            	        && lugar!=null && uriFoto!=null) {
                   lugar.setFoto(uriFoto.toString());
                   ponerFoto((ImageView)findViewById(R.id.foto), lugar.getFoto());
            }
        }
        
        public void tomarFoto(View view) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            uriFoto = Uri.fromFile(
                new File(Environment.getExternalStorageDirectory() + File.separator
                + "img_" + (System.currentTimeMillis() / 1000) + ".jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
            startActivityForResult(intent, RESULTADO_FOTO);
        }
        
        protected void ponerFoto(ImageView imageView, String uri) {
            if (uri != null) {
                imageView.setImageURI(Uri.parse(uri));
            } else{
                imageView.setImageBitmap(null);
            }
        }
        
        public void eliminarFoto(View view) {
            lugar.setFoto(null);
            ponerFoto((ImageView)findViewById(R.id.foto), null);
        }
        
        /* Estudio del ciclo de vida de una aplicación */

        @Override 
        protected void onStart() {
        	super.onStart();
        	Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        }
        	 
        @Override 
        protected void onResume() {
            super.onResume();
            Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
         }
        
        @Override 
        protected void onStop() {
            super.onStop();
            Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
         }

        @Override 
     	protected void onDestroy() {
            super.onDestroy();
            Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();     
         }
}
