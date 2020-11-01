var botaoPronto = document.querySelector("#botao-pronto");
var xhr = new XMLHttpRequest();

botaoPronto.addEventListener("click", function (event) {
    event.preventDefault();

    var form = document.querySelector("#form-aula");
    var evento = obtemAula(form);

    xhr.open("POST", "http://192.168.15.136:8081/alteraaula", true)
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.withCredentials = true;

    xhr.addEventListener("load", function () {

        if(xhr.status != 200){
            alert("Falha");
        }else{
            form.aula.value = "";
            form.professor.value = "";
            form.diaSemana.value = "";
            form.hora.value = "";
            alert("Alterado!!");
        }
        
    });
    xhr.send(JSON.stringify(evento));
});

function obtemAula(form) {

    var evento = {
        aula: form.aula.value,
        professor: form.professor.value,
        diaSemana: form.diaSemana.value,
        hora: form.hora.value
    }
    return evento;
}