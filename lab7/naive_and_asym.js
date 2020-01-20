async = require("async");

let Fork = function () {
    this.state = 0;
    return this;
};

let globalDelay = 0;

Fork.prototype.acquire = function (id, callback) {
    // zaimplementuj funkcje acquire, tak by korzystala z algorytmu BEB
    // (http://pl.wikipedia.org/wiki/Binary_Exponential_Backoff), tzn:
    // 1. przed pierwsza proba podniesienia widelca Filozof odczekuje 1ms
    // 2. gdy proba jest nieudana, zwieksza czas oczekiwania dwukrotnie
    //    i ponawia probe itd.

    let delay = 1;

    let loop = () => {
        setTimeout(() => {

            globalDelay += delay;
            // console.log(globalDelay);

            if (this.state === 1) {
                delay *= 2;
                console.log("Waiting: " + delay);
                loop();
            } else {
                console.log(id + " taking fork");
                this.state = 1;
                if (callback) callback();
            }
        }, delay)
    };

    loop();

};

Fork.prototype.release = function () {
    this.state = 0;
};

let Philosopher = function (id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id + 1) % forks.length;
    return this;
};

calculateTimeDifference = function (startTime, endTime) {
    return (endTime[0] - startTime[0]) * 1e9 + (endTime[1] - startTime[1]);
};

Philosopher.prototype.startNaive = function (count) {
    let forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    // zaimplementuj rozwiazanie naiwne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow


    let loop = () => {
        if (count > 0) {
            console.log("loop: " + count + " " + id);
            forks[f1].acquire(id, eatWithSecondFork);
            --count;
        }
    };

    let eatWithSecondFork = () => {
        forks[f2].acquire(id, eat)
    };

    let think = () => {
        setTimeout(() => {
                console.log(id + " is thinking");
                loop();
            },
            Math.floor(Math.random * 100)
        )
    };

    let eat = () => {
        setTimeout(() => {
                console.log(id + " is eating");
                forks[f1].release();
                forks[f2].release();
                think();
            },
            Math.floor(Math.random * 100)
        )
    };

    loop();
};

Philosopher.prototype.startAsym = function (count) {
    let forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;

    // zaimplementuj rozwiazanie asymetryczne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow

    let loop = () => {
        if (count > 0) {
            console.log("loop: " + count + " " + id);
            let fork = id % 2 === 0 ? f2 : f1;
            forks[fork].acquire(id, eatWithSecondFork);
            count--;
        }
    };

    let eatWithSecondFork = () => {
        let fork = id % 2 === 0 ? f1 : f2;
        forks[fork].acquire(id, eat);
    };

    let think = () => {
        setTimeout(() => {
            console.log(id + " is thinking");
            loop();
        }, Math.floor(Math.random() * 100));
    };

    let eat = () => {
        setTimeout(() => {
            console.log(id + " is eating");
            forks[f1].release();
            forks[f2].release();
            think();
        }, Math.floor(Math.random() * 100));
    };

    loop();
};

let N = 5;
let forks = [];
let philosophers = [];

let i;
for (i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (i = 0; i < N; i++) {
    // philosophers[i].startNaive(10);
    philosophers[i].startAsym(10);
}