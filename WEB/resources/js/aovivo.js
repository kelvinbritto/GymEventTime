var xhr1 = new XMLHttpRequest();

var xhr2 = new XMLHttpRequest();

var url = "http:192.168.15.141:8081";

function aovivo() {

    xhr1.open("GET", url + "/aovivo");
    xhr1.withCredentials = true;

    xhr1.addEventListener("load", function () {

        var resposta = xhr1.responseText;
        var aula = JSON.parse(resposta);

            aulaOriginal = aula;

            document.getElementById('aulaatual').innerHTML= aula.aula;
            document.getElementById('professor').innerHTML= aula.professor;
            trocaImagem(aula.id);

        proximas();


    });

    xhr1.send();

    setTimeout('aovivo()',5000);

}

aovivo();

function trocaImagem(id) {
    var imagem = document.querySelector('#logoaula');
    imagem.classList.add("fadeOut");
    setTimeout(function() {
        imagem.src = 'resources/img/modalidades/' + id + '.png';
        imagem.classList.add("fadeIn");
    },500);
}



function proximas() {


    xhr2.open("GET", url + "/proximas");
    xhr2.withCredentials = true;

    xhr2.addEventListener("load", function () {

        var resposta = xhr2.responseText;
        var aulas = JSON.parse(resposta);


    var tabela = document.querySelector("#tabela-proximas");
    tabela.innerHTML = "";


    var tabela = document.querySelector("#tabela-proximas");
    var tr1 = document.createElement("tr");
    tr1.classList.add("borda");

    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");

    td1.textContent = "HORÁRIO";
    td2.textContent = "AULA";
    td3.textContent = "STATUS";

    tr1.appendChild(td1);
    tr1.appendChild(td2);
    tr1.appendChild(td3);

    tabela.appendChild(tr1);


        aulas.forEach(adicionaProxima);

    });

    xhr2.send();

}


function adicionaProxima(aula)  {

    var tabela = document.querySelector("#tabela-proximas");
    var tr = document.createElement("tr");
    var tdAula = document.createElement("td");
    var tdHora = document.createElement("td");
    var tdStatus = document.createElement("td");

    tdAula.textContent = aula.aula;
    tdHora.textContent = aula.hora + "h";
    tdStatus.textContent = aula.status;

    tr.appendChild(tdHora);
    tr.appendChild(tdAula);
    tr.appendChild(tdStatus);

    if(aula.status == "Ao Vivo") {
        tr.classList.add("aovivo");
    }

    if(aula.status == "Terminou") {
        tr.classList.add("terminou");
    }

    if(aula.status == "A seguir") {
        tr.classList.add("proxima");
    }

    tr.classList.add("borda");

    tabela.appendChild(tr);

}
