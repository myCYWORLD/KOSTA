$(function () {
  let url = `${backPath}/loginstatus`;
  //let url='/back/loginstatus';
  let method = 'get';
  $.ajax({
    url: url,
    method: method,
    success:function(jsonObj){
      let $navObj = $('header>nav');
      let $navObjHtml = '';
      if(jsonObj.status == 1){//로그인된경우
        $navObjHtml += '<a href="vieworder.html">주문내역</a>';
        $navObjHtml += `<a href="${backPath}/logout">로그아웃</a>`;
      }else{ //로그인 안된경우
        $navObjHtml += '<a href="login.html">로그인</a>';
        $navObjHtml += '<a href="signup.html">가입</a>';        
      }
      $navObjHtml += '<a href="productlist.html">상품</a>';
      $navObjHtml += '<a href="viewcart.html">장바구니</a>';
      $navObj.html($navObjHtml);
    },
    error: function(jqXHR){
      alert('오류:' + jqXHR.status);
    }
    
  });

  //메뉴객체들 찾기
  let $menuObj = $('header>nav>a');
  //section의 첫번째자식요소인 article객체 찾기
  let $articleObj = $('section article:first');

  //---메뉴클릭 START---
  //메뉴가 클릭되면 article영역의 innerHTML로 로드
  // $menuObj.click(function(){
   $('header>nav').on('click', 'a', function(){
    let url = $(this).attr('href');
    let title = $(this).html();
    
    $articleObj.load(url, function (responseText,
      statusText,
      xhr) {
      if (statusText != 'success') {
        if (xhr.status == 404) {
          let msg = title + ' 자원을 찾을 수 없습니다';
          alert(msg);
        }
      }
      if(url == `${backPath}/logout`){
        location.href='css_js_layout.html';
      }
    });
    
    return false;
  });
  //---메뉴클릭 END---

});