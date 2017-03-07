stompClient = null;
var findFood = function () {
    this.connect = function () {
        var socket = new SockJS('/find-food');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            //                    setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/food', function (fRes) {
                console.log(JSON.parse(fRes.body).response);
                var response = JSON.parse(fRes.body);
                $("#result").html(response.response + ", been adviced By " + response.userName);
            });
        });
    };

    this.sendMessage = function (foodName) {
        console.log(foodName);
        var food = {};
        food.userName = 'donnylie';
        food.foodName = foodName;
        stompClient.send("/app/find-food", {}, JSON.stringify(food));
    };

    return this;

};

