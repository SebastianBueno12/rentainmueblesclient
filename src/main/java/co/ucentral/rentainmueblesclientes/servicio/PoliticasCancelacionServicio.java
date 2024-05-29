package co.ucentral.rentainmueblesclientes.servicio;

import org.springframework.stereotype.Service;

@Service
public class PoliticasCancelacionServicio {

    public String obtenerPoliticas() {
        // Aquí podrías obtener las políticas de una base de datos o configuración externa
        return "Si la cancelación se realiza con más de 24 horas de antelación, se retorna el 100% del dinero; " +
                "si se cancela dentro de las 24 horas, se retorna el 80%; " +
                "y si se cancela en la fecha de ingreso al inmueble, se retorna el 60%.";
    }
}
