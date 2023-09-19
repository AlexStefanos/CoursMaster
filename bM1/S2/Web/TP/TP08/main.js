const http = require('http');
const URL = require('url');
const FS = require('fs');
const port = 3000;
const request = require('request');
const jsdom = require('jsdom');
const parse = require('url-parse');

const errCheck = (err, file) => {
    let writer = FS.createWriteStream(file, {
        flags: 'w'
    });
    let reader = FS.createReadStream(err).pipe(writer);
};

const load = (url, file, errCheck) => {
    request(url).pipe(FS.createWriteStream(file)).on('error', (err) => {
        errCheck(err, file);
    });
};

const jsdomParser = (url) => {
    request(url, function(error, response, body) {
        let dom = new jsdom.JSDOM(body);
        dom.window.document.querySelectorAll("img").forEach(function (image, index) {
            console.log(image.getAttribute('src'));
            let urlParse = parse(image.getAttribute('src'), true);
            let extension = image.getAttribute('src').split('.').pop();
            let name = 'imgs/' + urlParse.hostname + index + '.' + extension;
            load(image.getAttribute('src'), name, errCheck);
            image.setAttribute('src', name);
        });
        FS.writeFile('writed.html', dom.serialize(), err => {
            console.log('done');
        });
    });
};

const getImages = (url, prefix, errCheck) => {
    request(url, function(error, response, body) {
        let dom = new jsdom.JSDOM(body);
        dom.window.document.querySelectorAll("img").forEach(function(image, index) {
            console.log(image.getAttribute('src'));
            let extension = image.getAttribute('src').split('.').pop();
            let name = 'imgs/' + prefix + index + '.' + extension;
            load(image.getAttribute('src'), name, errCheck);
        });
    });
};

const requestHandler = (request, response) => {
    let urlp = URL.parse(request.url);
    console.log("request : " + urlp.pathname);
    switch(urlp.pathname) {
        case '/file':
            let writer = FS.createWriteStream('out.txt', {
                flags: 'w'
            });
        
        let reader = FS.createReadStream('in.txt').pipe(writer);
        response.writeHead(200, {'Content-Type': 'text/html'});
        response.end();
        break;

        case '/requestHTML':
            load('http://www.google.com', 'writed.html', errCheck);
            response.writeHead(200, {'Content-Type': 'text/html'});
            response.end();
            break;
        
        case '/requestFile':
            load('https://upload.wikimedia.org/wikipedia/en/thumb/1/19/Dr.DreTheChronic.jpg/220px-Dr.DreTheChronic.jpg', 'imgs/Dr.DreTheChronic.jpg', errCheck);
            response.writeHead(200, {'Content-Type': 'text/html'});
            response.end();
            break;
        
        case '/jsdom':
            request('http://localhost:3000/index.html', function(error, response, body){
                const dom = new jsdom.JSDOM(body);
                dom.window.document.querySelectorAll("img").forEach(function(image, index) {
                    console.log(image.getAttribute('src'));
                    let name = 'imgs/' + index + '.png';
                    load(image.getAttribute('src'), name. errCheck);
                    image.setAttribute('src', name);
                });
                FS.writeFile('writed.html', dom.serialize(), err => {
                    console.log('done');
                });
            });
            break;
        
        case '/jsdomParser':
            jsdomParser('http://localhost:3000/index.html');
            break;
        
        case '/get':
            getImages('http://localhost:3000/index.html', 'im', errCheck);
            break;
        
        default:
            let def = urlp.pathname;
            if(def.length > 1) {
                def = def.slice(1);
            }
            else {
                def = 'index.html';
            }
            FS.readFile(def, (err, contents) => {
                if(!err) {
                    response.end(contents);
                }
                else {
                    response.writeHead(404, {'Content-Type': 'text/html'});
                    response.end();
                }
            });
            break;
    }
};

const server = http.createServer(requestHandler);
server.listen(port, (err) => {
    if(err) {
        return(console.log('Prob : ', err));
    }
    console.log('Serveur en ligne sur le port : ${port}');
});