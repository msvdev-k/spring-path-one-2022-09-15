let SpringApp = angular.module('SpringApp', ['ngRoute']);

const productsContextPath = "http://localhost:8080/app/api/v1/products/"
const cartContextPath = "http://localhost:8080/app/api/v1/carts/"


SpringApp.config(function ($routeProvider, $locationProvider, $sceProvider) {
    $sceProvider.enabled(false);
    $routeProvider
        .when('/', {
            templateUrl: 'products.html',
            controller: 'ProductController'
        })
        .when('/cart', {
            templateUrl: 'cart.html',
            controller: 'CartCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
});




SpringApp.controller('ProductController', function ($scope, $rootScope, $routeParams, $http) {

    $scope.pagination = {
        pageNumber: 1,
        pageSize: 5
    };


    $scope.loadProducts = function() {
        $http({
            url: productsContextPath,
            method: 'GET',
            params: {
                page: $scope.pagination.pageNumber,
                page_size: $scope.pagination.pageSize
            }
        }).then(resp => {
                            console.log(resp.data)
                            $scope.products = resp.data.content;
                            $scope.pagination = {
                                    empty: resp.data.empty,
                                    firstPage: resp.data.first,
                                    lastPage: resp.data.last,
                                    pageNumber: resp.data.number+1,
                                    pageSize: resp.data.size,
                                    totalPages: resp.data.totalPages,
                                    pageSequence: Array(resp.data.totalPages).fill().map((element, index) => index + 1)
                                };
                            console.log($scope.pagination)
                        },
                resp => {
                            console.error(resp);
                        });
    };


    $scope.remove = function (id) {
        $http.delete(productsContextPath + id)
            .then(resp => {
                            $scope.loadProducts();
                },
                resp => {
                    console.error(resp);
                });
    };


    $scope.addToCart = function (product) {

        $http.post(cartContextPath,
                   {
                        productId : product.id,
                        reservedCost : product.cost,
                        quantity : 1
                   }).then(resp => {
                                     console.log(resp)
                                     product.inCartFlag = true;
                                 },
                         resp => {
                                     console.error(resp);
                                 });

    };


    $scope.shiftPage = function (count) {
        $scope.pagination.pageNumber += count;
        $scope.loadProducts();
    };

    $scope.changePage = function (number) {
            $scope.pagination.pageNumber = number;
            $scope.loadProducts();
        };

    $scope.loadProducts();
});




SpringApp.controller('CartCtrl', function ($scope, $rootScope, $routeParams, $location, $http) {


    $scope.loadCartItems = function() {
            $http.get(cartContextPath)
            .then(resp => {
                            $scope.cartItems = resp.data;
                            console.log($scope.cartItems);
                          },
                  resp => {
                            console.error(resp);
                          });
    };

    $scope.loadCartItems();

});