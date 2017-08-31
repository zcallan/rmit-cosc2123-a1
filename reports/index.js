const async = require('async');
const fs = require('fs');
const template = fs.readFileSync('graph.html').toString();
const { exec } = require('child_process');

function generateReport( name, list, implementation, items, runs, minRemovals, maxRemovals ) {
  let total = maxRemovals - minRemovals;
  const data = [];
  async.timesSeries( total, (n, next) => {
    runTest( list, implementation, items, runs, n, 0, time => {
      data.push({
        runs: n,
        time: parseInt(time.replace('\n', '')),
      });
      console.log( n );
      next();
    });
  }, () => {
    data.sort(( a , b ) => a.runs - b.runs);
    fs.writeFileSync( `output/${name}.html`, template.replace('{{data}}', JSON.stringify( data )));
  });
}


function runTest( list, implementation, items, runs, removals, searches, callback ) {
  exec( `java ReportEvaluator ${list} ${items} ${implementation} ${runs} ${removals} ${searches}`, { cwd: `${__dirname}/../build` }, (error, stdout, stderr) => {
    if ( error ) {
      console.log( error );
    }

    if ( stderr ) {
      console.log( stderr );
    }

    callback(stdout);
  });
}

generateReport( 'test', 'shoppingList', 'bst', 100, 1000, 1, 100 );
