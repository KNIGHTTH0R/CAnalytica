var firebaseConfig = {
    apiKey: "AIzaSyC2thBwQrgummVbpPoTwzsAjf-5cLNrv3A",
    authDomain: "rbcanalytica.firebaseapp.com",
    databaseURL: "https://rbcanalytica.firebaseio.com",
    projectId: "rbcanalytica",
    storageBucket: "rbcanalytica.appspot.com",
    messagingSenderId: "854631962280",
    appId: "1:854631962280:web:7c60bcefc6fb86f3f9949d",
    measurementId: "G-9E0G9R6XL6"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

var db = firebase.firestore();

//   db.collection("facebook").add({
//     phrase: "Boo",
//     positive: "False",
//     technical: "false"
// })
// .then(function(docRef) {
//     console.log("Document written with ID: ", docRef.id);
// })
// .catch(function(error) {
//     console.error("Error adding document: ", error);
// });

// db.collection("twitter").get().then((querySnapshot) => {
//     querySnapshot.forEach((doc) => {
//         console.log(`${doc.id} => ${doc.data()}`);
//     });
// });

// var docRef = db.collection("facebook").doc("ZdR9ZAe0dBOwI9tnX3DI");

// docRef.get().then(function(doc) {
//     if (doc.exists) {
//         console.log("Document data:", doc.data());
//     } else {
//         // doc.data() will be undefined in this case
//         console.log("No such document!");
//     }
// }).catch(function(error) {
//     console.log("Error getting document:", error);
// });
var tableRef = document.getElementById('myTable');
db.collection("twitter").get().then(function (querySnapshot) {
    querySnapshot.forEach(function (doc) {
        // doc.data() is never undefined for query doc snapshots
        console.log(doc.id, " => ", doc.data());
        var newRow = tableRef.insertRow();
        // Insert a cell in the row at index 0
        var newCell = newRow.insertCell(0);
        var newText = document.createTextNode(doc.data().Date);
        newCell.appendChild(newText);
        var newCell1 = newRow.insertCell(1);
        var newText1 = document.createTextNode(doc.data().phrase);
        newCell1.appendChild(newText1);
        var newCell2 = newRow.insertCell(2);
        var newText2 = document.createTextNode(doc.data().positive);
        newCell2.appendChild(newText2);
        var newCell3 = newRow.insertCell(3);
        var newText3 = document.createTextNode(doc.data().technical);
        newCell3.appendChild(newText3);
        var newCell4 = newRow.insertCell(4);
        var newText4 = document.createTextNode(doc.data().location);
        newCell4.appendChild(newText4);
    });
});