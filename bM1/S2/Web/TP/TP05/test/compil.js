var NwBuilder = require('nw-builder');
var nw = new NwBuilder({
    files:['./**'],
    platforms:['linux64']
});
nw.build().then(function() {
    console.log('Compilation terminée !');
}).catch(function(error) {
    console.error(error);
});