package com.example.Inmobiliaria_Pamela.ui.inmuebles;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.Inmobiliaria_Pamela.modelo.Inmueble;
import com.example.Inmobiliaria_Pamela.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class InmueblesViewModel extends AndroidViewModel
{

    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;
    ArrayList<Inmueble> in;

    public InmueblesViewModel(@NonNull Application application)
    {
        super(application);

    }

    public LiveData<ArrayList<Inmueble>> getInmueblesMutable()
    {
        if (inmueblesMutable == null)
        {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;

    }
	
	//inmuebeles del propietario....
    public void mostrarInmuebles()
    {
        in = ApiClient.getApi().obtenerPropiedades();
        inmueblesMutable.setValue(in);

    }



}
