const app = angular.module('sp-app', [])
app.controller('sp-ctrl', function ($scope, $http) {
    $scope.db = [];
    $scope.form = {};
    $scope.post = true
    $scope.put = false

    $http.get("/rest/sanpham").then(response => {
        $scope.db = response.data;
        console.log($scope.db.sanpham);
    })
    $scope.reset = function () {
        $scope.form = null;
    }
    // phân trang
    $scope.pager = {
        page: 0,
        size: 7,
        get items() {
            var start = this.page * this.size;
            return $scope.db.sanpham.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.db.sanpham.length / this.size);
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

    $scope.index_of = function (id) {
        return $scope.db.sanpham.findIndex(a => a.maSP == id);
    }
    $scope.edit = function (id) {
        $scope.post = false;
        $scope.put = true;
        var url = `/rest/sanpham/${id}`;
        $http.get(url).then(response => {
            $scope.form = response.data;
        }).catch(err => {
            console.log("Error", err)
        })
    }
    $scope.save = function () {
        var index = $scope.db.loai.findIndex(a => a.maLoaiBanh === $scope.form.loaiBanh.maLoaiBanh)
        var item = {
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
        var url = `/rest/sanpham/save`;
        $http.post(url, item).then(response => {
            $scope.db.sanpham.push(response.data);
            alert("Thêm sản phẩm thành công")
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
        var url = `/rest/sanpham/${$scope.form.maSP}`;
        $http.put(url, item).then(response => {
            var index = $scope.db.sanpham.findIndex(a => a.maSP === $scope.form.maSP);
            console.log($scope.db[index])
            $scope.db.sanpham[index] = response.data;
            alert("Cập nhật sản phẩm thành công")
        }).catch(err => {
            alert("Lỗi cập nhật sản phẩm");
            console.log("Error", error);
        });
    }

    $scope.updateAvailability = function (id) {
        var index = $scope.index_of(id);
        var item = angular.copy($scope.db.sanpham[index]);
        if (item.isAvailable === true) {
            item.isAvailable = false;
        } else {
            item.isAvailable = true;
        }
        var url = `/rest/sanpham/${id}`;
        $http.put(url, item).then(response => {
            $scope.db.sanpham[index] = response.data;
        })
    }

    /*  $scope.setFile = function (element) {
         $scope.$apply(function ($scope) {
           
             $scope.theFile = element.files[0];
         });
     }; */

    $scope.imageChanged = function (files) {
        console.log($scope.form);
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/img', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(response => {
            $scope.form.anh = response.data.name
        }).catch(err => {
            alert("Loi up anh");
            console.log("Error", err)
        })
    }

})