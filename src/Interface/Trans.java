package Interface;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Trans {
	private FileInputStream in = null;
	private FileOutputStream out = null;
	private String nota = "";
	private int c;
	private BarraDeProgresso barra;
	private char carac;
	
	private String saida = "";
	
	private String[] notas1 = {"do", "do#", "re", "re#", "mi", "fa", "fa#", "sol", "sol#", "la", "la#", "si"};
    
	//atribui a entrada
	public void setIn(FileInputStream arqO) {
		this.in = arqO;
	}
	
	//atribui a saida
	public void setOut(FileOutputStream arqS) {
		this.out = arqS;
	}
	
	//retorna se o arquivo de entrada foi enviado
	public boolean isIn() {
		if(this.in != null)
			return true;
		
		else
			return false;
	}
	
	//retorna se o arquivo de saida foi enviado
	public boolean isOut() {
		if(this.out != null)
			return true;
		
		else
			return false;
	}
	
	public boolean Converter() {
		if(removeEspacos()) 
			if(paraAscii()) 
				return true;
			
			else
				return false;
		
		else
			return false;
	}
	
	private boolean removeEspacos() {
		try {
			int cont = 1;
			while((c = in.read()) != -1) {
				carac = (char)c;
				if((carac != '\n') && (carac != '\t') && (carac != ' ') &&
						(carac != '\r') && (cont > 0 && cont < 5)) {
					nota += carac;
					cont++;
				}
				else if((cont == 5 || cont == 3 || cont == 4) && (carac == ' ' || carac == '\n' ||
						carac == '\t' || carac == '\r')) {
					nota += ' ';
					cont = 1;
				}
			}
			nota += " ";
			
			return true;
		}
		catch (IOException e){
			return false;
		}
		
	}
	
	private boolean paraAscii() {
		int oitava = 0;
		String notAt = "";
		int notOut = 0;
		char[] auxNota = new char[nota.length()];
		auxNota = nota.toCharArray();

		barra = new BarraDeProgresso(nota.length());
		
		
		
		for(int i = 0; i < nota.length(); i++) {
			
			if(auxNota[i] != ' ') {
				notAt += auxNota[i];
				
			}
			
			
			else {
				
				if(notAt != "") {
					if(notAt.toCharArray()[1] >= 'A' && notAt.toCharArray()[1] <= 'Z') {
						notAt = notAt.toLowerCase();
						oitava = 12;
					}
					
					barra.setValor(i+1);
					
					for(int j = 0; j < 12; j++){
				        if(notAt.equals(notas1[j])){
				            switch(j+1){
				                case 1:
				                    notOut = 60 + oitava;
				                    break;

				                case 2:
				                    notOut = 61 + oitava;
				                    break;

				                case 3:
				                    notOut = 62 + oitava;
				                    break;

				                case 4:
				                    notOut = 63 + oitava;
				                    break;

				                case 5:
				                    notOut = 64 + oitava;
				                    break;

				                case 6:
				                    notOut = 65 + oitava;
				                    break;

				                case 7:
				                    notOut = 66 + oitava;
				                    break;

				                case 8:
				                    notOut = 67 + oitava;
				                    break;

				                case 9:
				                    notOut = 68 + oitava;//sol#
				                    break;

				                case 10:
				                    notOut = 69 + oitava;
				                    break;

				                case 11:
				                    notOut = 70 + oitava;
				                    break;

				                case 12:
				                    notOut = 71 + oitava;
				                    break;

				                default:
				                    notOut = 126;
				                    break;
				            }
				            saida += "   " + (char)notOut;
				            notAt="";
				            oitava = 0;
				            break;
				        }	
					}
				}
			}
			
		}
		try{
            out.write(saida.getBytes(), 0, saida.length());
            out.flush();
			out.close();
			in.close();
		}
		catch (IOException e) {
			
		}
		return true;
	}	
}
