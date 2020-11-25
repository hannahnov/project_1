package pojos;

import java.io.File;

public class EventResult {
	
	private ReimbursementRequest req;
	
	private String grade;
	
	private File presentation;
	
	

	public EventResult(ReimbursementRequest req, String grade, File presentation) {
		super();
		this.req = req;
		this.grade = grade;
		this.presentation = presentation;
	}

	public ReimbursementRequest getReq() {
		return req;
	}

	public void setReq(ReimbursementRequest req) {
		this.req = req;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public File getPresentation() {
		return presentation;
	}

	public void setPresentation(File presentation) {
		this.presentation = presentation;
	}
}
