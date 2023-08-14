const app = angular.module('invoice-app', []);
app.controller('invoice-ctrl', function ($scope, $http) {
    $http.get("/rest/invoice").then(response => {
        $scope.db = response.data;
    })

    $scope.pager = {
        page: 0,
        size: 10,
        get db() {
            var start = this.page * this.size;
            return $scope.db.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.db.length / this.size);
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
})
