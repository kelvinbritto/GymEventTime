var ativo = true;

function time() {

    today=new Date();

    hour = colocaZero   (today.getHours());
    min = colocaZero    (today.getMinutes());
    sec = colocaZero    (today.getUTCSeconds());



    document.getElementById('time').innerHTML=hour+":"+min+":"+sec;

    setTimeout('time()',200);
}

function data() {

    today=new Date();

    day = colocaZero(today.getDate());
    month = colocaZero(today.getMonth()+1);
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


function colocaZero(numero) {
    if(numero < 10) {
        x = "0" + numero;
        return x;
    }
    return numero;
}


function progress() {
    today=new Date();
    horaAtual = today.getHours();
    horaInicio = 7;

    for (var i = horaInicio; i < horaAtual; i++) {
        progressoSuccess(i);
    }

    sec = today.getUTCSeconds();
    min = today.getMinutes();

    u = min * 60;
    y = u + sec;
    x = (y * 7) / 3600;

    document.getElementById('i' + horaAtual).style.width = x + "%";

    if(x < 7) {
        document.getElementById('i' + horaAtual).classList.add("bg-info");
    } else {
	document.getElementById('i' + horaAtual).classList.remove("bg-info");
}

    setTimeout('progress()',1000);
}


function progressoSuccess(i) {
    document.getElementById('i' + i).style.width = 7 + "%";
}

function progresso() {
    today=new Date();

    horaAtual = today.getHours();
    horaInicio = 7;
    aulasPassadas = horaAtual - horaInicio;

    horas = aulasPassadas * 60;
    min = horas + today.getMinutes();


    i = (min * 100) / 840;
    document.getElementById('progresso').style.width = i + "%";

}

time();
data();
progress();

