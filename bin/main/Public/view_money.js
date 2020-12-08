// JavaScript Document
window.onload = function () {
    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
  
    let xhr = new XMLHttpRequest();

    const url = "http://localhost:9096/getemployee";
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
    let pendingCol = document.createElement("td");
    let availableCol = document.createElement("td");
    let awardedCol = document.createElement("td");

    table.appendChild(tableRow);
    table.appendChild(pendingCol);
    table.appendChild(availableCol);
    table.appendChild(awardedCol);

    pendingCol.innerHTML = '$' + Requests.pendingReimbursement;
    availableCol.innerHTML = '$' + Requests.availableReimbursement;
    awardedCol.innerHTML = '$' + Requests.awardedReimbursement;

    pendingCol.className = "table-style";
    availableCol.className = "table-style";
    awardedCol.className = "table-style";

}