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

    var days = ["DOM", "SEG", "TER", "QUA", "QUI", "SEXTOU", "SAB"];
    
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

    setTimeout('time()',200);
}

function data() {

    today=new Date();

    day = today.getDate();
    month = today.getMonth()+1;
    year = today.getFullYear();

    weekday = today.getDay();

    var days = ["DOM", "SEG", "TER", "QUA", "QUI", "SEXTOU", "SAB"];

    if(weekday != 5) {
        document.getElementById('date').innerHTML=days[weekday] + " - " + day+"/"+month+"/"+year;
    } else {

        document.getElementById('date').innerHTML=day+"/"+month+"/"+year;
        document.getElementById('sexta').innerHTML=days[weekday];

        if(ativo){
            document.getElementById('sexta').classList.remove("text-dark", "text-gradient");
            ativo = false;
        } else{
            document.getElementById('sexta').classList.add("text-dark", "text-gradient");
            ativo = true;
        }

        setTimeout('data()',200);
    }

    setTimeout('data()',50000);
    
    
}

time();
data();



