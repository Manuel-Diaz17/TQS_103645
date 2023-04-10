$(document).ready(function(){

    $.ajax({
        url: "http://localhost:8080/api/list",
        headers:{
            "Access-Control-Allow-Origin":"http://localhost",
        },
        statusCode: {
            500: function(xhr){
                console.log("erro 500");
                return;
            },
            403: function(xhr){
                console.log("erro 403");
                return;
            }
        }

    }).then(function(data) {
        if(!$.trim(data)){
            alert("City not available at this moment");
        }
        else{
            data.forEach(country=>{
            $('#countries').append('<option value=\"' + country + '\"> ' + country + '</option>')
        })
        }
        
        
       
    });

    $.ajax({
        url: "http://localhost:8080/api/cache",
        headers:{
            "Access-Control-Allow-Origin":"http://localhost",
        },
        statusCode: {
            500: function(xhr){
                console.log("erro 500");
                return;
            },
            403: function(xhr){
                console.log("erro 403");
                return;
            }
        }

    }).then(function(data) {
        $('#hits').text(data.hits);
        $('#misses').text(data.misses);
        $('#requests').text(data.requests);
        
        
       
    });
});

$(function() {
    $('#countries').change(function(){

        if($('#countries').find(":selected").text() != 'select the country'){
            $('#states').prop('disabled', false);
            $('#states').empty();
            $('#states').append('<option value="default">select the state</option>')
            $.ajax({
                url: "http://localhost:8080/api/list/" + $('#countries').find(":selected").text(),
                headers:{
                    "Access-Control-Allow-Origin":"http://localhost",
                },
                statusCode: {
                    500: function(xhr){
                        console.log("erro 500");
                        return;
                    },
                    403: function(xhr){
                        console.log("erro 403");
                        return;
                    }
                }
            
            }).then(function(data) {
                if(!$.trim(data)){
                    alert("City not available at this moment");
                }
                else{
                    data.forEach(state=>{
                    $('#states').append('<option value=\"' + state + '\"> ' + state + '</option>')
                })
                }

            });
        }

    });
});

$(function() {
    $('#states').change(function(){

        if($('#states').find(":selected").text() != 'select the state'){
            $('#cities').prop('disabled', false);
            $('#cities').empty();
            $('#cities').append('<option value="default">select the city</option>')
            $.ajax({
                url: "http://localhost:8080/api/list/" + $('#countries').find(":selected").text() + "/" + $('#states').find(":selected").text(),
                headers:{
                    "Access-Control-Allow-Origin":"http://localhost",
                },
                statusCode: {
                    500: function(xhr){
                        console.log("erro 500");
                        return;
                    },
                    403: function(xhr){
                        console.log("erro 403");
                        return;
                    }
                }
            
            }).then(function(data) {
                if(!$.trim(data)){
                    alert("City not available at this moment");
                }
                else{
                    data.forEach(city=>{
                    $('#cities').append('<option value=\"' + city + '\"> ' + city + '</option>')
                })
                }
            
            });
        }

    });
});

$(function() {
    $('#getDataButton').click(function(){
        $.ajax({
            url: "http://localhost:8080/api/" + $('#countries').find(":selected").text() + "/" + $('#states').find(":selected").text() + "/" + $('#cities').find(":selected").text(),
            headers:{
                "Access-Control-Allow-Origin":"http://localhost",
            },
            statusCode: {
                500: function(xhr){
                    console.log("erro 500");
                    return;
                },
                403: function(xhr){
                    console.log("erro 403");
                    return;
                }
            }

        }).then(function(data) {
            console.log(data);
            
            $('#tpspan').text(" ");
            $('#prspan').text("  ");
            $('#huspan').text("  ");
            $('#wsspan').text("  ");
            $('#aqiusspan').text (" ");
            $('#aqicnspan').text(" ");
            $('#mainusspan').text(" ");
            $('#maincnspan').text(" ");
            
            $('#tpspan').text(data.weather.temperature + " ºC");
            $('#prspan').text(data.weather.pressure + " hPa");
            $('#huspan').text(data.weather.humidity + " %");
            $('#wsspan').text(data.weather.windSpeed + " m/s");
            $('#aqiusspan').text(data.airQuality.aqiUs);
            $('#aqicnspan').text(data.airQuality.aqiCn);
            $('#mainusspan').text(data.airQuality.mainPolluentUs);
            $('#maincnspan').text(data.airQuality.mainPolluentCn);
        
        });
    });
});


$(function() {
    $('#deleteButton').click(function(){
        $('#countries').prop('selected', false);
        $('#countries option[value="default"]').prop('selected', true);

        $('#states').prop('selected', false);
        $('#states').prop('disabled', true);
        $('#states option[value="default"]').prop('selected', true);

        $('#cities').prop('selected', false);
        $('#cities').prop('disabled', true);
        $('#cities option[value="default"]').prop('selected', true);

        $('#tpspan').text(" ");
        $('#prspan').text("  ");
        $('#huspan').text("  ");
        $('#wsspan').text("  ");
        $('#aqiusspan').text (" ");
        $('#aqicnspan').text(" ");
        $('#mainusspan').text(" ");
        $('#maincnspan').text(" ");

    });
});

$(function() {
    $('#updateButton').click(function(){
        $.ajax({
            url: "http://localhost:8080/api/cache",
            headers:{
                "Access-Control-Allow-Origin":"http://localhost",
            },
            statusCode: {
                500: function(xhr){
                    console.log("erro 500");
                    return;
                },
                403: function(xhr){
                    console.log("erro 403");
                    return;
                }
            }

        }).then(function(data) {
            $('#hits').text(data.hits);
            $('#misses').text(data.misses);
            $('#requests').text(data.requests);


        
        });
    });
});