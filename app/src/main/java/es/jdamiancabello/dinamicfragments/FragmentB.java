package es.jdamiancabello.dinamicfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {
    public static final String TAG = "FRAGMENT_B";
    private final String SIZE = "SIZE";
    private final String MESSAGE = "MESSAGE";
    private TextView tvMessage;


    //Estas dos variables retienen el estado del fragment
    private int size;
    private String message;

    /**
     * Metodo creado por las directrices de Google [Patrón factoría]
     *
     * Método que crea un objeto de la propia clase FragmentB garantizando
     * que se llama al método setArguments inmediatamente
     * @param bundle
     * @return
     */
    public static Fragment newInstance(Bundle bundle){
        FragmentB fragmentB = new FragmentB();
        if(bundle != null){
            fragmentB.setArguments(bundle);
        }
        return fragmentB;
    }

    /**
     * En este fragment se retiene su estado en una re-creación o cambio de configuración de la Activity
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentb,container,false);
        Log.d(TAG,"FRAGMENTB -> onCreateView()");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvMessage = view.findViewById(R.id.tvText);

        //Se comprueba si el fragment tiene argumentos. Se podría usar lo siguiente:
        //Bundle bundle = getArguments() y luego en el if se mira la condicion bundle != null
        if(savedInstanceState == null){
            Bundle bundle = getArguments();
                if (bundle != null) {
                    message = bundle.getString(MESSAGE);
                    size = bundle.getInt(SIZE);
                }
        }
        tvMessage.setText(message);
        tvMessage.setTextSize(size);
    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putFloat(SIZE,tvMessage.getTextSize());
//        outState.putString(MESSAGE,tvMessage.getText().toString());
//        Log.d(TAG,"FRAGMENTB -> onSaveInstanceState()");
//    }
//
//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        Log.d(TAG,"FRAGMENTB -> onViewStateRestored()");
//        if(savedInstanceState != null){
//            tvMessage.setTextSize(getArguments().getInt("Size"));
//            tvMessage.setText(getArguments().getString("Message"));
//        }
//    }
}
