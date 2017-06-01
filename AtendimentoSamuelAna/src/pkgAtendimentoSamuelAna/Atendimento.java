package pkgAtendimentoSamuelAna;

import javax.swing.*;

import java.io.*;
import java.util.*;

public class Atendimento {
	private static class Fila{
		public int cartao;
		public String nome;
		public String sobreNome;
		public Double valor;
		public Fila prox;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fila inicio = null;
		Fila fim = null;
		Fila aux = null;
		double valorSomado = 0;
		int contador = 0;
		int op = 0;
		int filtro;
		int position;
		boolean achou;
		String filtroNome;
		FileWriter arq;
		PrintWriter gravar;
		int yesOrNo;
		float filtroFloat;
		do{
			try{
				op = Integer.parseInt(input(menu(),1));
				if (op<1 || op >13){
					mDialog("OPÇÃO INVALIDA!","MENSAGEM DO PROGRAMA");
					break;
				}
			}catch (Exception e){
				mDialog("TECLA CANCELAR FOI ACIONADA - ENCERRANDO ...","MENSAGEM DO PROGRAMA");
				break;
			}
			if (op==1){
				int numero = Integer.parseInt(input("NÚMERO DO CARTÃO:",0));
				aux = inicio;
				boolean numRepet = false;
				while (aux != null){
					if (aux.cartao == numero){
						numRepet = true;
						mDialog("Esse número do cartão já foi usado.\nFavor verificar!");
						break;
					}
					aux = aux.prox;
				}
				if (numRepet == false){
					Fila novo = new Fila();
					novo.cartao = numero;
					novo.nome = input("NOME:");
					novo.sobreNome = input("SOBRENOME:");
					novo.valor = Double.parseDouble(input("VALOR:"));
					novo.prox = null;
					if(inicio == null){
						inicio = novo;
						fim = novo;
					} else {
						fim.prox = novo;
						fim = novo;
					}
				}
			}
			if (op==2){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS","MENSAGEM DO PROGRAMA");
				}else{
					JTextArea saida = new JTextArea(6,35);
					JScrollPane scroll = new JScrollPane(saida);
					saida.append("CARTÃO\t"+"NOME\t"+"SOBRENOME\t"+"VALOR\n");
					saida.append("----------------------------------------------------------------------\n");
					aux = inicio;
					while(aux != null){
						saida.append(aux.cartao + "\t" + aux.nome + "\t" + aux.sobreNome + "\t" + aux.valor + "\n");
						aux = aux.prox;
					}
					JOptionPane.showMessageDialog(null, scroll, "CONSULTAR DADOS DO ATENDIMENTO",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			if (op==3){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS","MENSAGEM DO PROGRAMA");
				}else{
					mDialog("CARTÃO: "+inicio.cartao+" NOME: " +inicio.nome + " foi atendido(a)!","MENSAGEM DO PROGRAMA");
					inicio = inicio.prox;
				}
			}
			if (op ==4){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS", "MENSAGEM DO PROGRAMA");
				}else{
					mDialog("* * O ATENDIMENTO FOI LIBERADO * *","MENSAGEM DO PROGRAMA");
					inicio = null;
				}
			}
			
			if (op ==5){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS","MENSAGEM DO PROGRAMA");
				}else{
					aux = inicio;
					contador = 0;
					valorSomado = 0;
					while(aux != null){
						contador += 1;
						valorSomado = valorSomado + aux.valor;
						aux = aux.prox;
					}
					mDialog("O ATENDIMENTO CONTÉM: " + contador + "ELEMENTOS.\nVALOR TOTAL: " + valorSomado,"MENSAGEM DO PROGRAMA");
				
				    
				}
				
			}
			if (op ==6){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS","MENSAGEM DO PROGRAMA");
				}else{
					filtro = Integer.parseInt(input("Informe o número do cartão:"));
					aux = inicio;
					position = 1;
					achou = false;
					while(aux != null){
						if(filtro == aux.cartao){
							mDialog("DADOS DO CLENTE:\n"
									+ "\nCARTÂO: "+aux.cartao
									+ "\nNOME: "+aux.nome
									+ "\nSOBRENOME: "+aux.sobreNome
									+ "\nVALOR: "+aux.valor
									+ "\nPOSIÇÃO: "+position+"a. posição","MENSAGEM DO PROGRAMA");
							achou = true;
							break;
						}
						position++;
						aux = aux.prox;
					}
					if(achou != true){
						mDialog("O número do cartão não foi encontrado!");
					}
				}
			}
			if (op==7){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS","MENSAGEM DO PROGRAMA");
				}else{
					filtroNome = input("Nome do cliente:");
					aux = inicio;
					position = 1;
					achou = false;
					while(aux != null){
						if(filtroNome.equals(aux.nome)){
							mDialog("DADOS DO CLENTE:\n"
									+ "\nCARTÂO: "+aux.cartao
									+ "\nNOME: "+aux.nome
									+ "\nSOBRENOME: "+aux.sobreNome
									+ "\nVALOR: "+aux.valor
									+ "\nPOSIÇÃO: "+position+"a. posição","MENSAGEM DO PROGRAMA");
							achou = true;
							break;
						}
						position++;
						aux = aux.prox;
					}
					if(achou != true){
						mDialog("Esse cliente não foi encontrado!");
					}
				}
			}
			if (op==8){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS","MENSAGEM DO PROGRAMA");
				}else{
					aux = inicio;
					try{
						arq = new FileWriter("C:\\Users\\316138870\\Desktop\\Arquivos\\Atendimento.txt");
						gravar = new PrintWriter(arq);
						
						while(aux != null){
							gravar.printf("%d, %s, %s, %.2f, %n", aux.cartao,aux.nome,aux.sobreNome,aux.valor);
							aux = aux.prox;
						}
						gravar.println("------------------------------------");
						gravar.println("copyright (c) by: Samuel Cleto, Ana Elisa Renaut");
					}
					catch(IOException e){
						escreva("MENSAGEM / CLASS ArquivoTexto:\nErro ao tentar gravar no arquivo");
					}
					mDialog("ARQUIVO GRAVADO COM SUCESSO","MENSAGEM DO SISTEMA");
				}
			}
			if (op==9){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS","MENSAGEM DO PROGRAMA");
				}else{
					yesOrNo = cDialog("DESEJA VER ARQUIVO?","MENSAGEM");
					if(yesOrNo == JOptionPane.YES_OPTION){
						try{
							Process pro = Runtime.getRuntime().exec("cmd.exe /c c://Users//316138870//Desktop//Arquivos//Atendimento.txt");
						}
						catch(Exception e){
							escreva("Erro . . .");
						}
					}
				}
			}
			if (op==10){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS","MENSAGEM DO PROGRAMA");
				}else{
					filtroFloat = Float.parseFloat(input("FILTRAR ATENDIMENTOS PARA VALORES SUPERIORES A:"));
					aux = inicio;
					position = 1;
					achou = false;
					JTextArea saida = new JTextArea(6,35);
					JScrollPane scroll = new JScrollPane(saida);
					saida.append("CARTÃO\t"+"NOME\t"+"SOBRENOME\t"+"VALOR\n");
					saida.append("--------------------------------------------------------------------------\n");
					aux = inicio;
					while(aux != null){
						if(filtroFloat <= aux.valor){
								saida.append(aux.cartao + "\t" + aux.nome + "\t" + aux.sobreNome + "\t" + aux.valor + "\n");
								achou = true;
						}
						position++;
						aux = aux.prox;
					}
					if(achou != true){
						mDialog("O número do cartão não foi encontrado!");
					}else{
						JOptionPane.showMessageDialog(null, scroll, "ATENDIMENTOS COM VALORES SUPERIORES A: "+filtroFloat,
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			if(op==11){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS","MENSAGEM DO PROGRAMA");
				}else{
					JTextArea saida = new JTextArea(8,35);
					JScrollPane scroll = new JScrollPane(saida);
					saida.append("NOME\t"+"ENDEREÇO\t"+"PROX\n");
					saida.append("--------------------------------------------------------------------------\n");
					aux = inicio;
					while(aux.prox != null){
						saida.append(aux.nome + "\t" + aux.hashCode() + "\t" + aux.prox.hashCode() +"\n");
						aux = aux.prox;
					}
					saida.append(aux.nome + "\t" + aux.hashCode() + "\t" + "fim" +"\n");
					JOptionPane.showMessageDialog(null, scroll, "CONSULTAR DADOS DO ATENDIMENTO",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			if(op==12){
				JTextArea saida = new JTextArea(10,35);
				JScrollPane scroll = new JScrollPane(saida);
				saida.append("\nPROGRAMA DE ATENDIMENTO AO CLIENTE\n");
				saida.append("--------------------------------------------------------------------\n");
				saida.append("Copyright(c) Delta Labs Ltda\n");
				saida.append("Programadores: Ana Elisa Renault e Samuel Cleto\n");
				saida.append("Versão 1.0\n");
				saida.append("Data: Junho de 2017\n");
				JOptionPane.showMessageDialog(null, scroll, "SOBRE O PROGRAMA",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		while(op!=13);
		mDialog("PROGRAMA ENCERRADO!");
		
	}
	private static String menu(){
		String menu = "MENU DE OPÇÕES\n"
				+ "1 - Recepcionar cliente\n"
				+ "2 - Consultar clientes a serem atendidos\n"
				+ "3 - Atender cliente\n"
				+ "4 - Liberar todos os clientes\n"
				+ "5 - Verificar quatidade de clientes a atender\n"
				+ "6 - Localizar cliente por número\n"
				+ "7 - Localizar cliente por nome\n"
				+ "8 - Emitir relatório de cliente\n"
				+ "9 - Ver relatório de clientes\n"
				+ "10 - Filtrar clientes por valor\n"
				+ "11 - Ver endereços hash\n"
				+ "12 - Sobre\n"
				+ "13 - Sair";
		return menu;
	}
	private static void escreva(String a){
		System.out.println(a);
	}
	private static void mDialog(String a, String b){
		JOptionPane.showMessageDialog(null, a, b, JOptionPane.CLOSED_OPTION);
	}
	private static void mDialog(String a){
		JOptionPane.showMessageDialog(null, a);
	}
	private static int cDialog(String a, String b){
		int op;
		op = JOptionPane.showConfirmDialog(null, a, b, JOptionPane.YES_NO_OPTION);
		return op;
		}
	private static String input(String a){
		String resp;
		resp = JOptionPane.showInputDialog(a);
		return resp;
	}
	private static String input(String a, int i){
		String resp;
		resp = JOptionPane.showInputDialog(a,i);
		return resp;
	}

}
