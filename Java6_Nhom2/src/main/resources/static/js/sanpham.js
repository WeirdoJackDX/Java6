const app = angular.module('sp-app', [])
app.controller('sp-ctrl', function ($scope, $http) {
    $http.get("/rest/sanpham").then(response => {
        $scope.db = response.data;
        console.log($scope.db);
    })

    $scope.index_of = function (id) {
        return $scope.db.findIndex(a => a.maSP == id);
    }
    $scope.edit = function (id) {
        var url = `/rest/sanpham/${id}`;
        $http.get(url).then(response => {
            $scope.form = response.data;
            console.log($scope.form);
        }).catch(err => {
            console.log("Error", err)
        })
    }
    $scope.update = function () {
        var index = $scope.db.loai.findIndex(a => a.maLoaiBanh === $scope.form.loaiBanh.maLoaiBanh)
        var item = {
            "maSP": $scope.form.maSP,
            "tenSP": $scope.form.tenSP,
            "soLuong": $scope.form.soLuong,
            "anh": $scope.form.anh,
            "giaNhap": $scope.form.giaNhap,
            "giaBan": $scope.form.giaBan,
            "ghiChu": $scope.form.ghiChu,
            "isAvailable": $scope.form.isAvailable,
            "loaiBanh": $scope.db.loai[index]
        }
        console.log(item)
        // var url = `/rest/sanpham/${$scope.form.maSP}`;
        // $http.put(url, item).then(response => {
        //     var index = $scope.db.sanpham.findIndex(a => a.maSP === $scope.form.maSP);
        //     $scope.db[index] = response.data;
        // })
    }
    $scope.setFile = function (element) {
        $scope.$apply(function ($scope) {
            $scope.theFile = element.files[0];
        });
    };
})