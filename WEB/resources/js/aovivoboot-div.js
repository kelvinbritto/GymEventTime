var xhr1 = new XMLHttpRequest();

var xhr2 = new XMLHttpRequest();

var url = "http://192.168.15.136:8081";

function aovivo() {

    xhr1.open("GET", url + "/aovivo");
    xhr1.withCredentials = true;

    xhr1.addEventListener("load", function () {

        var resposta = xhr1.responseText;
        var aula = JSON.parse(resposta);

        aulaAoVivo(aula);
        
        setTimeout('aovivo()',5000);

    });
    
    xhr1.send();

}

function aulaAoVivo(aula) {

    trocaImagem(aula.id)
    
    document.querySelector("#modalidade-titulo").textContent = aula.aula;
    document.querySelector("#modalidade-prof").textContent = aula.professor;

    if(aula.status == "Ao Vivo") {
        document.querySelector("#acontecendo").textContent = "ACONTECENDO AGORA";
    } else if(aula.status == "A Seguir") {
        document.querySelector("#acontecendo").textContent = "A SEGUIR";
    } else{
        document.querySelector("#acontecendo").textContent = aula.aula;
        document.querySelector("#modalidade-titulo").textContent = "";
    }  

}

function proximas() {

    xhr2.open("GET", url + "/proximas");
    xhr2.withCredentials = true;

    xhr2.addEventListener("load", function () {

        var resposta = xhr2.responseText;
        var aulas = JSON.parse(resposta);

        preenchertabela(aulas);


        setTimeout('proximas()',5000);

    });
    
    xhr2.send();

}

proximas();
aovivo();

function preenchertabela(aulas) {

    var tabela = document.querySelector("#tabela-proximas");
    tabela.innerHTML = "";

    primeiraLinha();

    aulas.forEach(criaHeader);
    
}

function criaHeader(aula) {

    var tabela = document.querySelector("#tabela-proximas");

    divLinha = criaLinha();

    divColuna1 = divColuna = document.createElement("div");
    divColuna1.classList.add("col-2", "border", "border-end-0", "border-dark", "text-center", "fs-4", "text-white");
    divColuna1.textContent = aula.hora + "h";

    divColuna2 = document.createElement("div");
    divColuna2.classList.add("col-5", "border", "border-end-0", "border-dark", "text-center", "fs-4", "text-white");
    divColuna2.textContent = aula.aula;

    divColuna3 = document.createElement("div");
    divColuna3.classList.add("col-3", "border", "border-end-0", "border-dark", "text-center", "fs-4", "text-white");
    var stringExemplo = aula.professor;
    var resultado = stringExemplo.split(" ");
    divColuna3.textContent =  resultado[0];

    divColuna4 = document.createElement("div");
    divColuna4.classList.add("col-2", "border", "border-dark", "text-center", "fs-4", "text-white");
    divColuna4.textContent = aula.status;

    if(aula.status == "Terminou") {
        divLinha.classList.add("fw-bolder", "text-white", "bg-success");
    }
    
    if(aula.status == "Ao Vivo") {
        divLinha.classList.add("fw-bolder","text-white", "bg-danger");
    }

    if(aula.status == "A Seguir") {
        divLinha.classList.add("fw-bolder", "text-white", "bg-warning");
    }

    divLinha.appendChild(divColuna1);
    divLinha.appendChild(divColuna2);
    divLinha.appendChild(divColuna3);
    divLinha.appendChild(divColuna4);

    tabela.appendChild(divLinha);

}

function primeiraLinha() {

    var tabela = document.querySelector("#tabela-proximas");

    divLinha = criaLinha();
    divLinha.classList.add("bg-primary");

    divColuna1 = divColuna = document.createElement("div");
    divColuna1.classList.add("col-2", "border", "border-end-0", "border-dark", "text-center", "fw-bold", "fs-4", "text-white");
    divColuna1.textContent = "HORÁRIO"

    divColuna2 = document.createElement("div");
    divColuna2.classList.add("col-5", "border", "border-end-0", "border-dark", "text-center", "fw-bold", "fs-4", "text-white");
    divColuna2.textContent = "AULA"

    divColuna3 = document.createElement("div");
    divColuna3.classList.add("col-3", "border", "border-end-0", "border-dark", "text-center", "fw-bold", "fs-4", "text-white");
    divColuna3.textContent = "PROFESSOR"

    divColuna4 = document.createElement("div");
    divColuna4.classList.add("col-2", "border", "border-dark", "text-center", "fw-bold", "fs-4", "text-white");
    divColuna4.textContent = "STATUS"

    divLinha.appendChild(divColuna1);
    divLinha.appendChild(divColuna2);
    divLinha.appendChild(divColuna3);
    divLinha.appendChild(divColuna4);

    tabela.appendChild(divLinha);
}

function criaLinha() {

    divLinha = document.createElement("div");
    divLinha.classList.add("row", "row-cols-4");
    divLinha.style.width = "800px";

    return divLinha;
}


function trocaImagem(id) {
    var imagem = document.querySelector('#logoaula');
    imagem.classList.add("fadeOut");
    setTimeout(function() {
        imagem.src = 'resources/img/modalidades/' + id + '.png';
        imagem.classList.add("fadeIn");
    },500);
}