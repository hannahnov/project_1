class Reimbursement{
    constructor(request_id,sender_id,recipient_id,date_sent,is_received, message_header, message){
    this.requestId = request_id;
    this.senderId = sender_id;
    this.recipientId = recipient_id;
    this.dateSent = date_sent;
    this.isReceived = is_received;
    this.messageHeader = message_header;
    this.message = message;
    }
}
// JavaScript Document
window.onload = function () {
    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
  
    let xhr = new XMLHttpRequest();

    const url = "http://localhost:9096/viewmessagesap";
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
function addRow(Messages) {
    let table = document.getElementById("message-table");
    let tableRow = document.createElement("tr");
    let messageIdCol = document.createElement("td");
    let requestIdCol = document.createElement("td");
    let senderIdCol = document.createElement("td");
    let recipientIdCol = document.createElement("td");
    let dateSentCol = document.createElement("td");
    let isReceivedCol = document.createElement("td");
    let messageHeaderCol = document.createElement("td");
    let messageCol = document.createElement("td");
    
    table.appendChild(tableRow);
    table.appendChild(messageIdCol);
    table.appendChild(requestIdCol);
    table.appendChild(senderIdCol);
    table.appendChild(recipientIdCol);
    table.appendChild(dateSentCol);
    table.appendChild(isReceivedCol);
    table.appendChild(messageHeaderCol);
    table.appendChild(messageCol);

    messageIdCol.innerHTML = Messages.messageId;
    requestIdCol.innerHTML = Messages.requestId;
    senderIdCol.innerHTML = Messages.senderId;
    recipientIdCol.innerHTML = Messages.recipientId;
    dateSentCol.innerHTML = Messages.dateSent;
    isReceivedCol.innerHTML = Messages.receieved;
    messageHeaderCol.innerHTML = Messages.header;
    messageCol.innerHTML = Messages.message;
    
    messageIdCol.className = "table-style";
    requestIdCol.className = "table-style";
    senderIdCol.className = "table-style";
    recipientIdCol.className = "table-style";
    isReceivedCol.className = "table-style";
    messageHeaderCol.className = "table-style";
    messageCol.className = "table-style";
}
