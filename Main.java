import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hora tempo = new Hora(10, 10, 10, 100);

        int funcao;
        int soma;
        int unidade = 0;
        Scanner sc = new Scanner(System.in);

        while (true){
            // Menu

            System.out.println("\n***** RELÓGIO *****\n");
            System.out.println(tempo+"\n");

            System.out.println("Digite o número correspondente a função desejada:");
            System.out.println("1 - Somar");
            System.out.println("2 - Subtrair");
            System.out.println("3 - Resetar Relógio");
            System.out.println("4 - Transformar em Milissegundos");
            System.out.println("0 - SAIR");

            funcao = sc.nextInt();
            if (funcao == 0)
            {
                break;
            }
            if (funcao == 1 || funcao == 2)
            {
                System.out.println("Digite o número correspondente a opção que deseja alterar:");
                System.out.println("1 - Hora");
                System.out.println("2 - Minuto");
                System.out.println("3 - Segundo");
                System.out.println("4 - Milissegundo");
                unidade = sc.nextInt() - 1;

                sc = new Scanner(System.in);
            }

            if (funcao == 1)
            {
                System.out.println("Quanto tempo deseja Adicionar?");
                int valor = sc.nextInt();
                switch (unidade) {
                    case Unidades.HORA:
                        tempo.acrescentar(valor,Unidades.HORA);
                        break;
                    case Unidades.MINUTO:
                        tempo.acrescentar(valor,Unidades.MINUTO);
                        break;
                    case Unidades.SEGUNDO:
                        tempo.acrescentar(valor,Unidades.SEGUNDO);
                        break;
                    case Unidades.MILISSEGUNDO:
                        tempo.acrescentar(valor,Unidades.MILISSEGUNDO);
                        break;
                }
            }
            else if (funcao == 2) {
                System.out.println("Quanto tempo deseja Tirar?");
                int valor = sc.nextInt();
                switch (unidade) {
                    case Unidades.HORA:
                        tempo.diminuir(valor,Unidades.HORA);
                        break;
                    case Unidades.MINUTO:
                        tempo.diminuir(valor,Unidades.MINUTO);
                        break;
                    case Unidades.SEGUNDO:
                        tempo.diminuir(valor,Unidades.SEGUNDO);
                        break;
                    case Unidades.MILISSEGUNDO:
                        tempo.diminuir(valor,Unidades.MILISSEGUNDO);
                        break;
                }
            }
            else if (funcao == 3) {
                tempo = new Hora();
            }
            else if (funcao == 4) {
                soma = tempo.getHora()*3600000 + tempo.getMin()*60000 + tempo.getSeg()*1000 + tempo.getMilis();
                System.out.println("\nA hora atual em milissegundos é:" + soma);
            }
        }
    }
}