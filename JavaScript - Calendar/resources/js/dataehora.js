var ativo = true;

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

    if(weekday != 5){
        document.getElementById('date').innerHTML=days[weekday] + " - " + day+"/"+month+"/"+year;
    } else{
        document.getElementById('date').innerHTML=day+"/"+month+"/"+year;

        if(ativo){
            document.getElementById('sexta').innerHTML=days[weekday];
            ativo = false;
        } else{
            document.getElementById('sexta').innerHTML="";
            ativo = true;
        }
    }


    setTimeout('time()',200);
}

time();



