package com.example.Inmobiliaria_Pamela.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Inmobiliaria_Pamela.modelo.Propietario;
import com.example.Inmobiliaria_Pamela.request.ApiClient;

public class PerfilViewModel extends ViewModel
{

    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> mensajeMutable;
    private MutableLiveData<Boolean> editar;


    public PerfilViewModel()
    {    }

    public LiveData<Boolean> getEditar()
    {
        if(editar == null)
        {
            editar = new MutableLiveData<>();
        }
        return editar;
    }

    public LiveData<Propietario> getPropietarioMutable()
    {

        if (propietarioMutable == null)
        {
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }
    

    public LiveData<String> getMensajeMutable()
    {
        if (mensajeMutable == null)
        {
            mensajeMutable = new MutableLiveData<>();
        }
        return mensajeMutable;
    }

    public void habilitar()
    {
        editar.setValue(true);
    }

    public void deshabilitar()
    {
        editar.setValue(false);
    }

    //usuario Logueado
    public void obtenerDatos()
    {
        Propietario p = ApiClient.getApi().obtenerUsuarioActual();
        propietarioMutable.setValue(p);

    }

    public void editarPropietario(Propietario propietario)
    {
        ApiClient api = ApiClient.getApi();
        api.actualizarPerfil(propietario);
        propietarioMutable.setValue(propietario);
        mensajeMutable.setValue("Sus datos fueron editados exitosamente!");

    }


}