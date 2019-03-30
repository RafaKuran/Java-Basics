package turma;
//Bizu: a planta é classe e a casa é um objeto

import java.util.Scanner;

public class Turma {
    public static void main(String[] args) {
        final int FIM = 0;
        final int TAM = 10;
        int opcao, cont = 0;
        Aluno[] alunos = new Aluno[TAM];
        
        opcao = menu();
        while (opcao != FIM){
            switch (opcao) {
                case 1: cont = incluir(alunos, cont);
                    break;
                case 2: alterar(alunos, cont);
                    break;
                case 3: excluir(alunos, cont);
                    break;
                case 4: listar(alunos, cont);
                    break;
            }
            opcao = menu();
        }
    }
    
    public static int incluir(Aluno[] alunos, int cont){
        int matricula, n1, n2;
        String nome;
        Aluno aluno;
        
        matricula = leMatricula(alunos, cont);
        nome = leNome();
        n1 = leNota();
        n2 = leNota();        
        
        aluno = new Aluno(matricula, nome, n1, n2);
        alunos[cont] = aluno;
        cont++;
        return cont;  
    }
    
    public static void alterar(Aluno[] alunos, int cont){
        int matricula, pos, n1, n2;
        String nome;
        Scanner entrada = new Scanner(System.in);
        
        if (cont == 0){
            System.out.printf("\nNão há alunos na turma!\n\n");
            return;
        }
        System.out.print("Entre com a matrícula: ");
        matricula = entrada.nextInt();
        pos = verificaMatricula(alunos, cont, matricula);
        if (pos != -1){
           System.out.println("Entre com o nome: ");
           nome = entrada.nextLine();
            n1 = leNota();
            n2 = leNota();
            alunos[pos].setNome(nome);
            alunos[pos].setN1(n1);
            alunos[pos].setN2(n2);
        }
        else{
            System.out.println("Erro: matrícula inválida!");
        }
        
    }
    
    public static int excluir(Aluno[] alunos, int cont){
        int matricula, pos;
        Scanner entrada = new Scanner(System.in);
        
        if (cont == 0){
            System.out.printf("\nNão há alunos na turma!\n\n");
            return cont;
        }
        System.out.print("Entre com a matrícula: ");
        matricula = entrada.nextInt();
        pos = verificaMatricula(alunos, cont, matricula);
        if (pos != -1){
            for (int i = pos; i < cont; i++){
                alunos[i] = alunos[i+1];
            }
            cont --;
        }
        else {
            System.out.println("Erro: matrícula inválida!");
        }
        return cont;
    } 
            
    public static void listar(Aluno[] alunos, int cont){
        int opcao;
        Scanner entrada = new Scanner(System.in);
        
        if (cont == 0){
            System.out.printf("\nNão há alunos na turma!\n\n");
            return;
        }
        do {
           System.out.println("[1] - Incluir");
           System.out.println("[2] - Alterar");
           System.out.println("[0] - Sair");
           System.out.println("Escolha uma opção: ");
           opcao = entrada.nextInt();
           
           if((opcao < 0) || (opcao > 2)){
               System.out.println("Erro: opção inválida!");
           }
        } while ((opcao < 0) || (opcao > 2));
        if (opcao == 0){
            return;
        }
        switch (opcao){
            case 1: listarTodos(alunos,cont);
                break;
            case 2: listarAluno(alunos, cont);
                break;
        }
    }
    
    
    public static void listarTodos(Aluno[] alunos, int cont){
        for (int i = 0; i < cont; i++){
            System.out.println(alunos[i].getMatricula() + " " + 
                alunos[i].getNome() + " " + alunos[i].getN1() + " " + 
                alunos[i].getN2() + " " + alunos[i].calculaMedia());
        }
    }
    
    public static void listarAluno(Aluno[] alunos, int cont){
        int matricula, pos = -1;
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Entre com a matrícula: ");
        matricula = entrada.nextInt();
        pos = verificaMatricula(alunos, cont, matricula);
        if (pos != -1){
            System.out.println(alunos[pos].getMatricula() + " " + 
            alunos[pos].getNome() + " " + alunos[pos].getN1() + " " + 
            alunos[pos].getN2() + " " + alunos[pos].calculaMedia());
            }
        else{
            System.out.println("Não há alunos na turma!");
        }
    }
    
    public static String leNome(){
        String nome;
        Scanner entrada = new Scanner(System.in);
        
        do{
            System.out.println("Entre com o nome: ");
            nome = entrada.nextLine();
            if (nome.length() < 2){
                System.out.println("Erro: nome inválido!");
            }
        } while(nome.length() < 2);
    return nome.toUpperCase();
    }
    
    public static int leNota(){
        int n;
        Scanner entrada = new Scanner(System.in);
        
        do{
            System.out.print("Entre com a nota: ");
            n = entrada.nextInt();
            if ((n < 0) || (n > 10)){
                System.out.print("Erro: nota inválida!");
            }
        } while ((n < 0 ) || (n > 10));
        return n;
    }
   
    public static int verificaMatricula(Aluno[] alunos, int cont, int matricula){
       int pos = -1;
       for (int i = 0; i < cont; i++){
           if (alunos[i].getMatricula() == matricula){
               pos = i;
               break;
           }
       }
       return pos;
    }
    
    public static int leMatricula(Aluno[] alunos, int cont){
        int matr;
        Scanner entrada = new Scanner(System.in);
        int pos;
        
        do{
            pos = -1;
            System.out.println("Entre com a matrícula: ");
            matr = entrada.nextInt();
            entrada.nextLine();
            pos = verificaMatricula(alunos, cont, matr);
            if (pos != -1){
                System.out.println("Erro: matrícula já existe!");
            }
        } while (pos != -1);
        return matr;
    }
    
    public static int menu(){
        int opcao;
        Scanner entrada = new Scanner(System.in);
        
        do {
           System.out.println("[1] - Incluir");
           System.out.println("[2] - Alterar");
           System.out.println("[3] - Excluir");
           System.out.println("[4] - Listar");
           System.out.println("[0] - Sair");
           System.out.println("Escolha uma opção: ");
           opcao = entrada.nextInt();
           
           if((opcao < 0) || (opcao > 4)){
               System.out.println("Erro: opção inválida!");
           }
        } while ((opcao < 0) || (opcao > 4));
        return opcao;
    }
}
