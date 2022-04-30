package com.example.Inmobiliaria_Pamela.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Inmobiliaria_Pamela.modelo.Inmueble;
import com.example.Inmobiliaria_Pamela.request.ApiClient;

public class InmuebleViewModel extends ViewModel
{

    private MutableLiveData<Inmueble> inmuebleMutable;
    private MutableLiveData<String> mensajeMutable;
    Inmueble in;

    public LiveData<Inmueble> getInmueble()
    {
        if (inmuebleMutable == null) {
            inmuebleMutable = new MutableLiveData<>();
        }
        return inmuebleMutable;
    }

    public LiveData<String> getMensajeMutable()
    {
        if (mensajeMutable == null)
        {
            mensajeMutable = new MutableLiveData<>();
        }
        return mensajeMutable;
    }


    public void cargarInmueble(Bundle bundle)
    {
        in = (Inmueble) bundle.getSerializable("inmueble");
        inmuebleMutable.setValue(in);
    }


    public void actualizarDisponible(Boolean disponibilidad)
    {
        ApiClient api = ApiClient.getApi();
        in.setEstado(disponibilidad);
        api.actualizarInmueble(in);
        mensajeMutable.setValue("El Inmueble se actualizo a \n 'No Disponible'!!");
    }

}







