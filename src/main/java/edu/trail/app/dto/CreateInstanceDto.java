package edu.trail.app.dto;

public class CreateInstanceDto {
    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    private String riskName;

    public Float getImpact() {
        return impact;
    }

    public void setImpact(Float impact) {
        this.impact = impact;
    }

    private Float impact;

    public boolean isEmerging() {
        return emerging;
    }

    public void setEmerging(boolean emerging) {
        this.emerging = emerging;
    }

    private boolean emerging;
}
