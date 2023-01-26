import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] arqs) {

        //3.1
        final List<Funcionario> funcionario = new ArrayList<>();

        funcionario.add(new Funcionario("Maria", LocalDate.parse("18-10-2000", DateTimeFormatter.ofPattern("dd-MM-yyyy")), new BigDecimal("2009.44"), "Operador"));
        funcionario.add(new Funcionario("João", LocalDate.parse("12-05-1990", DateTimeFormatter.ofPattern("dd-MM-yyyy")), new BigDecimal("2284.38"), "Operador"));
        funcionario.add(new Funcionario("Caio", LocalDate.parse("02-05-1961", DateTimeFormatter.ofPattern("dd-MM-yyyy")), new BigDecimal("9836.14"), "Coordenador"));
        funcionario.add(new Funcionario("Miguel", LocalDate.parse("14-10-1988", DateTimeFormatter.ofPattern("dd-MM-yyyy")), new BigDecimal("19119.88"), "Diretor"));
        funcionario.add(new Funcionario("Alice", LocalDate.parse("05-01-1995", DateTimeFormatter.ofPattern("dd-MM-yyyy")), new BigDecimal("2234.68"), "Recepcionista"));
        funcionario.add(new Funcionario("Heitor", LocalDate.parse("19-11-1999", DateTimeFormatter.ofPattern("dd-MM-yyyy")), new BigDecimal("1582.72"), "Operador"));
        funcionario.add(new Funcionario("Arthur", LocalDate.parse("31-03-1993", DateTimeFormatter.ofPattern("dd-MM-yyyy")), new BigDecimal("4071.84"), "Contador"));
        funcionario.add(new Funcionario("Laura", LocalDate.parse("08-07-1994", DateTimeFormatter.ofPattern("dd-MM-yyyy")), new BigDecimal("3017.45"), "Gerente"));
        funcionario.add(new Funcionario("Heloísa", LocalDate.parse("24-05-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy")), new BigDecimal("1606.85"), "Eletricista"));
        funcionario.add(new Funcionario("Helena", LocalDate.parse("02-09-1996", DateTimeFormatter.ofPattern("dd-MM-yyyy")), new BigDecimal("2799.93"), "Gerente"));

        System.out.println();

        //3.2
        funcionario.remove(funcionario.stream()
                .filter(t -> t.nome.equalsIgnoreCase("João")).findFirst().get());

        //3.3
        funcionario.stream()
                .map(t -> t.nome + "\t   " + t.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\t  " + t.salario.toString().replace('.', ',') + "   " + t.funcao)
                .forEach(System.out::println);

        //3.4
        funcionario.stream().map(t -> t.salario.multiply(BigDecimal.valueOf(1.1)));

        System.out.println();

        //3.5
        var agrupados = new HashMap<String, List<Funcionario>>();

        for(var grupos : funcionario) {
            var funcoes = grupos.funcao;
            var agrupado = agrupados.get(funcoes);

            if(agrupado == null) {
                agrupado = new ArrayList<>();
                agrupado.add(grupos);
                agrupados.put(funcoes, agrupado);
                continue;
            }
            agrupado.add(grupos);
        }
        System.out.println();

        //3.6
        for (var grupos : agrupados.keySet())
            System.out.println("Chave = " + grupos + " valor = [" + agrupados.get(grupos).stream().map(t -> t.nome + " ").collect(Collectors.joining()) + "]");

        System.out.println();

        //3.8
        funcionario.stream()
                .filter(t -> t.dataNascimento.getMonth().getValue() == 10 || t.dataNascimento.getMonth().getValue() == 12)
                .map(t -> t.nome + "\t   " + t.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\t  " + t.salario.toString().replace('.', ',') + "   " + t.funcao)
                .forEach(System.out::println);

        System.out.println();

        //3.9
        var maior = funcionario.stream()
                .min(Comparator.comparingInt(t -> t.dataNascimento.getYear()))
                .map(t -> t.nome + "\t   " + t.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\t  " + t.salario.toString().replace('.', ',') + "   " + t.funcao).get();
        System.out.println(maior);

        System.out.println();

        //3.10
        funcionario.stream()
                .map(t -> t.nome + "\t   " + t.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\t  " + t.salario.toString().replace('.', ',') + "   " + t.funcao)
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        //3.11
        var salarioOrdenado = funcionario.stream()
                .map(t -> t.salario.toString().replace('.', ',') + "\t\t" + t.funcao +  "\t\t" + t.nome + "\t   " + t.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .sorted()
                .toList();

        salarioOrdenado.forEach(System.out::println);

        System.out.println();

        //3.12
        funcionario.stream()
                .map(t -> t.nome + "\t   " + t.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\t  " + String.format("%d",t.salario.intValue()/1212) + " salários minímos " + t.funcao)
                .forEach(System.out::println);
    }

}