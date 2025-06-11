package org.example.expedfacil.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ConversorLoteUtil {

    private static final Map<Character, Integer> mapaLote = new HashMap<>();

    static {
        mapaLote.put('C', 0);
        mapaLote.put('O', 1);
        mapaLote.put('N', 2);
        mapaLote.put('S', 3);
        mapaLote.put('E', 4);
        mapaLote.put('R', 5);
        mapaLote.put('V', 6);
        mapaLote.put('A', 7);
        mapaLote.put('D', 8);
        mapaLote.put('I', 9);
    }

    public static LocalDate converterParaDataProducao(String lote) {
        if (lote == null || lote.length() != 6) {
            throw new IllegalArgumentException("Lote deve conter exatamente 6 letras");
        }

        try {
            int dia = mapaLote.get(lote.charAt(0)) * 10 + mapaLote.get(lote.charAt(1));
            int mes = mapaLote.get(lote.charAt(2)) * 10 + mapaLote.get(lote.charAt(3));
            int ano = mapaLote.get(lote.charAt(4)) * 10 + mapaLote.get(lote.charAt(5));

            if (dia < 1 || dia > 31 || mes < 1 || mes > 12) {
                throw new IllegalArgumentException("Lote representa uma data inválida: dia ou mês fora do intervalo");
            }

            // Tratamos ano como 2000 + YY (por padrão)
            return LocalDate.of(2000 + ano, mes, dia);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Lote contém caractere inválido: use apenas letras da palavra CONSERVADI");
        }
    }
}
