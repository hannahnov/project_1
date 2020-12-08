// JavaScript Document
window.onload = function () {
    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
  
    let xhr = new XMLHttpRequest();

    const url = "http://localhost:9096/depheadviewrequests";
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
    approveForm.action = "http://localhost:9096/approverequest/" + Requests.requestId;
    let denyForm = document.createElement("form");
    denyForm.method = "POST";
    denyForm.action = "http://localhost:9096/denyrequest/" + Requests.requestId;
    let approveCol = document.createElement("td");
    let denyCol = document.createElement("td");
    let approveButton = document.createElement("button");
    let denyButton = document.createElement("button");
    let requestIdCol = document.createElement("td");
    let eventIdCol = document.createElement("td");
    let projectedReimbursementCol = document.createElement("td");
    let isUrgentCol = document.createElement("td");
    let requestDateCol = document.createElement("td");
    let workDaysMissedCol = document.createElement("td");
    let justificationCol = document.createElement("td");
    let employeeIdCol = document.createElement("td");
    let approvalStatusCol = document.createElement("td");
    let descriptionCol = document.createElement("td");
   
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
    table.appendChild(eventIdCol);
    table.appendChild(projectedReimbursementCol);
    table.appendChild(isUrgentCol);
    table.appendChild(requestDateCol);
    table.appendChild(workDaysMissedCol);
    table.appendChild(justificationCol);
    table.appendChild(employeeIdCol);
    table.appendChild(approvalStatusCol);
    table.appendChild(descriptionCol);
    

    approveButton.innerHTML = 'Approve';
    denyButton.innerHTML = 'Deny';
    projectedReimbursementCol.innerHTML = Requests.projectedReimbursement;
    isUrgentCol.innerHTML = Requests.isUrgent;
    requestIdCol.innerHTML = Requests.requestId; 
    requestDateCol.innerHTML = Requests.requestDate;
    workDaysMissedCol.innerHTML = Requests.workDaysMissed;
    justificationCol.innerHTML = Requests.justification;
    employeeIdCol.innerHTML = Requests.requestorId;
    eventIdCol.innerHTML = Requests.eventId;
    approvalStatusCol.innerHTML = Requests.approvalStatus;
    descriptionCol.innerHTML = Requests.description;
    
    approveButton.className = "approve-button";
    denyButton.className = "deny-button";
    approveCol.className = "table-style";
    denyCol.className = "table-style";
    requestIdCol.className = "table-style";
     eventIdCol.className = "table-style";
     projectedReimbursementCol.className = "table-style";
     isUrgentCol.className = "table-style";
     requestDateCol.className = "table-style";
     workDaysMissedCol.className = "table-style";
     justificationCol.className = "table-style";
     employeeIdCol.className = "table-style";
     approvalStatusCol.className = "table-style";
     descriptionCol.className = "table-style";

     approveButton.addEventListener("mouse-over", function () {
        document.cookie = "request_id =" + reimbursement.requestId;
    });

    denyButton.addEventListener("mouse-over", function() {
        document.cookie = "request_id =" + reimbursement.requestId;
    });

}