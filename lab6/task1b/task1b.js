async = require("async");

function printAsync(s, cb) {
    var delay = Math.floor((Math.random() * 1000) + 500);
    setTimeout(function () {
        console.log(s);
        if (cb) cb();
    }, delay);
}

function task1(cb) {
    printAsync("1", function () {
        task2(cb);
    });
}

function task2(cb) {
    printAsync("2", function () {
        task3(cb);
    });
}

function task3(cb) {
    printAsync("3", cb);
}

// wywolanie sekwencji zadan
//task1(function() {
//    console.log('done!');
//});


/*
** Zadanie:
** Napisz funkcje loop(n), ktora powoduje wykonanie powyzszej
** sekwencji zadan n razy. Czyli: 1 2 3 1 2 3 1 2 3 ... done
**
*/

function loop(n) {
    var array_of_tasks = [];

    for (var i = 0; i < n; ++i) {
        array_of_tasks.push(task1);
    }

    async.waterfall(array_of_tasks, function (err, result) {
        console.log("Done!");
    });
}
loop(3);