/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signals;

/**
 *
 * @author root
 */
import java.util.ArrayList;

public class FileProposalOK extends Signal {
	private String fileName;
	private long size;
	private String from;
	private ArrayList<String> to;
	
	public FileProposalOK(String fileName, long size, String from, ArrayList<String> to) {
		super();
		this.fileName = fileName;
		this.size = size;
		this.from = from;
		this.to = to;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	public ArrayList<String> getTo() {
		return to;
	}

	public void setTo(ArrayList<String> to) {
		this.to = to;
	}

	
}

