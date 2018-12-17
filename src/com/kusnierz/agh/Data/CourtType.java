package com.kusnierz.agh.Data;

public enum CourtType {
    common,
    supreme,
    tribunal,
    national_appeal_chamber,
    common_administrative,
    national_administrative;

    @Override
    public String toString() {
        switch (this){
            case common:
                return  "common";
            break;
            case tribunal:
                return  "tribunal";
            break;
            case national_administrative:
                return  "national_administrative";
            break;
            case common_administrative:
                return  "common_administrative";
            break;
            case supreme:
                return  "supreme";
            break;
            case national_appeal_chamber:
                return  "national_appeal_chamber";
            break;
        }
    }
}
