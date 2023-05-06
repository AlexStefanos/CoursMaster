class Candy {
    constructor(color_, width_, height_) {
        this.width = width_;
        this.height = height_;
        this.color = color_;
        this.x = 0;
        this.y = 0;
        this.xMoveTo = 0;
        this.yMoveTo = 0;
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

    drawSprite(context_) {
        if (this.getColor() == 1) {
            context_.drawImage(document.getElementById("blue"), this.x, this.y);
        } else if (this.getColor() == 2) {
            context_.drawImage(document.getElementById("green"), this.x, this.y);
        } else if (this.getColor() == 3) {
            context_.drawImage(document.getElementById("orange"), this.x, this.y);
        } else if (this.getColor() == 4) {
            context_.drawImage(document.getElementById("red"), this.x, this.y);
        } else if (this.getColor() == 5) {
            context_.drawImage(document.getElementById("yellow"), this.x, this.y);
        } else if (this.getColor() == 0) {
            console.log("erreur bonbon");
        }
    }

    movingTo(xMoveTo_, yMoveTo_) {
        this.xMoveTo = xMoveTo_;
        this.yMoveTo = yMoveTo_;
    }

    candyIsMoving() {
        return(!((this.x == this.xMoveTo) && (this.y == this.yMoveTo)));
    }

    updateCandy() {
        if(this.candyIsMoving()) {
            if (this.x < this.xMoveTo) {
                this.x = this.x + 5;
            }
            if (this.y < this.yMoveTo) {
                this.y = this.y + 5;
            }
            if (this.x > this.xMoveTo) {
                this.x = this.x - 5;
            } 
            if (this.y > this.yMoveTo) {
                this.y = this.y - 5;
            } 
        }
    }
}

class Vue {
    constructor(sideBoardLength_, controller_, model_, candySize_) {
        this.sideBoardLength = sideBoardLength_;
        this.controller = controller_;
        this.model = model_;
        this.candySize = candySize_;
        this.listSprites = this.updateModel();
    }

    updateModel() {
        this.listSprites = []
        for (let i = 0; i < this.model.board.length; i++) {
            for (let j = 0; j < this.model.board.length; j++) {
                if (this.model.board[i][j] != 0) {
                    this.generateNewCandy(i, j, this.model.board[i][j]);
                }
            }
        }
    }

    generateNewCandy(x_, y_, color_) {
        let sprite = new Candy(color_, this.candySize, this.candySize);
        sprite.setPosition(x_ * this.candySize, y_ * this.candySize);
        this.listSprites.push(sprite);
    }

    generateNewSprite(x_, y_, color_, count_) {
        let sprite = new Candy(color_, this.candySize, this.candySize);
        sprite.setPosition(x_ * this.candySize, -count_ * this.candySize);
        sprite.movingTo(x_ * this.candySize, y_ * this.candySize);
        this.listSprites.push(sprite);
    }

    possibleSpriteCombination(x_, y_, xMoveTo_, yMoveTo_){
        var result = false;
        this.model.animation(x_, y_, xMoveTo_, yMoveTo_);
        let listExplosion = this.model.possibleExplodeLine();
        for (let i = 0; (i < listExplosion.length) && (result != true); i++) {
            for (let j = 0; (j < listExplosion.length) && (result != true); j++) {
                if(listExplosion[i][j] == 1) {
                    result = true;
                }
            }
        }
        if(result == false) {
            this.model.animation(x_, y_, xMoveTo_, yMoveTo_);
        }
        return(result);
    }

    spriteCombinations(x_, y_, xMoveTo_, yMoveTo_) {
        if(((x_ == xMoveTo_) && (y_ == (yMoveTo_ - 1) || y_ == (yMoveTo_ + 1))) || ((y_ == yMoveTo_) && (x_ == (xMoveTo_ + 1) || x_ == (xMoveTo_ - 1)))) {
            if(this.possibleSpriteCombination(x_, y_, xMoveTo_, yMoveTo_)) {
                this.listSprites.forEach(sprite => {
                    if (sprite.x == (x_ * this.candySize) && sprite.y == (y_ * this.candySize)) {
                        sprite.movingTo(xMoveTo_ * this.candySize, yMoveTo_ * this.candySize);
                    }
                    if (sprite.x == (xMoveTo_ * this.candySize) && sprite.y == (yMoveTo_ * this.candySize)) {
                        sprite.movingTo(x_ * this.candySize, y_ * this.candySize);
                    }
                });
                this.displayAnimation(context);
            }
            
        }
    }

    deleteScreen(context_) {
        context_.clearRect(0, 0, context_.width, context_.height);
        context_.fillStyle = "grey";
        context_.fillRect(0, 0, this.controller.sideBoardLength * this.candySize, this.controller.sideBoardLength * this.candySize);
        context_.font = '48px serif';
        context_.fillStyle = "black";
        context_.fillText("Score", 100, 800);
        context_.fillText(this.model.score, 250, 800);
    }

    displayScreen(context_) {
        this.deleteScreen(context_);
        this.listSprites.forEach(sprite => {
            sprite.drawSprite(context_);
        });
    }

    updateVue() {
        this.listSprites.forEach(sprite => {
            sprite.updateCandy();
        });
    }

    vueIsMoving() {
            var result = false;
            this.listSprites.forEach(sprite => {
                if (sprite.candyIsMoving()) {
                    result = true;
                }
            });
            return result;
        }

    displayAnimation(context_) {
        this.updateVue();
        this.displayScreen(context_);
        var display = this;
        if(this.vueIsMoving()) {
            setTimeout(() => {display.displayAnimation(context_)}, 10)
        }else{
            this.controller.endAnimation(context_);
        }
    }

    deleteSprite(context_,x,y){
        context_.clearRect(x*this.candySize, y*this.candySize, this.candySize, this.candySize);
        context_.fillStyle = "grey";
        context_.fillRect(x*this.candySize, y*this.candySize, this.candySize, this.candySize);
    }
}

class Model {
    constructor(size_) {
        this.score = 0;
        this.size = size_;
        this.board = Array.from({length: size_}, e => Array(size_).fill(0));
        for(let i = 0; i < this.board.length; i++) {
            for(let j = 0; j < this.board.length; j++) {
                this.board[i][j] = Math.floor(Math.random() * Math.floor(5) + 1);
            }
        }
    }

    animation(x_, y_, xMoveTo_, yMoveTo_) {
        var tmp = this.board[x_][y_];
        this.board[x_][y_] = this.board[xMoveTo_][yMoveTo_];
        this.board[xMoveTo_][yMoveTo_] = tmp;
    }

    explodeIsDone() {
        var listExplosion = this.possibleExplodeLine();
        for(let i = 0; i < listExplosion.length; i++) {
            for(let j = 0; j < listExplosion.length; j++) {
                if(listExplosion[i][j] == 1) {
                    this.score = this.score + 1;
                    this.board[i][j] = 0;
                }
            }
        }
    }

    possibleExplodeLine() {
        var list = Array.from({length: this.size}, e => Array(this.size).fill(0));
        for(let i = 0; i < list.length; i++) {
            for(let j = 0; j < list.length; j++) {
                if(list[i][j] == 0) {
                    if(this.possibleMatching(i, j, i + 1, j)) {
                        if(this.possibleMatching(i, j, i + 2, j)) {
                            list[i][j] = 1;
                            list[i + 1][j] = 1;
                            list[i + 2][j] = 1;
                        }
                    }
                    if(this.possibleMatching(i, j, i - 1, j)) {
                        if(this.possibleMatching(i, j, i - 2, j)) {
                            list[i][j] = 1;
                            list[i - 1][j] = 1;
                            list[i - 2][j] = 1;
                        }
                    }
                    if(this.possibleMatching(i, j, i, j - 1)) {
                        if(this.possibleMatching(i, j, i, j - 2)) {
                            list[i][j] = 1;
                            list[i][j - 1] = 1;
                            list[i][j - 2] = 1;
                        }
                    }
                    if(this.possibleMatching(i, j, i, j + 1)) {
                        if(this.possibleMatching(i, j, i, j + 2)) {
                            list[i][j] = 1;
                            list[i][j + 1] = 1;
                            list[i][j + 2] = 1;
                        }
                    }
                }
            }
        }
        return(list);
    }

    possibleCombination() {
        let result = false;
        this.possibleExplodeLine().forEach(ligne => ligne.forEach(colonne => {
            if(colonne == 1){
                result = true;
            }
        }))
        return(result);
    }

    possibleMatching(x_, y_, xMoveTo_, yMoveTo_) {
        if (this.verifiedIndex(x_) && this.verifiedIndex(y_) && this.verifiedIndex(xMoveTo_) && this.verifiedIndex(yMoveTo_)) {
            if(this.board[x_][y_] != 0 && this.board[xMoveTo_][yMoveTo_] != 0){
                return(this.board[x_][y_] == this.board[xMoveTo_][yMoveTo_]);
            }
        }
    }

    verifiedIndex(position_) {
        return ((position_ >= 0) && (position_ < this.size));
    }
}

class Controller {
    constructor(sideBoardLength_, candySize_) {
        this.sideBoardLength = sideBoardLength_;
        this.model = new Model(sideBoardLength_);
        this.vue = new Vue(sideBoardLength_, this, this.model, candySize_);
        this.vue.updateModel();
        this.model.score = 0;
    }

    endAnimation(context_) {
        if(this.model.possibleCombination()){
            this.explodeVue(context_);
            this.model.explodeIsDone();
            this.controlBoard(context_);
            this.vue.displayAnimation(context_);
        } else {
            this.vue.updateModel();
            this.vue.displayScreen(context_);
        }
    }

    listenerClick(x_, y_) {
        if (this.index_x1 == undefined && this.index_y1 == undefined) {
            this.index_x1 = Math.floor(x_ / this.vue.candySize);
            this.index_y1 = Math.floor(y_ / this.vue.candySize);
        } else if (this.index_x2 == undefined && this.index_y2 == undefined) {
            this.index_x2 = Math.floor(x_ / this.vue.candySize);
            this.index_y2 = Math.floor(y_ / this.vue.candySize);
            this.vue.spriteCombinations(this.index_x1, this.index_y1, this.index_x2, this.index_y2);
            this.resetListenerClick();
        }
        this.vue.displayScreen(context_);
    }

    resetListenerClick() {
            this.index_x1 = undefined;
            this.index_x2 = undefined;
            this.index_y1 = undefined;
            this.index_y2 = undefined;
        }

    controlBoard() {
        for(let i = 0; i < this.sideBoardLength; i++){
            this.controlColumn(i);
        }
        this.generateSprites();
    }

    generateSprites() {
        for(let i = 0; i < this.sideBoardLength; i++) {
            let count = 0;
            for(let j = this.sideBoardLength - 1; j >= 0 ; j--){
                if(this.model.board[i][j]==0) {
                    this.model.board[i][j] = Math.floor(Math.random() * Math.floor(5) + 1);
                    count = count + 1;
                    this.vue.generateNewSprite(i, j, this.model.board[i][j], count);
                }
            }
        }
    }

    controlColumn(column_) {
        let result = false;
        if(this.handleEmptySpaces()) {
            for(let i = 0; (i < this.sideBoardLength) && (result != true);i++){
                if(this.model.board[column_][i] == 0){
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

    handleEmptySpaces() {
        let result = false;
        this.model.board.forEach(e => e.forEach(element => {if(element == 0)result = true;}))
        return(result);
    }

    filledCaseAbove(x_, y_){
        let result = false;
        for(let i = y_ - 1; (i >= 0) && (result == false); i--){
            if(this.model.board[x_][i] != 0){
                result = true;
            }
        }
        return(result);
    }

    downwardCase(x_, y_){
        if((x_ >= 0) && (x_ < this.sideBoardLength) && (y_ > 0) && (y_ < this.sideBoardLength)){
            this.model.animation(x_ ,y_ ,x_ , y_ - 1);
            this.downwardCase(x_ , y_ - 1);
        }
    }

    explodeVue(context_){
        var listExplosion = this.model.possibleExplodeLine();
        for (let i = 0; i < listExplosion.length; i++) {
            for (let j = 0; j < listExplosion.length; j++) {
                if (listExplosion[i][j] == 1) {
                    this.vue.deleteSprite(context_, i, j);
                }
            }
        }
    }
}

function init() {
    context = document.getElementById("canvas").getContext("2d");
    context.width = document.getElementById("canvas").width;
    context.height = document.getElementById("canvas").height;
    var game = new Controller(6, 100);
    game.vue.displayScreen(context)
    game.vue.displayAnimation(context)
    function captureClick(event) {
        if (event.target.id == "canvas") {
            var x = event.pageX - event.target.offsetLeft;
            var y = event.pageY - event.target.offsetTop;
            game.listenerClick(x, y)
        }
    }

    document.addEventListener("click", captureClick);
}