angular.module('market-front').controller('cartController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/api/v1';

    $scope.loadCart = function () {
        $http.post('http://localhost:5555/cart/api/v1/carts', $localStorage.cartName)
            .then(function (response) {
                $scope.Cart = response.data;
            });
    };

    $scope.clearCart = function () {
        $http.post('http://localhost:5555/cart/api/v1/carts/clear', $localStorage.cartName)
            .then(function (response) {
                $scope.loadCart();
            });
    };
    $scope.addToCart = function (productId) {
       $http.post('http://localhost:5555/cart/api/v1/carts/add/' + productId, $localStorage.cartName)
           .then(function (response) {
                $scope.loadCart();
                });
    };
    $scope.removeFromCart = function (productId) {
                $http.post('http://localhost:5555/cart/api/v1/carts/remove/' + productId, $localStorage.cartName)
                    .then(function (response) {
                        $scope.loadCart();
                    });
    };

    $scope.loadCart();

});