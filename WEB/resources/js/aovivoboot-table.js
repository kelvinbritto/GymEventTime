var xhr1 = new XMLHttpRequest();
var xhr2 = new XMLHttpRequest();
var xhrteste = new XMLHttpRequest();


var url = "http://calendarmixplay.ddns.net:8081";


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

    trocaImagem(aula)
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

        if(aulas[0].status == "Terminou" && aulas[1].status == "Terminou") {
            aulas.splice(0, 1); 
        }

        var tabela = document.querySelector("#tabela-corpo");
        tabela.innerHTML = "";
    
        aulas.forEach(criaHeader);

        setTimeout('proximas()',5000);

    });
    
    xhr2.send();

}

proximas();
aovivo();


function criaHeader(aula) {

    var tabela = document.querySelector("#tabela-corpo");
    
    tr = document.createElement("tr");
    
    tdHora = document.createElement("td");
    tdHora.classList.add("border", "border-end-0", "border-dark", "text-center", "fs-4", "text-white", "align-middle");

    tdAula = document.createElement("td");
    tdAula.classList.add("border", "border-end-0", "border-dark", "text-center", "fs-4", "text-white", "align-middle");

    tdProf = document.createElement("td");
    tdProf.classList.add("border", "border-end-0", "border-dark", "text-center", "fs-4", "text-white", "align-middle");

    tdStatus = document.createElement("td");
    tdStatus.classList.add("border", "border-dark", "text-center", "fs-5", "text-white", "align-middle");

    tdHora.textContent = aula.hora + "h";
    tdAula.textContent = aula.aula;

    var stringExemplo = aula.professor;
    var resultado = stringExemplo.split(" ");
    tdProf.textContent = resultado[0];
    
    tdStatus.textContent = aula.status;

    tr.appendChild(tdHora);
    tr.appendChild(tdAula);
    tr.appendChild(tdProf);
    tr.appendChild(tdStatus);


    if(aula.status == "Terminou") {
        tr.classList.add("fw-bolder", "text-white", "bg-success");
    }
    
    if(aula.status == "Ao Vivo") {
        tr.classList.add("fw-bolder","text-white", "bg-danger");
    }

    if(aula.status == "A Seguir") {
        tr.classList.add("fw-bolder", "text-white", "bg-warning");
    }

    tabela.appendChild(tr);

}

function criaLinha() {
    divLinha = document.createElement("div");
    divLinha.classList.add("row", "row-cols-4");
    divLinha.style.width = "800px";
    return divLinha;
}


function trocaImagem(aula) {
    imagem = document.querySelector('#logoaula');
    imagem.classList.add("fadeOut");
    setTimeout(function() {
        imagem.src = aula.urlLogo;
        imagem.classList.add("fadeIn");
    },500);
}