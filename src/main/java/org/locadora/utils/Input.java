package org.locadora.utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Input {
    public static int integer(String mensagem) throws Exception {

        int option = 0;
        int tentadas = 0;

        boolean isInvalid;

        do {
            isInvalid = false;

            try {
                System.out.print(mensagem);
                option = new Scanner(System.in).nextInt();

            } catch (Exception ex) {
                tentadas += 1;
                System.out.printf("-> Opção inválida" + "\n\n");
                if (tentadas < 3) {
                    isInvalid = true;
                    continue;
                }
                throw new Exception("-> Multiplas tentativas incorretas");
            }

        } while (isInvalid);

        return option;
    }

    //TODO: FAZER VALIDAÇÃO
    public static String string(String nomeDoCampo) {
        System.out.printf("POR FAVOR, INFORME O %s\n", nomeDoCampo);
        return new Scanner(System.in).nextLine().trim();
    }

    public static String stringNotNullable(String nomeDoCampo, int tentativas) throws Exception {
        int tentadas = 0;
        boolean isInvalid;
        String input = "";

        do {
            isInvalid = false;

            try {
                input = string(nomeDoCampo);

                if (input.isBlank()) throw new Exception(nomeDoCampo);


            } catch (Exception ex) {
                tentadas += 1;

                System.out.printf("-> % não pode ser nulo\n", ex.getMessage());
                if (tentadas < tentativas) {
                    isInvalid = true;
                    continue;
                }

                throw new Exception("-> Número de tentativas excedidas");
            }

        } while (isInvalid);

        return input;
    }

    public static BigDecimal bigDecimal(String nomeDoCampo, int tentativas) throws Exception {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setParseBigDecimal(true);

        int tentadas = 0;
        boolean isInvalid;
        BigDecimal output = null;

        do {
            isInvalid = false;

            try {
                System.out.println(nomeDoCampo);
                String input = string(nomeDoCampo);

                if (!input.isBlank()) {
                    output = (BigDecimal) decimalFormat.parse(input);
                }

            } catch (NumberFormatException ex) {
                tentadas += 1;

                System.out.printf("NÚMERO INVÁLIDO, TENTE NOVAMENTE\n");
                if (tentadas < tentativas) {
                    isInvalid = true;
                    continue;
                }

                throw new Exception("-> Número de tentativas excedidas");
            } catch (Exception ex) {
                tentadas += 1;

                System.out.printf("-> % não pode ser nulo\n", ex.getMessage());
                if (tentadas < tentativas) {
                    isInvalid = true;
                    continue;
                }

                throw new Exception("-> Número de tentativas excedidas");
            }

        } while (isInvalid);

        return output;
    }
}
