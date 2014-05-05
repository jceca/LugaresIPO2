package es.ipo2.lugaresipo2;

import java.util.ArrayList;
import java.util.List;

public class Lugares {

	static final String TAG = "MyActivity";
    protected static List<Lugar> vectorLugares = ejemploLugares();

    public Lugares() {
         vectorLugares = ejemploLugares();
    }

    static Lugar elemento(int id){
          return vectorLugares.get(id);
    }

    static void anyade(Lugar lugar){
          vectorLugares.add(lugar);
    }

    static int nuevo(){
          Lugar lugar = new Lugar();
          vectorLugares.add(lugar);
          return vectorLugares.size()-1;
    }

    static void borrar(int id){
          vectorLugares.remove(id);
    }

    public static int size() {
          return vectorLugares.size();
    }

    public static ArrayList<Lugar> ejemploLugares() {
          ArrayList<Lugar> lugares = new ArrayList<Lugar>();
          lugares.add(new Lugar("Escuela Superior de Informática",
           "Paseo de la Universidad, 4 13071 Ciudad Real (Ciudad Real, CLM)",
           TipoLugar.EDUCACION, 926295300, "http://webpub.esi.uclm.es/",
           "Uno de los mejores lugares para formarse.", 4));

          lugares.add(new Lugar("Biblioteca UCLM",
           "Avda de Camilo José Cela, s/n 13071, Ciudad Real (Ciudad Real, CLM)",
           TipoLugar.EDUCACION, 902204100, "http://biblioteca.uclm.es/",
           "Un perfecto lugar para estudiar", 3));

          lugares.add(new Lugar("El Androide libre",
           "Internet", TipoLugar.EDUCACION, 
           0, "http://www.elandroidelibre.com/",
           "Amplia tus conocimientos sobre Android.", 5));

          lugares.add(new Lugar("Polideportivo - Juan Carlos I",
           "c/ Juan Ramón Jiménez, 4 Ciudad Real (Ciudad Real, CLM)", 
           TipoLugar.DEPORTE, 926211044, "http://www.ciudadreal.es/la-ciudad/deportes/polideportivo-juan-carlos-i.html",
           "Instalaciones deportivas", 4));

          lugares.add(new Lugar("Springfield Man & Woman",
           "C/ Ramón y Cajal, 6 13001 Ciudad Real (Ciudad Real, CLM)", 
           TipoLugar.COMPRAS,
           926224698, "http://spf.com/",
           "Tienda Springfield", 2));

          return lugares;

    }
    
    static List<String> listaNombres(){
        ArrayList<String> resultado = new ArrayList<String>();
        for (Lugar lugar:vectorLugares){
              resultado.add(lugar.getNombre());
        }
        return resultado;
 }
  }

