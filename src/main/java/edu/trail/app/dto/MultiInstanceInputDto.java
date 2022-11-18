package edu.trail.app.dto;

import java.util.List;

public class MultiInstanceInputDto {

    public ControlMeasure getControlValue() {
        return controlValue;
    }

    public void setControlValue(ControlMeasure controlValue) {
        this.controlValue = controlValue;
    }

    private ControlMeasure controlValue;
}

