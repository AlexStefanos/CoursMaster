class Candy {
    constructor(color_, width_, height_) {
        this.width = width_
        this.height = height_
        this.color = color_
        this.x = 0
        this.y = 0
        this.xMoveTo = 0
        this.yMoveTo = 0
        this.selectionne = false
    }

    setPosition(x_, y_) {
        this.x = x_
        this.y = y_
        this.xMoveTo = x_
        this.yMoveTo = y_
    }

    getColor() {
        return this.color
    }

    marque(v) {
        this.selectionne = v
    }

    dessin(context) {
        if (this.getColor() == 1) {
            context.drawImage(document.getElementById("blue"), this.x, this.y);
        } else if (this.getColor() == 2) {
            context.drawImage(document.getElementById("green"), this.x, this.y);
        } else if (this.getColor() == 3) {
            context.drawImage(document.getElementById("orange"), this.x, this.y);
        } else if (this.getColor() == 4) {
            context.drawImage(document.getElementById("red"), this.x, this.y);
        } else if (this.getColor() == 5) {
            context.drawImage(document.getElementById("yellow"), this.x, this.y);
        } else if (this.getColor() == 0) {
            console.log("Erreur couleur bonbon");
        }
    }

    deplacementVers(xMoveTo_, yMoveTo_) {
        this.xMoveTo = xMoveTo_
        this.yMoveTo = yMoveTo_
    }

    estEnMouvement() {
        return !((this.x == this.xMoveTo) && (this.y == this.yMoveTo))
    }

    metAJour() {
        if (this.estEnMouvement()){
            if (this.x < this.xMoveTo) {
                this.x = this.x1 + 5
            }
            if (this.y < this.yMoveTo) {
                this.y = this.y1 + 5
            }
            if (this.x > this.xMoveTo){
                this.x = this.x - 5
            } 
            if (this.y > this.yMoveTo){
                this.y = this.y - 5
            } 
        }
        
    }
}

class Vue {
    constructor(txy, controller_, modele_, tailleLutin_) {
        this.taille = txy;
        this.controller = controller_;
        this.modele = modele_;
        this.tailleLutin = tailleLutin_;
        this.listLutin = this.metAJourAPartirDuModele();
    }

    metAJourAPartirDuModele() {
        this.listLutin = []
        for (let i = 0; i < this.modele.tableau.length; i++) {
            for (let j = 0; j < this.modele.tableau.length; j++) {
                if (this.modele.tableau[i][j] != 0) {
                    this.nouveauLutin(i, j, this.modele.tableau[i][j]);
                }
            }
        }
    }

    nouveauLutin(x, y, quel) {
        let candy = new Candy(quel, this.tailleLutin, this.tailleLutin)
        candy.setPosition(x * this.tailleLutin, y * this.tailleLutin)
        this.listLutin.push(candy)
    }

    nouveauLutinPourApparition(x,y,quel,compteur){
        let candy = new Candy(quel, this.tailleLutin, this.tailleLutin)
        candy.setPosition(x*this.tailleLutin,-compteur*this.tailleLutin);
        candy.deplacementVers(x * this.tailleLutin, y * this.tailleLutin);
        this.listLutin.push(candy);
    }

    echangePossible(x1, y1, x2, y2){
        var ret = false;
        this.modele.echange2cases(x1, y1, x2, y2);
        let listExplosion = this.modele.explosePossible();
        for (let i = 0; i < listExplosion.length && ret != true; i++) {
            for (let j = 0; j < listExplosion.length && ret != true; j++) {
                if (listExplosion[i][j] == 1) {
                    ret = true
                }
            }
        }
        if(ret == false){
            this.modele.echange2cases(x1, y1, x2, y2);
        }
        return ret
    }

    echange2lutins(x1, y1, x2, y2) {
        if(((x1 == x2) && (y1 == y2-1 || y1 == y2+1)) || ((y1 == y2) && (x1 == x2+1 || x1 == x2-1) )){
            
            if(this.echangePossible(x1, y1, x2, y2)){
                this.listLutin.forEach(sprite => {
                    if (sprite.x1 == (x1 * this.tailleLutin) && sprite.y1 == (y1 * this.tailleLutin)) {
                        sprite.deplacementVers(x2* this.tailleLutin, y2* this.tailleLutin);
                    }
                    if (sprite.x1 == (x2 * this.tailleLutin) && sprite.y1 == (y2 * this.tailleLutin)) {
                        sprite.deplacementVers(x1* this.tailleLutin, y1* this.tailleLutin);
                    }
                });
                this.animeVue(context)
            }
            
        }
    }


    effaceEcran(contexte) {
        contexte.clearRect(0, 0, contexte.width,contexte.height);
        contexte.fillStyle = "grey";
        contexte.fillRect(0, 0, this.controleur.tailleJeu*this.tailleLutin, this.controleur.tailleJeu*this.tailleLutin);
        contexte.font = '48px serif';
        contexte.fillStyle = "black";
        contexte.fillText("Score : ", 100, 800);
        contexte.fillText(this.modele.score, 250, 800);
    }

    afficheVue(contexte) {
        this.effaceEcran(contexte)
        this.listLutin.forEach(sprite => {
            sprite.dessin(contexte);
        });
    }

    metAJour() {
        this.listLutin.forEach(sprite => {
            sprite.metAJour();
        });
    }

    estEnMouvement() {
            var ret = false;
            this.listLutin.forEach(sprite => {
                if (sprite.estEnMouvement()) {
                    ret = true;
                }
            });
            return ret;
        }

    animeVue(contexte) {
        this.metAJour()
        this.afficheVue(contexte)
        var that = this
        if (this.estEnMouvement()) {
            setTimeout(() => { that.animeVue(contexte) }, 10)
        }else{
            this.controleur.finAnimation(contexte);
        }
    }

    effaceLutin(contexte,x,y){
        contexte.clearRect(x*this.tailleLutin, y*this.tailleLutin, this.tailleLutin, this.tailleLutin);
        contexte.fillStyle = "grey";
        contexte.fillRect(x*this.tailleLutin, y*this.tailleLutin, this.tailleLutin, this.tailleLutin);
    }
}

class Modele {
    constructor(taille_) {
        this.score = 0
        this.taille = taille_
        this.tableau = Array.from({ length: taille_ }, e => Array(taille_).fill(0));
        for (let i = 0; i < this.tableau.length; i++) {
            for (let j = 0; j < this.tableau.length; j++) {
                this.tableau[i][j] = Math.floor(Math.random() * Math.floor(5) + 1)
            }
        }
    }

    echange2cases(x1, y1, x2, y2) {
        var tmp = this.tableau[x1][y1]
        this.tableau[x1][y1] = this.tableau[x2][y2]
        this.tableau[x2][y2] = tmp
    }

    faitExplosion() {
        var listExplosion = this.explosePossible();
        for (let i = 0; i < listExplosion.length; i++) {
            for (let j = 0; j < listExplosion.length; j++) {
                if (listExplosion[i][j] == 1) {
                    this.score = this.score + 1;
                    this.tableau[i][j] = 0;
                }
            }
        }
    }

    explosePossible() {
        var list = Array.from({ length: this.taille }, e => Array(this.taille).fill(0));
        for (let i = 0; i < list.length; i++) {
            for (let j = 0; j < list.length; j++) {
                if (list[i][j] == 0) {
                    if (this.correspondanceElement(i, j, i + 1, j)) {
                        if (this.correspondanceElement(i, j, i + 2, j)) {
                            list[i][j] = 1;
                            list[i + 1][j] = 1;
                            list[i + 2][j] = 1;
                        }
                    }
                    if (this.correspondanceElement(i, j, i - 1, j)) {
                        if (this.correspondanceElement(i, j, i - 2, j)) {
                            list[i][j] = 1;
                            list[i - 1][j] = 1;
                            list[i - 2][j] = 1;
                        }
                    }
                    if (this.correspondanceElement(i, j, i, j - 1)) {
                        if (this.correspondanceElement(i, j, i, j - 2)) {
                            list[i][j] = 1;
                            list[i][j - 1] = 1;
                            list[i][j - 2] = 1;
                        }
                    }
                    if (this.correspondanceElement(i, j, i, j + 1)) {
                        if (this.correspondanceElement(i, j, i, j + 2)) {
                            list[i][j] = 1;
                            list[i][j + 1] = 1;
                            list[i][j + 2] = 1;
                        }
                    }
                }
            }
        }
        return list
    }

    combbinaisonExistante(){
        let ret = false;
        this.explosePossible().forEach(ligne => ligne.forEach(colonne => {
            if(colonne == 1){
                ret = true;
            }
        }))
        return ret;
    }

    correspondanceElement(x1, y1, x2, y2) {
        if (this.verifieIndice(x1) && this.verifieIndice(y1) && this.verifieIndice(x2) && this.verifieIndice(y2)) {
            if(this.tableau[x1][y1] != 0 && this.tableau[x2][y2] != 0){
                return this.tableau[x1][y1] == this.tableau[x2][y2];
            }
        }
    }

    verifieIndice(position_) {
        return (position_ >= 0 && position_ < this.taille)
    }
}

class Controleur {
    constructor(tailleJeu, tailleLutin) {
        this.tailleJeu = tailleJeu;
        this.modele = new Modele(tailleJeu);
        this.vue = new Vue(tailleJeu, this, this.modele, tailleLutin);
        this.vue.metAJourAPartirDuModele()
        
        this.modele.score = 0;
    }

    finAnimation(contexte) {
        if(this.modele.combbinaisonExistante()){
            this.explosionVue(context);
            this.modele.faitExplosion();
            this.repackGrille(contexte);
            this.vue.animeVue(contexte)
        }else{
            this.vue.metAJourAPartirDuModele();
            this.vue.afficheVue(contexte);
        }
    }

    click(x, y) {
        if (this.indice_x1 == undefined && this.indice_y1 == undefined) {
            this.indice_x1 = Math.floor(x / this.vue.tailleLutin);
            this.indice_y1 = Math.floor(y / this.vue.tailleLutin);
        } else if (this.indice_x2 == undefined && this.indice_y2 == undefined) {
            this.indice_x2 = Math.floor(x / this.vue.tailleLutin);
            this.indice_y2 = Math.floor(y / this.vue.tailleLutin);
            this.vue.echange2lutins(this.indice_x1, this.indice_y1, this.indice_x2, this.indice_y2);
            this.effacePointClicker();
        }
        this.vue.afficheVue(context);
    }

    effacePointClicker() {
            this.indice_x1 = undefined;
            this.indice_x2 = undefined;
            this.indice_y1 = undefined;
            this.indice_y2 = undefined;
        }

    repackGrille(contexte) {
        for(let i=0; i<this.tailleJeu;i++){
            this.repackColonne(i);
        }
        this.rajouteDesLutins();
    }

    rajouteDesLutins(){
        for(let i=0; i<this.tailleJeu;i++){
            let compteur = 0;
            for(let j=this.tailleJeu-1;j>=0 ;j--){
                if(this.modele.tableau[i][j]==0){
                    this.modele.tableau[i][j] = Math.floor(Math.random() * Math.floor(5) + 1);
                    compteur = compteur+1;
                    this.vue.nouveauLutinPourApparition(i,j,this.modele.tableau[i][j],compteur);
                }
            }
        }
    }

    repackColonne(col) {
        let ret = false;
        if(this.elementVideDansColonne(col)){
            for(let i=0;i<this.tailleJeu && ret != true;i++){
                if(this.modele.tableau[col][i] == 0){
                    if(this.elementPlusHaut(col,i)){
                        this.deplaceVersLeBasAPartirDe(col,i);
                        ret = true;
                        this.repackColonne(col)
                    }
                }
            }
        }
        return ret;
    }

    elementVideDansColonne(col){
        let ret = false
        this.modele.tableau.forEach(e=> e.forEach(element => {if(element==0)ret = true}))
        return ret
    }

    elementPlusHaut(x,y){
        let ret = false;
        for(let i=y-1; i>=0 && ret==false;i--){
            if(this.modele.tableau[x][i] != 0){
                ret = true;
            }
        }
        return ret;
    }

    deplaceVersLeBasAPartirDe(x,y){
        if(x>=0 && x<this.tailleJeu && y>0 && y<this.tailleJeu){
            this.modele.echange2cases(x,y,x,y-1);
            this.deplaceVersLeBasAPartirDe(x,y-1);
        }
    }

    explosionVue(contexte){
        var listExplosion = this.modele.explosePossible();
        for (let i = 0; i < listExplosion.length; i++) {
            for (let j = 0; j < listExplosion.length; j++) {
                if (listExplosion[i][j] == 1) {
                    this.vue.effaceLutin(contexte,i,j);
                }
            }
        }
    }
}

function init() {
    context = document.getElementById("cvs").getContext("2d");
    context.width = document.getElementById("cvs").width;
    context.height = document.getElementById("cvs").height;
    var jeu = new Controleur(6, 100);
    jeu.vue.afficheVue(context)
    jeu.vue.animeVue(context)

    function captureClick(event) {
        if (event.target.id == "cvs") {
            var x = event.pageX - event.target.offsetLeft;
            var y = event.pageY - event.target.offsetTop;
            jeu.click(x, y)

        }
    }

    document.addEventListener("click", captureClick);
}