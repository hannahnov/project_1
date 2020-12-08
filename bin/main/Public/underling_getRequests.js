// JavaScript Document
window.onload = function () {
    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
  
    let xhr = new XMLHttpRequest();

    const url = "http://localhost:9096/underlingrequests";
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
                    //    console.log(element);
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
    
    table.appendChild(tableRow);
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

    requestIdCol.innerHTML = Requests.requestId;
    eventIdCol.innerHTML = Requests.eventId;
    projectedReimbursementCol.innerHTML = Requests.projectedReimbursement;
    isUrgentCol.innerHTML = Requests.isUrgent;
    requestDateCol.innerHTML = Requests.requestDate;
    workDaysMissedCol.innerHTML = Requests.workDaysMissed;
    justificationCol.innerHTML = Requests.justification;
    employeeIdCol.innerHTML = Requests.employeeId;
    approvalStatusCol.innerHTML = Requests.approvalStatus;
    descriptionCol.innerHTML = Requests.description;
    
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
}