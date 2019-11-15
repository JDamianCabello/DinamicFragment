package es.jdamiancabello.dinamicfragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {
    public static final String TAG = "FRAGMENT_A";
    private EditText edMessage;
    private SeekBar skbSize;
    private Button btResize;
    private setOnTextSizeListener listener;

    /**
     * Esta interfaz servirá de contrato entre el fragment y su clase contenedora (Activity)
     */
    public interface setOnTextSizeListener{
        void onSetTextSize(String message, int size);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmenta,container,false);
        Log.d(TAG,"FRAGMENTA -> onCreateView()");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edMessage = view.findViewById(R.id.edMessage);
        skbSize = view.findViewById(R.id.skbSize);

        btResize = view.findViewById(R.id.btnSize);
        btResize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSetTextSize(edMessage.getText().toString(),skbSize.getProgress());
            }
        });

    }

    /**
     * Controla que este suscrito al evento. Esto es para los desarrolladores.
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (setOnTextSizeListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implements setOnTextSizeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        Log.d(TAG,"FRAGMENTA -> onDetach()");
    }
}
