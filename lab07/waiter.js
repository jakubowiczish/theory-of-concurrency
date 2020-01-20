async = require("async");

let Fork = function () {
    this.state = 0;
    return this;
};

let globalDelay = 0;

Fork.prototype.acquire = function (id, callback) {
    let delay = 1;

    let loop = () => {
        setTimeout(() => {

            globalDelay += delay;
            console.log(globalDelay);

            if (delay > 2048) delay = 1;

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

let Waiter = function (N) {
    this.freePlaces = N - 1;
    return this;
};

Waiter.prototype.getAccessToTable = function (id, callback) {
    let delay = 1;

    let loop = () => {
        setTimeout(() => {

            globalDelay += delay;
            console.log(globalDelay);

            if (this.freePlaces === 0) {
                delay *= 2;
                if (delay > 2048) delay = 1;
                console.log(id + " is blocked by waiter, been waiting: " + delay);
                loop();
            } else {
                console.log(id + " has now access to table");
                this.freePlaces--;
                if (callback) callback();
            }
        }, delay);
    };

    loop();
};

Waiter.prototype.leaveTable = function (id) {
    console.log(id + " left the table");
    this.freePlaces++;
};

let Philosopher = function (id, forks, waiter) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id + 1) % forks.length;
    this.waiter = waiter;
    return this;
};

Philosopher.prototype.startConductor = function (count) {
    let forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id,
        waiter = this.waiter;

    let loop = () => {
        if (count > 0) {
            console.log("loop: " + count + " " + id);
            waiter.getAccessToTable(id, getFirstFork);
            count--;
        }
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
            waiter.leaveTable(id);
            think();
        }, Math.floor(Math.random() * 100));
    };

    let getFirstFork = () => {
        forks[f1].acquire(id, getSecondFork);
    };

    let getSecondFork = () => {
        forks[f2].acquire(id, eat);
    };

    loop();
};

let N = 10;
let forks = [];
let philosophers = [];

let waiter = new Waiter(N);

let i;
for (i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks, waiter));
}

for (i = 0; i < N; i++) {
    philosophers[i].startConductor(10);
}