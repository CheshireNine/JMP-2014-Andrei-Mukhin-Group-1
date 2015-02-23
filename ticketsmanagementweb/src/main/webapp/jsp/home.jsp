<!doctype html>
<html lang="en" ng-app="ticketingApp">
<head>
    <meta charset="utf-8">
    <title>Address Book</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="lib/angular.js"></script>
    <script src="lib/angular-route.js"></script>
    <script src="js/app.js"></script>
    <script src="js/controllers.js"></script>
    <script src="js/ticketsController.js"></script>
    <script src="js/filmsController.js"></script>
    <script src="js/filmController.js"></script>
</head>
<body>
    <div class="container">
        <!-- Header -->
        <div class="page-header">
            <img src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRDyTcdFKv2ils8mOMXnFhZ1P-cJMPRnEHPFyttc8ZByU58lh0ysQ" style="width:200px;padding-top:10px;" />
        </div>
        <!-- Menu Page -->
        <div class="col-md-2">
            <div class="panel panel-default">
			    <div class="panel-heading">
			        <h3 class="panel-title">Menu</h3>
			    </div>
			    <div class="panel-body">
			        <ul class="nav nav-pills nav-stacked">
			            <li>
			                <a href="#/home" class="action">View Films</a>
			            </li>
			        </ul>
			    </div>
			</div>
        </div>
        <!-- Body Page -->
        <div class="col-md-10 container">
            <div ng-view></div>
        </div>
    </div>
    <!-- Footer Page -->
    <div class="col-md-12 footer">
        <p class="col-md-12 copyright">&#0169 Epam. All rights reserved</p>
    </div>

</body>
</html>