var app = angular.module("App");

app.controller("ListCtrl", function ($scope, $http) {

//    $scope.students = [
//        {id: 1, name: "Luana", cpf: "938723492-34", gender: "FEMALE", identifyNumber: "293874238423", militaryDocumentNumber: "8237483274", nacionality: "BRA", birthday: "04/03/2012"},
//        {id: 1, name: "Anderson", cpf: "823782734-45", gender:  "MALE", identifyNumber: "289374829374", militaryDocumentNumber: "2874683745", nacionality: "BRA", birthday: "10/10/2010"}
//    ];

//    $scope.genders = [{desc: "MALE"}, {desc: "FEMALE"}];

    var loadStudents = function () {
        $http.get("http://localhost:8080/WebServices/webapi/student").then(function (response) {
            console.log(response.data);
            $scope.students = response.data;
            /**console.log(response.status);
             console.log(response.statusText);
             console.log(response.config);
             console.log(response.headers);**/
        }), function (response) {
            console.log("erro");
        };
    };

    $scope.orderBy = function (field) {
    	console.log($scope.genders);
        $scope.orderedBy = field;
        $scope.inverse = !$scope.inverse;
    };
});