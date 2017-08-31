const async = require('async');
const fs = require('fs');
const template = fs.readFileSync('graph.html').toString();
const { exec } = require('child_process');

function generateReport( name, list, implementation, items, runs, minRemovals, maxRemovals ) {
  let total = maxRemovals - minRemovals;
  const data = [];
  async.timesSeries( total + 2, (n, next) => {
    runTest( list, implementation, items, runs, n, 0, time => {
      time = parseInt(time.replace('\n', ''));
      data.push({
        runs: n,
        time,
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
generateReport( 'test', 'shoppingList', 'linkedlist', 100, 1000, 1, 100 );
generateReport( 'test', 'shoppingList', 'sortedlinkedlist', 100, 1000, 1, 100 );
generateReport( 'test', 'shoppingList', 'hash', 100, 1000, 1, 100 );
generateReport( 'test', 'shoppingList', 'baltree', 100, 1000, 1, 100 );
generateReport( 'test', 'classList', 'bst', 100, 1000, 1, 100 );
generateReport( 'test', 'classList', 'linkedlist', 100, 1000, 1, 100 );
generateReport( 'test', 'classList', 'sortedlinkedlist', 100, 1000, 1, 100 );
generateReport( 'test', 'classList', 'hash', 100, 1000, 1, 100 );
generateReport( 'test', 'classList', 'baltree', 100, 1000, 1, 100 );
