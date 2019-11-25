function printAsync(s, cb) {
    let delay = Math.floor((Math.random() * 1000) + 500);
    setTimeout(function () {
        console.log(s);
        if (cb) cb();
    }, delay);
}

// Napisz funkcje (bez korzytania z biblioteki async) wykonujaca
// rownolegle funkcje znajdujace sie w tablicy
// parallel_functions. Po zakonczeniu wszystkich funkcji
// uruchamia sie funkcja final_function. Wskazowka:  zastosowc
// licznik zliczajacy wywolania funkcji rownoleglych


function inparallel(parallel_functions, final_function) {
    let counter = parallel_functions.length;
    let decFunction = () => {
        counter -= 1;
        if (counter === 0) {
            final_function()
        }
    };

    for (let parallel_function of parallel_functions) {
        parallel_function(decFunction);
    }
}

A = function (cb) {
    printAsync("A", cb);
};

B = function (cb) {
    printAsync("B", cb);
};

C = function (cb) {
    printAsync("C", cb);
};

D = function (cb) {
    printAsync("DONE", cb);
};

inparallel([A, B, C, A, B, C, A, B, C], D);

// kolejnosc: A, B, C - dowolna, na koncu zawsze "Done"