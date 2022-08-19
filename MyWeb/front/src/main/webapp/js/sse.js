window.addEventListener('load',function() {
    let source = new EventSource("../demo_sse.jsp");
    source.onmessage = function(event) { //source.addEventListener('message',function() {});로 써도 됨
        document.getElementById("result").innerHTML += event.data + "<br>";
      };
});