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
		int tamanho = 0;
		int op = 0;
		do{
			try{
				op = Integer.parseInt(input(menu(),1));
				if (op<1 || op >13){
					mDialog("OPÇÃO INVALIDA!");
				}
			}catch (Exception e){
				mDialog("TECLA CANCELAR FOI ACIONADA - ENCERRANDO ...");
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
					mDialog("NÃO HÁ ATENDIMENTOS");
				}else{
					JTextArea saida = new JTextArea(6,35);
					JScrollPane scroll = new JScrollPane(saida);
					saida.append("CARTÃO\t"+"NOME\t"+"SOBRENOME\t"+"VALOR\n");
					saida.append("----------------------------------------\n");
					aux = inicio;
					while(aux != null){
						saida.append(aux.cartao + "\t" + aux.nome + "\t" + aux.sobreNome + "\t" + aux.valor + "\n");
						aux = aux.prox;
					}
					JOptionPane.showMessageDialog(null, scroll, "CONSULTAR CHAPAS CADASTRADAS",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			if (op==3){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS");
				}else{
					mDialog("CARTÃO: "+inicio.cartao+" NOME: " +inicio.nome + " foi atendido(a)!");
					inicio = inicio.prox;
				}
			}
			if (op ==4){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS");
				}else{
					mDialog("* * O ATENDIMENTO FOI LIBERADO * *");
					inicio = null;
				}
			}
			
			if (op ==5){
				if(inicio == null){
					mDialog("NÃO HÁ ATENDIMENTOS");
				}else{
					aux = inicio; 
					while(aux != null){
						contador += 1;
						valorSomado = valorSomado + aux.valor;
						aux = aux.prox;
					}
					mDialog("O ATENDIMENTO CONTÉM:" + contador + "ELEMENTOS .\n VALOR TOTAL:" + valorSomado);
				
				    
				}
				
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
