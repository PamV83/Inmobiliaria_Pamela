package com.example.Inmobiliaria_Pamela;


import android.app.Application;
import android.content.Context;
import android.content.Intent;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.Inmobiliaria_Pamela.modelo.Propietario;
import com.example.Inmobiliaria_Pamela.request.ApiClient;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginViewModel extends AndroidViewModel
{
    Context context;
    private MutableLiveData<String> mensajeMutable;
    private MutableLiveData<Propietario> verificarPro;
    private MutableLiveData<String> llamar;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;

    public LoginViewModel(@NonNull Application application)
    {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<String> getLLamar()
    {
        if(llamar == null)
            llamar = new MutableLiveData<>();
        return llamar;
    }

    public LiveData<String> getMensajeMutable()
    {
        if (mensajeMutable == null)
            mensajeMutable = new MutableLiveData<>();
        return mensajeMutable;
    }

    public LiveData<Propietario> getPropietario()
    {
        if(verificarPro == null)
            verificarPro = new MutableLiveData<>();
        return verificarPro;
    }



    public void autenticar(String mail, String password)
    {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail);

        if (mail != null && password != null && password.length() > 0 && mail.length() > 0 && matcher.matches())
        {
            Propietario propietario = ApiClient.getApi().login(mail,password);

            if (propietario == null)
            {
                mensajeMutable.setValue("Datos incorrectos");
            }
            else
            {
                verificarPro.setValue(propietario);
            }

        }
        else
        {
            mensajeMutable.setValue("Datos insuficientes");
        }

    }

    public void usuarioLogueado(Propietario p)
    {
        Intent intent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("propietario", p);
        intent.putExtra("propietario", bundle);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void setSensorLlamar(SensorEvent sensorEvent)
    {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long c = System.currentTimeMillis();

            if ((c - lastUpdate) > 100)
            {
                long diffTime = (c - lastUpdate);
                lastUpdate = c;

                float movimiento = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (movimiento > SHAKE_THRESHOLD)
                {
                    //set Mutable...
                    llamar.setValue("*138");
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }

        }

    }
}

