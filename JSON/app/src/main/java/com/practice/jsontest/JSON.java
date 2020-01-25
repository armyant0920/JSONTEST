package com.practice.jsontest;

public class JSON {
    private  String caseID;
    private String caseName;
    private String assetsClassifyCode;
    private String representImage;

    public JSON(String caseID, String caseName, String assetsClassifyCode) {//, String representImage
        this.caseID = caseID;
        this.caseName = caseName;
        this.assetsClassifyCode = assetsClassifyCode;
        //this.representImage = representImage;
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getAssetsClassifyCode() {
        return assetsClassifyCode;
    }

    public void setAssetsClassifyCode(String assetsClassifyCode) {
        this.assetsClassifyCode = assetsClassifyCode;
    }

    public String getRepresentImage() {
        return representImage;
    }

    public void setRepresentImage(String representImage) {
        this.representImage = representImage;
    }
}
