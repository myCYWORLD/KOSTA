$(function () {
    let $zipAddr = $('table tr'); //table > tbody >tr
    $zipAddr.click (function() {
        let $addrNum = ($(this).children('td[name=code]').html().trim()); //클릭한 곳에 자식요소인 태그명이 center인걸 html메소드로 화면에 띄워줌
        let $addrStr= ($(this).children('td[name=addrs]').html().trim()); //클릭한 곳에 자식요소인 태그명이 center인걸 html메소드로 화면에 띄워줌
        $("form input.zipcode", parent.opener.document).attr('value', $addrNum );
        $("form #addr1", parent.opener.document).attr('value', $addrStr);
		window.self.close();
	});
});

