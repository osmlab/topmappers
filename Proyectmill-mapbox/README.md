Using Projectmill https://github.com/mapbox/projectmill
=======================================================

### Some Problems and solutions
Aqui se generan problemas como los siguientes:

		ruben@ruben-pc:~/projectmill$ ./index.js --mill --render -c config.example.json -t /usr/share/tilemill

		node.js:134
		        throw e; // process.nextTick error, or 'error' event on first tick
		        ^
		Error: Cannot find module 'sqlite3'
		    at Function._resolveFilename (module.js:326:11)
		    at Function._load (module.js:271:25)
		    at require (module.js:355:19)
		    at Object.<anonymous> (/home/ruben/projectmill/index.js:281:19)
		    at Module._compile (module.js:411:26)
		    at Object..js (module.js:417:10)
		    at Module.load (module.js:343:31)
		    at Function._load (module.js:302:12)
		    at Array.<anonymous> (module.js:430:10)
		    at EventEmitter._tickCallback (node.js:126:26)
		ruben@ruben-pc:~/projectmill$ 

Primero iniciamos el proyecto projectmill sin ningun "node_modules"
Cambiamos algunas lineas  en package.json
https://github.com/mapbox/projectmill/blob/master/package.json#L15 a 2.1.7
y luego en el directorio de projectmill, hacer: npm install














