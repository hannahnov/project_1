package pojos;


public class EventResult {
	
	private int req;
	
	private String grade;
	
	private byte[] presentation;
	
	private boolean bencoApproval;
	
	private boolean directsupervisorApproval;
	
	public EventResult(int req, byte[] presentation) {
		super();
		this.setReq(req);
		this.presentation = presentation;
	}

	public EventResult(int req, String grade) {
		super();
		this.setReq(req);
		this.grade = grade;
	}

	public EventResult(int req, String grade, byte[] presentation) {
		super();
		this.setReq(req);
		this.grade = grade;
		this.presentation = presentation;
	}
	
	public boolean isBencoApproval() {
		return bencoApproval;
	}

	public void setBencoApproval(boolean bencoApproval) {
		this.bencoApproval = bencoApproval;
	}

	public boolean isDirectsupervisorApproval() {
		return directsupervisorApproval;
	}

	public void setDirectsupervisorApproval(boolean directsupervisorApproval) {
		this.directsupervisorApproval = directsupervisorApproval;
	}



	public EventResult() {
		// TODO Auto-generated constructor stub
	}


	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public byte[] getPresentation() {
		return presentation;
	}

	public void setPresentation(byte[] presentation) {
		this.presentation = presentation;
	}

	public int getReq() {
		return req;
	}

	public void setReq(int req) {
		this.req = req;
	}
}
