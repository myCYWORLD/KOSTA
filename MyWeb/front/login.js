window.addEventListener('load', function() {
    //----아이디 저장 체크박스 객체찾기
    let cb = document.querySelector('input[type=checkbox]');

    //----로그인 버튼 객체 찾기
    let btLogin = this.document.querySelector('button[type=submit]');

    //----아이디 입력객체 찾기
    let inputId = this.document.querySelector('input[type=text]');
    
    //localStorage에 inValue이름의 item이 있다면
    //아이디 입력 객체의 value로 정하기
    let idValue = this.localStorage.getItem('idValue');
    if(idValue != null && idValue != '') {//문자열 비교할때 equal자 안씀
        inputId.value = idValue;
    } 
    //----로그인 버튼 클릭 START
    //아이디 저장 체크박스가 체크되었다면
    //사용자가 입력해줄 아이디 값을 localstorage에 저장 (이름: idValue)
    //아이디 저장 체크박스가 체크인되었다면
    // localstorage의 idValue이름의 item을 삭제

    btLogin.addEventListener('click', function() {
        if(cb.checked) {
            localStorage.setItem('idValue', inputId.value);
        }else{
            localStorage.removeItem('idValue');
        }
    });
    //----로그인 버튼 클릭 END
});