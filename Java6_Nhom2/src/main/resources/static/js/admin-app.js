app = angular.module("admin-app",["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("/sanpham",{
		templateUrl: "/templates/quanli/product/index.html"
		
	})
	.otherwise({
		template:"<h1 class=''text-center>FPT Polytechnic cần Thơ</h1>"
	});
});