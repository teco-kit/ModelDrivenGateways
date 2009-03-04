package edu.teco.automata.generator.popup.actions;

public class ConfigStorage {
	private String destinationPath = "";
	private String modelFile       = "";
	private String automataFile    = "";
	private String nsPrefix        = "";
	private String deviceName      = "";
	private String msgOutURI       = "";
	private String msgRspURI       = "";
	private boolean gnerateWS4d    = true;
	private boolean generateC      = true;
	private boolean generateJava   = true;
	private boolean generate       = false;
	
	/* Only getter and setter functions follow */
	
	public boolean getGenerate() {
		return generate;
	}
	public void setGenerate(boolean generate) {
		this.generate = generate;
	}
	public boolean getGenerateC() {
		return generateC;
	}
	public void setGenerateC(boolean generateC) {
		this.generateC = generateC;
	}
	public boolean getGenerateJava() {
		return generateJava;
	}
	public void setGenerateJava(boolean generateJava) {
		this.generateJava = generateJava;
	}

	public boolean getGnerateWS4d() {
		return gnerateWS4d;
	}
	public void setGnerateWS4d(boolean gnerateWS4d) {
		this.gnerateWS4d = gnerateWS4d;
	}
	public String getDestinationPath() {
		return destinationPath;
	}
	public void setDestinationPath(String destinationPath) {
		this.destinationPath = destinationPath;
	}
	public String getModelFile() {
		return modelFile;
	}
	public void setModelFile(String modelFile) {
		this.modelFile = modelFile;
	}
	public String getAutomataFile() {
		return automataFile;
	}
	public void setAutomataFile(String automataFile) {
		this.automataFile = automataFile;
	}
	public String getNsPrefix() {
		return nsPrefix;
	}
	public void setNsPrefix(String nsPrefix) {
		this.nsPrefix = nsPrefix;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getMsgOutURI() {
		return msgOutURI;
	}
	public void setMsgOutURI(String msgOutURI) {
		this.msgOutURI = msgOutURI;
	}
	public String getMsgRspURI() {
		return msgRspURI;
	}
	public void setMsgRspURI(String msgRspURI) {
		this.msgRspURI = msgRspURI;
	}
}
