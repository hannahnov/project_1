// JavaScript Document
window.onload = function () {
    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
  
    let xhr = new XMLHttpRequest();

    const url = "http://localhost:9096/vieweventgrade";
    //sets up ready state handler
    xhr.onreadystatechange = function () {
        console.log(xhr.readyState);
        switch (xhr.readyState) {
            case 0:
                console.log("nothing, initalized not sent");
                break;
            case 1:
                console.log("connection established")
                break;
            case 2:
                console.log("request sent");
                break;
            case 3:
                console.log("waiting response");
                break;
            case 4:
                console.log("finished");
                //logic to add message to table
                if (xhr.status === 200) {
                    console.log(xhr.responseText);
                    var requestList = JSON.parse(xhr.responseText);
                    console.log(requestList);
                    console.log(requestList[0]);
                    requestList.forEach(element => {
                        addRow(element);
                       console.log(element);
                   });
                }
                break;
        }
    }
    
   
    
    //opens up the request
    xhr.open("GET", url, true);
   // xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  //  var recipient = "recipient_id=" ;
    //sends request
    xhr.send();
}
function addRow(Requests) {
    let table = document.getElementById("message-table");
    
    let tableRow = document.createElement("tr");
    let approveForm = document.createElement("form");
    approveForm.method = "POST";
    approveForm.action = "http://localhost:9096/approvegrade";
    let denyForm = document.createElement("form");
    denyForm.method = "POST";
    denyForm.action = "http://localhost:9096/denygrade";
    let approveCol = document.createElement("td");
    let denyCol = document.createElement("td");
    let approveButton = document.createElement("button");
    let denyButton = document.createElement("button");
    let requestIdCol = document.createElement("td");
    let gradeCol = document.createElement("td");
    let bencoApprovalCol = document.createElement("td");
   
    let approveHeader = document.getElementById("approveHeader");
    let denyHeader = document.getElementById("denyHeader");
    table.appendChild(tableRow);
    approveForm.appendChild(approveButton);
    denyForm.appendChild(denyButton);
    approveCol.appendChild(approveForm);
    denyCol.appendChild(denyForm);
    table.appendChild(approveCol);
    table.appendChild(denyCol);
    table.appendChild(requestIdCol);
    table.appendChild(gradeCol);
    table.appendChild(bencoApprovalCol);
    

    approveButton.innerHTML = 'Approve';
    denyButton.innerHTML = 'Deny';
    requestIdCol.innerHTML = Requests.req; 
    gradeCol.innerHTML = Requests.grade;
    bencoApprovalCol.innerHTML = Requests.bencoApproval;
    
    approveButton.className = "approve-button";
    denyButton.className = "deny-button";
    approveCol.className = "table-style";
    denyCol.className = "table-style";
    requestIdCol.className = "table-style";
     gradeCol.className = "table-style";
     bencoApprovalCol.className = "table-style";

      approveButton.addEventListener("mouseover", function () {
         document.cookie = "request_id =" + reimbursement.requestId;
     });

     denyButton.addEventListener("mouseover", function() {
         document.cookie = "request_id =" + reimbursement.requestId;
     });

}