// For a pie chart
var chartDataStr = decodeHTML(chartData);
var chartJSONArray = JSON.parse(chartDataStr);

var arrayLength = chartJSONArray.length;
var numericData = [];
var labelData = [];

for (var i=0; i < arrayLength; i++) {
	numericData[i] = chartJSONArray[i].value;
	labelData[i] = chartJSONArray[i].label;
}

var ctx = document.getElementById('myPieChart').getContext('2d');
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'pie',

    // The data for our data set
    data: {
        labels: labelData,
        datasets: [{
            label: labelData,
            backgroundColor: ["#4285F4", "#DB4437", "#F4B400"],
           
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
    	title : {
    		display : true,
    		text : 'Project Statuses'
    	}
    }
});

function decodeHTML(html){
	var txt = document.createElement("textarea");
	txt.innerHTML =html;
	return txt.value;

}