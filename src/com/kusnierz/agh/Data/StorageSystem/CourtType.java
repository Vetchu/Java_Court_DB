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
            case tribunal:
                return  "tribunal";
            case national_administrative:
                return  "national_administrative";
            case common_administrative:
                return  "common_administrative";
             case supreme:
                return  "supreme";
            case national_appeal_chamber:
                return  "national_appeal_chamber";
        }
        return null;
    }
}
