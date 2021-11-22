#include <iostream>
#include <fstream>
#include <string>
#include <cstring>

using namespace std;

int TransformaEmAscii(char nomeArq[], char arqSaida[]);
char PassaParaAscii(char nota[]); //função que retorna valor da nota em ascii
string RemoveEspaco(string cadeia);

int main()
{
    char nomeArq[800], arqOut[800];

    cout << "R" << endl;
    cin >> nomeArq;
    cout <<"S";
    fflush(stdin);

    cout << "D" << endl;
    cin >> arqOut;
    fflush(stdin);

    TransformaEmAscii(nomeArq, arqOut);

    return 0;
}

int TransformaEmAscii(char nomeArq[], char arqSaida[]){
    char auxC, auxLine[200], parseAscii[4];
    string nota = "";
    string line = "";
    int cont = 0;

    ifstream arq;
    ofstream arqOut;

    arq.open(nomeArq, ios::in);

    if(!arq.is_open()){
        cout << "Erro ao abrir arquivo!" << endl;
        return 69;
    }
    arqOut.open(arqSaida, ios::trunc);

    while(arq.getline(auxLine, 200)){ //lerá linha por linha do arquivo de texto
        //cout << "Tamanho de caracteres na linha: " << nota.size();
        line = auxLine;
        for(int i = 0; i < line.size(); i++){ // vai percorrer caractere por caractere para separar as notas
            if(auxLine[i] != ' ' && auxLine[i] != '\n' && auxLine[i] != '\t' && auxLine[i] != '\r'){ //quando encontrara a primeira nota ele vai começar a passar pra string
                nota += auxLine[i];
                nota += auxLine[i+1]; // salvamos os dois seguitnes caracteres pq toda nota tem no minimo 2 letras

                if(auxLine[i+2] != ' ' || auxLine[i+2] != '\n' || auxLine[i+2] != '\0' || auxLine[i+2] != '\t'){ //pode haver um terceiro caractere, podendo ser a nota sol ou qualquer otura nota sustenida (#)
                    nota += auxLine[i+2];
                    //esse if é caso a nota seja sol#, então tera q testar o quarto caractere da sequencia
                    if((auxLine[i+2] == 'l' || auxLine[i+2] == 'L') && (auxLine[i+3] != ' ' ||
                        auxLine[i+3] != '\n' || auxLine[i+3] != '\0' || auxLine[i+3] != '\t')){
                        nota += auxLine[i+3];
                        i++;
                    }
                    i++;
                }

                i++;

                //cout << nota;
                strcpy(parseAscii, RemoveEspaco(nota).c_str()); // passa a string para caractere pra poder mandar pra função de ascii

                arqOut << "   " << PassaParaAscii(parseAscii);

                nota = "";
            }
        }
        arqOut << "   " << '~';
    }
    arq.close();
    arqOut.close();

    cout << "F" << endl;

    return 0;
}

char PassaParaAscii(char nota[4]){ //função que retorna valor da nota em ascii
    string notaAux = nota;
    int cont = 0;
    char notOut;
    string notas[] = {"do","do#","re","re#", "mi", "fa","fa#", "sol","sol#", "la", "la#", "si"};
    string notas2[] = {"DO","DO#","RE","RE#", "MI", "FA","FA#", "SOL","SOL#", "LA", "LA#", "SI"};

    if(nota[1]>='A' && nota[1]<='Z'){
        cont = 12;
        for(int i=0; i < notaAux.length(); i++)
            nota[i] = tolower(nota[i]);
        notaAux = nota;
    }



    for(int i = 0; i < 12; i++){
        if(notaAux == notas[i]){
            switch(i+1){
                case 1:
                    notOut = 60 + cont;
                    break;

                case 2:
                    notOut = 61 + cont;
                    break;

                case 3:
                    notOut = 62 + cont;
                    break;

                case 4:
                    notOut = 63 + cont;
                    break;

                case 5:
                    notOut = 64 + cont;
                    break;

                case 6:
                    notOut = 65 + cont;
                    break;

                case 7:
                    notOut = 66 + cont;
                    break;

                case 8:
                    notOut = 67 + cont;
                    break;

                case 9:
                    notOut = 68 + cont;
                    break;

                case 10:
                    notOut = 69 + cont;
                    break;

                case 11:
                    notOut = 70 + cont;
                    break;

                case 12:
                    notOut = 71 + cont;
                    break;

                default:
                    notOut = 126;
                    break;
            }
        }

    }


    return notOut;
}

string RemoveEspaco(string cadeia){
    char auxC[5];
    string saida = "";
    strcpy(auxC, cadeia.c_str());

    for(int i = 0; i <cadeia.length(); i++){
        if(auxC[i] != ' ' && auxC[i] != '\n' && auxC[i] != '\t' && auxC[i] != '\r'){
            saida += auxC[i];
        }
    }

    return saida;
}
