package mx.com.agutierrezm.mynasaapp.util;

/**
 * Created by Alumno on 06/08/2016.
 */
public class Comunicador {
    private static Object objeto = null;

    public static void setObjeto(Object newObjeto) {
        objeto = newObjeto;
    }

    public static Object getObjeto() {
        return objeto;
    }
}
