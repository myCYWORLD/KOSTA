var a = 'window객체의 변수';
console.log(window.a);
console.log(a);   //var로 선언한 변수는  window에 선언한 객체와 같아서 window. 을 생략해도 됨

for(var i = 0; i<3; i++){
    console.log(i); //0, 1, 2
}
console.log(window.i); //3 ->java script에서는 변수 영역이 구분되지 않아서 for 블럭 밖에 나와서도 출력가능

var b = function(){
    var lv1 = 'outer local variable';
    var lv1 = function() {
        var inner  = 'inner local variable';
        console.log(lv1); //outer function variable
}
console.log(inner); //error -> inner에서 선언한 함수는 outer에서 사용할 수 없음
}
//console.log(lv1);//error
b();
//console.log(lv1); //error