var a;
a = function(){
  alert("꼭 짱이 되어야지..아무도 못까불게 짱이 될거야");//함수 구현
};
a(); //함수 호출

var b; 
b = a;
b();

var c;
c = function(p){      //매개변수 이름만 적음
    console.log("매개변수 p = " + p);
    if(typeof(p) == 'function'){
      p(); //함수호출
    }
}
var str = 'hello';
c(str);
var num = 999;
c(num);
c(a);

c('hello');
c(function(){
  console.log('콜백함수입니다');
});

d= function(p1,p2,p3){
  if(typeof(p1) == 'function'){
    p1(p2, p3);
    //for(var i=p2, i<=p3; i++){
    //  p1();  
    //}
  }
}    //함수 구현부까지는 세미콜론 사용 안해도 되지만 호출 할 때에는 꼭 사용해야함

d(function(a, b){
    for(var i=a; i<=b; i++){
      console.log(a, b, a+b);
    }
  }  //console.log(i);
, 1, 10);

//매개변수 개수와 인자의 개수
var e = function(p1, p2) {
  console.log(p1, p2); 
}
  e(1,2);   //1, 2
  e(1);     //1,
  e(1,2,3); //1, 2

  //반환값
  var f = function() {
    return true;
  }

  var f1 = f();
  if (f1) {
    console.log('f함수의 반환값은 true입니다');
  }
  var f2 = f();
  console.log(5+f2);//5+true -> 5+1 ->6

  var g = function () {
    console.log('반환값 없는 함수입니다');
  }
  var g1 = g();
  console.log('g함수의 반환값:' + g1); //undefined
  //------------------------------------------------
  //outer-inner function  
  var h = function () {
    var lv1 = '지역변수1';
    var lv2 = function () {
      var inner = '지역변수2';
      console.log('lv2함수 내부에서 lv1', lv1); //지역변수 1
      console.log('lv2함수 내부에서 inner', inner); //지역변수 2
    }
    lv2();
    console.log('h함수 내부에서 lv1', lv1); //지역변수 1
    console.log('h함수 내부에서 inner', inner); //Uncaught ReferenceError: inner is not defined
  }                                             //중첩된 inner함수에서는 outer함수 사용할 수 없다
  h(); 