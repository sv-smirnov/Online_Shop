angular.module('market-front').controller('qiwiController', function ($scope, $http, $location, $localStorage, $routeParams) {

    $scope.loadOrder = function () {
        $http({
            url: 'http://localhost:5555/order/api/v1/orders/' + $routeParams.orderId,
            method: 'GET'
        }).then(function (response) {
            $scope.order = response.data;
        });
    };

    $scope.renderPaymentButtons = function () {
        $http({
            url: 'http://localhost:5555/order/api/v1/qiwi/create/' + $scope.order.id,
            method: 'GET'
        }).then(function (response) {
            $scope.billid = response.data.billid;
            params = {
                payUrl: response.data.responseUrl,
                customFields: {
                    themeCode:"Maksym-GeLno-j5g0"
                }
            }
            QiwiCheckout.openInvoice(params)
                .then(function (onFullField){
                        $http.post('http://localhost:5555/order/api/v1/qiwi/capture/' + $scope.billid)
                            .then(function (response) {
                            $scope.status = response.data.status;
                            });
                     // Заглушка. ToDo при корректной отправке платежа, необходимо отправить запрос на /capture/{billId} (billid уже задан в scope)
                })
                .then(function (onRejection){
                    $location.path("http://localhost:3000/front")
                });

        });
    };

    $scope.loadOrder();
});