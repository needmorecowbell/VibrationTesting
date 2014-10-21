package adam.example.com.vibrationtesting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {


            final Button btnVibrate= (Button)findViewById(R.id.btnVibrate);
            final Switch switchLoop= (Switch)findViewById(R.id.switchLoop);

            btnVibrate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                        btnVibrate.setEnabled(false);


                        final Vibrator vi = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                        if (vi.hasVibrator()) {
                            int dot = 200;      // Length of a Morse Code "dot" in milliseconds
                            int dash = 500;     // Length of a Morse Code "dash" in milliseconds
                            int short_gap = 200;    // Length of Gap Between dots/dashes
                            int medium_gap = 500;   // Length of Gap Between Letters
                            int long_gap = 1000;    // Length of Gap Between Words
                            final long[] pattern = {
                                    0,  // Start immediately
                                    dot, short_gap, dot, short_gap, dot,    // s
                                    medium_gap,
                                    dash, short_gap, dash, short_gap, dash, // o
                                    medium_gap,
                                    dot, short_gap, dot, short_gap, dot,    // s
                                    long_gap

                            };



                            if(!switchLoop.isChecked()){
                                vi.vibrate(pattern,-1);
                                }else{

                                    new Thread(){
                                        public void run(){
                                            while(switchLoop.isChecked()){
                                                vi.vibrate(pattern,-1);
                                                try{
                                                    Thread.sleep(7400);
                                                }catch (Exception e){
                                                    Log.e("Vibe error", e.toString());
                                                }
                                        }
                                    }


                                    }.start();
                            }

                        }
                        btnVibrate.setEnabled(true);



                }
            });

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


