package edu.trail.app.dto;

public class MultiInstanceOutputDto {
    public ControlMeasure getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(ControlMeasure approvalResult) {
        this.approvalResult = approvalResult;
    }

    private ControlMeasure approvalResult;
}
