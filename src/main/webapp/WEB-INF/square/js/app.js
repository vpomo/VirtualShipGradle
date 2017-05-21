function createSquare() {
    var square = {};
    square.rows = [];
    
    for(var i = 0; i < 96; i++) {
        var row = {};
        row.cells = [];
        
        for(var j = 0; j < 96; j++) {
            var cell = {};
            cell.isCovered = true;
	    cell.content = "empty";
            row.cells.push(cell);
        }
        
        square.rows.push(row);
    }
   
    return square;
}




function squareController($scope) {
    $scope.square = createSquare();
    $scope.uncovercell = function(cell) {
        cell.isCovered = false;
    };
}
