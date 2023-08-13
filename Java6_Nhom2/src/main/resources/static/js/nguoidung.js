const app = angular.module('nguoidung-app', [])
app.controller('nguoidung-ctrl', function ($scope, $http) {
    $scope.items = []

    $scope.initialize = function () {
        $http.get("/rest/nguoidung").then(response => {
            $scope.items = response.data;
            console.log($scope.items);
        })
    }

    $scope.pager = {
        page: 0,
        size: 5,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count(){
            return Math.ceil(1.0 * $scope.items.length/this.size);
        },
        first(){
            this.page = 0;
        },
        pre(){
            this.page--;
            if(this.page < 0){
                this.last();
            }
        },
        last(){
            this.page = this.count -1;
        },
        next(){
            this.page ++;
            if(this.page>= this.count){
                this.first();
            }
        }
    }
    
    $scope.initialize()
})
