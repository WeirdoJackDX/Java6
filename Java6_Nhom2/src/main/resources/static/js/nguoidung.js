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
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        pre() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        last() {
            this.page = this.count - 1;
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        }
    }

    $scope.initialize()

    $scope.index_of = function (id) {
        return $scope.items.findIndex(a => a.maND == id);
    }

    $scope.banning = function (id) {
        var index = $scope.index_of(id);
        var item = angular.copy($scope.items[index]);
        var url = `/rest/nguoidung`
        $http.put(url, item).then(response => {
            $scope.items[index] = response.data;
        })
    }
})
