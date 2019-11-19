package es.jdamiancabello.dinamicfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.setOnTextSizeListener{

    private Fragment fragmenta, fragmentb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmenta = fragmentManager.findFragmentByTag(FragmentA.TAG);


        if (fragmenta == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            //Se inicializa el FragmentA
            fragmenta = FragmentA.newInstance(null);
            fragmentTransaction.add(android.R.id.content,fragmenta,FragmentA.TAG);
            fragmentTransaction.commit();
        }



    }

    @Override
    public void onSetTextSize(String message, int size) {
        //Guardamos los parámetros para el fragment.
        Bundle bundle = new Bundle();
        bundle.putString("MESSAGE",message);
        bundle.putInt("SIZE",size);

        //Se inicializa el FragmentB
        fragmentb = FragmentB.newInstance(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,fragmentb,FragmentB.TAG);

        //Se guarda el estado de la transacción en la pila de Fragment que guarda la Activity.
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
