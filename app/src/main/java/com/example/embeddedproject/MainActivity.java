package com.example.embeddedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    EditText ServerAddr;

    TextView LightStartHour_Text;
    TextView LightEndHour_Text;
    TextView AmbientTemp_Text;
    TextView AmbientHum_Text;
    TextView SoilHum_Text;
    TextView CheckPeriod_Text;

    SeekBar LightStartHour_Bar;
    SeekBar LightEndHour_Bar;
    SeekBar AmbientTemp_Bar;
    SeekBar AmbientHum_Bar;
    SeekBar SoilHum_Bar;
    SeekBar CheckPeriod_Bar;

    Switch TurnLightsOff_Switch;

    Button SendData_Button;

    SettingData settingData;

    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServerAddr = findViewById(R.id.EditText_addr);
        ServerAddr.setText("192.168.1.2");

        LightStartHour_Text = findViewById(R.id.textView16);
        LightEndHour_Text = findViewById(R.id.textView18);
        AmbientTemp_Text = findViewById(R.id.textView19);
        AmbientHum_Text = findViewById(R.id.textView20);
        SoilHum_Text = findViewById(R.id.textView21);
        CheckPeriod_Text = findViewById(R.id.textView22);

        LightStartHour_Bar = findViewById(R.id.seekBar9);
        LightEndHour_Bar = findViewById(R.id.seekBar10);
        AmbientTemp_Bar = findViewById(R.id.seekBar14);
        AmbientHum_Bar = findViewById(R.id.seekBar13);
        SoilHum_Bar = findViewById(R.id.seekBar12);
        CheckPeriod_Bar = findViewById(R.id.seekBar11);

        TurnLightsOff_Switch = findViewById(R.id.switch2);

        SendData_Button = findViewById(R.id.button);

        settingData = new SettingData();

        executorService = Executors.newSingleThreadExecutor();

        LightStartHour_Bar.setProgress(100*settingData.getLightStartHour()/23);
        LightStartHour_Text.setText(String.valueOf(settingData.getLightStartHour()));

        LightEndHour_Bar.setProgress(100*settingData.getLightEndHour()/23);
        LightEndHour_Text.setText(String.valueOf(settingData.getLightEndHour()));

        AmbientTemp_Bar.setProgress(100*(settingData.getAmbientTemperature() - 25)/30);
        AmbientTemp_Text.setText(String.valueOf(settingData.getAmbientTemperature()));

        AmbientHum_Bar.setProgress(100*(settingData.getAmbientHumidity() - 20)/80);
        AmbientHum_Text.setText(String.valueOf(settingData.getAmbientHumidity()));

        SoilHum_Bar.setProgress(100*(settingData.getSoilHumidity() - 20)/80);
        SoilHum_Text.setText(String.valueOf(settingData.getSoilHumidity()));

        CheckPeriod_Bar.setProgress(settingData.getCheckPeriod());
        CheckPeriod_Text.setText("Fast");

        TurnLightsOff_Switch.setChecked(settingData.getShouldTurnLightsOff() == 1);

        LightStartHour_Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float temp = progress;
                int fin = Math.round(temp*23/100);
                settingData.setLightStartHour(fin);
                LightStartHour_Text.setText(String.valueOf(fin));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        LightEndHour_Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float temp = progress;
                int fin = Math.round(temp*23/100);
                settingData.setLightEndHour(fin);
                LightEndHour_Text.setText(String.valueOf(fin));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        AmbientTemp_Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float temp = progress;
                int fin = 25 + Math.round(temp*30/100);
                settingData.setAmbientTemperature(fin);
                AmbientTemp_Text.setText(String.valueOf(fin));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        AmbientHum_Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float temp = progress;
                int fin = 20 + Math.round(temp*80/100);
                settingData.setAmbientHumidity(fin);
                AmbientHum_Text.setText(String.valueOf(fin));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SoilHum_Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float temp = progress;
                int fin = 20 + Math.round(temp*80/100);
                settingData.setSoilHumidity(fin);
                SoilHum_Text.setText(String.valueOf(fin));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        CheckPeriod_Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 50;
                progress = progress*50;
                float temp = progress;
                if (progress == 0) {
                    settingData.setCheckPeriod(10);
                    CheckPeriod_Text.setText("Fast");
                } else if (progress == 50) {
                    settingData.setCheckPeriod(100);
                    CheckPeriod_Text.setText("Med");
                } else {
                    settingData.setCheckPeriod(1000);
                    CheckPeriod_Text.setText("Slow");
                }

                CheckPeriod_Bar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        TurnLightsOff_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                settingData.setShouldTurnLightsOff(isChecked? 1 : 0);
            }
        });

        SendData_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        sendDataToServer();
                    }
                });
            }
        });

    }

    private void sendDataToServer(){

        String url = setUrlBasedOnData("http://" + ServerAddr.getText() + "/Data/ChangeData");
        System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getBaseContext(), "the change was successful", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "an error has occurred, please try again later", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private String setUrlBasedOnData(String mainAddress){

        String str1 = "turn_off_led_if_light="+String.valueOf(settingData.getShouldTurnLightsOff());
        String str2 = "light_start_hour="+String.valueOf(settingData.getLightStartHour());
        String str3 = "light_end_hour="+String.valueOf(settingData.getLightEndHour());
        String str4 = "ambient_temp="+String.valueOf(settingData.getAmbientTemperature());
        String str5 = "ambient_humidity="+String.valueOf(settingData.getAmbientHumidity());
        String str6 = "soil_humidity="+String.valueOf(settingData.getSoilHumidity());
        String str7 = "check_period="+String.valueOf(settingData.getCheckPeriod());
        return mainAddress+"?"+str1+"&"+str2+"&"+str3+"&"+str4+"&"+str5+"&"+str6+"&"+str7;
     }
}
