var reviewsApp = angular.module("reviewsApp", [ 'ui.bootstrap', 'ngRoute', 'ngResource' ]);

reviewsApp.config(function($routeProvider) {
	$routeProvider.when('/reviews/library', {
		controller : 'LibraryCtrl',
		templateUrl : '/views/library.html'
	}).when('/reviews/newbook', {
		controller : 'NewBookCtrl',
		templateUrl : '/views/newbook.html'	
	}).when('/reviews/books/:bookId', {
		controller : 'BookCtrl',
		templateUrl : '/views/book.html'
	}).otherwise({
		controller : 'HomeCtrl',
		templateUrl: '/views/home.html'
    });
});

reviewsApp.controller("HomeCtrl", [ '$scope', function($scope) {			
} ]);

reviewsApp.controller("LibraryCtrl", [ '$scope','libservice', function($scope, libservice) {
	libservice.getBookList( $scope );		
} ]);

reviewsApp.controller("BookCtrl", [ '$scope','libservice', '$routeParams', function($scope, libservice, $routeParams) {	
	libservice.getBook($routeParams.bookId, $scope);
	
	$scope.createNewComment = function(){
		var newcomment = { 'bookId': $scope.book.id, 'comment':$scope.comment };
		
		// Call LibService to create a new book comment
		//
		libservice.addComment ( newcomment, $scope );
					
		// Push new comment to book comment list
		//
		$scope.book.commentList.push( newcomment );
		
		// Reset fields values
		//
		$scope.comment='';
	};		

} ]);

reviewsApp.controller("NewBookCtrl", [ '$scope','libservice', function($scope, libservice) {				
	
	$scope.createNewBook = function(){
		var newbook = { 'name' : $scope.name, 'author': $scope.author, 'publicationYear' : $scope.publicationYear };

		// Call RESTFull web service to create a new book 
		//
		libservice.createBook ( newbook, $scope );
					
		// Reset fields values
		//
		$scope.name='';
		$scope.author='';
		$scope.publicationYear='';
	};		
} ]);

reviewsApp.factory( 'libservice', [ '$resource', function( $resource ){
	return new Book( $resource );
}] );

/* Class Book declaration */
function Book( resource ) {
	
	this.resource = resource; 
	
	this.getBookList = function( scope ) {
		//
		// Send GET request to RESTFull web service
		//
		var Books = resource('/reviews/library');
		Books.query(function(booklist){
			scope.booklist = booklist;
		});
	}
	
	this.getBook = function ( id, scope ) {
		//
		// Send GET request to RESTFull web service
		//
		var Book = resource('/reviews/books/:bookId', {bookId:'@bookId'});
		Book.get( {bookId:id}, function(book){
			scope.book = book;
		})
	}
	
	this.createBook = function ( book, scope ) {
		// 
		// Send POST request to RESTFull web service (save method)
		//
		var Book = resource('/reviews/newbook');		
		Book.save(book, function(response){
			//scope.message = response.data;
		});		
	}
	
	this.addComment = function ( comment, scope ) {
		// 
		// Send POST request to RESTFull web service (save method)
		//
		var Comment = resource('/reviews/newcomment');
		Comment.save(comment, function(response){
			//scope.message  = response.message;
		});		
	}
	
}
