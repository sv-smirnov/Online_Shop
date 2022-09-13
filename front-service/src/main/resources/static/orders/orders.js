angular.module('market-front').controller('orderController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/order/api/v1';

    $scope.loadOrders = function () {
        $http.get(contextPath + '/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    };
    $scope.loadCart = function () {
            $http.post('http://localhost:5555/cart/api/v1/carts', $localStorage.cartName)
                .then(function (response) {
                    $scope.Cart = response.data;
                });}

    $scope.checkOut = function () {
    if ($scope.orderDetailsDto == null)
    {alert("Заполните детали заказа");}
    else {
        $http.post(contextPath + '/orders/' + $localStorage.cartName, $scope.orderDetailsDto
//            url: contextPath + '/orders/' + $localStorage.cartName,
//            method: 'POST',
//            data: {$scope.orderDetailsDto}
        ).then(function (response) {
                $scope.loadCart();
                $scope.orderDetailsDto = null;
                alert($scope.orderDetailsDto.address);
            });
        }
    };

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    };

    $rootScope.isUserLoggedIn = function () {
            if ($localStorage.springWebUser) {
                return true;
            } else {
                return false;
            }
        };
    $scope.loadOrders();
    $scope.loadCart();



});