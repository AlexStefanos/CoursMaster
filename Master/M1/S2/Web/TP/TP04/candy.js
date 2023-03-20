class CandyBoard {
    board = [[]];
}

class Candy {
    constructor(length_, width_, color_) {
        this.length = length_;
        this.width = width_;
        this.color = color_;
        this.x = 0;
        this.y = 0;
        this.xMoveTo = 0;
        this.yMoveTo = 0;
        this.targetted = false;
    }
    position = (0, 0);
    
    setPosition(x_, y_) {
        this.x = x_;
        this.y = y_;
        this.xMoveTo = x_;
        this.yMoveTo = y_;
    }

    getColor() {
        return(this.type);
    }

    getTargetted(targetted_) {
        this.targetted = targetted_;
    }

    drawSprite(context_) {
        if(this.getColor() == "blue") {
            context_.drawImage(document.getElementById("blue", this.x, this.y));
        }else if(this.getColor() == "green") {
            context_.drawImage(document.getElementById("green", this.x, this.y));
        }else if(this.getColor() == "orange") {
            context_.drawImage(document.getElementById("orange", this.x, this.y));
        }else if(this.getColor() == "red") {
            context_.drawImage(document.getElementById("red", this.x, this.y));
        }else if(this.getColor() == "yellow") {
            context_.drawImage(document.getElementById("yellow", this.x, this.y));
        }else {
            console.log("Erreur couleur Bonbon");
        }
    }

    moveTo(xMoveTo_, yMoveTo_) {
        this.xMoveTo = xMoveTo_;
        this.yMoveTo = yMoveTo_;
    }

    isMoving() {
        return((this.x != this.moveTo) && (this.y != this.moveTo));
    }
}

class Modele {
    constructor(sideBoardLength_) {
        this.sideBoardLength_ = sideBoardLength;
        this.score = 0;
        this.Q = 
    }

    explodeLine(x_) {
        this.
    }

    animation(departX, departY, arrivedX, arrivedY) {

    }
}

class Controller {
    constructor(sideBoardLength, candyLength, candyWidth) {
        this.sideBoardLength = sideBoardLength;
        this.candyLength = candyLength;
        this.candyWidth = candyWidth;
    }

    listenerClick(x, y) {

    }

    makeAllCombinations(x, y) {

    }

    handleEmptySpacesFrom(x) {

    }
}

class Vue {
    constructor(controller, model) {
        this.controller = controller;
        this.model = model;
    }

    generateNewCandy() {

    }

    deleteCandy(x, y) {

    }

    displayBoard() {

    }

    displayAnimations() {

    }
}

function captureClick(event) {
    var x = event.pageX - event.target-offsetLeft;
    var y = event.pageY - event.target.offsetTop;
    game.click();
}

function init() {
    context = document.getElementById("canvas").getContext("2d");
    context.width = document.getElementById("canvas").width;
    context.height = document.getElementById("canvas").height;
    document.addEventListener("click", captureClick);
    var game = new Controller(10, 50);
}