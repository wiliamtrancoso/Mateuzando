package br.com.dalcim.mateuzando.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.Random;

import br.com.dalcim.mateuzando.R;

public class MainActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private CircularImageView fotoToolBar;
    private TextView mensageA;
    private TextView mensageB;
    private EditText edtSend;
    private ImageButton btnShare;

    private Drawable[] fotos;
    private String[] desculpas;
    private String[] chamadas;

    private String desculpaCorrente;
    private String chamadaCorrente;

    Handler handler;

    private Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();
        handler = new Handler();

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        fotoToolBar = (CircularImageView) findViewById(R.id.foto_toobar);
        mensageA = (TextView) findViewById(R.id.mensageA);
        mensageB = (TextView) findViewById(R.id.mensageB);
        edtSend = (EditText) findViewById(R.id.edtSend);
        btnShare = (ImageButton)findViewById(R.id.btnShare);

        popularFotos();
        desculpas = getResources().getStringArray(R.array.desculpas);
        chamadas = getResources().getStringArray(R.array.chamadas);

        chamadaCorrente = chamadas[r.nextInt(chamadas.length)];
        edtSend.setText(chamadaCorrente);

        geraDesulpa(null);

        btnShare.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, desculpaCorrente);
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void popularFotos(){
        fotos = new Drawable[]{
                getResources().getDrawable(R.drawable.mateus001),
                getResources().getDrawable(R.drawable.mateus002),
                getResources().getDrawable(R.drawable.mateus003),
                getResources().getDrawable(R.drawable.mateus004),
                getResources().getDrawable(R.drawable.mateus005),
                getResources().getDrawable(R.drawable.mateus006),
                getResources().getDrawable(R.drawable.mateus007),
                getResources().getDrawable(R.drawable.mateus008),
                getResources().getDrawable(R.drawable.mateus009),
                getResources().getDrawable(R.drawable.mateus010),
                getResources().getDrawable(R.drawable.mateus011),
                getResources().getDrawable(R.drawable.mateus012),
                getResources().getDrawable(R.drawable.mateus013),
                getResources().getDrawable(R.drawable.mateus014),
                getResources().getDrawable(R.drawable.mateus015),
                getResources().getDrawable(R.drawable.mateus016),
                getResources().getDrawable(R.drawable.mateus017),
                getResources().getDrawable(R.drawable.mateus018),
                getResources().getDrawable(R.drawable.mateus019)
        };
    }

    public void geraDesulpa(View v){
        YoYo.with(Techniques.TakingOff).duration(0).playOn(mensageA);
        YoYo.with(Techniques.TakingOff).duration(0).playOn(mensageB);
        String aux;

        aux = chamadas[r.nextInt(chamadas.length)];
        while(aux.equals(chamadaCorrente)){
            aux = chamadas[r.nextInt(chamadas.length)];
        }
        chamadaCorrente = aux;
        mensageB.setText(edtSend.getText().toString());
        edtSend.setText(chamadaCorrente);

        fotoToolBar.setImageDrawable(fotos[r.nextInt(fotos.length)]);

        aux = desculpas[r.nextInt(desculpas.length)];
        while(aux.equals(desculpaCorrente)){
            aux = desculpas[r.nextInt(desculpas.length)];
        }
        desculpaCorrente = aux;
        mensageA.setText(desculpaCorrente);

        YoYo.with(Techniques.ZoomIn).duration(300).playOn(mensageB);


        handler.postDelayed(new Runnable() {
            public void run() {
                YoYo.with(Techniques.ZoomIn).duration(300).playOn(mensageA);
            }
        }, 1700);


    }

}
