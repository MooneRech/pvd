'use strict';

var usernamePage = document.querySelector('#username-page');
var gamePage = document.querySelector('#game-page');
var playerCountPage = document.querySelector('#player-count-page');
var usernameForm = document.querySelector('#usernameForm');
var countForm = document.querySelector('#player-count-page');
var gameForm = document.querySelector('#game-page');
var resultForm = document.querySelector('#result-page');
var hiddenInput = document.querySelector('#hidden-id-value');
var guessArea = document.querySelector('#guessList');

var myStorage = window.localStorage;
var count;
var input;
var interval;

function onload() {
    if (myStorage.getItem("count") > -1 && myStorage.getItem("count") != null) {
        countForm.classList.add('hidden');
        usernamePage.classList.remove('hidden');
        console.log(sessionStorage.getItem("currentIdValue"));
        hiddenInput.value = sessionStorage.getItem("currentIdValue");
    }
}

function onCountSubmit(event) {
    count = document.querySelector('#count').value.trim();
    myStorage.setItem("count", count);
    myStorage.setItem("inProgress", true);
    myStorage.setItem("hasWinner", false);

    hiddenInput.value = 0;

    if(count) {
        countForm.classList.add('hidden');
        usernamePage.classList.remove('hidden');

        var i;
        for(i = 1; i < count; i++) {
            sessionStorage.setItem("currentIdValue",i);
            window.open("http://localhost:8080/", "_blank");
        }
        $.ajax({
            url: "http://localhost:8080/game/"+count
        });
    }
    event.preventDefault();
}

function setUsername(event) {
console.log(hiddenInput.value)
console.log(document.querySelector('#inputUsername').value.trim())

    $.ajax({
        url: "http://localhost:8080/game/username/" + hiddenInput.value +"/" + document.querySelector('#inputUsername').value.trim()
    });

    usernamePage.classList.add('hidden');
    gamePage.classList.remove('hidden');

    event.preventDefault();
}

function sendNumber(event) {
    var value = document.querySelector('#guessNumber');

    $.ajax({
        url: "http://localhost:8080/game/progress/" + hiddenInput.value +"/" + value.value.trim()
    }).then(function(data) {

        console.log(data)

        myStorage.setItem("inProgress", data.inProgress);

        if (data.guessed == false) {
            setMessage(data.message);
        } else {
            setMessage(data.message);
            document.querySelector('#guessInputContainer').classList.add('hidden');
            document.querySelector('#waitingHeader').classList.remove('hidden');
            interval();
        }
    });;

    value.value = "";
    event.preventDefault();
}

function setMessage(message) {
    var listMessage = document.createElement('li');
    var messageText = document.createTextNode(message);
    listMessage.appendChild(messageText);
    guessArea.appendChild(listMessage);
}

function interval() {
    myStorage.setItem("count", -1);
    interval = setInterval(function() { getResult() }, 1000);
}

function getResult() {
    myStorage = window.localStorage;
    console.log(myStorage);
    if(myStorage.getItem("inProgress") == "false") { console.log("myStorage");
        clearInterval(interval);
        gameForm.classList.add('hidden');
        resultForm.classList.remove('hidden');

        $.ajax({
            url: "http://localhost:8080/game/results/"
        }).then(function(data) {
            var field = document.querySelector('#resultField');
            var message = document.createTextNode(data.message);
            field.appendChild(message);
        });;
    }
}

countForm.addEventListener("submit", onCountSubmit, true);
usernameForm.addEventListener("submit", setUsername, true);
gameForm.addEventListener("submit", sendNumber, true);