function time() {

    today=new Date();


    hour=today.getHours();
    min=today.getMinutes();
    sec=today.getUTCSeconds();

    day = today.getDate();
    month = today.getMonth()+1;
    year = today.getFullYear();

    weekday = today.getDay();

    var days = ["DOM", "SEG", "TER", "QUA", "QUI", "SEXTOU!", "SAB"];
    
    if(day < 10) {
        day = "0" + day;
    }
    
    if(month < 10) {
        month = "0" + month;
    }

    if(hour < 10) {
        hour = "0" + hour;
    }

    if(min < 10) {
        min = "0" + min;
    }

    if(sec < 10) {
        sec = "0" + sec;
    }



    document.getElementById('time').innerHTML=hour+":"+min+":"+sec;

    document.getElementById('date').innerHTML=days[weekday] + " - " + day+"/"+month+"/"+year;


    setTimeout('time()',200);
}

time();

function recarrega() {
    document.location.reload(true);
    
    setTimeout('recarrega()',60000);
}



