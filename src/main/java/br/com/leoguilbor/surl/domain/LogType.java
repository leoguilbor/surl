package br.com.leoguilbor.surl.domain;


public enum LogType {
	SHORT(0),ACCESS(1);
	public int typeValue;

	LogType(int value) {
		this.typeValue = value;
	}
    public int getTypeValue(){
        return typeValue;
    }
}