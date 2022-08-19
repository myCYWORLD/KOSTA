/*
var로 선언하면 재선언 가능,   window객체 scope에 선언, 함수 내부에 선언하면 함수 scope에 선언 Hoisting 가능
var없이 선언하면 재선언 가능, window개게 scope에 선언
let으로 선언하면 재선언 불가능, block scope에 선언, Hoisting안됨
플젝할 때 변수의 scope도 영역을 가져야하고 덮어쓰면 안되기 때문에 let 사용 
*/
var a = 10;
var a = 'hello';
console.log(window.a); //hello
var f1 = function () {
    var lv = true; //함수 scope에 선언
    aa = true; //window객체 scope에 선언
    a = true;  //window객체 scope에 재선언
    var f2 = function(){
        aaa = true; //window객체 scope에 선언
    }
    f2();
}
f1();
// console.log(lv); //ReferenceError : lv is not defined
console.log(window.aa,  aa); //true
console.log(window.aaa, aaa); //true
console.log(window.a, aaa); //true

// let b = 10;
// let b = 'hello';
// console.log(b); //cannot redeclare block-scoped variable 'b'
let b = 10;
console.log(window.b); //undefined
let f2 = function(){
    let bb = false; //함수 scope에 선언
    let b = false;  //함수 scope에 선언
}
    f2();
    //console.log(bb); //ReferenceError : bb is not defined
    console.log(b); //10

    //Hoisting : 사용을 먼저하고 선언을 나중에 하는 방법
    aaaa = new Date();
    console.log(aaaa);
    var aaaa = new Date();

    bb = new Date();
    console.log(bb);
    let bb;