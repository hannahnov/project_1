package pojos;


public class EventResult {
	
	private ReimbursementRequest req;
	
	private String grade;
	
	private byte[] presentation;
	
	private boolean bencoApproval;
	
	private boolean directsupervisorApproval;
	
	public EventResult(ReimbursementRequest req, byte[] presentation) {
		super();
		this.req = req;
		this.presentation = presentation;
	}

	public EventResult(ReimbursementRequest req, String grade) {
		super();
		this.req = req;
		this.grade = grade;
	}

	public EventResult(ReimbursementRequest req, String grade, byte[] presentation) {
		super();
		this.req = req;
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

	public byte[] getPresentation() {
		return presentation;
	}

	public void setPresentation(byte[] presentation) {
		this.presentation = presentation;
	}
}
