package pojos;

public class RequestApproval {
	
	private ReimbursementRequest request;
	
	private Approver directSupervisor;
	
	private String directSupApprovalDate;
	
	private Approver departmentHead;
	
	private String depHeadApprovalDate;
	
	private Approver benCo;
	
	private String benCoApprovalDate;
	
	

	public RequestApproval(ReimbursementRequest request, Approver directSupervisor, String directSupApprovalDate,
			Approver departmentHead, String depHeadApprovalDate, Approver benCo, String benCoApprovalDate) {
		super();
		this.request = request;
		this.directSupervisor = directSupervisor;
		this.directSupApprovalDate = directSupApprovalDate;
		this.departmentHead = departmentHead;
		this.depHeadApprovalDate = depHeadApprovalDate;
		this.benCo = benCo;
		this.benCoApprovalDate = benCoApprovalDate;
	}

	public ReimbursementRequest getRequest() {
		return request;
	}

	public void setRequest(ReimbursementRequest request) {
		this.request = request;
	}

	public Approver getDirectSupervisor() {
		return directSupervisor;
	}

	public void setDirectSupervisor(Approver directSupervisor) {
		this.directSupervisor = directSupervisor;
	}

	public String getDirectSupApprovalDate() {
		return directSupApprovalDate;
	}

	public void setDirectSupApprovalDate(String directSupApprovalDate) {
		this.directSupApprovalDate = directSupApprovalDate;
	}

	public Approver getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(Approver departmentHead) {
		this.departmentHead = departmentHead;
	}

	public String getDepHeadApprovalDate() {
		return depHeadApprovalDate;
	}

	public void setDepHeadApprovalDate(String depHeadApprovalDate) {
		this.depHeadApprovalDate = depHeadApprovalDate;
	}

	public Approver getBenCo() {
		return benCo;
	}

	public void setBenCo(Approver benCo) {
		this.benCo = benCo;
	}

	public String getBenCoApprovalDate() {
		return benCoApprovalDate;
	}

	public void setBenCoApprovalDate(String benCoApprovalDate) {
		this.benCoApprovalDate = benCoApprovalDate;
	}
	
	

}
