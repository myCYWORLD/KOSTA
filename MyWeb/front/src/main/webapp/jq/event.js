// $(document).ready(function(){});
$(function () {
  let $txtObj = $("input[type=text]");
  let $btObj = $("button").first(); //jq객체를 반환
  $btObj.click(function () {
    alert("클릭되었습니다");
    $txtObj.val("클릭되었습니다");
  });

  //---------계산기 START------
  //DOM트리에서 class속성값이 calculator인 객체의 자식중 모든 button객체 찾기

  //querySelectorAll()의 반환형은 NodeList자료형
  //$()의 반환형은 jquery객체형
  //$()의 결과가 여러객체인 경우 각인덱스의 요소는 js객체이다
  //js객체를 jq객체로 변환하려면 $(js객체)처리 필요하다
  //반복문용 jq메서드는 each()

  let $btArr = $("div.calculator>button"); //
  //$($btArr[0]).hide();
  // $btArr.forEach(function(item, index){
  //   if(index % 2 == 0){
  //     item.hide(); //?
  //   }
  // });
  let $resultObj = $("div.calculator>div.result");
  let resultNum = 0; //계산된 결과값
  let operator; //연산자

  // $btArr.each(function(index, item){
  //   $(item).click(function(){
  //     let inner = $(this).html();
  //     switch(inner){
  //       case '+':
  //         operator = inner;
  //         break;
  //       case '=':
  //         $resultObj.html(resultNum);
  //         operator = undefined;
  //         resultNum = 0;
  //         break;
  //       default://숫자버튼들
  //         $resultObj.html(inner);
  //         if(operator == '+'){
  //           resultNum += parseInt(inner);
  //         }else{
  //           resultNum = parseInt(inner);
  //         }
  //     }
  //   });
  // });
  $btArr.click(function () {
    let inner = $(this).html();
    switch (inner) {
      case "+":
        operator = inner;
        break;
      case "=":
        $resultObj.html(resultNum);
        operator = undefined;
        resultNum = 0;
        break;
      default: //숫자버튼들
        $resultObj.html(inner);
        if (operator == "+") {
          resultNum += parseInt(inner);
        } else {
          resultNum = parseInt(inner);
        }
    }
  });
  //---------계산기 END--------

  //----CHECKBOX START----
  //let $cb = $('input[type=checkbox]').first();
  //alert($cb.prop('checked')); //prop() checked프로퍼티값 얻기
  //TODO 완성하세요
  let $cbArr = $("div.checkbox input[type=checkbox]");
  let $cbAll = $cbArr.first(); //첫번째 Checkbox
  let $cbOther = $cbArr.not($cbAll); //나머지 Checkbox들
  $cbAll.click(function () {
    let status = $(this).prop("checked");
    $cbOther.prop("checked", status);
  });
  //----CHECKBOX END----

  //----SELECT START----
  var $selectSidoObj = $("div.select>select.sido");
  var $selectSigunguObj = $("div.select>select.sigungu");

  $selectSidoObj.click(function () {
    console.log($(this).val(), "클릭되었습니다");
  });
  $selectSidoObj.change(function () {
    console.log($(this).val(), "변경되었습니다");
    switch ($(this).val()) {
      case "서울시":
        $selectSigunguObj.empty();
        var seoul = "<option>구를 선택하세요</option>";
        seoul += "<option>중구</option>";
        seoul += "<option>강북구</option>";
        seoul += "<option>강동구</option>";
        seoul += "<option>강남구</option>";
        seoul += "<option>강서구</option>";
        $selectSigunguObj.html(seoul);
        $selectSigunguObj.show();
        break;
      case "제주도":
        $selectSigunguObj.empty();
        var jeju = ["시를 선택하세요", "제주시", "서귀포시"];
        for (var i = 0; i < jeju.length; i++) {
          var $opt = $("<option>");
          var txt = jeju[i];
          $opt.append(txt);
          $selectSigunguObj.append($opt);
        }
        $selectSigunguObj.show();
        break;
      default:
        $selectSigunguObj.empty();
        $selectSigunguObj.hide();
    }
  });
  //----SELECT END----

  //----keyboard START---
  //TODO 완성하세요
  //DOM트리에서 div.keyboard의 input객체찾기
  var $inputObj = $("div.keyboard>input[type=text]");
  $inputObj.click(function () {
    console.log("input객체 클릭되었습니다");
  });
  $inputObj.focus(function () {
    console.log("input객체 포커스받았습니다");
    $(this).css("color", "blue");
  });

  //keydown->keypress->keyup
  $inputObj.keyup(function (event) {
    alert("입력된키값:" + event.key);
    if (event.key == "Enter") {
    }
  });
  //----keyboard END---
  //----submit START----
  //전송관련이벤트 : 버튼의 click이벤트 -> 폼의 submit이벤트 ->폼의 submit이벤트 기본처리(전송)됨
  let $formObj = $("div.submit>form");

  //form객체의 후손중 input[type=text]객체찾기
  let $textSubmitObj = $formObj.find("input[type=text]");
  let $btSubmitObj = $formObj.find("button");
  $btSubmitObj.click(function () {
    alert("전송버튼 클릭이벤트가 발생했습니다");
  });
  $formObj.submit(function (event) {
    alert("폼의 서브밋이벤트가 발생했습니다");
    if ($textSubmitObj.val() == "") {
      alert("값을 입력하세요");
      //event.preventDefault(); //기본이벤트처리 금지
      return false; //기본이벤트처리 금지 + 이벤트전파중지
    }
  });
  //----submit END----
  //----a START----
  //이동관련 이벤트 :a객체의 클릭이벤트 -> 클릭이벤트 기본처리(이동)
  //이벤트전파 : 이벤트버블링 - 하위객체에서 발생한 이벤트가 상위객체로 전파
  var $divAObj = $("div.a");
  $divAObj.click(function () {
    $(this).css("background-color", "yellow");
  });
  var $aObj = $("a");
  $aObj.click(function (event) {
    $(this).css("background-color", "green");
    //event.preventDefault(); //기본이벤트처리 금지
    //event.stopPropagation();//이벤트전파 중지
    return false;
  });
  //----a END----
});
