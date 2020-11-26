package pojos;

public class RequestApproval {
	
	private ReimbursementRequest request;
	
	private String directSupApprovalDate;
	
	private String depHeadApprovalDate;
	
	private String benCoApprovalDate;
	
	private ApprovalStatus supervisorApproval;
	
	private ApprovalStatus depHeadApproval;
	
	private ApprovalStatus benCoApproval;
	
	public RequestApproval(ReimbursementRequest request, String directSupApprovalDate, String depHeadApprovalDate, String benCoApprovalDate,
			ApprovalStatus supervisorApproval, ApprovalStatus depHeadApproval, ApprovalStatus benCoApproval) {
		super();
		this.request = request;
		this.directSupApprovalDate = directSupApprovalDate;
		this.depHeadApprovalDate = depHeadApprovalDate;
		this.benCoApprovalDate = benCoApprovalDate;
		this.supervisorApproval = supervisorApproval;
		this.depHeadApproval = depHeadApproval;
		this.benCoApproval = benCoApproval;
	}

	public RequestApproval() {
		// TODO Auto-generated constructor stub
	}

	public ReimbursementRequest getRequest() {
		return request;
	}

	public void setRequest(ReimbursementRequest request) {
		this.request = request;
	}


	public String getDirectSupApprovalDate() {
		return directSupApprovalDate;
	}

	public void setDirectSupApprovalDate(String directSupApprovalDate) {
		this.directSupApprovalDate = directSupApprovalDate;
	}


	public String getDepHeadApprovalDate() {
		return depHeadApprovalDate;
	}

	public void setDepHeadApprovalDate(String depHeadApprovalDate) {
		this.depHeadApprovalDate = depHeadApprovalDate;
	}


	public String getBenCoApprovalDate() {
		return benCoApprovalDate;
	}

	public void setBenCoApprovalDate(String benCoApprovalDate) {
		this.benCoApprovalDate = benCoApprovalDate;
	}

	public ApprovalStatus getSupervisorApproval() {
		return supervisorApproval;
	}

	public void setSupervisorApproval(ApprovalStatus supervisorApproval) {
		this.supervisorApproval = supervisorApproval;
	}

	public ApprovalStatus getDepHeadApproval() {
		return depHeadApproval;
	}

	public void setDepHeadApproval(ApprovalStatus depHeadApproval) {
		this.depHeadApproval = depHeadApproval;
	}

	public ApprovalStatus getBenCoApproval() {
		return benCoApproval;
	}

	public void setBenCoApproval(ApprovalStatus benCoApproval) {
		this.benCoApproval = benCoApproval;
	}
	
	

}
