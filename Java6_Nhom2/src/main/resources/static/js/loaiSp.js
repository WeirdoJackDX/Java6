const app = angular.module('loaiSp-app', []);
app.controller('loaiSp-ctrl', function ($scope, $http) {
    $scope.post = true
    $scope.put = false
    $scope.delete = false
    $scope.form = {}
    
    $scope.initialize = function () {
    $http.get("/rest/loaiSp").then(response => {
        $scope.db = response.data;
        console.log($scope.db);
    })}
    $scope.initialize()
    $scope.pager = {
        page: 0,
        size: 5,
        get db() {
            var start = this.page * this.size;
            return $scope.db.slice(start, start + this.size);
        },
        get count(){
            return Math.ceil(1.0 * $scope.db.length/this.size);
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
    $scope.index_of = function (id) {
        return $scope.db.findIndex(a => a.maLoaiBanh == id);
    }

    $scope.edit = function (id) {
        var url = `/rest/loaiSp/${id}`;
        $http.get(url).then(response => {
            $scope.form = response.data;
            $scope.post = false;
            $scope.put = true;
        }).catch(err => {
            console.log("Error", err)
        })
    }
    $scope.reset = function(){
        $scope.form = null;
        $scope.post = true;
            $scope.put = false;
    }
    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `/rest/loaiSp/${$scope.form.maLoaiBanh}`;
        $http.put(url, item).then(response => {
            var index = $scope.db.findIndex(a => a.maLoaiBanh === $scope.form.maLoaiBanh);
            $scope.db[index] = response.data;
        })
    }

    $scope.save = function () {
        var item = angular.copy($scope.form);
        var url = `/rest/loaiSp`;
        $http.post(url,item).then(response => {
            $scope.db.push(response.data);
        })
    }
})