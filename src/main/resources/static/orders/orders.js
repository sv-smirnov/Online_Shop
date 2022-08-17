angular.module('market-front').controller('orderController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadOrders = function () {
        $http.get(contextPath + '/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    };
    $scope.loadCart = function () {
            $http.post('http://localhost:8189/app/api/v1/carts', $localStorage.cartName)
                .then(function (response) {
                    $scope.Cart = response.data;
                });}

    $scope.checkOut = function () {
        $http({
            url: contextPath + '/orders/' + $localStorage.cartName,
            method: 'POST',
            data: {orderDetailsDto: $scope.orderDetailsDto}
        }).then(function (response) {
                $scope.loadCart();
                $scope.orderDetailsDto = null
            });
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