package com.medac.t6navigation3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.medac.t6navigation3.basedatos.UsuariosSQLiteHelper;

import org.w3c.dom.Text;

public class OtraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView3);
        textView.setText(message);


        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();
        Toast toast = Toast.makeText(getApplicationContext(), db.toString(), Toast.LENGTH_LONG);
        toast.show();
        if (db != null) {
            //Consultamos los datos
            Cursor c = db.rawQuery("SELECT codigo, nombre FROM Usuarios", null);

            if (c != null) {
                c.moveToFirst();
                do {
                    //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                    String codigo = c.getString(c.getColumnIndexOrThrow("codigo"));
                    String nombre = c.getString(c.getColumnIndexOrThrow("nombre"));
                    TextView txtView = (TextView) findViewById(R.id.textView4);
                    txtView.setText(codigo + " " + nombre);
                } while (c.moveToNext());
            }

            //Cerramos el cursor y la conexion con la base de datos
            c.close();
            db.close();
        }


        Toast toast3 = Toast.makeText(getApplicationContext(), "text", Toast.LENGTH_LONG);
        toast3.show();

    }
}