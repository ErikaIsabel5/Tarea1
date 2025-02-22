package com.example.holaatodos;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.holaatodos.R;

public class HolaTodos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        //Vamos a usar el paquete Bundle para convertir nuestra imagen a ASCII

        // Cargamos la imagen desde recursos
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gato);

        //Redimensionamos la imagen a una deseada
        int newWidth = 160;
        int newHeight = 75;
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);

        // Usamos la imagen redimensionada
        String asciiArt = convertToAscii(resizedBitmap);

        // Mostramos el ASCII Art en un TextView
        TextView textView = findViewById(R.id.textView);
        textView.setText(asciiArt);
    }

    private String convertToAscii(Bitmap bitmap) {
        StringBuilder sb = new StringBuilder();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // Caracteres para representar los niveles de gris
        char[] chars = {'@', '#', '*', '+', ';', ':', ',', '.', ' '};

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = bitmap.getPixel(x, y);
                int r = (pixel >> 16) & 0xff; // Componente rojo
                int g = (pixel >> 8) & 0xff;  // Componente verde
                int b = pixel & 0xff;         // Componente azul
                int gray = (r + g + b) / 3;    // Convertir a escala de grises

                // Mapear el nivel de gris a un carácter
                char c = chars[(gray * (chars.length - 1)) / 255];
                sb.append(c);
            }
            sb.append('\n'); // Nueva línea al final de cada fila
        }
        return sb.toString();
    }


}
