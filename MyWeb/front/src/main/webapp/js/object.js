var customer = {
    id: 'id1',
    name: '황초연',
    info: function(){
        console.log(this.id, this.name); //this 현재 객체를 알리는 예약어 (javascript에서는 생략 못함)
    }
};

customer.id = 'id9';
customer.info();

var customerArr = [];  //push를 이용해서 
// customerArr[0] = customer;

customerArr.push({
    id: 'id1',
    name: '한미래',
},{
    id: 'id2',
    name: '조건우',
}, {
    id: 'id3',
    name: '전승현',
}
);

console.log(customerArr);
console.log(customerArr[2]);

