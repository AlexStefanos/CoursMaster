class Candy {
    constructor(color_, length_, width_) {
        this.length = length_;
        this.width = width_;
        this.color = color_;
        this.x = 0;
        this.y = 0;
        this.xMoveTo = 0;
        this.yMoveTo = 0;
        this.targetted = false;
    }
    
    setPosition(x_, y_) {
        this.x = x_;
        this.y = y_;
        this.xMoveTo = x_;
        this.yMoveTo = y_;
    }

    getColor() {
        return(this.color);
    }

    getTargetted(targetted_) {
        this.targetted = targetted_;
    }

    drawSprite(context_) {
        if(this.getColor() == 1) {
            context_.drawImage(document.getElementById("blue"), this.x, this.y);
        }else if(this.getColor() == 2) {
            context_.drawImage(document.getElementById("green"), this.x, this.y);
        }else if(this.getColor() == 3) {
            context_.drawImage(document.getElementById("orange"), this.x, this.y);
        }else if(this.getColor() == 4) {
            context_.drawImage(document.getElementById("red"), this.x, this.y);
        }else if(this.getColor() == 5) {
            context_.drawImage(document.getElementById("yellow"), this.x, this.y);
        }else if(this.getColor() == 0) {
            console.log("Erreur couleur Bonbon");
        }
    }

    moveTo(xMoveTo_, yMoveTo_) {
        this.xMoveTo = xMoveTo_;
        this.yMoveTo = yMoveTo_;
    }

    isMoving() {
        return (!((this.x == this.xMoveTo) && (this.y == this.yMoveTo)));
    }

    update() {
        if(this.isMoving()) {
            if(this.x < this.xMoveTo) {
                this.x = this.x + 5;
            }
            if(this.y < this.yMoveTo + 5) {
                this.y = this.y + 5;
            }
            if(this.x > this.xMoveTo) {
                this.x = this.x - 5;
            }
            if(this.y > this.yMoveTo) {
                this.y = this.y - 5
            }
        }
    }
}

class Vue {
    constructor(sideBoardLength_, candyLength_, candyWidth_, controller_, model_) {
        this.sideBoardLength = sideBoardLength_;
        this.candyLength = candyLength_;
        this.candyWidth = candyWidth_;
        this.controller = controller_;
        this.model = model_;
        this.listSprite = this.updateDisplay();
    }

    updateDisplay() {
        this.listSprites = [];
        for(let i = 0; i < this.model.board.length; i++) {
            for(let j = 0; j < this.model.board.length; j++) {
                if(this.model.board[i][j] != 0) {
                    this.generateNewSprite(i, j, this.model.board[i][j]);
                }
            }
        }
    }

    generateNewSprite(x_, y_, color_) {
        let sprite = new Candy(color_, this.candyLength, this.candyWidth);
        sprite.setPosition(x_ * this.candyLength, y_ * this.candyWidth);
        this.listSprites.push(sprite);
    }

    generateNewCandy(x_, y_, color_, count_) {
        var sprite = new Candy(color_, this.candyLength, this.candyWidth);
        sprite.setPosition(x_ * this.candyLength, -count_ * this.candyWidth);
        sprite.moveTo(x_ * this.candyLength, y_ * this.candyWidth);
        this.listSprites.push(sprite);
    }

    possibleSpriteCombination(x_, y_, xMoveTo_, yMoveTo_) {
        var result = false;
        this.model.animation(x_, y_, xMoveTo_, yMoveTo_);
        let listExplo = this.model.possibleExplo();
        for(let i = 0; (i < listExplo.length) && (result != true); i++) {
            for(let j = 0; (j < listExplo.length) && (result != true); j++) {
                if(listExplo[i][j] == 1) {
                    result = true;
                }
            }
        }
        if(result == false) {
            this.model.animation(x_, y_, xMoveTo_, yMoveTo_);
        }
        return(result);
    }

    spriteCombination(x_, y_, xMoveTo_, yMoveTo_) {
        if(((x_ == xMoveTo_) && ((y_ == (yMoveTo_ - 1)) || (y_ == (yMoveTo_ + 1)))) || ((y_ == yMoveTo_) && ((x == (xMoveTo_ + 1)) || (x == (xMoveTo_ - 1))))){
            if(this.possibleSpriteCombination(x_, y_, xMoveTo_, yMoveTo_)) {
                this.listSprites.forEach(sprite => {
                    if(sprite.x == ((x_ * this.candyLength) && (sprite.y == (y_ * this.candyWidth)))) {
                        sprite.moveTo(xMoveTo_ * this.candyLength, yMoveTo_ * this.candyWidth);
                    }
                    if((sprite.x == (xMoveTo_ * this.candyLength)) && (sprite.yMoveTo_ == (yMoveTo_ * this.candyWidth))) {
                        sprite.moveTo(x_ * this.candyLength, y_ * this.candyWidth);
                    }
                });
                this.displayAnimations(context);
            }
        }
    }

    updateListSprites() {
        this.listSprites.forEach(sprite => {
            sprite.update();
        })
    }

    displayBoard(context_) {
        this.deleteAll(context_);
        this.listSprites.forEach(sprite => {
            sprite.drawSprite(context_);
        })
    }

    isMoving() {
        var result = false;
        this.listSprites.forEach(sprite => {
            if(sprite.isMoving()) {
                result = true;
            }
        });
        return(result);
    }

    displayAnimations(context_) {
        this.updateListSprites();
        this.displayBoard(context_);
        var display = this;
        if(this.isMoving()) {
            setTimeout(() => {display.displayAnimations(context_)}, 10);
        }
        else {
            this.controller.endAnimation(context_);
        }
    }

    deleteAll(context_) {
        context_.clearRect(0, 0, context_.width, context.height);
        context_.fillStyle = "grey";
        context_.fillRect(0, 0, this.controller.sideBoardLength * this.candyLength, this.controller.sideBoardLength);
        context_.font = "48px serif";
        context_.fillStyle = "black";
        context_.fillText("Score : ", 100, 800);
        context_.fillText(this.model.score, 250, 800);
    }

    deleteCandy(context_, x_, y_) {
        context_.clearRect(x_ * this.candyLength, y_ * this.candyWidth, this.candyLength, this.candyWidth);
        context_.fillStyle = "grey";
        context_.fillRect(x_ * this.candyLength, y * this.candyWidth, this.candyLength, this.candyWidth);
    }
}

class Model {
    constructor(sideBoardLength_) {
        this.sideBoardLength = sideBoardLength_;
        this.score = 0;
        this.board = Array.from({length: sideBoardLength_}, tmp => Array(this.sideBoardLength).fill(0));
            for(let i = 0; i < this.board.length; i++) {
                for(let j = 0; j < this.board.length; j++) {
                    this.board[i][j] = Math.floor(Math.random() * Math.floor(5) + 1);
                }
            }
        }

        animation(x_, y_, xMoveTo_, yMoveTo_) {
            var switches = this.board[x_][y_];
            this.board[x_][y_] = this.board[xMoveTo_][yMoveTo_];
            this.board[xMoveTo_][yMoveTo_] = switches;
        }

        explodeLine() {
            var listExplo = this.possibleExplosion();
            for(let i = 0; i < listExplo.length; i++) {
                for(let j = 0; j < listExplo.length; j++) {
                    if(listExplo[i][j] == 1) {
                        this.score = this.score + 1;
                        this.board[i][j] = 0;
                    }
                }
            }
        }

        possibleExplosion() {
            var listPossibleExplo = Array.from({length: this.sideBoardLength}, possibleExplo => Array(this.sideBoardLength).fill(0));
            for(let i = 0; i < listPossibleExplo.length; i++) {
                for(let j = 0; j < listPossibleExplo.length; j++) {
                    if(listPossibleExplo[i][j] == 0) {
                        if(this.possibleMatching(i, j, i + 1, j)) {
                            if(this.possibleMatching(i, j, i + 2, j)) {
                                listPossibleExplo[i][j] = 1;
                                listPossibleExplo[i + 1][j] = 1;
                                listPossibleExplo[i + 2][j] = 1;
                            }
                        }
                        if(this.possibleMatching(i, j, i - 1, j)) {
                            if(this.possibleMatching(i, j, i - 2, j)) {
                                listPossibleExplo[i][j] = 1;
                                listPossibleExplo[i - 1][j] = 1;
                                listPossibleExplo[i - 2][j] = 1;
                            }
                        }
                        if(this.possibleMatching(i, j, i, j - 1)) {
                            if(this.possibleMatching(i, j, i, j - 2)) {
                                listPossibleExplo[i][j] = 1;
                                listPossibleExplo[i][j - 1] = 1;
                                listPossibleExplo[i][j - 2] = 1;
                            }
                        }
                        if(this.possibleMatching(i, j, i, j + 1)) {
                            if(this.possibleMatching(i, j, i, j + 2)) {
                                listPossibleExplo[i][j] = 1;
                                listPossibleExplo[i][j + 1] = 1;
                                listPossibleExplo[i][j + 2] = 1;
                            }
                        }
                    }
                }
            }
            return(listPossibleExplo);
        }

        verifiedIndex(position_) {
            return((position_ >= 0) && (position_ < this.sideBoardLength));
        }

        possibleMatching(x_, y_, xMoveTo_, yMoveTo_) {
            if(this.verifiedIndex(x_) && this.verifiedIndex(y_) && this.verifiedIndex(xMoveTo_) && this.verifiedIndex(yMoveTo_)) {
                if((this.board[x_][y_] != 0) && (this.board[xMoveTo_][yMoveTo_] != 0)) {
                    return(this.board[x_][y_] == this.board[xMoveTo_][yMoveTo_]);
                }
            }
        }

        possibleCombination() {
            let result = false;
            this.possibleExplosion().forEach(line => line.forEach(column => {
                if(column == 1) {
                    result = true;
                }
            }))
            return(result);
        }
}

class Controller {
    constructor(sideBoardLength_, candyLength_, candyWidth_) {
        this.sideBoardLength = sideBoardLength_;
        this.candyLength = candyLength_;
        this.candyWidth = candyWidth_;
        this.model = new Model(sideBoardLength_);
        this.vue = new Vue(this.sideBoardLength, this.candyLength, this.candyWidth, this, this.model);
        this.vue.updateListSprites();
        this.model.score = 0;
    }

    explodeVue(context_) {
        var listExplo = this.model.possibleExplosion();
        for(let i = 0; i < listExplo.length; i++) {
            for(let j = 0; j < listExplo.length; j++) {
                if(listExplo[i][j] == 1) {
                    this.vue.deleteCandy(context_, i, j);
                }
            }
        }
    }

    handleEmptySpaces(column_) {
        var result = false;
        this.model.board.forEach(space => space.forEach(element => {
            if(element == 0) {
                result = true;
            }
        }))
        return(result);
    }
 
    filledCaseAbove(x_, y_) {
        var result = false;
        for(var i = y - 1; (i >= 0) && (result == false); i--) {
            if(this.model.board[x][i] != 0) {
                result = true;
            }
        }
        return(result);
    }

    downwardCase(x_, y_) {
        if((x >= 0) && (x < this.sideBoardLength) && (y > 0) && (y < this.sideBoardLength)) {
            this.model.animation(x_, y_, x_, y_ - 1);
            this.downwardCase(x, y - 1);
        }
    }

    controlColumn(column_) {
        var result = false;
        if(this.handleEmptySpaces(column_)) {
            for(var i = 0; (i < this.sideBoardLength) && (result != true); i++) {
                if(this.model.board[column_][i] == 0) {
                    if(this.filledCaseAbove(column_, i)) {
                        this.downwardCase(column_, i);
                        result = true;
                        this.controlColumn(column_);
                    }
                }
            }
        }
        return(result);
    }

    generateSprites() {
        for(var i = 0; i < this.sideBoardLength; i++) {
            var count = 0;
            for(var j = this.sideBoardLength - 1; j >= 0; j--) {
                if(this.model.board[i][j] == 0) {
                    this.model.board[i][j] = Math.floor(Math.random() * Math.floor(5) + 1);
                    count += 1;
                    this.vue.generateNewCandy(i, j, this.model.board[i][j], count);
                }
            }
        }
    }

    controlBoard(context_) {
        for(var i = 0; i < this.candyLength; i++) {
            this.controlColumn(i);
        }
        this.generateSprites();
    }

    endAnimation(context_) {
        if(this.model.possibleCombination()) {
            this.explodeVue(context_);
            this.model.explodeLine();
            this.controlBoard(context_);
            this.vue.displayAnimations(context_);
        }
        else {
            this.vue.updateDisplay();
            this.vue.displayBoard(context_);
        }
    }

    resetListenerClick() {
        this.indexX = undefined;
        this.indexY = undefined;
        this.indexXMoveTo = undefined;
        this.indexYMoveTo = undefined;
    }

    listenerClick(x_, y_) {
        if((this.indexX == undefined) && (this.indexY == undefined)) {
            this.indexX = Math.floor(x_ / this.vue.candyLength);
            this.indexY = Math.floor(y_ / this.vue.candyWidth);
        } else if((this.indexXMoveTo == undefined) && (this.indexYMoveTo == undefined)) {
            this.indexXMoveTo = Math.floor(x_ / this.vue.candyLength);
            this.indexYMoveTo = Math.floor(y_ / this.vue.candyWidth);
            this.vue.spriteCombination(this.indexX, this.indexY, this.indexXMoveTo, this.indexY);
            this.resetListenerClick();Q
        }
        this.vue.displayBoard(context);
    }
}

function init() {
    context = document.getElementById("canvas").getContext("2d");
    context.width = document.getElementById("canvas").width;
    context.height = document.getElementById("canvas").height;
    var game = new Controller(6, 100);
    game.vue.displayBoard(context);
    game.vue.displayAnimations(context);

    function captureClick(event) {
        if(event.target.id == "canvas") {
            var x = event.pageX - event.target.offsetLeft;
            var y = event.pageY - event.target.offsetTop;
            game.listenerClick(x, y);
        }
    }

    document.addEventListener("click", captureClick);
}
