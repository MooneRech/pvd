'use strict';

var usernamePage = document.querySelector('#username-page');
var usernameForm = document.querySelector('#usernameForm');
var gamePage = document.querySelector('#game-page');
var countForm = document.querySelector('#player-count-page');
var resultForm = document.querySelector('#result-page');
var hiddenInput = document.querySelector('#hidden-id-value');
var guessArea = document.querySelector('#guessList');

var myStorage = window.localStorage;
var count;
var interval;
var resultInterval;
var inProgress;

function onload() {
    $.ajax({
        url: window.location.href + "init"
    }).then(function(data) {
        if(data.initPlayers == true) {
            countForm.classList.add('hidden');
            usernamePage.classList.remove('hidden');
        }
    });
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

        $.ajax({
            url: window.location.href + "game/"+count
        });
    }
    event.preventDefault();
}

function setUsername(event) {
    $.ajax({
        url: window.location.href + "game/username/" + document.querySelector('#inputUsername').value.trim()
    }).then(function(data) {
        hiddenInput.value = data.id;

        if(data.inProgress == false) {
            document.querySelector('#usernameForm').classList.add('hidden');
            document.querySelector('#userHeader').classList.add('hidden');
            var usernameHeader = document.querySelector('#waitingUsernameHeader');
            var usernameLink = document.querySelector('#link');
            usernameHeader.classList.remove('hidden');
            usernameLink.classList.remove('hidden');
            usernameHeader.innerHTML  = "Waiting for other players to join (" + data.count + " players remains)";
            usernameLink.innerHTML = "Please consider giving this link to your friends: " + window.location.href;
            setWaitingInterval();
        } else {
            usernamePage.classList.add('hidden');
            gamePage.classList.remove('hidden');
        }
    });

    event.preventDefault();
}

function setWaitingInterval() {
    interval = setInterval(function() { getGameStatus() }, 3000);
}

function getGameStatus() {
    $.ajax({
        url: window.location.href + "game/status/"
    }).then(function(data) {
        if (data.inProgress == true) {
            usernamePage.classList.add('hidden');
            gamePage.classList.remove('hidden');
            clearInterval(interval);
        } else {
            document.querySelector('#waitingUsernameHeader').value("Waiting for other players to join (" + data.count + " players remains)");
        }
    });
}

function sendNumber(event) {
    var value = document.querySelector('#guessNumber');

    $.ajax({
        url: window.location.href + "game/progress/" + hiddenInput.value +"/" + value.value.trim()
    }).then(function(data) {

        if (data.guessed == false) {
            setMessage(data.message);
        } else {
            setMessage(data.message);
            document.querySelector('#guessInputContainer').classList.add('hidden');
            document.querySelector('#waitingHeader').classList.remove('hidden');
            intervalInit();

        }
    });

    value.value = "";
    event.preventDefault();
}

function setMessage(message) {
    var listMessage = document.createElement('li');
    var messageText = document.createTextNode(message);
    listMessage.appendChild(messageText);
    guessArea.appendChild(listMessage);
}

function intervalInit() {
    resultInterval = setInterval(function() { getResult() }, 1000);
}

function getResult() {
    $.ajax({
        url: window.location.href + "game/status/"
    }).then(function(data) {
        inProgress = data.inProgress;
        if(inProgress == false) {
            clearInterval(resultInterval);
            gamePage.classList.add('hidden');
            resultForm.classList.remove('hidden');
            getResultTwo();
        }
    });
}

function getResultTwo() {
    if(inProgress == false) {
        $.ajax({
            url: window.location.href + "game/results/"
        }).then(function(dataTwo) {
            var field = document.querySelector('#resultField');
            var message = document.createTextNode(dataTwo.message);
            field.appendChild(message);
        });
    }
}

countForm.addEventListener("submit", onCountSubmit, true);
usernameForm.addEventListener("submit", setUsername, true);
gamePage.addEventListener("submit", sendNumber, true);