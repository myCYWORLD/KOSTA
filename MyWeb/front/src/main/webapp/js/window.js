function displayTime(element){
    var today = new Date();
    var month = today.getMonth()+1;
    var date = today.getDate();

    var now ='<h1>';
    now += today.getFullYear();
    now += '-';
    now += month<10? '0'+month: month;
    now += '-';
    now += date<10? '0'+date: date;
    now += ' ';
    now +=       today.getHours() + 
        ':' + today.getMinutes()+ 
        ':' + today.getSeconds();
    now += '</h1>';
    element.innerHTML = now;
}

window.addEventListener('load', function(){
    var windowID;
    var btClose = this.document.querySelector('button.close');

    //----새창띄우기 버튼 클릭 START ----
    var btOpen = 
    this.document.querySelector('button.open');
    btOpen.addEventListener('click', function(){
    var url = 'js_popup.html';'http://www.google.com';
    var target = 'first';
    var features = 'width=300px, height=300px';
    windowID = window.open(url, target, features);
    this.style.display = 'none'; //새 창 띄우기 버튼이 사라진다
    btClose.style.displat = 'inLine'; //새 창 닫기 버튼이 보여진다
    });
    //----새창띄우기 버튼 클릭 END ----

    //----새창닫기 버튼 클릭 START----
   
    btClose.addEventListener('click',function(){
    windowID.close();
    this.style.display = 'none'; //새 창 닫기 버튼 사라진다
    btOpen.style.display = 'inline' //새 창 띄우기 버튼 눌러진다.
    });
    //----새창닫기 버튼 클릭 END----
    
    //----5초후 버튼 클릭 START----
    var btTimeout = document.querySelector("button.timeout");
    btTimeout.addEventListener('click',function(){
    window.setTimeout(function(){
        alert('5초가 지났습니다');
    }, 5*1000);
    });
    //----5초후 버튼 클릭 END----

    //----현재시간값 출력 START----
    var divObj = document.querySelector('div');
    
    displayTime(divObj);

    //----현재시간값 출력 END----

    //----1초간격 버튼 클릭 START----
    var intervalID;
    var btInterval = document.querySelector('button.interval');
    btInterval.addEventListener('click', function(){
         window.setInterval(displayTime, 1000, divObj); //displayTime() XX
    //   window.setInterval(function(){
    //     displayTime(divObj);
    //   }, 1000);
    intervalID = window.setInterval(displayTime, 1000, divObj);
    });
    //----1초간격 버튼 클릭 END----

    //----1초간격 버튼 해제 START----
    var btClear = this.document.querySelector('button.clear');
    btClear.addEventListener('click', function(){
        window.clearInterval(interverID);
    });
    //----1초간격 버튼 해제 END----
    
});