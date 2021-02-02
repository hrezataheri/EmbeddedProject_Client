package com.example.embeddedproject;

public class SettingData {

    private int LightStartHour;
    private int LightEndHour;
    private int ShouldTurnLightsOff;
    private int AmbientTemperature;
    private int AmbientHumidity;
    private int SoilHumidity;
    private int CheckPeriod;

    public SettingData(){
        LightStartHour = 6;
        LightEndHour = 19;
        ShouldTurnLightsOff = 1;
        AmbientHumidity = 50;
        AmbientTemperature = 30;
        SoilHumidity = 50;
        CheckPeriod = 10;
    }

    public void setLightStartHour(int lightStartHour) {
        LightStartHour = lightStartHour;
    }

    public void setLightEndHour(int lightEndHour) {
        LightEndHour = lightEndHour;
    }

    public void setShouldTurnLightsOff(int shouldTurnLightsOff) {
        ShouldTurnLightsOff = shouldTurnLightsOff;
    }

    public void setAmbientTemperature(int ambientTemperature) {
        AmbientTemperature = ambientTemperature;
    }

    public void setAmbientHumidity(int ambientHumidity) {
        AmbientHumidity = ambientHumidity;
    }

    public void setSoilHumidity(int soilHumidity) {
        SoilHumidity = soilHumidity;
    }

    public void setCheckPeriod(int checkPeriod) {
        CheckPeriod = checkPeriod;
    }

    public int getLightStartHour() {
        return LightStartHour;
    }

    public int getLightEndHour() {
        return LightEndHour;
    }

    public int getShouldTurnLightsOff() {
        return ShouldTurnLightsOff;
    }

    public int getAmbientTemperature() {
        return AmbientTemperature;
    }

    public int getAmbientHumidity() {
        return AmbientHumidity;
    }

    public int getSoilHumidity() {
        return SoilHumidity;
    }

    public int getCheckPeriod() {
        return CheckPeriod;
    }
}
