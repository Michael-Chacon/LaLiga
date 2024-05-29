package org.laLiga;

import org.laLiga.modelo.Equipo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public Utils() {
    }

    public static String formatearFecha(String fecha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaValida = formato.parse(fecha);
        return formato.format(fechaValida);
    }
}
